package org.uv.SGLAC.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.SGLAC.entities.OTPCode;
import org.uv.SGLAC.entities.User;
import org.uv.SGLAC.repositories.OTPCodeRepository;
import org.uv.SGLAC.services.MailService;
import org.uv.SGLAC.services.OTPCodeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OTPCodeServiceImpl implements OTPCodeService {

    @Autowired
    private OTPCodeRepository otpCodeRepository;

    @Autowired
    private final MailService mailService;

    public OTPCodeServiceImpl(OTPCodeRepository otpCodeRepository, MailService mailService) {
        this.otpCodeRepository = otpCodeRepository;
        this.mailService = mailService;
    }

    @Override
    public OTPCode generateOTP(User user) {
        String code = String.valueOf((int) (Math.random() * 900000) + 100000);

        OTPCode otp = new OTPCode();
        otp.setUser(user);
        otp.setCode(code);
        otp.setCreation_date(LocalDateTime.now());
        otp.setExp_date(LocalDateTime.now().plusMinutes(5));
        otp.setUsed(false);
        otp.setMachine("default");

        OTPCode saved = otpCodeRepository.save(otp);

        mailService.sendOTP(code, user.getEmail());
        return saved;
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

    @Override
    public boolean validateOTP(User user, String code) {
        Optional<OTPCode> otpOpt = otpCodeRepository
                .findFirstByUserAndCodeAndUsedFalseAndExpDateAfter(user, code, LocalDateTime.now());

        if (otpOpt.isPresent()) {
            OTPCode otp = otpOpt.get();
            otp.setUsed(true);
            otpCodeRepository.save(otp);
            return true;
        }

        return false;
    }
}
