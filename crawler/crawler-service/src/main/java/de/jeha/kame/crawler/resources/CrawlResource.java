package de.jeha.kame.crawler.resources;


import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class CrawlResource {

    @POST
    @Path("/crawl")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Object crawl() {
        return new CrawlResponse();
    }

}