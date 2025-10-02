package provides.impl;

import provides.Provider;

public class EmailProvider implements Provider {

    @Override
    public void send(String recipient, String subject, String content) {
        System.out.println("Sending Email to " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
        // Here would be the actual email sending logic
    }
}
