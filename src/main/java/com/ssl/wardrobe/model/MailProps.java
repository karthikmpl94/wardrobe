package com.ssl.wardrobe.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MailProps {

	private final Properties props;

	private final String protocol;
	private final String host;
	private final int port;
	private final String username;
	private final String password;
	private final String smtpAuth;
	private final String startTLSEnabled;
	private final String smtpDebugValue;
	private final String recipientmails;

	public MailProps(Environment env) {
		if (Objects.nonNull(env)) {
			props = getProps(env.getProperty("mail.props"));
			protocol = env.getProperty("mail.smtp.protocol");
			host = env.getProperty("mail.host");
			port = Integer.parseInt(env.getProperty("mail.smtp.port"));
			password = env.getProperty("mail.smtp.password");
			smtpAuth = env.getProperty("mail.smtps.auth");
			startTLSEnabled = env.getProperty("mail.smtp.starttls.enable");
			smtpDebugValue = env.getProperty("mail.smtp.debug");
			recipientmails = env.getProperty("mail.recipientmails");
			username = env.getProperty("mail.smtp.username");

		} else {
			throw new IllegalStateException("Enviroment object cannot be null to initilize mail configurations.");
		}

	}

	private Properties getProps(String token) {
		Properties properties = new Properties();
		Arrays.asList(token.split("#")).stream().filter(StringUtils::isNotBlank).map(a -> a.split("@"))
				.forEach(a -> properties.put(a[0], a[1]));
		return properties;

	}

}
