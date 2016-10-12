package com.exercise.test.resources;

/**
 * Created by SYSDEVA5 on 11/10/2016.
 */
import com.exercise.test.entity.ImageContainer;
import com.exercise.test.entity.ImageEntity;
import com.google.common.base.Optional;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@Path("/photo-album")
@Produces(MediaType.APPLICATION_JSON)
public class PhotoResource {

    private Client client;

    public PhotoResource(Client client) {
        this.client = client;
    }

    @GET
    public List<ImageEntity> getImages(@QueryParam("name") Optional<String> name) {

        WebTarget webTarget =
                client.target("https://api.flickr.com/services/feeds/photos_public.gne?format=json&tags=java&nojsoncallback=?");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        String jsonAsString = response.readEntity(String.class);
        Gson gson = new Gson();
        ImageContainer imageContainer = gson.fromJson( jsonAsString, ImageContainer.class );

        /*System.out.println("done......."+imageContainer.getItems().get(0).getTags());
        System.out.println("done......."+imageContainer.getLink());*/

        System.out.println("Media is >>>>>>>>>>>>>>>>>>>>>> ......."+imageContainer.getItems().get(0).getMedia().getM());

        return imageContainer.getItems();
    }
}
