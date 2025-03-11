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
public class ApplicationData {
    @JsonProperty("applicationid")
    private Integer applicationId;
    @JsonProperty("citizenid")
    private Integer citizenId;
    @JsonProperty("applicantid")
    private Integer applicantId;
    @JsonProperty("staffid")
    private Integer staffId;
    @JsonProperty("dateofapplication")
    private String dateOfApplication;
    @JsonProperty("kindofapplication")
    private String kindOfApplication;
    @JsonProperty("statusofapplication")
    private String statusOfApplication;
    private String channel;
    private String image;
}
