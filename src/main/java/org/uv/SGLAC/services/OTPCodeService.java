package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.OTPCode;
import java.util.List;

public interface OTPCodeService {

    OTPCode generateOTP(OTPCode otpCode);

    OTPCode getOTPById(Long id);

    List<OTPCode> getAllOTPs();

    OTPCode markAsUsed(Long id);

    void deleteOTP(Long id);
}
