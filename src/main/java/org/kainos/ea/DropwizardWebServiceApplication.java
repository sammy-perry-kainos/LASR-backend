package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.resources.*;

public class DropwizardWebServiceApplication extends Application<DropwizardWebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardWebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardWebService";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardWebServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropwizardWebServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardWebServiceConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final DropwizardWebServiceConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new DeliveryController());
        // TODO: implement application
        environment.jersey().register(new SalesEmployeeController());
        environment.jersey().register(new ProjectController());
        environment.jersey().register(new DeliveryController());
        environment.jersey().register(new ClientController());
        environment.jersey().register(new DeliveryEmployeeProjectController());
    }

}
