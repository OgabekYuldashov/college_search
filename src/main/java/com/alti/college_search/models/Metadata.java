package com.alti.college_search.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {
    private Integer total = 0;
    private Integer page = 0;
    private Integer per_page = 0;
    private String lat = "";
    private String lng = "";

}
