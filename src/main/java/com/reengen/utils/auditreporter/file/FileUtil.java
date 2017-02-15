package com.reengen.utils.auditreporter.file;

import com.reengen.utils.auditreporter.Client;

import java.io.*;

/**
 * Created by Canan Girgin on 14.02.2017.
 */
// Implements common file operations
public class FileUtil {
    public static BufferedReader readFileStream(final String fileName) throws IOException {
        final BufferedReader reader;
        final InputStream is = new FileInputStream(fileName);
        reader = new BufferedReader(new InputStreamReader(is));
        reader.readLine(); // skip header
        return reader;
    }

    private static InputStream readFileInputStream(final String fileName) {
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    public static void getProperties(final String propFileN) throws IOException {
        InputStream isProp = null;
        try {
            isProp = FileUtil.readFileInputStream(propFileN);
            if (isProp != null) {
                Client.properties.load(isProp);
            } else {
                throw new FileNotFoundException("property file '" + propFileN + "' not found in the classpath");
            }
        } finally {
            isProp.close();
        }
    }
}
