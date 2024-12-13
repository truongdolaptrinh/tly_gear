package com.example.ecommerce_prj4.controller;

import com.example.ecommerce_prj4.config.JwtProvider;
import com.example.ecommerce_prj4.domain.AccountStatus;
import com.example.ecommerce_prj4.modal.Seller;
import com.example.ecommerce_prj4.modal.SellerReport;
import com.example.ecommerce_prj4.modal.VerificationCode;
import com.example.ecommerce_prj4.repository.VerificationCodeRepository;
import com.example.ecommerce_prj4.request.LoginRequest;
import com.example.ecommerce_prj4.respone.ApiRespone;
import com.example.ecommerce_prj4.respone.AuthRespone;
import com.example.ecommerce_prj4.service.AuthService;
import com.example.ecommerce_prj4.service.EmailService;
import com.example.ecommerce_prj4.service.SellereService;
import com.example.ecommerce_prj4.utils.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellereService sellereService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;

//    @PostMapping("/sent/login-otp")
//    public ResponseEntity<ApiRespone> sentOtpHandler(@RequestBody VerificationCode req) throws Exception {

    /// /        User user = new User();
    /// /        user.setEmail(req.getEmail());
    /// /        user.setFullname(req.getFullName());
    /// /        User savedUser = userRepository.save(user);
//
//        authService.sentLoginOtp(req.getEmail());
//
//        ApiRespone res = new ApiRespone();
//
//        res.setMessage("OTP sent successfully");
//
//        return ResponseEntity.ok(res);
//    }
    @PostMapping("/login")
    public ResponseEntity<AuthRespone> loginSeller(
            @RequestBody LoginRequest req
    ) throws Exception {
        String otp = req.getOtp();
        String email = req.getEmail();

//        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
//        if (verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
//            throw new Exception("Wrong otp");
//
//        }
        req.setEmail("seller_" + email);
        AuthRespone authRespone = authService.signing(req);
        return ResponseEntity.ok(authRespone);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception, MessagingException {
        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);
        if (verificationCode != null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("wrong otp");
        }
        Seller seller = sellereService.verifyEmail(verificationCode.getEmail(), otp);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {
        Seller savedSeller = sellereService.createSeller(seller);

        String otp = OtpUtil.generateOtp();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "TLY_GEAR email verification";
        String text = "Welcome to TLY_GEAR, verify by this link";
        String frontend_url = "http://localhost:3000/verify-seller/";
        emailService.sendVerificationMail(seller.getEmail(), verificationCode.getOtp(), subject, text + frontend_url);
        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws Exception {
        Seller seller = sellereService.getSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Seller seller = sellereService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

//    @GetMapping("/report")
//    public ResponseEntity<SellerReport> getSellerReport(
//            @RequestHeader("Authorization") String jwt
//    ) throws Exception {
//        String email = jwtProvider.getEmailFromJwtToken(jwt);
//        Seller seller = sellereService.getSellerByEmail(email);
//        SellerReport report = sellerReportService.getSellerReport(seller);
//        return new ResponseEntity<>(report, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(
            @RequestParam(required = false) AccountStatus status
    ) throws Exception {
        List<Seller> sellers = sellereService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @PostMapping()
    public ResponseEntity<Seller> updateSeller(@RequestHeader("Authorization") String jwt,
                                               @RequestBody Seller seller) throws Exception {
        Seller profile = sellereService.getSellerProfile(jwt);
        Seller updateSeller = sellereService.updateSeller(profile.getId(), seller);
        return ResponseEntity.ok(updateSeller);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
        sellereService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
