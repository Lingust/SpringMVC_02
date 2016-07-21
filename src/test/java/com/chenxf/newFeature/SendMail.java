package com.chenxf.newFeature;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendMail {
	public static void main(String[] args) throws AddressException, MessagingException, GeneralSecurityException {
		String to = "@126.com";
		String from ="@foxmail.com";
		String password = "";
		
		/*本地客户端设置*/
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		
		Properties props = System.getProperties();
		props.setProperty("mail.debug", "false");	//开启DEBUG
		props.setProperty("mail.smtp.auth", "true");	//服务器需要身份验证
		props.setProperty("mail.transport.protocol", "smtp");	//发送邮件协议名称
		props.setProperty("mail.smtp.host", "smtp.qq.com");		//邮件服务器主机名
		props.setProperty("mail.smtp.ssl.enable", "true");	//SSL功能开启
		props.put("mail.smtp.ssl.socketFactory", sf);	//服务器需要SSL连接
		System.out.println(props.getProperty("mail.smtp.host"));
		System.exit(0);
		//props.put("mail.stmp.prot", "465");
		
		/*连接服务器并生成邮件信息*/
		Session session = Session.getInstance(props);	//获取服务端连接
		
		StringBuilder builder = new StringBuilder();
		builder.append("这是由Java Mail生成并发送的邮件\n");
		builder.append("来自XXX");
		
		Message msg = new MimeMessage(session);
		msg.setSubject("测试邮件1");
		msg.setText(builder.toString());
		msg.setFrom(new InternetAddress(from));
		msg.addRecipient(Message.RecipientType.TO,
				new InternetAddress("Chenxf@Chenxf-PC.com"));		//写入收件人（写入邮件中，可选）
		
		/*本地发送邮件到服务端*/
		Transport transport = session.getTransport();
		transport.connect("smtp.qq.com", from, password);
		transport.sendMessage(msg, new Address[]{
				new InternetAddress(to)						//收件人（发送到服务器，必选）
		});
		transport.close();
		
		System.out.println("Send msg.successful...");
	}
}
