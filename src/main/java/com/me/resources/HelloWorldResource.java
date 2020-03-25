package com.me.resources;

import com.me.api.Saying;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

/**
 * Resource classes are used by multiple threads concurrently.
 * In general, we recommend that resources be stateless/immutable, but it’s important to keep the context in mind.
 */

/**
 * at"Path("/hello-world") tells Jersey that this resource is accessible at the URI /hello-world.
 * at"Produces(MediaType.APPLICATION_JSON) lets Jersey’s content negotiation code know 
 * that this resource produces representations which are application/json
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource{

    private final String template;
    private final String defaultName;

    // An AtomicLong provides us with a cheap, thread-safe way of generating unique(ish) IDs
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {

        this.template = template;
        this.defaultName = defaultName;

        this.counter = new AtomicLong();
    }

    /**
     * at"Timed: Dropwizard automatically records the duration and rate of its invocations as a Metrics Timer
     */
    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {

        final String value = String.format(this.template, name.orElse(this.defaultName));

        return new Saying(counter.incrementAndGet(), value);
    }
}