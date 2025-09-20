package subscriber;

import publisher.Message;

import java.util.regex.Pattern;

public class RegexSubscriber implements Subscriber{
    private final Pattern pattern;
    private final long id = System.currentTimeMillis(); // Unique ID based on current time

    public RegexSubscriber(String regex){
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean matches(Message message) {
        return message!=null && pattern.matcher(message.getType()).matches();
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Received message: " + message + " in subscriber.RegexSubscriber with ID: " + id);
//        throw new RuntimeException("Testing exception handling in subscriber.RegexSubscriber");
    }
}
