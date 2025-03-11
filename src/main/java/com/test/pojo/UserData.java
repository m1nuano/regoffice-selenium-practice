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
public class UserData {
    @JsonProperty("applicantid")
    private int applicantId;
    @JsonProperty("citizenid")
    private int citizenId;
    @JsonProperty("applicationid")
    private int applicationid;
    @JsonProperty("birthcertificateid")
    private Integer birthCertificateId;
    @JsonProperty("deathcertificateid")
    private Integer deathCertificateId;
    @JsonProperty("merrigecertificateid")
    private Integer merrigeCertificateId;
}
