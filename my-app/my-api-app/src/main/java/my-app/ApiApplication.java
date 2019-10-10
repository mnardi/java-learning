import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.google.inject.Injector;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;

public class ApiApplication extends Application<ApiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ApiApplication().run(args);
    }

    @Override
    public String getName() {
        return "Api";
    }

    @Override
    public void initialize(final Bootstrap<ApiConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ApiConfiguration configuration,
                    final Environment environment) {

        /*
        Injector injector = Guice.createInjector(
                new AbstractModule()
                {
                    protected void configure() {
                        bind(Post.class).to(PostRabbitMq.class);
                    }
                });
        final BookResource book = injector.getInstance(BookResource.class);
        */

        final Post post = new PostRabbitMq("localhost", "library");

        try {
            post.Initialize();
            final BookResource book = new BookResource(post);
            environment.jersey().register(book);
            final MagazineResource magazine = new MagazineResource(post);
            environment.jersey().register(magazine);

            environment.healthChecks().register("message-queue", new MessageQueueHealthCheck(post));

        }
        catch(Exception e)
        {
            System.out.println("Error to Initialize Post Message");
        }


    }

}
