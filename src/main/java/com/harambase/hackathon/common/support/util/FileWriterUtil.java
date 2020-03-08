package com.harambase.hackathon.common.support.util;

import java.io.File;
import java.util.UUID;

public class FileWriterUtil {

    public static String checkAndCreate(String dirPath) {
        String directory = FileWriterUtil.class.getResource("/../../").getPath();
        File toFileDir = new File(directory + dirPath);
        if (!toFileDir.exists()) {
            toFileDir.mkdirs();
        }
        return directory;
    }

    /**
     * 获取二级目录存储路径
     *
     * @param basePath       基础路径
     * @param fileName       文件名
     * @param returnRelative 是否返回相对路径, 相对于参数<i>basePath</i>
     * @return String 已存在的二级目录绝对或相对路径
     * @Title: getSecondPathByHashCode
     * @Description: 二级目录存储路径不存在时则创建
     */
    public static String getSecondPathByHashCode(String basePath, String fileName, boolean returnRelative) {
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }

        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

        int code = fileName.hashCode();
        int dir1 = code & 0xf;
        int dir2 = code >> 8 & 0xf;

        if (!basePath.endsWith("/") && !basePath.endsWith("\\")) {
            basePath += "/";
        }

        String targetDir = dir1 + "/" + dir2 + "/";
        String path = basePath + targetDir;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        return returnRelative ? targetDir : path;
    }

    /**
     * 获取二级目录存储路径
     *
     * @param basePath 基础路径
     * @param fileName 文件名
     * @return String 文件夹绝对路径
     * @Title: getSecondPathByHashCode
     * @Description: 二级目录存储路径不存在时则创建
     */
    public static String getSecondPathByHashCode(String basePath, String fileName) {
        return getSecondPathByHashCode(basePath, fileName, false);
    }

    /**
     * @param imageName 图片名
     * @param dirName   文件目录
     * @return String 返回文件目录路径
     * @Title: createFileName
     * @Description: 创建文件目录路径
     */
    public static String getOriginalFileDirName(String imageName, String dirName) {
        /*String newImageName = UUID.randomUUID().toString() + "."
                + imageName.substring(imageName.lastIndexOf(".") + 1);*/
        String fileName = (dirName + imageName).replaceAll("\\\\", "/");
        return fileName;
    }

    /**
     * @param imageName 图片名
     * @param dirName   文件目录
     * @return String 返回文件目录路径
     * @Title: createFileName
     * @Description: 创建文件目录路径
     */
    public static String getSingleFileDirName(String imageName, String dirName) {
        String newImageName = UUID.randomUUID().toString() + "."
                + imageName.substring(imageName.lastIndexOf(".") + 1);
        String fileName = (dirName + newImageName).replaceAll("\\\\", "/");
        return fileName;
    }


    public static void main(String[] args) {
        System.out.println(getSingleFileDirName("a.jsp", "z:/userfile/admin/"));
        getOriginalFileDirName("a.jsp", "z:/");

    }

}
