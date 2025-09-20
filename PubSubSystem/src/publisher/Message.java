package publisher;

public class Message {
    private final int id;
    private final String type;
    private final String content; // We can stringify the content for simplicity

    public Message(int id, String type, String content) {
        this.id = id;
        this.type = type;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "publisher.Message{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
