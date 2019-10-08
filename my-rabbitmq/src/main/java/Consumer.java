import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable , false, false, null);
        channel.basicQos(1); //accept only one unack-ed message at a time

        System.out.println(" [*] Waiting for messages. to exit process CTRL+C");

        DeliverCallback deliveryCallback = (consumerTag, delivery) -> {
          String message = new String(delivery.getBody(), "UTF-8");
          System.out.println(" [x] Received '" + message + "'");

          try {
              doWork(message);
          } catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
              System.out.println(" [x] Done");
              channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
          }
        };

        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, deliveryCallback, consumerTag -> { });

    }

    private static void doWork(String task) throws InterruptedException {
        for(char ch:task.toCharArray()) {
            if(ch == '.') Thread.sleep(10000);
        }
    }
}
