package org.francis.framework.utils;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote
 */
public class StringUtils {
    private StringUtils() {
    }

    public static String getSetterMethodName(String fieldName){
        return "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
    }
}
