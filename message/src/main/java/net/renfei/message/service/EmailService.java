package net.renfei.message.service;

import net.renfei.api.message.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件服务
 *
 * @author RenFei
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(Email email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email.getTo());
        msg.setSubject(email.getSubject());
        msg.setText(email.getContent());

        javaMailSender.send(msg);
    }

    public void sendEmailWithHtml(Email email) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        helper.setText(email.getContent(), true);

        javaMailSender.send(msg);

    }
}
