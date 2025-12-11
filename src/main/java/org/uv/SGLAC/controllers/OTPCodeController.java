package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.OTPCode;
import org.uv.SGLAC.services.OTPCodeService;
import java.util.List;

@RestController
@RequestMapping("/otps")
public class OTPCodeController {

    @Autowired
    private OTPCodeService otpCodeService;

    @PostMapping
    public OTPCode generateOTP(@RequestBody OTPCode otpCode) {
        return otpCodeService.generateOTP(otpCode);
    }

    @GetMapping("/{id}")
    public OTPCode getById(@PathVariable Long id) {
        return otpCodeService.getOTPById(id);
    }

    @GetMapping
    public List<OTPCode> getAll() {
        return otpCodeService.getAllOTPs();
    }

    @PutMapping("/{id}/mark-used")
    public OTPCode markAsUsed(@PathVariable Long id) {
        return otpCodeService.markAsUsed(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        otpCodeService.deleteOTP(id);
    }
}
