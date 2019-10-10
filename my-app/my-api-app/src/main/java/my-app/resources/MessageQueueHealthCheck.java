import com.codahale.metrics.health.HealthCheck;

public class MessageQueueHealthCheck extends HealthCheck {
    Post post;
    public  MessageQueueHealthCheck(Post post){
        this.post = post;
    }

    @Override
    protected Result check() throws Exception {
        if(post.isInitialized()) {
            return Result.healthy();
        }
        else {
            return Result.healthy("failed");
        }

    }

}