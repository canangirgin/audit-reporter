package com.reengen.utils.auditreporter.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Canan Girgin on 15.02.2017.
 */
public class TestUserInfo {
    @Test
    public void fieldCanbeAssigned() {
        final String userId = "id-1";
        final String userName = "userNameUser1";
        final String id = "id-1";
        final String name = "testFilename.abc";
        final int size = 56335262;
        final FileInfo fileInfo = new FileInfo(id, name, size);

        final UserInfo userInfo = new UserInfo(userId, userName);
        userInfo.addFile(fileInfo);
        Assert.assertNotNull(userInfo);
        Assert.assertEquals(userInfo.getUserId(), userId);
        Assert.assertEquals(userInfo.getUserName(), userName);
        Assert.assertTrue(userInfo.getFiles().contains(fileInfo));
    }

    @Test
    public void userInfoNullIfDidntAdd() {
        final String userId = "id-1";
        final String userName = "userNameUser1";

        final UserInfo userInfo = new UserInfo(userId, userName);
        Assert.assertNotNull(userInfo);
        Assert.assertEquals(userInfo.getUserId(), userId);
        Assert.assertEquals(userInfo.getUserName(), userName);
        Assert.assertNull(userInfo.getFiles());
    }
}
