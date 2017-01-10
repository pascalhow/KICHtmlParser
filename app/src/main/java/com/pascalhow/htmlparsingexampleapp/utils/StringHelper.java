package com.pascalhow.htmlparsingexampleapp.utils;

/**
 * Created by pascal on 04/01/2017.
 */

public class StringHelper {

    /**
     * Returns true if input string is null or empty
     * @param s Input string
     * @return  True if input string is null or empty
     */
    public static boolean isNullOrEmpty(String s) {
        return (s == null || s.isEmpty());
    }

    public static boolean isInteger(String s) {
        try{
            int num = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            // not an integer!
            return false;
        }
    }
}
