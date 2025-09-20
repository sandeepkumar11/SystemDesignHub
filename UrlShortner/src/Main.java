import controller.UrlController;

public class Main {
    public static void main(String[] args) {
        UrlController controller = new UrlController();

        String url = "https://www.instahyre.com/candidate/opportunities/?matching=true";

        String shortUrl = controller.getShortURL(url);
        System.out.println("Short URL mapped to " + url + " : " + shortUrl);

        String longUrl = controller.getLongUrl(shortUrl);
        System.out.println("Long URL mapped to " + shortUrl + " : " + longUrl);

        // Check to create short url for existing Long url
        System.out.println(controller.getShortURL(url));
    }
}