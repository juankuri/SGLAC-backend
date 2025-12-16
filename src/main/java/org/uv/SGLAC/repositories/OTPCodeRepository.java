package org.uv.SGLAC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.OTPCode;
import org.uv.SGLAC.entities.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OTPCodeRepository extends JpaRepository<OTPCode,Long>{
  Optional<OTPCode> findByUserId(Long userId);
  Optional<OTPCode> findByCodeAndUserId(String code, Long userId);
  Optional<OTPCode> findFirstByUserAndCodeAndUsedFalseAndExpDateAfter(User user, String code, LocalDateTime now);
}