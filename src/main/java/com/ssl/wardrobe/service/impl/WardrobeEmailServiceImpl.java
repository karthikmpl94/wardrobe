package com.ssl.wardrobe.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ssl.wardrobe.model.MailProps;
import com.ssl.wardrobe.service.WardrobeEmailService;

@Service
@PropertySource("classpath:application.properties")
public class WardrobeEmailServiceImpl implements WardrobeEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(WardrobeEmailServiceImpl.class);
	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";
	@Autowired
	private Environment env;

	@Override
	public void sendEmail(String message) {
		MailProps mailProps = new MailProps(env);
		if (Objects.nonNull(mailProps) && StringUtils.isNotBlank(mailProps.getUsername())
				&& StringUtils.isNotBlank(mailProps.getPassword())) {
			JavaMailSenderImpl sender = getMailTransporter(mailProps);
			String date = new SimpleDateFormat(DD_MMM_YYYY).format(new Date());
			MimeMessage mimeMsg = sender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);
				sender.setJavaMailProperties(mailProps.getProps());
				helper.setFrom(mailProps.getUsername());
				helper.setTo(InternetAddress.parse(mailProps.getRecipientmails()));
				helper.setSubject("Wardrobe API Failure Report -" + date);
				helper.setText("Hi All \n <p>" + message + " </p>\n  Thanks ", true);
				sender.send(mimeMsg);
			} catch (MessagingException e) {
				LOG.error("Unable to send email.{}", e);
			}
		}else {
			LOG.info("No mail credentials present in application.properties file ");
		}

	}

	private JavaMailSenderImpl getMailTransporter(MailProps mailProps) {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setProtocol(mailProps.getProtocol());
		sender.setHost(mailProps.getHost());
		sender.setPort(mailProps.getPort());
		sender.setUsername(mailProps.getUsername());
		sender.setPassword(mailProps.getPassword());
		return sender;
	}

}
