package com.car.util;

import com.google.gson.Gson;

public class GsonUtil {
	public static GsonUtil util;
	private Gson gson;

	private GsonUtil() {
		gson = new Gson();
	}

	public synchronized static GsonUtil getIncetance() {
		if (util == null) {
			util = new GsonUtil();
		}
		return util;
	}

	public Gson getGson() {
		return gson;
	}
}
