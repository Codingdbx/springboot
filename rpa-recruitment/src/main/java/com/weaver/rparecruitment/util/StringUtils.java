package com.weaver.rparecruitment.util;

/**
 * <p>Description: 字符串处理工具类</p>
 *
 * @author dbx
 * @date 2020/3/20 15:28
 * @since JDK1.8
 */
public final class StringUtils {


    /**
     * <p>
     * Checks if a String is empty ("") or null.
     * </p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>
     * NOTE: This method changed in Lang version 2.0. It no longer trims the String. That functionality is available in isBlank().
     * </p>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * A string with any whitespace removed.
     * </p>
     *
     * @param str trimEnhance string
     * @return A string whose value is this string with any whitespace removed.
     */
    public static String trimEnhance(String str) {

        char[] charArray = str.toCharArray();
        int len = charArray.length;

        int st = 0;
        StringBuilder sb = new StringBuilder();
        while (st < len) {
            if (charArray[st] > ' ') sb.append(charArray[st]);
            st++;
        }
        return sb.toString();
    }

    /**
     * <p>
     * A string exclude one char.
     * </p>
     *
     * @param str excludeChar string
     * @param ch one char exclude
     * @return A string whose value is this string with any ch.
     */
    public static String excludeChar(String str,char ch){

        char[] charArray = str.toCharArray();
        int len = charArray.length;
        int st = 0;
        StringBuilder sb = new StringBuilder();
        while (st < len) {
            if (charArray[st]!=ch) sb.append(charArray[st]);
            st++;
        }
        return sb.toString();
    }

    /**
     * <p>
     * format all strings to numeric.
     * </p>
     *
     * @param str format string
     * @return A format string are numeric.
     */
    public static String findNumber(String str){

        char[] charArray = str.toCharArray();
        int len = charArray.length;
        int st = 0;

        StringBuilder sb = new StringBuilder();
        while (st < len) {
            //ASCII code  '0'-'9' 48-57
            if (charArray[st] >= '0' && charArray[st] <= '9') sb.append(charArray[st]);
            st++;
        }
        return sb.toString();
    }



}
