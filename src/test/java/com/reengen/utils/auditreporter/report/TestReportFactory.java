package com.reengen.utils.auditreporter.report;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Canan Girgin on 15.02.2017.
 */
public class TestReportFactory {

    @Test
    public void canBeProvideUserReportService() {
        final ReportService reportService = ReportFactory.getReportService(0);
        Assert.assertNotNull(reportService);
        Assert.assertTrue(reportService instanceof ReportUser);
    }

    @Test
    public void canBeProvideTopNReportService() {
        final int topNValueBiggerThanZero = 10;
        final ReportService reportService = ReportFactory.getReportService(topNValueBiggerThanZero);
        Assert.assertNotNull(reportService);
        Assert.assertTrue(reportService instanceof ReportTopN);
    }
}
