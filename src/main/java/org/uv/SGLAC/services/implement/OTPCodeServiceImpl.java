package org.uv.SGLAC.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.SGLAC.entities.OTPCode;
import org.uv.SGLAC.repositories.OTPCodeRepository;
import org.uv.SGLAC.services.OTPCodeService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OTPCodeServiceImpl implements OTPCodeService {

    @Autowired
    private OTPCodeRepository otpCodeRepository;

    @Override
    public OTPCode generateOTP(OTPCode otpCode) {
    
        if (otpCode.getUser() == null) {
            throw new RuntimeException("El OTP debe estar asociado a un usuario");
        }
        // Configurar fecha de creación y expiración si no vienen
        if (otpCode.getCreation_date() == null) {
            otpCode.setCreation_date(LocalDateTime.now());
        }
        if (otpCode.getExp_date() == null) {
            //5 min de validez
            otpCode.setExp_date(LocalDateTime.now().plusMinutes(5));
        }
        otpCode.setUsed(false);
        return otpCodeRepository.save(otpCode);
    }

    @Override
    public OTPCode getOTPById(Long id) {
        return otpCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OTP no encontrado"));
    }

    @Override
    public List<OTPCode> getAllOTPs() {
        return otpCodeRepository.findAll();
    }

    @Override
    public OTPCode markAsUsed(Long id) {
        OTPCode otp = otpCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OTP no encontrado"));

        if (otp.isUsed()) {
            throw new RuntimeException("este OTP ya fue utilizado");
        }

        if (otp.getExp_date() != null && otp.getExp_date().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expirado");
        }

        otp.setUsed(true);
        return otpCodeRepository.save(otp);
    }

    @Override
    public void deleteOTP(Long id) {
        OTPCode otp = otpCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OTP no encontrado"));
        otpCodeRepository.delete(otp);
    }
}
