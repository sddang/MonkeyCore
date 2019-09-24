package com.monkey.api;

import com.monkey.services.data.DataMapper;

public class MonkeyDataMapper {
	public static String  mapData(final String parameter) {
		return DataMapper.getSessionMapper().mapData(parameter);
	}
}
