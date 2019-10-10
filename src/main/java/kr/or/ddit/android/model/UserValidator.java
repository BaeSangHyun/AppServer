package kr.or.ddit.android.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		Pattern p = Pattern.compile("^[a-zA-Z\\d\\.!@#]{3,20}$");
		Matcher m = p.matcher(user.getUserId());
		if(!m.find()) {
			errors.rejectValue("userId", "required");	//필드명, 에러코드(개발자 정의)
		}
	}

}
