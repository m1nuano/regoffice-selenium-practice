package com.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationData {
    private Integer applicationid;
    private Integer citizenid;
    private Integer applicantid;
    private Integer staffid;
    private String dateofapplication;
    private String kindofapplication;
    private String statusofapplication;
    private String channel;
    private String image;
}
