package com.test.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetApplicationsResponse {
    @JsonProperty("total")
    private int total;
    @JsonProperty("data")
    private List<ApplicationData> data;
    @JsonProperty("requestId")
    private String requestId;
}
