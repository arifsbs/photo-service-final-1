package com.exercise.test.health;

import com.codahale.metrics.health.HealthCheck;
//import com.example.helloworld.core.Template;

import java.util.Optional;
/**
 * Created by SYSDEVA5 on 11/10/2016.
 */
public class PhotoHealthCheck extends HealthCheck {
    /*private final Template template;

    public TemplateHealthCheck(Template template) {
        this.template = template;
    }
    */

    @Override
    protected Result check() throws Exception {
        /*template.render(Optional.of("woo"));
        template.render(Optional.empty());
        return Result.healthy();*/

        return null;
    }
}
