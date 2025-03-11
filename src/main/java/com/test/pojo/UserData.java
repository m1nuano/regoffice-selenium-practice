package com.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private int applicantid;
    private int citizenid;
    private int applicationid;
    private Integer birthcertificateid;
    private Integer deathcertificateid;
    private Integer merrigecertificateid;
}
