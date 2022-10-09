package com.example.userapi.application;


import com.example.userapi.domain.SignInForm;
import com.example.userapi.domain.model.Customer;
import com.example.userapi.domain.model.Seller;
import com.example.userapi.exception.ErrorCode;
import com.example.userapi.service.customer.CustomerService;
import com.example.userapi.service.seller.SellerService;
import com.zerobase.domain.common.UserType;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.userapi.exception.CustomException;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final SellerService sellerService;
    private final JwtAuthenticationProvider provider;

    public String customerLoginToken(SignInForm form) {

        //로그인 가능 여부
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        // 토근 발행

        // 토근 Response


        return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
    }

    public String sellerLoginToken(SignInForm form) {

        //로그인 가능 여부
        Seller s = sellerService.findValidSeller(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        // 토근 발행

        // 토근 Response


        return provider.createToken(s.getEmail(), s.getId(), UserType.SELLER);
    }

}
