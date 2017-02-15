package com.reengen.utils.auditreporter.file;

/**
 * Created by Canan Girgin on 14.02.2017.
 */
// File Strategy for Txt files
public class Txt implements FileStrategy {
    @Override
    public String getTemplate() {
        return "ReportTxt";
    }
}
