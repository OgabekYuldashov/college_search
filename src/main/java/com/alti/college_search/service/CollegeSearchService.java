package com.alti.college_search.service;

import com.alti.college_search.models.Metadata;
import com.alti.college_search.models.ResponseObj;
import com.alti.college_search.models.School;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollegeSearchService {

    @Value("${schools.api.url}")
    String baseUrl;

    @Value("${schools.api.key}")
    String apiKey;

    public ResponseObj getFilteredSchools(String zip, Integer year, Integer distance, Integer page, String predominantDegrees) {
        ResponseObj responseObj = new ResponseObj();

        RestTemplate restTemplate = new RestTemplate();

        String finalUrl = baseUrl
                + "?api_key=" + apiKey
                + "&fields=id,school.name," + year + ".student.size"
                + "&zip=" + zip;

        if(distance != null){
            finalUrl += "&distance=" + distance + "mi";
        }
        if(page != null){
            finalUrl += "&page=" + page;
        }

        if(predominantDegrees != null){
            finalUrl += "&school.degrees_awarded.predominant=" + predominantDegrees;
        }

//        System.out.println(finalUrl);

        String jsonEncoded = restTemplate.getForObject(finalUrl, String.class);

        try {
            JSONObject jsonObject = new JSONObject(jsonEncoded);

            // Create Metadata
            JSONObject metaJson = jsonObject.getJSONObject("metadata");
            Metadata metadata = new Metadata();
            metadata.setPage(metaJson.getInt("page"));
            metadata.setPer_page(metaJson.getInt("per_page"));
            metadata.setTotal(metaJson.getInt("total"));

            responseObj.setMetadata(metadata);

            // Create the School list
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            List<School> schools = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject innerObj = jsonArray.getJSONObject(i);
                School s = new School();
                s.setName(innerObj.getString("school.name"));
                s.setId(innerObj.getLong("id"));
                s.setStudentSize(innerObj.getInt(year+".student.size"));

                schools.add(s);
            }

            schools = schools.stream().sorted(Comparator.comparing(School::getStudentSize).reversed()).collect(Collectors.toList());

            responseObj.setSchools(schools);
        }catch (Exception e){
//            TODO:Improve exception handling
            System.out.println(e.getMessage());
            responseObj.setSuccess(false);
        }


        return responseObj;
    }

}
