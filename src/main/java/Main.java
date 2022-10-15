import com.nbd.service.api.client.Child;
import com.nbd.service.api.client.Client;

public class Main {
    public static void main(String[] args) {
        Client childClient = new Client("Szymon", "Zakrzewski", "0123", 21, new Child());
        System.out.println(childClient.getClientType().getPenalty());
    }
}
