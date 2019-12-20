package com.alti.college_search.service;

import com.alti.college_search.models.ResponseObj;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapsService {

    @Value("${maps.api.url}")
    String baseUrl;

    @Value("${schools.api.key}")
    String apiKey;

    public ResponseObj getLongLatByZip(String zip) {
        ResponseObj responseObj = new ResponseObj();

        RestTemplate restTemplate = new RestTemplate();

        return responseObj;
    }

}
