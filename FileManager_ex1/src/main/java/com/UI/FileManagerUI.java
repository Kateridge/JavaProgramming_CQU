package com.UI;

import com.Function.FileManager;
import java.io.*;

public class FileManagerUI {
    public static void main(String[] args) throws IOException{
        System.out.println("* File Manager start *");
        System.out.println("* Please enter your command *");
        System.out.println("* 文件夹创建：mkdir Dirname *");
        System.out.println("* 文件/文件夹删除：del targetName *");
        System.out.println("* 当前目录文件罗列：ls");
        System.out.println("* 文件拷贝：cp sourceFile (targetFile) *");
        System.out.println("* 文件夹拷贝：cpDir sourceDir targetDir *");
        System.out.println("* 文件加密：encode sourceFile (targetFile) *");
        System.out.println("* 文件解密：decode sourceFile (targetFile) *");
        System.out.println("* 文件管理器默认运行在目录 C:\\Test 下 *");
        String instr;
        String[] command = new String[]{"null","null","null"}; //Init command array
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Init BufferedReader
        FileManager inst = new FileManager(); //Instantiate Function Class

//        File dir = new File("C:/Test");
//        File [] temp = dir.listFiles();
//        for(File item : temp){
//            System.out.println(item.getPath());
//        }
        do{
            System.out.print("> "); //prompt
            instr = br.readLine(); //read whole instruction
            String [] split_result = instr.split("\\s+"); //split instr by space
            //copy per parameter to array
            int i = 0;
            for(String item : split_result){
                command[i] = item;
                i++;
            }
            //analyse command type, call corresponding functions
            if ("ls".equals(command[0])) {
                inst.dir_ls();
            } else if ("mkdir".equals(command[0])) {
                inst.createDir(command[1]);
            } else if ("del".equals(command[0])) {
                inst._delete(command[1]);
            } else if ("cp".equals(command[0])) {
                inst.copyFile(command[1], command[2]);
            } else if ("cpDir".equals(command[0])) {
                inst.copyDir(command[1], command[2]);
            } else if ("encode".equals(command[0])) {
                inst.encodeFile(command[1], command[2], 5);
            } else if ("decode".equals(command[0])) {
                inst.encodeFile(command[1], command[2], -5);
            }
        }while(!command[0].equals("quit"));
    }
}
