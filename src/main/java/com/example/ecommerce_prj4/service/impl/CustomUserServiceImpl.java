package com.example.ecommerce_prj4.service.impl;

import com.example.ecommerce_prj4.domain.USER_ROLE;
import com.example.ecommerce_prj4.modal.Seller;
import com.example.ecommerce_prj4.modal.User;
import com.example.ecommerce_prj4.repository.SellerRepository;
import com.example.ecommerce_prj4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CustomUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserServiceImpl(UserRepository userRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    private final SellerRepository sellerRepository;
    private static final String SELLER_PREFIX = "seller_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith(SELLER_PREFIX)) {
            String actualUsername = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepository.findByEmail(actualUsername);
            if(seller!=null){
                return buildUserDetails(seller.getEmail(),seller.getPassword(),seller.getRole());
            }
        } else {
            User user = userRepository.findByEmail(username);
            if (user != null) {
                return buildUserDetails(user.getEmail(), user.getPassword(), user.getRole());
            }
        }
        throw new UsernameNotFoundException("Username not found");
    }

    private UserDetails buildUserDetails(String email, String password, USER_ROLE role) {
        if (role == null) role = USER_ROLE.ROLE_USER;

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + role));
        return new org.springframework.security.core.userdetails.User(email,
                password,
                authorityList);
    }
}
