package com.liang.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class EcrmFileClient {
	private final static int port = 18110;  
    private final static int SIZE = 8192;  
    //private String basePath = "/home/admin/allen/file";  
    private String basePath = "E:/test";  
    private DataInputStream  in = null;  
    private DataOutputStream out = null;  
    private DataOutputStream fileOut = null;  
      
    private  void receive(String host){  
        System.out.println("***********start**********");  
        try {  
            Socket socket = new Socket(host, port);  
            in  = new DataInputStream(socket.getInputStream());  
            out = new DataOutputStream( socket.getOutputStream()) ;  
              
            String fileNames = in.readUTF();  
            String[] files = fileNames.split(",");  
            System.out.println("---------Read files------------");  
            try {  
                for (String fileName : files) {  
                    System.out.println("the file is :" + fileName);  
                    out.writeUTF(fileName);  
                    out.flush();  
                    long length = in.readLong();  
                    System.out.println("the file length is :" + length);  
                    int slength = 0;  
                    File file = new File(this.basePath + File.separator + fileName);  
                    fileOut = new DataOutputStream(new FileOutputStream(file));  
                      
                    System.out.println("begin copy :" + file);  
                    int read = 0;  
                    boolean mark = true;  
                    while (mark) {  
                        System.out.println("开始读字节流:" + file.getName() + "此时:" + mark);  
                        byte[] b = new byte[SIZE];  
                        if ((read = in.read(b)) != -1) {  
                            System.out.println("读取大小:" + read);  
                            fileOut.write(b, 0, read);  
                            System.out.println("写文件:" + file.getAbsolutePath());  
                            slength += read;  
                            System.out.println("length:" + length + ": slength" + slength);  
                            if(slength == length){  
                                mark = false;  
                                System.out.println("文件读完了:" + file.getName());  
                            }  
                        }  
                    }  
                    System.out.println("end copy :" + fileName);  
                }  
                out.writeUTF("EOF");  
                out.flush();  
            } finally {  
                System.out.println("执行finally");  
                in.close();  
                out.close();  
                socket.close();  
            }  
            System.out.println("******one file ok*********");  
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    public static void main(String arg[]) {  
        PrintStream myout = null;  
        try {  
            myout = new PrintStream(new FileOutputStream(new File("E://test/log.log")));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }          
        System.setOut(myout);           
        System.setErr(myout);   
        new EcrmFileClient().receive("localhost");  
    }  
}
