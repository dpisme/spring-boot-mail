package com.imooc.springbootmail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

/**
 * @author 1639489689@qq.com
 * @date 2018/9/6 0006 下午 11:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Resource
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail("wang_dp@tangdi.net","这是第一份邮件","搭建好，这是第一份邮件");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content="<html>\n" +
                "<body>\n" +
                "<h3>hello world,这是第一份html邮件</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("916077805@qq.com","这是一封html邮件",content);
    }

    @Test
    public void sendAttachmentsMailTest() throws MessagingException {
        String filePath="C:\\Users\\Administrator\\Desktop\\guns.docx";
        mailService.sendAttachmentsMail("916077805@qq.com","这是一封带附件的邮件","这是一封带附件的邮件内容",filePath);
    }

    @Test
    public void sendInlinResourceMailTest() throws MessagingException {
        String imgPath="C:\\Users\\Administrator\\Desktop\\128\\081A0625.JPG";
        String srcId="a001";
        String cotent="<html><body>这是有图片的邮件：<img src=\'cid:"+srcId+"\'></img></body></html>";
        mailService.sendInlinResourceMail("916077805@qq.com","这是一封图片的邮件",cotent,imgPath,srcId);
    }

    @Test
    public void testTemplateMailTest() throws MessagingException {
        Context context=new Context();
        context.setVariable("id","006");
        String emailContent=templateEngine.process("emailTemplate",context);
        mailService.sendHtmlMail("916077805@qq.com","【壹配送】会员邮箱验证",emailContent);
    }

}