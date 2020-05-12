package com.ruoyi.cms.util.email;

public class Test {

	public static void main(String[] args) {
			MailSenderInfo info=new MailSenderInfo();
		info.setMailServiceHost("smtp.qq.com");//qq邮箱服务器
		info.setMailServicePort("25");//端口号
		info.setFromAddress("747506908@qq.com");
		info.setValidate(true);
		info.setUserName("747506908@qq.com");
		info.setPassword("swigvspxyywdbehe");
		String[] to={"747506908@qq.com"};
		info.setToAddress(to);
		info.setSubject("邮件发送测试");
		info.setContent("<h4>测试内容<a href='http://www.baidu.com'>百度</a></h4>");
		String[] attach={"D:\\1.jpg"};
		info.setAttachs(attach);
		
		SimpleMailSender sender=new SimpleMailSender();
		boolean b=sender.sendHtmlMail(info);
		if(b){
			System.out.println("成功！");
		}else{
			System.out.println("失败！");
		}
	}

}
