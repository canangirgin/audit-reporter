package com.reengen.utils.auditreporter.report;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Canan Girgin on 15.02.2017.
 */
public class TestTopNReport {
    @Test
    public void testGenerateReportCsv_Top2() throws IOException {
        final int topnValue = 2;
        final String fileType = "csv";
        final String outputString = generateReport(fileType, topnValue);

        Assert.assertNotNull(outputString);
        Assert.assertTrue(!outputString.isEmpty());

        final String expectedOutput = "movie.avi, jpublic, 734,003,200\n" +
                "pic.jpg, atester, 5,372,274\n";

        Assert.assertEquals(outputString, expectedOutput);
    }

    @Test
    public void testGenerateReportCsv_Top4() throws IOException {
        final int topnValue = 4;
        final String fileType = "csv";
        final String outputString = generateReport(fileType, topnValue);

        Assert.assertNotNull(outputString);
        Assert.assertTrue(!outputString.isEmpty());

        final String expectedOutput = "movie.avi, jpublic, 734,003,200\n" +
                "pic.jpg, atester, 5,372,274\n" +
                "audit.xlsx, jpublic, 1,638,232\n" +
                "holiday.docx, atester, 570,110\n";

        Assert.assertEquals(outputString, expectedOutput);
    }

    @Test
    public void testGenerateReportTxt_Top2() throws IOException {
        final int topnValue = 2;
        final String fileType = "txt";
        final String outputString = generateReport(fileType, topnValue);

        Assert.assertNotNull(outputString);
        Assert.assertTrue(!outputString.isEmpty());

        final String expectedOutput = "=======================\n" +
                "===   Top #2 Report  ====\n" +
                "=======================\n" +
                "* movie.avi ==> user jpublic, 734,003,200 bytes\n" +
                "* pic.jpg ==> user atester, 5,372,274 bytes\n";

        Assert.assertEquals(outputString, expectedOutput);
    }

    @Test
    public void testGenerateReportTxt_Top4() throws IOException {
        final int topnValue = 4;
        final String fileType = "txt";
        final String outputString = generateReport(fileType, topnValue);

        Assert.assertNotNull(outputString);
        Assert.assertTrue(!outputString.isEmpty());

        final String expectedOutput = "=======================\n" +
                "===   Top #4 Report  ====\n" +
                "=======================\n" +
                "* movie.avi ==> user jpublic, 734,003,200 bytes\n" +
                "* pic.jpg ==> user atester, 5,372,274 bytes\n" +
                "* audit.xlsx ==> user jpublic, 1,638,232 bytes\n" +
                "* holiday.docx ==> user atester, 570,110 bytes\n";

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