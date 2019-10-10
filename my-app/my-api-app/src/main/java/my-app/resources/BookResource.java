import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.google.inject.Inject;


@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource  {
    private final String responseTemplate;
    private final AtomicLong counterId;
    private final Post post;

    //@Inject
    public BookResource(Post post){
        this.responseTemplate = "Command executed '%s'";
        this.counterId = new AtomicLong();
        this.post=post;
    }

    @GET
    @Timed
    public Response queryBook(@QueryParam("title")String title){

        String cmdResp = String.format("book query >>> title:%s", title);
        try {
            this.post.PostMessage("{\"command\": \"select\", \"resource\": \"Book\", \"title\": \"" + title + "\"}");
        }
        catch (Exception e){}

        String response = String.format(responseTemplate,cmdResp);
        return new Response(counterId.incrementAndGet(), response);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertBook(InsertBook body){


        String cmdResp = String.format("book insert >>> title:%s, subject:%s", body.getSubject(), body.getTitle());
        try {
            this.post.PostMessage("{\"command\": \"insert\", \"resource\": \"Book\", \"title\": \"" + body.getTitle() + "\" , \"subject\": \"" + body.getSubject() + "\"}");
        }
        catch (Exception e){}
        String response = String.format(responseTemplate,cmdResp);
        return new Response(counterId.incrementAndGet(), response);
    }

}