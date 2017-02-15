package com.reengen.utils.auditreporter.file;

// File Strategy for CSV files
public class Csv implements FileStrategy {
    @Override
    public String getTemplate() {
        return "ReportCsv";
    }
}
