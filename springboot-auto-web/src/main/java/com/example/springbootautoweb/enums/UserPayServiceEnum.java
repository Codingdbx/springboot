package com.example.springbootautoweb.enums;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/12 10:47
 * @since JDK1.8
 */
public enum UserPayServiceEnum {

    VIP(1,"Vip"),

    SUPER_VIP(2,"SuperVip"),

    PARTICULARLY_VIP(3,"ParticularlyVip"),

    NORMAL(4,"Normal");


    /**
     * 状态值
     */
    private int code;

    /**
     * 类型描述
     */
    private String value;

    private UserPayServiceEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static UserPayServiceEnum valueOf(int code) {
        for (UserPayServiceEnum type : UserPayServiceEnum.values()) {
            if (type.getCode()==code) {
                return type;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(UserPayServiceEnum.VIP.getValue());
    }

}
