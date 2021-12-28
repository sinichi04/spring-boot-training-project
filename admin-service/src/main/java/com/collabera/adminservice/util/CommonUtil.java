package com.collabera.adminservice.util;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.collabera.adminservice.repository.UserRepository;

@Component
public class CommonUtil {
	
	@Autowired
	UserRepository userRepository;
	
	public <T> boolean isNotEmpty(T parameter) {
		String param = parameter.toString();
		
		return (!param.isBlank() || !param.isEmpty() || param != null);
	}
	
	public boolean isMatch(String input, String regex) {
		return Pattern.matches(regex, input);
	}
}	
