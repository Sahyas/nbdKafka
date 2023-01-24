import com.nbd.repository.kafka.Consumers;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Consumers consumers = new Consumers();
        consumers.initConsumers();
        consumers.consumeTopicsByGroup(10000000);
    }
}
