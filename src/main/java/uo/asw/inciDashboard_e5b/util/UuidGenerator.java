package uo.asw.inciDashboard_e5b.util;

import java.util.UUID;

public class UuidGenerator {
	
	public static String getUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
