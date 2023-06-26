package h2tk.ecom.controller;

import h2tk.ecom.model.Cart;
import h2tk.ecom.model.Users;
import h2tk.ecom.repository.StatusRepository;
import h2tk.ecom.service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class router {

    @Value("${localhost.url:http://localhost:8080}")
    private String localhostUrl;

    private final EmailService emailService;
    @Autowired
    public router(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String Index(){
        return "content_page/index";
    }

    @GetMapping("/admin")
    public String Category(HttpSession session){
        Users users = (Users) session.getAttribute("user");
        if(users != null){
            if(users.getRole().getId() == 2){
                return "content_page/ManageCategory";
            }else if(users.getRole().getId() == 1){
                return "error/404";
            }
        }
        return "content_page/login_signup";
    }

    @GetMapping("/adminManageProduct")
    public String product(HttpSession session){
        Users users = (Users) session.getAttribute("user");
        if(users != null){
            if(users.getRole().getId() == 2){
                return "content_page/ManageProduct";
            }else if(users.getRole().getId() == 1){
                return "error/404";
            }
        }
        return "content_page/login_signup";
    }

    @GetMapping("/adminManageUser")
    public String User(HttpSession session){
        Users users = (Users) session.getAttribute("user");
        if(users != null){
            if(users.getRole().getId() == 2){
                return "content_page/ManageUser";
            }else if(users.getRole().getId() == 1){
                return "error/404";
            }
        }
        return "content_page/login_signup";
    }

    @GetMapping("/login")
    public String UserLogin(){
        return "content_page/login_signup";
    }

    @GetMapping("/showCart")
    public String cart(HttpSession session){
        Users users = (Users) session.getAttribute("user");
        if(users == null){
            return "content_page/showCart";
        }
        if(users.getRole().getId() == 2){
            return "error/404";
        }else if (users.getRole().getId() == 1){
            return "content_page/showCart";
        }
        return "error/404";
    }

    @GetMapping("/changePassword")
    public String changePass(HttpSession session){
        return "content_page/changePassword";
    }

    @GetMapping("/verify-email")
    public String loadVerifyEmailPage(@RequestParam("token") String token, @RequestParam("email") String email) {
        return "content_page/verify-email";
    }

    @PostMapping("/verify-email")
    @ResponseBody
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token, @RequestParam("email") String email) {
        // Thực hiện xác thực email
        boolean isEmailVerified = emailService.performEmailVerification(token, email);

        if (isEmailVerified) {
            return ResponseEntity.ok("Xác nhận email thành công!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email đã được xác nhận hoặc lỗi!");
        }
    }
}
