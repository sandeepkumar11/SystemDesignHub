package provides.impl;

import provides.Provider;

public class SmsProvider implements Provider {
    @Override
    public void send(String recipient, String subject, String content) {
        System.out.println("Sending SMS to " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
        // Here would be the actual SMS sending logic
    }
}
