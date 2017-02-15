package com.reengen.utils.auditreporter.file;


// File Strategy for Txt files
public class Txt implements FileStrategy {
    @Override
    public String getTemplate() {
        return "ReportTxt";
    }
}
