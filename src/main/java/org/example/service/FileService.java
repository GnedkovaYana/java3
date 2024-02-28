package org.example.service;

import java.io.File;

public class FileService {
    public File[] getDirectories(String path) {
        if (path == null) {
            path = "C:\\";
        }
        File directory = new File(path);
        return directory.listFiles(File::isDirectory);
    }

    public File[] getFiles(String path) {
        if (path == null) {
            path = "C:\\";
        }
        File file = new File(path);
        return file.listFiles(File::isFile);
    }
}
