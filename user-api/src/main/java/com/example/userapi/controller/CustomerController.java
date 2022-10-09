package com.example.userapi.controller;


import com.example.userapi.domain.customer.ChangeBalanceForm;
import com.example.userapi.domain.customer.CustomerDto;
import com.example.userapi.domain.model.Customer;
import com.example.userapi.exception.CustomException;
import com.example.userapi.exception.ErrorCode;
import com.example.userapi.service.customer.CustomerBalanceService;
import com.example.userapi.service.customer.CustomerService;
import com.zerobase.domain.common.UserVo;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final CustomerService customerService;
    private final CustomerBalanceService customerBalanceService;


    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token){
        UserVo vo = jwtAuthenticationProvider.getUserVo(token);
        Customer c = customerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
                ()->new CustomException(ErrorCode.NOT_FOUND_USER));
        return ResponseEntity.ok(CustomerDto.from(c));
    }

    @PostMapping("/balance")
    public ResponseEntity<Integer> changeBalance(@RequestHeader(name = "X-AUTH-TOKEN") String token, @RequestBody ChangeBalanceForm form){

        UserVo vo = jwtAuthenticationProvider.getUserVo(token);

        return ResponseEntity.ok(customerBalanceService.changeBalance(vo.getId(), form).getCurrentMoney());
    }
}
