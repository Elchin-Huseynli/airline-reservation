package com.airline.user_ms.repository;


import com.airline.user_ms.model.entity.ConfirmationOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationOtpRepository extends JpaRepository<ConfirmationOtp,Long> {

    @Override
    Optional<ConfirmationOtp> findById(Long aLong);

    Optional<ConfirmationOtp> findByOtp(String otp);



}
