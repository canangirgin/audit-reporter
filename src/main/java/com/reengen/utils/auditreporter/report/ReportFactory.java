package com.reengen.utils.auditreporter.report;

/**
 * Created by Canan Girgin on 14.02.2017.
 */
//Creates different reportService.
// If you need to add new report type, create new class for report than create new instance in this class
public class ReportFactory {
    public static ReportService getReportService(final int topN) {
        if (topN > 0)
            return new ReportTopN(topN);
        else
            return new ReportUser();
    }
}
