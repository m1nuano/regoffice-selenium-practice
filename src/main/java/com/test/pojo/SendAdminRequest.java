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
public class SendAdminRequest {
    @JsonProperty("personalLastName")
    private String personalLastName;
    @JsonProperty("personalFirstName")
    private String personalFirstName;
    @JsonProperty("personalMiddleName")
    private String personalMiddleName;
    @JsonProperty("personalPhoneNumber")
    private String personalPhoneNumber;
    @JsonProperty("personalNumberOfPassport")
    private String personalNumberOfPassport;
    @JsonProperty("dateofbirth")
    private String dateOfBirth;
}

