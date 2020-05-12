package com.ruoyi.system.domain;

import java.util.List;
import java.util.Map;

public class ImportExcelResult {

    private Integer successNum;
    private List<Map<String,String>> details;

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public List<Map<String, String>> getDetails() {
        return details;
    }

    public void setDetails(List<Map<String, String>> details) {
        this.details = details;
    }

    public ImportExcelResult(Integer successNum, List<Map<String, String>> details) {
        this.successNum = successNum;
        this.details = details;
    }
}
