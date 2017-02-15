package com.reengen.utils.auditreporter.report;

import com.reengen.utils.auditreporter.file.FileStrategy;
import com.reengen.utils.auditreporter.file.FileStrategyFactory;
import com.reengen.utils.auditreporter.file.FileUtil;
import com.reengen.utils.auditreporter.model.FileInfo;
import com.reengen.utils.auditreporter.model.UserInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public abstract class ReportService {

    final HashMap<String, UserInfo> userFileList = new HashMap<>();

    // generates report
    public void generateReport(final String fileType) {
        final FileStrategy fileStrategy = FileStrategyFactory.getFileStrategy(fileType);
        prepareReport(fileStrategy);
    }

    //loads data from file
    public void loadData(final String userFn, final String filesFn) throws IOException {
        // Read users:
        getUserData(userFn);
        //Read user Files:
        addFileData(filesFn);
        //FileUtil.readFile(filesFn);
    }

    //adds files data for reporting
    private void addFileData(final String filesFn) throws IOException {
        BufferedReader reader = null;
        String line;
        try {
            reader = FileUtil.readFileBR(filesFn);
            while ((line = reader.readLine()) != null) {
                final String[] fileData = line.split(",");
                final FileInfo fileInfo = new FileInfo(fileData[0], fileData[2], Integer.parseInt(fileData[1]));
                final String userId = fileData[3];
                final UserInfo userInfo = this.userFileList.get(userId);
                addFiles(userInfo, fileInfo);
            }
        } catch (final IOException e) {
            throw new FileNotFoundException("audit file '" + filesFn + "' not found in the classpath");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    //gets user data from file and creates a map
    private void getUserData(final String userFn) throws IOException {
        BufferedReader reader = null;
        String line;
        try {
            reader = FileUtil.readFileBR(userFn);
            while ((line = reader.readLine()) != null) {
                final String[] userData = line.split(",");
                final UserInfo userReport = new UserInfo(userData[0], userData[1]);
                this.userFileList.put(userData[0], userReport);
            }
        } catch (final IOException e) {
            throw new FileNotFoundException("user file '" + userFn + "' not found in the classpath");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    // prepares report
    private void prepareReport(final FileStrategy fileStrategy) {
        final Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setClassForTemplateLoading(this.getClass(), "/en/templates/");
        try {
            final Template tpl = cfg.getTemplate(fileStrategy.getTemplate() + getTemplateSuffix() + ".ftl");
            final Map<String, Object> data = getReportData();
            final OutputStreamWriter output = new OutputStreamWriter(System.out);
            tpl.process(data, output);
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final TemplateException e) {
            e.printStackTrace();
        }
    }

    //gets report tempate suffix.
    protected abstract String getTemplateSuffix();

    //add files with using user requirements
    protected abstract void addFiles(UserInfo userInfo, FileInfo fileInfo);

    //add report contents
    protected abstract Map<String, Object> getReportData();

}
