package com.reengen.utils.auditreporter.file;

import com.reengen.utils.auditreporter.Reporter;

import java.io.*;

// Implements common file operations
public class FileUtil {

    public static BufferedReader readFileBR(final String fileName) throws IOException {
        final BufferedReader reader;
        final InputStream is = readFileInputStream(fileName);
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
                Reporter.properties.load(isProp);
            } else {
                throw new FileNotFoundException("property file '" + propFileN + "' not found in the classpath");
            }
        } finally {
            isProp.close();
        }
    }
}
