package de.jeha.kame.crawler.resources;


import com.codahale.metrics.annotation.Timed;
import de.jeha.kame.crawler.resources.dto.CrawlRequest;
import de.jeha.kame.crawler.resources.dto.CrawlResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/")
public class CrawlResource {

    @POST
    @Path("/crawl")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Object crawl(CrawlRequest request) {
        System.out.println(request.getUrl());

        return new CrawlResponse(UUID.randomUUID().toString());
    }

}