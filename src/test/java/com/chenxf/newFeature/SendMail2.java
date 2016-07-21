package com.chenxf.newFeature;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendMail2 {
	
	private String to;
	private String from;
	private String password;
	private String host;
	
	private Multipart multimsg;
	private Properties props = new Properties();
	
	public SendMail2() throws Exception {
		
		/*本地客户端设置*/
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		
		InputStream in = SendMail2.class.getResourceAsStream("/mail.properties");
		props.load(in);
		props.setProperty("mail.smtp.ssl.enable", "true");	//SSL功能开启
		props.put("mail.smtp.ssl.socketFactory", sf);	//服务器需要SSL连接
		
		from = props.getProperty("mail.sender.username");
		to = props.getProperty("mail.receiver.username");
		password = props.getProperty("mail.sender.password");
		host = props.getProperty("mail.smtp.host");
		
		/*连接服务器并生成邮件信息*/
		Session session = Session.getInstance(props);	//获取服务端连接
		
		StringBuilder builder = new StringBuilder();
		builder.append("这是由Java Mail生成并发送的邮件\n");
		builder.append("来自陈小峰");
		
		Message msg = new MimeMessage(session);
		msg.setSubject("测试邮件4");		//邮件主题
		//msg.setText(builder.toString());
		msg.setFrom(new InternetAddress(from));	//写入发件人（必须）
		msg.addRecipient(Message.RecipientType.TO,
				new InternetAddress(to));		//写入收件人防126转发（写入邮件中，可选）
		
		//下面可以添加附件(another way)
		multimsg = new MimeMultipart();
		
		//添加邮件正文
		BodyPart contentPart = new MimeBodyPart();
		contentPart.setContent(builder.toString(), 
				"text/html;charset=UTF-8");
		multimsg.addBodyPart(contentPart);
		
		//添加附件内容
		File file = new File("C:\\Users\\Chenxf\\Desktop\\错误代码.java");
		if(file != null){
			BodyPart attachmentPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file);
			attachmentPart.setDataHandler(new DataHandler(source));
			
			/*解决乱码的方法，通过下面的Base64编码的转换可以
			 * 保证中文附件标题名在发送时不会变成乱码*/
			attachmentPart.setFileName(MimeUtility.encodeWord(file.getName()));
			multimsg.addBodyPart(attachmentPart);
		}
		msg.setContent(multimsg);
		msg.saveChanges();
		
		/*本地发送邮件到服务端*/
		Transport transport = session.getTransport();
		transport.connect(host, from, password);
		transport.sendMessage(msg, new Address[]{
				new InternetAddress(to)						//收件人（发送到服务器，必选）
		});
		transport.close();
		
		System.out.println("Send message successful...");
	}
	public static void main(String[] args) throws Exception {
		new SendMail2();
	}
}
