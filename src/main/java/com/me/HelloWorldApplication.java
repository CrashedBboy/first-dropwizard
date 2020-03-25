package com.me;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.me.resources.HelloWorldResource;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "HelloWorld";
    }

    /**
     * An initialize method is used to configure aspects of the application required before the application is run,
     * like bundles, configuration source providers, etc.
     */
    @Override
    public void initialize(final Bootstrap<HelloWorldConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final HelloWorldConfiguration configuration,
                    final Environment environment) {
        
        final HelloWorldResource resource = new HelloWorldResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );

        /**
         * When our application starts, we create a new instance of our resource class 
         * with the parameters from the configuration file and hand it off to the Environment, 
         * which acts like a registry of all the things your application can do.
         */
        environment.jersey().register(resource);

        /**
         * A Dropwizard application can contain many resource classes, each corresponding to its own URI pattern.
         * Just add another @Path-annotated resource class and call register with an instance of the new class.
         */
    }

}
