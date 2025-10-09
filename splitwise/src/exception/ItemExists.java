package exception;

public class ItemExists extends RuntimeException{
    public ItemExists(String message) {
        super(message);
    }

    public ItemExists(){
        super("Item already exists");
    }
}
