package com.util.ftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class SftpUtil {

    private ChannelSftp sftp;
    private Session session;
    private String username;
    private String password;
    private String privateKey;
    private String host;
    private int post;

    /**
     * 构造基于密码认证的sftp对象信息
     */
    public SftpUtil(String username, String password, String host, int post) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.post = post;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     */
    public SftpUtil(String username, String host, int post,String privateKey) {
        this.username = username;
        this.host = host;
        this.post = post;
        this.privateKey = privateKey;
    }

    public SftpUtil() {
    }

    /**
     * 登陆到sftp服务器
     */
    public void login(){
        try {
            JSch jSch = new JSch();
            //添加密钥认证体系
            if(privateKey!=null){
                jSch.addIdentity(privateKey);
            }
            session = jSch.getSession(username,host,post);

            if(password!=null){
             session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking","no");

            session.setConfig(config);
            //session是链接远程sftp服务器对象
            session.connect();;
            Channel channel =session.openChannel("sftp");

            channel.connect();

            sftp = (ChannelSftp)channel;

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 关闭服务器
     */
    public void logout(){
        if(sftp!=null){
            if(sftp.isConnected()){
                sftp.disconnect();
            }
        }
        if(session!=null){
            if(session.isConnected()){
                session.disconnect();
            }
        }
    }

    /**
     * 文件上传
     */
    public void upload(String basePath, String dir, String sftpFilename, InputStream input) throws  Exception{
        try{
            sftp.cd(basePath);
            sftp.cd(dir);
        }catch (Exception e){
            String[] dirs = dir.split(File.separator);
            String tempPath = basePath;
            for(String d:dirs){
                if(null==dir||"".equals(d)) continue;
                tempPath+=File.separator+dir;
                try{
                    sftp.cd(tempPath);
                }catch (Exception e1){
                    sftp.mkdir(tempPath);
                    sftp.cd(tempPath);
                }
            }
        }
        sftp.put(input,sftpFilename);
    }

    /**
     * 文件下载
     */
    public void downLoad(String dir,String downfile,String saveFile) throws  Exception{
        if(dir!=null&&!"".equals(dir)){
            sftp.cd(dir);
        }
        File file = new File(saveFile);
        sftp.get(downfile,new FileOutputStream(file));
    }

    /**
     *删除文件
     */
    public void delete(String directory, String deleteFile) throws Exception{
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }

    public List<?> listFiles(String directory) throws Exception {
        return sftp.ls(directory);
    }

}
