package br.com.soapboxrace.jlauncher.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
public class EmailValidator {

	private Pattern pattern;
	private String email;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		this.email = email;
	}

	public boolean isValid() {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
