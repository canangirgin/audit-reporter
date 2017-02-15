package com.reengen.utils.auditreporter;

import com.reengen.utils.auditreporter.file.FileUtil;
import com.reengen.utils.auditreporter.report.ReportFactory;
import com.reengen.utils.auditreporter.report.ReportService;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Canan Girgin on 14.02.2017.
 */
public class Client {
    public static final Properties properties = new Properties();
    private final static String propFileName = "config.properties";
    private static int topN = 0;
    private static String fileType = "txt";

    public static void main(final String[] args) throws IOException, ParseException {
        loadClientProperties();

        final Options options = new Options();
        options.addOption("c", false, "CSV format"); // does not have a value
        options.addOption("topN", true, "Top N Record"); // has a value
        parseArgs(args, options);
        final ReportService reportService = ReportFactory.getReportService(topN);
        final String userFilePath = args[0];
        final String filesFilePath = args[1];
        reportService.loadData(userFilePath, filesFilePath);
        reportService.generateReport(fileType);
    }

    private static void loadClientProperties() throws IOException {
        FileUtil.getProperties(propFileName);
    }

    private static void parseArgs(final String[] args, final Options options) throws ParseException {
        final CommandLineParser parser = new PosixParser();
        final CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("c")) {
            fileType = "csv";
        }
        if (cmd.hasOption("topN")) {
            try {
                topN = Integer.parseInt(cmd.getOptionValue("topN"));
            } catch (final NumberFormatException e) {
                System.out.println("topN parameter must have a number");
                System.exit(0);
            }
        }
    }

}

//TODO Multi Language Support
// TODO topN
// TODO Unit Test Methods
// TODO pom.xml build support
// TODO REad.me file refactoring
// TODO Check file not found exception

