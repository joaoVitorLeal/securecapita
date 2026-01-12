package io.github.joaovitorleal.securecapita.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("smsService")
public class SmsService implements NotificationService {

    @Value("${FROM_NUMBER}")
    private String fromNumber;

    @Value("${TWILIO_ACCOUNT_SID}")
    private String twilioAccountSid;

    @Value("${TWILIO_AUTH_TOKEN}")
    private String twilioAuthToken;

    /**
     * @param to Phone number recipient
     * @param message body of the message
     */
    public void sendMessage(String to, String message) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        Message smsMessage = Message.creator(
                new PhoneNumber("+55" + to),
                new PhoneNumber("+55" + fromNumber),
                message
        ).create();
    }
}
