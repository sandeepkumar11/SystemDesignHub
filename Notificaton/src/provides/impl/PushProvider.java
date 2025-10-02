package provides.impl;

import provides.Provider;

public class PushProvider implements Provider {
    @Override
    public void send(String recipient, String subject, String content) {
        System.out.println("Push Notification sent to " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
        // Here would be the actual push notification sending logic
    }
}
