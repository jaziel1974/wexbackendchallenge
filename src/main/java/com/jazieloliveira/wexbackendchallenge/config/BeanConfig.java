package com.jazieloliveira.wexbackendchallenge.config;

import com.jazieloliveira.wexbackendchallenge.repository.TransactionRepository;
import com.jazieloliveira.wexbackendchallenge.service.quote.ExchangeService;
import com.jazieloliveira.wexbackendchallenge.service.quote.QuoteServiceClient;
import com.jazieloliveira.wexbackendchallenge.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class BeanConfig {
    @Value("${quotes.base-url}")
    private String baseUrl;

    @Bean
    TransactionService transactionService(TransactionRepository transactionRepository) {
        return new TransactionService(transactionRepository);
    }

    @Bean
    ExchangeService exchangeService(QuoteServiceClient quoteServiceClient) {
        return new ExchangeService(quoteServiceClient);
    }

    @Bean
    QuoteServiceClient quoteServiceClient(RestClient.Builder restClientBuilder){
        RestClient restClient = restClientBuilder
                .baseUrl(baseUrl)
                .build();

        HttpServiceProxyFactory clientFactory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
        return clientFactory.createClient(QuoteServiceClient.class);
    }
}