package com.reengen.utils.auditreporter.file;

/**
 * Created by Canan Girgin on 14.02.2017.
 */
// File Strategy for CSV files
public class Csv implements FileStrategy {
    @Override
    public String getTemplate() {
        return "ReportCsv";
    }
}
