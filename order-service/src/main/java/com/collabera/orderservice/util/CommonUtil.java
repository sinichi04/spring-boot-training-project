package com.collabera.orderservice.util;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
	
	public <T> boolean isNotEmpty(T parameter) {
		System.out.println(parameter);
		if(parameter == null)
			return false;
		
		String param = parameter.toString();
		return (!param.isBlank() || !param.isEmpty());
	}
	
	public boolean isMatch(String input, String regex) {
		return Pattern.matches(regex, input);
	}
}	
