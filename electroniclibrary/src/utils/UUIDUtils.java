package utils;

import java.util.UUID;

public class UUIDUtils {
	public static String getActiveCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
