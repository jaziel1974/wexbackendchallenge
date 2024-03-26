package com.jazieloliveira.wexbackendchallenge.service.quote;

import com.jazieloliveira.wexbackendchallenge.service.quote.dto.QuoteResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/rates_of_exchange")
@Cacheable("rates-of-exchange")
public interface QuoteServiceClient {
    @GetExchange()
    QuoteResponse quote(@RequestParam("fields") String fields,
                        @RequestParam("filter") String filter,
                        @RequestParam("sort") String sort,
                        @RequestParam("page[number]") Integer pageNumber,
                        @RequestParam("page[size]") Integer pageSize
    );
}