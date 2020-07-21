package com.weaver.rparecruitment.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 封装简历实体对象</p>
 *
 * @author dbx
 * @date 2020/3/18 16:57
 * @since JDK1.8
 */
public class Resume {

    private String title;//简历标题

    private String deliveryTime;//投递时间

    private String name;//姓名

    private String age; //年龄

    private String sex;//性别

    private String phone;//手机号

    private String email;//邮箱

    private String position;//应聘职位

    private String  source;//渠道来源

    private String address;//居住地址

    private String education;//最高学历

    private String imageFileId;//简历附件ID

    private String creatorId;//创建人loginId

    private String creatorIp;//创建人ip地址

    private Map<String, String> otherParamMap = new HashMap<>();//新增其他参数

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getImageFileId() {
        return imageFileId;
    }

    public void setImageFileId(String imageFileId) {
        this.imageFileId = imageFileId;
    }


    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorIp() {
        return creatorIp;
    }

    public void setCreatorIp(String creatorIp) {
        this.creatorIp = creatorIp;
    }

    public Map<String, String> getOtherParamMap() {
        return otherParamMap;
    }

    public void setOtherParamMap(Map<String, String> otherParamMap) {
        this.otherParamMap = otherParamMap;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "title='" + title + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", source='" + source + '\'' +
                ", address='" + address + '\'' +
                ", education='" + education + '\'' +
                ", imageFileId='" + imageFileId + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", creatorIp='" + creatorIp + '\'' +
                '}';
    }
}
