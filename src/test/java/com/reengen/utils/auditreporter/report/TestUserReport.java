package com.reengen.utils.auditreporter.report;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Canan Girgin on 15.02.2017.
 */
public class TestUserReport {
    @Test
    public void testGenerateReportCsv() throws IOException {
        final int topNValueForUserReport = 0;
        final String outputString = generateReport("csv", topNValueForUserReport);

        Assert.assertNotNull(outputString);
        Assert.assertTrue(!outputString.isEmpty());

        final String expectedOutput = "    jpublic,audit.xlsx,1,638,232\n" +
                "    jpublic,movie.avi,734,003,200\n" +
                "    jpublic,marketing.txt,150,680\n" +
                "    atester,pic.jpg,5,372,274\n" +
                "    atester,holiday.docx,570,110\n";

        Assert.assertEquals(outputString, expectedOutput);
    }

    @Test
    public void testGenerateReportTxt() throws IOException {
        final int topNValueForUserReport = 0;
        final String outputString = generateReport("txt", topNValueForUserReport);


        Assert.assertNotNull(outputString);
        Assert.assertTrue(!outputString.isEmpty());

        final String expectedOutput = "=======================\n" +
                "===  Audit Report  ====\n" +
                "=======================\n" +
                "## User: jpublic\n" +
                "    * audit.xlsx ==> 1,638,232 bytes\n" +
                "    * movie.avi ==> 734,003,200 bytes\n" +
                "    * marketing.txt ==> 150,680 bytes\n" +
                "## User: atester\n" +
                "    * pic.jpg ==> 5,372,274 bytes\n" +
                "    * holiday.docx ==> 570,110 bytes\n";

        Assert.assertEquals(outputString, expectedOutput);
    }

    private String generateReport(final String fileType, final int topnValue) throws IOException {
        final ReportService userReportService = ReportFactory.getReportService(topnValue);
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final String userFileName = classloader.getResource("users.csv").getPath();
        final String userFilesFileName = classloader.getResource("files.csv").getPath();
        userReportService.loadData(userFileName, userFilesFileName);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        userReportService.generateReport(fileType, outputStreamWriter);
        return byteArrayOutputStream.toString();
    }

}