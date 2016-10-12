package com.exercise.test;

import com.exercise.test.filter.CorsFilter;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
//import org.glassfish.jersey.client.JerseyClientBuilder;

import io.dropwizard.client.JerseyClientBuilder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import io.dropwizard.setup.Environment;
import com.exercise.test.resources.PhotoResource;
import com.exercise.test.health.PhotoHealthCheck;
import sun.net.www.http.HttpClient;

import javax.ws.rs.client.Client;

/**
 * Created by SYSDEVA5 on 11/10/2016.
 */
public class PhotoServiceApplication extends Application<PhotoConfiguration> {
    public static void main(String[] args) throws Exception {
        new PhotoServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "photo-service";
    }

    @Override
    public void initialize(Bootstrap<PhotoConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(PhotoConfiguration configuration, Environment environment) {

        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();

        final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration())
                .build(getName());
        environment.jersey().register(new PhotoResource(client));

        CorsFilter.insecure(environment);
    }
}
