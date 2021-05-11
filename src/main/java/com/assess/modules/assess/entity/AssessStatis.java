package com.assess.modules.assess.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AssessStatis implements Serializable {

    private Long id;

    private Long assessId;
    private String assessTitle;
    private Long deptId;
    private String deptName;
    private Integer approvalScoreSum;
    private Float approvalScoreAvg;
    private Integer scoreSum;
    private Float scoreAvg;


}