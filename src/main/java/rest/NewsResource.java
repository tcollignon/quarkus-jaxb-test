package rest;

import mapper.process.UnmarshalRSSProcess;
import object.Category;
import object.INews;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

@Path("/getnews")
public class NewsResource {

    @Inject
    UnmarshalRSSProcess newsProcessor;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews() {
        try {
            final Collection<INews> results = new ArrayList<>();
            results.addAll(newsProcessor.retrieveLastNews(1, Category.DIVERS));
            return Response.ok(results).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.noContent().build();
    }
}