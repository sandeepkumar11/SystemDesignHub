package request;

public class Request {
    private final String clientId;

    public Request(String clientId){
        this.clientId = clientId;
    }

    public String getClientId(){
        return this.clientId;
    }

}
