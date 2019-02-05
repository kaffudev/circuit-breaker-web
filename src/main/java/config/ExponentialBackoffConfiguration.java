package config;

import com.google.api.client.util.ExponentialBackOff;

public class ExponentialBackoffConfiguration {

    public ExponentialBackoffConfiguration() {
    }

    public static ExponentialBackOff createExponentialBackOff(){
        ExponentialBackOff exponentialBackOff = new ExponentialBackOff.Builder()
                .setInitialIntervalMillis(500)
                .setMaxElapsedTimeMillis(900000)
                .setMaxIntervalMillis(6000)
                .setMultiplier(1.5)
                .setRandomizationFactor(0.5)
                .build();
        return exponentialBackOff;
    }
}
