package com.jazieloliveira.wexbackendchallenge.controller;

import com.jazieloliveira.wexbackendchallenge.service.quote.QuoteServiceClient;
import com.jazieloliveira.wexbackendchallenge.service.quote.dto.QuoteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quote")
public class QuoteController {
    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

    private final QuoteServiceClient quoteServiceClient;

    public QuoteController(QuoteServiceClient quoteServiceClient) {
        this.quoteServiceClient = quoteServiceClient;
    }

    @GetMapping()
    //test
    QuoteResponse getQuote() {
        return quoteServiceClient.quote("record_date,country,currency,country_currency_desc,exchange_rate", "currency:eq:Lek", "-record_date", 1, 1);
    }
}