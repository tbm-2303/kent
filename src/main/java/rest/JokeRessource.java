package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JokeDTO;
import facades.FacadeExample;
import facades.JokeFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("joke")
public class JokeRessource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final JokeFacade FACADE = JokeFacade.getJokeFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getJoke() throws Exception {
        JokeDTO jokeDTO = GSON.fromJson(FACADE.fetchData("https://api.chucknorris.io/jokes/random"), JokeDTO.class);
        System.out.println(jokeDTO);
        return Response.ok().entity(GSON.toJson(jokeDTO)).build();
    }
}
