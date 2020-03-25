package com.me.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {

    private final String template;

    public TemplateHealthCheck(String template) {

        this.template = template;
    }

    @Override
    protected Result check() throws Exception {

        /**
         * If the string is not a well-formed format string
         * (for example, someone accidentally put Hello, %s% in the configuration file),
         * then String.format(String, Object...) will throw an IllegalFormatException 
         * and the health check will implicitly fail.
         */

        final String saying = String.format(this.template, "TEST");

        /**
         * If the rendered saying doesn’t include the test string,
         * the health check will explicitly fail by returning an unhealthy Result.
         */
        if (!saying.contains("TEST")) {

            return Result.unhealthy("template doesn't include a name");
        }

        return Result.healthy();
    }
}