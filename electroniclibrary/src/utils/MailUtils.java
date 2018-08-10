package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	public static void sendMail(String email,String emailMsg)throws Exception {
		//1.创建Properties对象,并设置邮件服务器信息
		Properties properties=new Properties();
		//1.1设置邮件传输协议为SMTP
		properties.setProperty("mail.transport.protocol", "SMTP");
		//1.2设置SMTP服务器地址
		properties.setProperty("mail.host", "smtp.qq.com");
		//1.3设置SMTP服务器端口(为QQ邮箱的smtp服务器端口)
		properties.setProperty("mail.smtp.port", "587");
		//1.4设置SMTP服务器是否需要用户验证,需要验证为true
		properties.setProperty("mail.smtp.auth", "true");
		//1.5创建验证器
		Authenticator authenticator=new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//邮箱账户和授权码
				/**
				 * 授权码是QQ邮箱推出的，用于登录第三方客户端的专用密码。
				 * 适用于登录以下服务：POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务。
				 * 温馨提醒：为了你的帐户安全，更改QQ密码以及独立密码会触发授权码过期，需要重新获取新的授权码登录。
				 */
				return new PasswordAuthentication("895246485", "tmqxrpopmsxybbee");
			}
		};
		//实例化邮件会话Session
		Session session=Session.getDefaultInstance(properties,authenticator);
		//2.创建一个Message,该对象相当于邮件内容
		Message message=new MimeMessage(session);
		//2.1设置发送者
		message.setFrom(new InternetAddress("895246485@qq.com"));
		//2.2设置发送方式与接收者,邮件接收者在调用sendMail()方法时通过参数传递进来
		message.setRecipient(RecipientType.TO, new InternetAddress(email));
		//2.3设置发送主题
		message.setSubject("用户注册激活");
		//2.4设置发送内容
		message.setContent(emailMsg,"text/html;charset=utf-8");
		//3.发送邮件
		Transport.send(message);
	}
}
