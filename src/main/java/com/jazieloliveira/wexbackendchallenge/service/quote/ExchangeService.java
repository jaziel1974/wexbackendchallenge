package com.jazieloliveira.wexbackendchallenge.service.quote;

import com.jazieloliveira.wexbackendchallenge.service.exceptions.ExchangeException;
import com.jazieloliveira.wexbackendchallenge.service.quote.dto.ConvertedTransactionRequestDto;
import com.jazieloliveira.wexbackendchallenge.service.quote.dto.ConvertedTransactionResponseDto;
import com.jazieloliveira.wexbackendchallenge.service.quote.dto.QuoteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class ExchangeService {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    QuoteServiceClient quoteServiceClient;

    public ExchangeService(QuoteServiceClient quoteServiceClient) {
        this.quoteServiceClient = quoteServiceClient;
    }

    public ConvertedTransactionResponseDto getRate(ConvertedTransactionRequestDto transaction, String country, String currency) {
        String filter = FILTER_CURRENCY_PATTERN.replace("$cur", currency);
        filter += "," + FILTER_COUNTRY_PATTERN.replace("$country", country);
        QuoteResponse quoteResponse = quoteServiceClient.quote(FIELDS, filter, SORT, 1, 1);

        if (CollectionUtils.isEmpty(quoteResponse.getData())) {
            logger.error("No exchange rate found for currency: " + currency);
            throw new ExchangeException("No exchange rate found for currency: " + currency);
        }

        Map<String, Object> quote = quoteResponse.getData().get(0);
        LocalDate rateDate = LocalDate.parse((String) quote.get("record_date"));
        if (rateDate.isBefore(LocalDate.now().minusMonths(6))) {
            logger.error("Exchange rate is too old for currency: " + currency);
            throw new ExchangeException("Exchange rate is too old for currency: " + currency);
        }

        BigDecimal exchangeRate = new BigDecimal((String) quote.get("exchange_rate"));
        BigDecimal convertedAmount = transaction.purchaseAmount().multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);

        return new ConvertedTransactionResponseDto(
                transaction.id(),
                transaction.description(),
                transaction.transactionDate(),
                transaction.purchaseAmount(),
                exchangeRate,
                convertedAmount
        );
    }

    private static final String FIELDS = "record_date,country,currency,country_currency_desc,exchange_rate";
    private static final String SORT = "-record_date";
    private static final String FILTER_COUNTRY_PATTERN = "country:eq:$country";
    private static final String FILTER_CURRENCY_PATTERN = "currency:eq:$cur";
}