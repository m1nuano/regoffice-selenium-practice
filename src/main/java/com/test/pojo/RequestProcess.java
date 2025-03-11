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
public class RequestProcess {
    @JsonProperty("applId")
    private int applicationId;
    @JsonProperty("staffid")
    private int staffId;
    private String action;
}
