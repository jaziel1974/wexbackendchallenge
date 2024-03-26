package com.jazieloliveira.wexbackendchallenge.service.quote.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class QuoteResponse implements Serializable {
    private List<Map<String, Object>> data;

}