package com.reengen.utils.auditreporter.report;

import com.reengen.utils.auditreporter.model.FileInfo;
import com.reengen.utils.auditreporter.model.UserInfo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Canan Girgin on 14.02.2017.
 */
// Prepares topN (size) files report
public class ReportTopN extends ReportService {

    private final TreeSet<FileInfo> fileTree = new TreeSet<>(new Comparator<FileInfo>() {
        @Override
        public int compare(final FileInfo file, final FileInfo fileOther) {
            if (file.getSize() > fileOther.getSize())
                return -1;
            else
                return 1;
        }
    });
    private int topN = 0;

    public ReportTopN(final int topN) {
        this.topN = topN;
    }

    @Override
    protected String getTemplateSuffix() {
        return "TopN";
    }

    @Override
    public void addFiles(final UserInfo userInfo, final FileInfo fileInfo) {
        fileInfo.setUserInfo(userInfo);
        addTopNFile(this.topN, fileInfo);
    }

    @Override
    protected Map<String, Object> getReportData() {
        final Map<String, Object> data = new HashMap<>();
        data.put("topNFile", this.fileTree);
        data.put("message", "Top #" + this.topN + " Report");
        return data;
    }

    private void addTopNFile(final int topN, final FileInfo fileInfo) {
        this.fileTree.add(fileInfo);
        if (this.fileTree.size() > topN)
            this.fileTree.pollLast();

    }
}
