package test.guestbook.task.util;

import test.guestbook.task.to.MessageTo;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailSender {

    public static final String senderEmail = "employeegb@gmail.com";
    public static final String senderPassword = "Rt4$32dW";
    public static final EmailSender sslSender = new EmailSender(senderEmail, senderPassword);

    private String username;
    private String password;
    private Properties props;

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }

    public static void sendMails(MessageTo message, List<String> emails){
        String name = message.getDefaultName() == null ? message.getUserName() : message.getDefaultName();
        String dateTime = message.getData() + " " + message.getTime();
        String text = message.getText();

        String title = "New message in GuestBook";
        String messageText = "Пользователь " + name + " оставил новое сообщение в "
                + dateTime + ".\n" + "Текст сообщения: " + text;
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (String email : emails) {
            SenderThread senderThread = new SenderThread(email, title, messageText);
            executor.execute(senderThread);
        }
        executor.shutdown();
    }

    public void send(String subject, String text, String fromEmail, String toEmail){
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //тема сообщения
            message.setSubject(subject);
            //текст
            message.setText(text);

            //отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {

        }
    }

    static class SenderThread implements Runnable{

        private String email;
        private String title;
        private String message;

        public SenderThread(String email, String title, String message){
            this.email=email;
            this.title=title;
            this.message = message;
        }

        @Override
        public void run() {
            sslSender.send(title, message, senderEmail, email);
        }
    }
}
