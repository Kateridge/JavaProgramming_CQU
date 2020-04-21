package com.Function;

import java.io.*;
import java.lang.String;

public class FileManager {
    String init_dir;
    File curr_dir;
    public static void main(String[] args) {
        System.out.println("FileManager Start");
    }
    public FileManager(){
        init_dir = "C:\\Test";
        curr_dir = new File(init_dir);
    }

    public void createDir(String dir_name){
        File dir = new File(curr_dir.getPath() + "\\" + dir_name);
        if(dir.exists()){
            System.out.println("文件夹已存在\n");
        }
        else{
            dir.mkdir();
            System.out.printf("%s创建成功\n",dir_name);
        }
    }

    public void _delete(String targetFile){
        File dir = new File(curr_dir.getPath() + "\\" + targetFile);
        if(!dir.exists()){
            System.out.println("文件/文件夹不存在\n");
        }
        else{
            if (!dir.isFile()) {
                File[] filelist = dir.listFiles();
                for (File item : filelist) {
                    if (item.isFile())
                        item.delete();
                    else
                        _delete(targetFile + "\\" + item.getName());
                }
            }
            dir.delete();
            System.out.printf("%s已删除\n",targetFile);
        }
    }

    public void dir_ls(){
        String dir_list[] = curr_dir.list();
        for(String item: dir_list){
            System.out.printf("%s   ", item);
        }
        System.out.println();
    }

    public void copyFile (String sourceFile, String targetFile) throws IOException{
        if(targetFile.equals("null")){
            String[] temp = sourceFile.split("\\."); //*****
            targetFile = temp[0] + "_copy." + temp[1];
        }
        sourceFile = curr_dir.getPath() + "\\" + sourceFile;
        targetFile = curr_dir.getPath() + "\\" + targetFile;

        // create file input stream with buffer
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff=new BufferedInputStream(input);

        // create file output stream with buffer
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff=new BufferedOutputStream(output);

        // use buffer array to copy file
        byte[] buff = new byte[1024 * 5];
        int len;
        while ((len =inBuff.read(buff)) != -1) {
            outBuff.write(buff, 0, len);
        }
        outBuff.flush();

        //close all stream
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();

        System.out.printf("%s复制成功\n",sourceFile);
    }

    public void copyDir(String sourceDir, String targetDir) throws IOException{
        File srcdir = new File(curr_dir.getPath() + "\\" + sourceDir);
        File tgtdir = new File(curr_dir.getPath() + "\\" + targetDir);
        if(!srcdir.isDirectory()){
            System.out.println("没有指定的文件夹");
        }
        else {
            if(!tgtdir.isDirectory()){
                tgtdir.mkdir();
            }
            File[] FileList = srcdir.listFiles();
            for (File item : FileList) {
                if (item.isFile()) {
                    String filename = item.getName();
                    copyFile(sourceDir + "\\" +filename, targetDir + "\\" + filename);
                }
                if (item.isDirectory()) {
                    String dir_temp1 = sourceDir + "\\" + item.getName();
                    String dir_temp2 = targetDir + "\\" + item.getName();
                    copyDir(dir_temp1, dir_temp2);
                }
            }
        }
    }

    public void encodeFile(String sourceFile, String targetFile, int offset) throws IOException{
        File inputFile = new File(curr_dir.getPath() + "\\" + sourceFile);
        if(targetFile.equals("null")){
            if(offset > 0) {
                String[] temp = sourceFile.split("\\."); //*****
                targetFile = temp[0] + "_xxx." + temp[1];
            }
            else{
                try{
                    String[] temp = sourceFile.split("_xxx"); //*****
                    targetFile = temp[0] + temp[1];
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("该文件未被加密");
                    return;
                }
            }
        }
        File outputFile = new File(curr_dir.getPath() + "\\" + targetFile);
        if(inputFile.isFile()){
            // create file input stream with buffer
            FileInputStream input = new FileInputStream(inputFile);
            BufferedInputStream inBuff=new BufferedInputStream(input);

            // create file output stream with buffer
            FileOutputStream output = new FileOutputStream(outputFile);
            BufferedOutputStream outBuff=new BufferedOutputStream(output);

            // use buffer array to copy file
            byte[] buff = new byte[1024 * 5];
            int len;
            while ((len =inBuff.read(buff)) != -1) {
                for(int i = 0; i < len; i++)
                    buff[i] += offset;
                outBuff.write(buff, 0, len);
            }
            outBuff.flush();

            //close all stream
            inBuff.close();
            outBuff.close();
            output.close();
            input.close();
        }
        else{
            System.out.println("目标不是有效的文件");
        }
    }

}

