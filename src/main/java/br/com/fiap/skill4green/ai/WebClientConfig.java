package br.com.fiap.skill4green.ai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(AiPythonProperties.class)
public class WebClientConfig {

    @Bean
    public WebClient pythonWebClient(AiPythonProperties props) {
        ExchangeStrategies strategies = ExchangeStrategies.builder()
            .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
            .build();

        return WebClient.builder()
            .baseUrl(props.getBaseUrl())
            .exchangeStrategies(strategies)
            .build();
    }
}
