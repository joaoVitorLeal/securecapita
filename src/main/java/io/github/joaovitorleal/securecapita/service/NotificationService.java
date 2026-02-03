package io.github.joaovitorleal.securecapita.service;

public interface NotificationService {

    void sendMessage(String to, String message);
    void sendMfaCode(String userFirstName, String to, String mfaCode);
    void sendResetPasswordUrl(String userFirstName, String to, String verificationUrl);
}
