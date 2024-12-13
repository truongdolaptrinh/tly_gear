package com.example.ecommerce_prj4.controller;

import com.example.ecommerce_prj4.domain.USER_ROLE;
import com.example.ecommerce_prj4.modal.VerificationCode;
import com.example.ecommerce_prj4.repository.UserRepository;
import com.example.ecommerce_prj4.request.LoginOtpRequest;
import com.example.ecommerce_prj4.request.LoginRequest;
import com.example.ecommerce_prj4.respone.ApiRespone;
import com.example.ecommerce_prj4.respone.AuthRespone;
import com.example.ecommerce_prj4.respone.SignupRequest;
import com.example.ecommerce_prj4.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    public AuthController(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }


    @PostMapping("/signup")
    public ResponseEntity<AuthRespone> createUserHandler(@RequestBody SignupRequest req) throws Exception {
//        User user = new User();
//        user.setEmail(req.getEmail());
//        user.setFullname(req.getFullName());
//        User savedUser = userRepository.save(user);

        String jwt = authService.createUser(req);

        AuthRespone res = new AuthRespone();
        res.setJwtToken(jwt);
        res.setMessage("register succes");
        res.setRole(USER_ROLE.ROLE_USER);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiRespone> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
//        User user = new User();
//        user.setEmail(req.getEmail());
//        user.setFullname(req.getFullName());
//        User savedUser = userRepository.save(user);

        authService.sentLoginOtp(req.getEmail(),req.getRole());

        ApiRespone res = new ApiRespone();

        res.setMessage("OTP sent successfully");

        return ResponseEntity.ok(res);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthRespone> loginHandler(@RequestBody LoginRequest req) throws Exception {
//        User user = new User();
//        user.setEmail(req.getEmail());
//        user.setFullname(req.getFullName());
//        User savedUser = userRepository.save(user);

         AuthRespone authRespone= authService.signing(req);

        ApiRespone res = new ApiRespone();

        return ResponseEntity.ok(authRespone);
    }
}
