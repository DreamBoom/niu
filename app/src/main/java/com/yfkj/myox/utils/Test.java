package com.yfkj.myox.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class Test {
    public static void fileSp(String inPath, String outPath) throws Exception {
        File file = new File(inPath);
        int size = 10 * 1024 * 1024;
        long totalSpace = file.length();
        System.out.println(totalSpace);
        long isSplit = totalSpace % size;
        long num = 0;
        if (isSplit == 0) {
            num = totalSpace / size;
        } else {
            num = totalSpace / size + 1;
        }
        long start = 0;
        long end = 0;
        RandomAccessFile raf = null;
        FileOutputStream fos = null;
        byte[] bytes = new byte[2048];
        for (int i = 0; i < num; i++) {
            raf = new RandomAccessFile(file, "r");
            start = i * size;
            raf.seek(start);
            fos = new FileOutputStream(outPath + "a" + i + ".mp4");
            int len = 0;
            long writeByte = 0;
            long wirteTotal = 0;
            wirteTotal = (i == (num - 1)) ? totalSpace % size : size;
            while ((len = raf.read(bytes)) > 0) {
                if (writeByte < wirteTotal) {
                    writeByte += len;
                    if (writeByte <= wirteTotal)
                        fos.write(bytes, 0, len);
                    else {
                        len = len - (int) (writeByte - wirteTotal);
                        fos.write(bytes, 0, len);
                    }
                }
                fos.flush();
            }
        }
        fos.close();
        raf.close();
    }
}