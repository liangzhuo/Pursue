package upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/upload",name="UploadServlet")
public class UploadServlet extends HttpServlet{
	public static final String ENCODING_REQUEST = "UTF-8";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获得响应流，获得输入对象
        InputStream inputStream = req.getInputStream();
        PrintWriter out = resp.getWriter();

        //建立接收流缓冲，准备处理
        StringBuffer requestBuffer = new StringBuffer();
        BufferedInputStream buffInputStream = new BufferedInputStream(inputStream);
        File file = new File("D:\\upload.mp4");
        BufferedOutputStream uploadFileOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        

        System.out.println("");
        System.out.println("Receive Http Request...");
        System.out.println("");

        byte[] buffer = new byte[1024*1024*10];
        while (buffInputStream.read() > -1) {
        	uploadFileOutputStream.write(buffer, 0, buffer.length);
        }
        buffInputStream.close();
        uploadFileOutputStream.flush();
        uploadFileOutputStream.close();

        //设置响应编码
        resp.setCharacterEncoding(ENCODING_REQUEST);
        out.print("done");

	}
}
