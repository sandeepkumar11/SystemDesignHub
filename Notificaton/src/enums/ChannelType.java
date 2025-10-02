package enums;

public enum ChannelType {
    EMAIL("Email"),
    SMS("SMS"),
    PUSH("Push");

    private final String channelName;

    ChannelType(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }
}
