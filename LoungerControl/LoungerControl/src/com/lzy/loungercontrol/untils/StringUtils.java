package com.lzy.loungercontrol.untils;

import java.io.UnsupportedEncodingException;

public class StringUtils {
	
	/**
     * 判断字符串是否为空
     * ps:此方法在android中有代替，TextUtils.isEmpty
     * @param str
     * @return
     */
	public static boolean isEmpty(String str) {
		// TODO Auto-generated method stub
		return str == null || "".equals(str.trim());
	}
	/**
     * 判断字符串是否为空或者null字符串
     * @param str
     * @return
     */
	public static boolean isEmptyNull(String str) {
        return str == null || "".equals(str.trim()) || "null".equals(str);
    }
	/**
	 * 将数组转为字符串
	 * 
	 * @param array
	 * @param encode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertByteArrayToString(byte[] array, String encode) throws UnsupportedEncodingException {
		if (array == null || array.length == 0) {
			return null;
		}
		return new String(array, encode);
	}

    /**
     * 返回int型参数的string值
     * @param param
     * @return
     */
    public static String nvl(int param) {
        return String.valueOf(param);
    }
    
    //---------------------下面的方法目前未使用----------------------------//
    
    
    public static String convertByteArrayToString(byte[] array) throws UnsupportedEncodingException {
        return convertByteArrayToString(array, "utf-8");
    }
}
