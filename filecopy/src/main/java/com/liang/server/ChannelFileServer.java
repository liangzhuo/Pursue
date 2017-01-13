package com.liang.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelFileServer {
	private final static int SIZE = 1024 * 1024 * 1;  
    private final static String filepath = "d://test";  
      
    public static void main(String[] args) {  
        ServerSocketChannel serverChannel = null;  
        InetSocketAddress host = null;  
        SocketChannel channel = null;  
        try {  
            serverChannel = ServerSocketChannel.open();  
            host  = new InetSocketAddress("localhost", 11100);  
            serverChannel.socket().bind(host);  
            channel = serverChannel.accept();  
              
            //传输文件名  
            StringBuilder builder = new StringBuilder();  
            File files = new File(filepath);  
              
            String[] sfile = files.list();  
              
            for(int i = 0; i < sfile.length; i++){  
                builder.append(sfile[i]);  
                if(i != sfile.length - 1) {  
                    builder.append(",");  
                }  
            }  
              
            byte[] b = builder.toString().getBytes();  
            ByteBuffer buffer =  ByteBuffer.wrap(b, 0, b.length);  
            channel.write(buffer);  
            buffer.clear();  
              
              
            //接收文件  
            while(true) {  
                //获取传递文件名  
                buffer = ByteBuffer.allocate(SIZE);  
                channel.read(buffer);  
                buffer.clear();  
                byte[] newbyte = buffer.array();  
                String name =new String(newbyte);  
                if (name.startsWith("END")) {  
                    break;  
                } else {  
                    File newFile = new File(filepath, name);  
                    // 传输文件大小  
                    buffer.clear();  
                    buffer.putLong(newFile.length());  
                    buffer.flip();  
                    channel.write(buffer);  
                    //传输文件  
                      
                    ByteBuffer src = ByteBuffer.allocate(SIZE);  
                    FileChannel fChannel = new FileInputStream(newFile).getChannel();  
                      
                    while ((fChannel.read(src)) != -1 ) {  
                        src.flip();  
                        channel.write(src);  
                        src.clear();  
                    }  
                    fChannel.close();  
                }  
            }  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (channel != null) {  
                try {  
                    channel.close();  
                } catch (IOException e) {  
                    System.err.println(e);  
                }  
            }  
            if (serverChannel != null) {  
                try {  
                    serverChannel.close();  
                } catch (IOException e) {  
                    System.err.println(e);  
                }  
            }  
        }  
          
    }  
      
    public static final String getLocalHostIP() {  
        try {  
            return InetAddress.getLocalHost().getHostAddress();  
        } catch (UnknownHostException e) {  
            throw new RuntimeException("[local-ip] an exception occured when get local ip address", e);  
        }  
    }  
}
