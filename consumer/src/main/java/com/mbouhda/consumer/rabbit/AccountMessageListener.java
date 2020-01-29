package com.mbouhda.consumer.rabbit;

import com.mbouhda.consumer.model.AccountDTO;
import com.mbouhda.consumer.tools.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class AccountMessageListener {

    private final static Logger log = LoggerFactory.getLogger(AccountMessageListener.class);

    private MailSender mailSender;

    public AccountMessageListener(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = "${account.queue.name}")
    public void receiveMessage(AccountDTO accountDTO) {
        log.info("Received 'Account' message: {}", accountDTO);
        sendEmail(accountDTO);
    }

    public void sendEmail(AccountDTO accountDTO) {
        try {
            mailSender.sendMailMultipart("test@test.com", "lorem", "ipsum");
        } catch (MessagingException e) {
            log.error("There was an error while sending the email: {}", e.getLocalizedMessage());
        }
        log.info("email sent !");
    }
}
