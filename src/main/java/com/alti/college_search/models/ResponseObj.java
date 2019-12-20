package com.alti.college_search.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObj implements Serializable {
    private boolean success = true;
    private Metadata metadata;
    private List<School> schools;

}
