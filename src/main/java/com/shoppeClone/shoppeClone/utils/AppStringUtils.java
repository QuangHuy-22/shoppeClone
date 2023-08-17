package com.shoppeClone.shoppeClone.utils;

public class AppStringUtils {

	public static boolean hasText(String input) {
		if (input != null && !input.isEmpty()) {
			return true;
		}
		return false;
	}
}
