
package com.monkey.core.utils;
/**
 * This package contains one class that parse jsosn files
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;

public class JsonParser<T extends Object> {
	/**
	 * Parse the given json files
	 * @param path is the path of the json file
	 * @param clazz is the class where thejson file will be mapped to
	 * @return 
	 * @throws IOException
	 */
	public T parse(final String path, final Class<T> clazz) throws IOException {

		if (clazz.getClassLoader().getResource(path) == null) {
			throw new MonkeyException(ExceptionCode.MAL_FORMED_FILE_ERROR, path);
		}
		String jsonPath = clazz.getClassLoader().getResource(path).getPath();
		
		if (OSValidator.isWindows()){
			if (jsonPath != null && jsonPath.startsWith("/")) {
				jsonPath = jsonPath.substring(1);
			}
		}
		// read json file data to String
		final byte[] jsonData = Files.readAllBytes(Paths.get(jsonPath));

		// create ObjectMapper instance
		final ObjectMapper objectMapper = new ObjectMapper();

		// convert json string to object
		return objectMapper.readValue(jsonData, clazz);

	}

}
