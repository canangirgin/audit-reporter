package com.reengen.utils.auditreporter.file;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Canan Girgin on 15.02.2017.
 */
public class TestFileStrategyFactory {
    @Test
    public void csvFileGetTest() {
        final FileStrategy fileStrategy = FileStrategyFactory.getFileStrategy("csv");
        Assert.assertTrue(fileStrategy instanceof Csv);
        Assert.assertTrue(fileStrategy.getTemplate().equals("ReportCsv"));
    }

    @Test
    public void txtFileGetTest() {
        final FileStrategy fileStrategy = FileStrategyFactory.getFileStrategy("txt");
        Assert.assertTrue(fileStrategy instanceof Txt);
        Assert.assertTrue(fileStrategy.getTemplate().equals("ReportTxt"));
    }
}
