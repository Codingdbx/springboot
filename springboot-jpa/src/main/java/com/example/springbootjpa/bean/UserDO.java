package com.example.springbootjpa.bean;


import javax.persistence.*;

/**
 * 实体类
 *
 * created by dbx on 2019/1/14
 */
@Entity
@Table(name = "AUTH_USER")//这里声明这个实体类对应的表名是 AUTH_USER。如果没有指定，则表名和实体的名称保持一致。
public class UserDO {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)// IDENTITY：采用数据库ID自增长的方式来自增主键字段
    private Long id;

    @Column(name="username",length = 32,nullable = false)
    private String name;

    @Column(length = 32)
    private String account;

    @Column(length = 64)
    private String pwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"account\":\"")
                .append(account).append('\"');
        sb.append(",\"pwd\":\"")
                .append(pwd).append('\"');
        sb.append('}');
        return sb.toString();
    }
}