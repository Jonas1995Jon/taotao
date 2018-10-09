package com.taotao.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: 黄运锐
 * @Date: 18-4-24 下午9:19
 * @Description:
 */

public class FtpTest {

    @Test
    public void TestFTPClient(){
        FTPClient ftpClient = new FTPClient();
        try {
            //创建链接
            ftpClient.connect("127.0.0.1",21);
            //登录；
            ftpClient.login("uftp","123456");

            //长传文件
            //第一个参数服务器端文件名
            //第二个参数：上传文档inputstream
            FileInputStream inputStream = new FileInputStream(new File("/home/rui/Pictures/599a1ad30c79b85299dc2822d343ea95.jpg"));
            //设置上传路劲
            ftpClient.changeWorkingDirectory("/home/uftp/www/images");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile("hello2.jpg",inputStream);
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
