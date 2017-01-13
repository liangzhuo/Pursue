package com.liang.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EcrmFileServer {
	private final static int port = 18110;  
    private final static int SIZE = 8192;  
    //private String basePath = "/home/admin/allen/file";  
    private String basePath = "d:/test";  
    private  void send(){  
        /** 
         * 1.发送FileList 
         * 2.接收FileName 
         * 3.传递stream 
         * 4.接收end，结束传递 
         */  
        ServerSocket server = null;  
        Socket socket = null;  
        DataInputStream  fileIn = null;  
        DataOutputStream out = null;  
        DataInputStream  in =null;  
          
        try {  
            server = new ServerSocket(port);  
            File files = new File(basePath);  
            socket = server.accept();  
            out = new DataOutputStream(socket.getOutputStream());  
            in  = new DataInputStream(socket.getInputStream());  
              
            StringBuilder builder = new StringBuilder();  
            for(String s : files.list()){  
                builder.append(s);  
                builder.append(",");  
            }  
              
            System.out.println("------------send file------------");  
            out.writeUTF(builder.toString());  
            out.flush();  
              
            while(true){  
                    System.out.println("------------begin read file------------");  
                    String fileName = in.readUTF();  
                    System.out.println("read file : " + fileName);  
                    if("EOF".equals(fileName)){  
                        System.out.println(fileName + "file send over ,thanks");  
                        break;  
                    }  
                    File file = constuctNewFile(files.getAbsolutePath(),fileName);  
                    fileIn = new DataInputStream(new FileInputStream(file));  
                    System.out.println("the file: " + fileName + "length is " + file.length());  
                    out.writeLong(file.length());  
                    out.flush();  
                      
                    byte[] buffer = new byte[SIZE];  
                    while(true){  
                        int read = 0;  
                        if(fileIn != null){  
                            read = fileIn.read(buffer);  
                        }  
                        if ( read != -1){  
                            out.write(buffer, 0, read);  
                        } else {  
                            break;  
                        }  
                    }         
                      
                    System.out.println("Write file over: " + fileName);  
                    out.flush();  
                    fileIn.close();  
                    fileIn = null;  
            }  
            System.out.println("All is over");  
        } catch (IOException e) {  
            throw new RuntimeException("IO传输异常",e);  
        } finally{  
            try {  
                out.close();  
                socket.close();  
                server.close();  
            } catch (IOException e) {  
                throw new RuntimeException("IO关闭异常",e);  
            }  
        }  
    }  
      
    private File constuctNewFile(String filePath , String fileName){  
        fileName = filePath + File.separator + fileName;  
        return new File(fileName);  
    }  
      
      
    public static void main(String arg[]) {  
        PrintStream myout = null;  
        try {  
            myout = new PrintStream(new FileOutputStream(new File("D://test/log.log")));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }          
        System.setOut(myout);           
        System.setErr(myout);   
        new EcrmFileServer().send();  
    }  
}
