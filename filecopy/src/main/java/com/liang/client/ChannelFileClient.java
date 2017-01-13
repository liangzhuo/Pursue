package com.liang.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class ChannelFileClient {
	private final static int SIZE = 1024 * 1024 * 1 ;  
    private final static String filePath = "E://test";  
      
    public static void main(String[] args) {  
        SocketChannel channel = null;  
        try {  
            channel = SocketChannel.open();  
            channel.socket().bind(new InetSocketAddress(InetAddress.getLocalHost() ,11111));  
            channel.connect(new InetSocketAddress("localhost" ,11100));  
            ByteBuffer buffer = ByteBuffer.allocate(SIZE);  
            //读取文件名  
            String sfile = null;  
            channel.read(buffer);  
            buffer.clear();  
            byte[] bt = buffer.array();  
            sfile = new String(bt);  
              
            String[] files = sfile.split(",");  
            //获取文件  
            byte[] sb = null;  
            for(String fileName : files) {  
                File file = new File(filePath , fileName);  
                  
                if (!file.exists()) {  
                    file.createNewFile();  
                } else {  
                    file.delete();  
                }  
                  
                //传递文件名给server端  
                sb = fileName.getBytes();  
                  
                buffer = ByteBuffer.wrap(sb, 0, sb.length);  
                channel.write(buffer);  
                buffer.clear();  
                  
                FileChannel fChannel = new FileOutputStream(file).getChannel();  
                  
                channel.read(buffer);  
                long fileSize = buffer.getLong(0);  
                  
                long size = 0;  
                int a = 0;  
                while ((a = channel.read(buffer)) != -1) {  
                    buffer.flip();  
                    fChannel.write(buffer);  
                    buffer.clear();  
                      
                    size += a;  
                    if(size == fileSize) {  
                        fChannel.close();  
                        break ;  
                    }  
                }  
            }  
              
              
            sb = "END".getBytes();  
            buffer = ByteBuffer.wrap(sb, 0, sb.length);  
            channel.write(buffer);  
            buffer.clear();  
              
              
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                channel.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    } 
}
