package com.reengen.utils.auditreporter.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Canan Girgin on 15.02.2017.
 */
public class TestFileInfo {
    @Test
    public void fieldCanbeAssigned() {
        final String id = "id-1";
        final String name = "testFilename.abc";
        final int size = 56335262;
        final FileInfo fileInfo = new FileInfo(id, name, size);
        Assert.assertNotNull(fileInfo);
        Assert.assertEquals(fileInfo.getId(), id);
        Assert.assertEquals(fileInfo.getName(), name);
        Assert.assertEquals(fileInfo.getSize(), size);
    }

    @Test(expected = Exception.class)
    public void exceptionIfUserInfoIsNull() {
        final String id = "id-1";
        final String name = "testFilename.abc";
        final int size = 56335262;
        final FileInfo fileInfo = new FileInfo(id, name, size);
        fileInfo.hashCode();
    }

    @Test
    public void noExceptionIfUserInfoNotNull() {
        final String id = "id-1";
        final String name = "testFilename.abc";
        final int size = 56335262;
        final FileInfo fileInfo = new FileInfo(id, name, size);
        final UserInfo userInfo = new UserInfo("user-x", "name-x");
        fileInfo.setUserInfo(userInfo);
        final int hashCode = fileInfo.hashCode();
        Assert.assertTrue(hashCode != 0);
    }


    @Test
    public void hashCodesMustBeEqualForSameValues() {
        final String id = "id-1";
        final String name = "testFilename.abc";
        final int size = 56335262;
        final FileInfo fileInfo1 = new FileInfo(id, name, size);
        final FileInfo fileInfo2 = new FileInfo(id, name, size);
        final UserInfo userInfo = new UserInfo("user-x", "name-x");
        fileInfo1.setUserInfo(userInfo);
        fileInfo2.setUserInfo(userInfo);
        Assert.assertEquals(fileInfo1.hashCode(), fileInfo2.hashCode());
        Assert.assertTrue(fileInfo1.equals(fileInfo2));

    }
}
