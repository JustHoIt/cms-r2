package com.example.userapi.service.seller;


import com.example.userapi.domain.model.Seller;
import com.example.userapi.domain.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;


    public Optional<Seller> findByIdAndEmail(Long id, String email){
        return sellerRepository.findById(id)
                .stream().filter(customer->customer.getEmail().equals(email))
                .findFirst();
    }


    public Optional<Seller> findValidSeller(String email, String password) {
        return sellerRepository.findByEmailAndPasswordAndVerifyIsTrue(email, password);
    }

}
