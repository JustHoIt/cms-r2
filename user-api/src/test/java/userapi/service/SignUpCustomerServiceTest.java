package userapi.service;


import com.example.userapi.domain.SignUpForm;
import com.example.userapi.domain.model.Customer;
import com.example.userapi.service.customer.SignUpCustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SignUpCustomerServiceTest {


    @Autowired
    private SignUpCustomerService service;

    @DisplayName("회원가입")
    @Test
    void signUp() {

        SignUpForm form = SignUpForm.builder()
                .name("name")
                .birth(LocalDate.now())
                .email("abc@gmail.com")
                .password("1")
                .phone("01000000000")
                .build();
        Customer c =service.signUp(form);

        assertNotNull(c.getId());
        assertNotNull(c.getCreatedAt());

    }
}