package com.reengen.utils.auditreporter.file;

//Creates different file strategy.
// If you need to add new file strategy, create new class for new strategy
// than create new instance in this class
public class FileStrategyFactory {
    public static FileStrategy getFileStrategy(final String filetype) {
        switch (filetype) {
            case "csv":
                return new Csv();
        }
        return new Txt();
    }
}
