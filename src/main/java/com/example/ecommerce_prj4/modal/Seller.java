package com.example.ecommerce_prj4.modal;

import com.example.ecommerce_prj4.domain.AccountStatus;
import com.example.ecommerce_prj4.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sellerName;
    private String mobile;
    @Column(unique = true ,nullable = false)
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BussinessDetails getBussinessDetails() {
        return bussinessDetails;
    }

    public void setBussinessDetails(BussinessDetails bussinessDetails) {
        this.bussinessDetails = bussinessDetails;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    public Address getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(Address pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Embedded
    private BussinessDetails bussinessDetails = new BussinessDetails();
    @Embedded
    private BankDetails bankDetails = new BankDetails();
    @OneToOne(cascade = CascadeType.ALL)
    private Address pickupAddress = new Address();
    private String GSTIN;
    private USER_ROLE role = USER_ROLE.ROLE_SELLER;
    private Boolean isEmailVerified = false;
    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;
}
