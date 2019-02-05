package config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

public class CircuitBreakerConfiguration {

    public CircuitBreakerConfiguration() {
    }

    public static final int CIRCUIT_BREAKER_BUFFER_SIZE = 4;

    public static CircuitBreaker createCircuitBreaker(){
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(20)
                .ringBufferSizeInClosedState(CIRCUIT_BREAKER_BUFFER_SIZE)
                .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        return registry.circuitBreaker("circuit-braker");
    }
}
