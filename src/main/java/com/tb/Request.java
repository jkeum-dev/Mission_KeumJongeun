package com.tb;

import java.util.HashMap;
import java.util.Map;

public class Request {
	String cmd;
	String action;
	String queryString;
	Map<String, String> params;

	Request(String cmd) {
		params = new HashMap<>();

		this.cmd = cmd;

		String[] cmdBits = cmd.split("\\?", 2);
		action = cmdBits[0].trim();
		if (cmdBits.length == 1)
			return;
		queryString = cmdBits[1].trim();

		String[] queryStringBits = queryString.split("&");

		for (int i = 0; i < queryStringBits.length; i++) {
			String queryParamStr = queryStringBits[i];
			String[] queryParamStrBits = queryParamStr.split("=", 2);

			String paramName = queryParamStrBits[0];
			String paramValue = queryParamStrBits[1];

			params.put(paramName, paramValue);
		}
	}

	String getAction() {
		return action;
	}

	public int getParamAsInt(String paramName, int defaultValue) {
		String value = params.get(paramName);

		if (value != null) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		}
		return defaultValue;
	}
}