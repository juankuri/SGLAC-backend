package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.OTPCode;
import org.uv.SGLAC.entities.User;

import java.util.List;

public interface OTPCodeService {

    OTPCode generateOTP(User user);

    OTPCode getOTPById(Long id);

    List<OTPCode> getAllOTPs();

    boolean validateOTP(User user, String code);
 
    OTPCode markAsUsed(Long id);

    void deleteOTP(Long id);
}
