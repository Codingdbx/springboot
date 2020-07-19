package com.example.springbootjavassist.service;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 动态生成Webservice
 *
 * created by dbx on 2019/1/9
 */
public class DynamicCreateWebservice {

        /**
         * 动态生成Webservice
         * @throws CannotCompileException
         * @throws IOException
         */

        public static Class<?>  dynamicCreateWebservice() throws CannotCompileException, IOException {

                ClassPool classpool = ClassPool.getDefault();

                //类
                CtClass ctclass= classpool.makeClass("com.cesgroup.soap.webservice.DynamicHelloWordWebservice");

                //添加方法
                CtMethod ctMethod = CtMethod.make("public String invokeHello(String paramString){" +
                        " System.out.print($1);"+
                        "\n	return \" hello,\"+$1;"+"\n"+
                        "}", ctclass);

                ctclass.addMethod(ctMethod);

                //Returns a class file for this class
                ClassFile cf = ctclass.getClassFile();
                ConstPool constPool=cf.getConstPool();

                /** 添加类的注释
                 *   类注解和方法注解生成流程：
                 1、 创建注解Annotation；
                 2、 注解队列AnnotationsAttribute添加注解Annotation；
                 3、 类ClassFile或方法信息CtMethod.getMethodInfo()添加注解队列AnnotationsAttribute。
                 @WebService(name="dynamicCreateWebservice")
                 public class DynamicHelloWordWebservice
                 */
                Annotation classAttr = new Annotation("javax.jws.WebService", constPool);
                classAttr.addMemberValue("name", new StringMemberValue("dynamicCreate",constPool));
                classAttr.addMemberValue("targetNamespace", new StringMemberValue("webservice.soap.cesgroup.com", constPool));
                //classAttr.addMemberValue("endpointInterface",new StringMemberValue("com.cesgroup.soap.service",constPool));
                AnnotationsAttribute classAnnotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
                classAnnotationsAttribute.addAnnotation(classAttr);
                cf.addAttribute(classAnnotationsAttribute);


                /***
                 * 添加方法注解
                 * 类注解和方法注解生成流程：
                 * 1、 创建注解Annotation；
                 * 2、 注解队列AnnotationsAttribute添加注解Annotation；
                 * 3、 类ClassFile或方法信息CtMethod.getMethodInfo()添加注解队列AnnotationsAttribute。
                 * 	   生成的格式：@WebMethod(operationName="invokeHello")
                 @WebResult(name="result")
                 public String invokeHello
                 */
                Annotation methodAttr = new Annotation("javax.jws.WebMethod", constPool);
                methodAttr.addMemberValue("operationName", new StringMemberValue("invokeHello", constPool));
                AnnotationsAttribute  methodAnnotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
                methodAnnotationsAttribute.addAnnotation(methodAttr);

                Annotation resultAttr = new Annotation("javax.jws.WebResult", constPool);
                resultAttr.addMemberValue("name", new StringMemberValue("result", constPool));
                methodAnnotationsAttribute.addAnnotation(resultAttr);

                ctMethod.getMethodInfo().addAttribute(methodAnnotationsAttribute);


                /***
                 * 参数注解生成流程：
                 * 1、 创建注解二维数组Annotation[][]：第一维对应参数序列，第二维对应注解序列；
                 * 2、 参数注解属性ParameterAnnotationsAttribute添加注解二维数组Annotation[][]；
                 * 3、 方法信息CtMethod.getMethodInfo()添加参数注解属性ParameterAnnotationsAttribute。
                 */
                Annotation[][] paramAnnotationArr = new  Annotation[1][1];
                Annotation paramAnnotation = new Annotation("javax.jws.WebParam", constPool);
                paramAnnotation.addMemberValue("name", new StringMemberValue("name",constPool));
                paramAnnotationArr[0][0]=paramAnnotation;

                ParameterAnnotationsAttribute parameterAnnotationsAttribute = new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
                parameterAnnotationsAttribute.setAnnotations(paramAnnotationArr);
                ctMethod.getMethodInfo().addAttribute(parameterAnnotationsAttribute);

                //输出到文件
                byte[] bytes = ctclass.toBytecode();
                FileOutputStream fos = new FileOutputStream(new File("f:\\DynamicHelloWordWebservice.class"));
                fos.write(bytes);
                fos.close();
                return ctclass.toClass();


        }

        public static void main(String[] args) {

                try {
                        Class<?> webservice = DynamicCreateWebservice.dynamicCreateWebservice();
                        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();

                        // Web服务的地址
                        factoryBean.setAddress("http://localhost:8081/ws");

                        // Web服务对象调用接口
                        factoryBean.setServiceClass(webservice);
                        Server server = factoryBean.create();
                        server.start();


                } catch (Exception e) {
                        e.printStackTrace();
                }


        }
}