package com.example.springbootjavassist;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class SpringbootJavassistApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(SpringbootJavassistApplication.class, args);
//    }

    private static final String WEBSERVICE_CONFIG_XML = "classpath:config/webservice.xml";

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(SpringbootJavassistApplication.class, args);

        try {
            List<Class<?>> classes = dynamicCreateWebservice(run);

            for (Class<?> webservice : classes) {

                JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();

                // Web服务的地址
                factoryBean.setAddress("http://localhost:8081/ws/" + webservice.getName());

                // Web服务对象调用接口
                factoryBean.setServiceClass(webservice);
                Server server = factoryBean.create();
                server.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 动态生成Webservice
     *
     * @throws CannotCompileException
     * @throws IOException
     */
    public static List<Class<?>> dynamicCreateWebservice(ConfigurableApplicationContext run) throws CannotCompileException, IOException, NotFoundException {

        List<Class<?>> classes = new ArrayList<>();

        ClassPool classpool = ClassPool.getDefault();

        //读取xml,动态生成webservice
        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = null;

        Resource webservice_config_xml = run.getResource(WEBSERVICE_CONFIG_XML);

        try {
            document = reader.read(webservice_config_xml.getInputStream());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 获取根节点元素对象
        Element rootElement = document.getRootElement();

        // 当前节点下面子节点迭代器
        Iterator<Element> root = rootElement.elementIterator();

        CtClass ctclass = null;
        // 遍历类
        while (root.hasNext()) {
            // 获取某个子节点对象
            Element classElement = root.next();
            // 对子节点属性进行遍历
            List<Attribute> classAttributes = classElement.attributes();

            Map<String, Node> classAttributesMap = new HashMap<>();

            // 遍历属性节点
            for (Attribute attr : classAttributes) {
                classAttributesMap.put(attr.getName(), attr);
            }

            //创建类
            ctclass = classpool.makeClass(classAttributesMap.get("className").getStringValue());
            ClassFile classFile = ctclass.getClassFile();
            ConstPool constPool = classFile.getConstPool();

            //添加类的注释
            Annotation classAttr = new Annotation("javax.jws.WebService", constPool);
            //注释属性
            classAttr.addMemberValue("name", new StringMemberValue(classAttributesMap.get("name").getStringValue(), constPool));
            classAttr.addMemberValue("targetNamespace", new StringMemberValue(classAttributesMap.get("targetNamespace").getStringValue(), constPool));
            AnnotationsAttribute classAnnotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            classAnnotationsAttribute.addAnnotation(classAttr);
            classFile.addAttribute(classAnnotationsAttribute);

            //获取方法
            Iterator<Element> method = classElement.elementIterator();
            while (method.hasNext()) {

                Element methodElement = method.next();

                List<Attribute> methodAttributes = methodElement.attributes();

                Map<String, Node> methodAttributesMap = new HashMap<>();

                for (Attribute attr : methodAttributes) {
                    methodAttributesMap.put(attr.getName(), attr);
                }

                //获取参数
                Iterator<Element> parameter = methodElement.elementIterator();

                Annotation[][] paramAnnotationArr = null;
                CtClass[] ctClasses = null;

                //记住参数注解类型
                List<Annotation> annotations = new ArrayList<>();

                //记住参数注解类型
                List<CtClass> ctParameterTypes = new ArrayList<>();

                while (parameter.hasNext()) {

                    Element parameterElement = parameter.next();

                    List<Attribute> parameterAttributes = parameterElement.attributes();

                    Map<String, Node> paramAttributesMap = new HashMap<>();

                    Annotation paramAnnotation = new Annotation("javax.jws.WebParam", constPool);

                    for (Attribute attr : parameterAttributes) {
                        paramAttributesMap.put(attr.getName(), attr);

                    }
                    paramAnnotation.addMemberValue("name", new StringMemberValue(paramAttributesMap.get("name").getStringValue(), constPool));
                    annotations.add(paramAnnotation);
                    ctParameterTypes.add(classpool.get(paramAttributesMap.get("type").getStringValue()));
                }

                paramAnnotationArr = new Annotation[annotations.size()][1];
                for (int i = 0; i < annotations.size(); i++) {
                    paramAnnotationArr[i][0] = annotations.get(i);
                }

                ctClasses = new CtClass[ctParameterTypes.size()];
                for (int i = 0; i < ctParameterTypes.size(); i++) {
                    ctClasses[i] = ctParameterTypes.get(i);
                }

                //添加方法

                //返回类型
                CtClass ccStringType = classpool.get("java.lang.String");
                //1：返回类型  2：方法名称  3：传入参数类型  4：所属类CtClass
                CtMethod ctMethod = new CtMethod(ccStringType, methodAttributesMap.get("methodName").getStringValue(),
                        ctClasses, ctclass);
                ctMethod.setModifiers(Modifier.PUBLIC);

                //方法体
                StringBuffer body = new StringBuffer();
                body.append("{");
                body.append("\n    System.out.println($1);" +
                        "System.out.println($2); ");
                body.append("\n    return \"Hello, \" + $2;");
                body.append("\n}");
                ctMethod.setBody(body.toString());
                ctclass.addMethod(ctMethod);

                ParameterAnnotationsAttribute parameterAnnotationsAttribute = new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
                parameterAnnotationsAttribute.setAnnotations(paramAnnotationArr);
                ctMethod.getMethodInfo().addAttribute(parameterAnnotationsAttribute);

            }

            classes.add(ctclass.toClass());
            //输出到文件
            byte[] bytes = ctclass.toBytecode();
            FileOutputStream fos = new FileOutputStream(new File("f:\\DynamicHello.class"));
            fos.write(bytes);
            fos.close();
        }
        return classes;

    }

}
