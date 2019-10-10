import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.google.inject.Inject;


@Path("/magazine")
@Produces(MediaType.APPLICATION_JSON)
public class MagazineResource  {
    private final String responseTemplate;
    private final AtomicLong counterId;
    private final Post post;

    //@Inject
    public MagazineResource(Post post){
        this.responseTemplate = "Command executed '%s'";
        this.counterId = new AtomicLong();
        this.post=post;
    }

    @GET
    @Timed
    public Response queryMagazine(@QueryParam("title")String title){

        String cmdResp = String.format("magazine query >>> title:%s", title);
        try {
            this.post.PostMessage("{\"command\": \"select\", \"resource\": \"Magazine\", \"title\": \"" + title + "\"}");
        }
        catch (Exception e){}

        String response = String.format(responseTemplate,cmdResp);
        return new Response(counterId.incrementAndGet(), response);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertMagazine(InsertBook body){


        String cmdResp = String.format("magazine insert >>> title:%s, subject:%s", body.getSubject(), body.getTitle());
        try {
            this.post.PostMessage("{\"command\": \"insert\", \"resource\": \"Magazine\", \"title\": \"" + body.getTitle() + "\" , \"subject\": \"" + body.getSubject() + "\"}");
        }
        catch (Exception e){}
        String response = String.format(responseTemplate,cmdResp);
        return new Response(counterId.incrementAndGet(), response);
    }

}