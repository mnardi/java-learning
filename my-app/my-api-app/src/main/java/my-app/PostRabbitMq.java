import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

class PostRabbitMq implements Post {
    private String hostAddress;
    private String queueName;
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    PostRabbitMq(String host, String queue){
        this.hostAddress = host;
        this.queueName = queue;
    }

    @Override
    public boolean Initialize() throws Exception {

        factory = new ConnectionFactory();
        factory.setHost(this.hostAddress);
        try{
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            return true;
        }
        catch(Exception e)  {
            System.out.println("Error to Initialize PostRabbitMQ: " + e.toString());
            return false;
        }
    }

    public boolean isInitialized() {
        return channel.isOpen();
    }

    @Override
    public boolean PostMessage(String message) throws Exception{
        try{

            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            return true;
        }
        catch(Exception e)  {
            System.out.println("Error to Post Message to RabbitMQ: " + e.getMessage());
            return false;
        }
    }
}