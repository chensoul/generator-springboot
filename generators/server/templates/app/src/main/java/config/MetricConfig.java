package <%=packageName%>.config;

import <%=packageName%>.util.AggravateMetricsEndpoint;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsEndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@AutoConfigureAfter(MetricsEndpointAutoConfiguration.class)
public class MetricConfig {
    @Bean
    @ConditionalOnAvailableEndpoint
    public AggravateMetricsEndpoint aggravateMetricsEndpoint(MeterRegistry meterRegistry) {
        log.info("Initializing AggravateMetricsEndpoint");

        return new AggravateMetricsEndpoint(meterRegistry);
    }

    @Bean
    TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
