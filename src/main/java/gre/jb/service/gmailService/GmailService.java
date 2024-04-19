package gre.jb.service.gmailService;

import gre.jb.entity.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class GmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        emailSender.send(message);
    }

    public void emailSubmitCV(EmailDTO emailDTO) {
        // Send email notification
        String subject = "CV Submitted";
        String body = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>CV Submitted</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"font-family: Arial, sans-serif; padding: 20px;\">\n" +
                "    <h2 style=\"color: #007bff;\">CV Submitted</h2>\n" +
                "    <p>Dear Applicant,</p>\n" +
                "    <p>A CV from <strong>"+emailDTO.getFrom()+"</strong> has been submitted to your job. Please check it!</p>\n" +
                "    <a href=\"http://localhost:4200/job/job-detail/"+emailDTO.getJobId()+"\">Link to job detail</a>\n" +
                "    <br>\n" +
                "    <p>Best Regards,</p>\n" +
                "    <h3>GreenJob</h3>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";
        try {
            this.sendSimpleMessage(emailDTO.getTo(), subject, body);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
