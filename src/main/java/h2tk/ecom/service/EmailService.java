package h2tk.ecom.service;


import h2tk.ecom.model.Status;
import h2tk.ecom.model.Users;
import h2tk.ecom.repository.StatusRepository;
import h2tk.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service

public class EmailService {
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    public EmailService(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository= userRepository;
    }
    public boolean performEmailVerification(String token, String email) {
        Users user = userRepository.findByEmail(email);
        Status isExitStatus = new Status(1,"Activated");
        if (user != null && user.getToken().equals(token) &&user.getStatus().getId() == 2) {
            user.setStatus(isExitStatus);
            user.setToken(null);
            userRepository.save(user);

            return true;
        }

        return false;
    }

    public void sendEmail(String to, String name, String link) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Hệ thống thương mại điện tử Vjet <hoaboy89@gmail.com>");
        message.setTo(to);
        message.setSubject("Xác thực Email");

        String htmlContent = "<h3>Xin chào " + name + "</h3>\n"
                + "<p>Bạn nhận được email này vì đã đăng ký tài khoản trên Hệ thống thương mại điện tử Vjet .</p>\n"
                + "<p>Nếu bạn đã đăng ký tài khoản và muốn xác nhận và hoàn tất quá trình đăng ký, vui lòng click vào đường link bên dưới:</p>\n"
                + "<div>\n"
                + "    <a href=\"" + link + "\" target=\"_blank\">Click vào đây</a>\n"
                + "</div>\n"
                + "<div>Xin chân thành cảm ơn!</div>";

        message.setText(htmlContent);
        mailSender.send(message);
    }
}