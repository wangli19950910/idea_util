package com.util.file;

import java.io.*;

public class HttpUtil {
    /**
     * 给出文件路径创建文件夹
     * a/b/c/d/a.txt
     * File可以递归的创建文件夹，不可以创建文件夹和文件的
     */
    public static void createFolder(String fileName) throws IOException {
        File file = new File(fileName);

        //创建父级目录文件夹 mkdir和mkdir递归的创建目录信息
        String dir = file.getParent();
        File dirs = new File(dir);
        dirs.mkdirs();
        File fs = new File(dirs, file.getName());
        fs.createNewFile();
        System.out.println(fs.exists());

    }

    /**
     * 递归的便利所给的目录
     */
    public static void listFiles(File file) {
        if (file == null || file.exists() == false) {
            return;
        }
        if (file.isFile()) {
            System.out.println(file.getAbsolutePath());
            return;
        }
        File[] fs = file.listFiles();
        System.out.println(file.getAbsolutePath());
        for (File f : fs) {
            listFiles(f);
        }
    }

    /**
     * 混有文件夹和文件的目录
     */
    public static void deleteFiles(File file) {
        if (file == null || file.exists() == false) {
            return;
        }
        if (file.isFile()) {
            file.delete();
            return;
        }
        File[] fs = file.listFiles();
        for (File f : fs) {
            deleteFiles(f);
        }
        file.delete();
    }

    /**
     * 文件复制功能的实现
     */
    public static void copyFile(File file) {

        String fileName = file.getName();
        //获取文件名
        String f = fileName.substring(0, fileName.lastIndexOf("."));

        //获取文件后缀名
        String h = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        String dir = f + "12" + "." + h;
        BufferedReader br = null;
        PrintWriter pw = null;

        try {

            br = new BufferedReader(new FileReader(file));
            pw = new PrintWriter(new FileWriter(dir));
            String line;
            while ((line = br.readLine()) != null) {
                pw.write(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }

    }
}
