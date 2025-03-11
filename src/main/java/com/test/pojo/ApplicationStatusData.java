package com.test.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationStatusData {
    @JsonProperty("dateofapplication")
    private String dateOfApplication;
    @JsonProperty("kindofapplication")
    private String kindOfApplication;
    @JsonProperty("statusofapplication")
    private String statusOfApplication;
}
