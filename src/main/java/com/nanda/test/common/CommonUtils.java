package com.nanda.test.common;

public class CommonUtils {

	public static boolean isNotEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}

	public static String likeQuery(String str) {
		if (isNotEmpty(str))
			return "%" + str.trim() + "%";
		return "%";
	}

	public static int indexpage(int currentpage) {
		if (currentpage > 0)
			return currentpage - 1;
		return 0;
	}

}
