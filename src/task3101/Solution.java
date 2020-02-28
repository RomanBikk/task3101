package task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String path = args[0];
        String resultFileAbsolutePath = args[1];
        List<File> smallFiles = new ArrayList<>();

        File resultFile = new File(resultFileAbsolutePath);
        File renamedResultFile = new File(resultFile.getParent() + "/allFilesContent.txt");

        if(FileUtils.isExist(resultFile)) {
            FileUtils.renameFile(resultFile,renamedResultFile);
        }

        File directory = new File(path);
       readRecursiveDirectory(directory, smallFiles);
        nameSorting(smallFiles);
        OutputStream output = new FileOutputStream(renamedResultFile);

        for (File file : smallFiles) {
            InputStream input = new FileInputStream(file);
            while (input.available() > 0) {
                output.write(input.read());
            }
            output.write('\n');
        }

        output.close();

    }

    public static void nameSorting(List<File> files) {
        Comparator<File> comparator = new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        files.sort(comparator);
    }

    public static void readRecursiveDirectory(File directory, List<File> listFiles) {
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                if (file.length() <= 50) {
                    listFiles.add(file);
                }
            } else {
                readRecursiveDirectory(file, listFiles);
            }
        }
    }
}
