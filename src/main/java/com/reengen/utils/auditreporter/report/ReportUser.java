package com.reengen.utils.auditreporter.report;

import com.reengen.utils.auditreporter.model.FileInfo;
import com.reengen.utils.auditreporter.model.UserInfo;

import java.util.HashMap;
import java.util.Map;

// Prepares users files report
public class ReportUser extends ReportService {
    @Override
    public void addFiles(final UserInfo userInfo, final FileInfo fileInfo) {
        userInfo.addFile(fileInfo);
    }

    @Override
    protected Map<String, Object> getReportData() {
        final Map<String, Object> data = new HashMap<>();
        data.put("usersAudit", this.userFileList);
        data.put("message", "Audit Report");
        return data;
    }

    @Override
    protected String getTemplateSuffix() {
        return "User";
    }

}
