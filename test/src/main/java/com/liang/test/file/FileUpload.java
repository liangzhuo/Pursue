package com.liang.test.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class FileUpload {
	public static String upload(String actionUrl, String fileName)
			throws IOException {

		String BOUNDARY = java.util.UUID.randomUUID().toString();
		String PREFIX = "--", LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";

		URL url = new URL(actionUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setReadTimeout(10 * 1000);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setChunkedStreamingMode(1024 * 1024);
		conn.setRequestMethod("POST");
		conn.setUseCaches(false);
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("Charset", "UTF-8");
		File file = new File(fileName);
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
				+ ";boundary=" + BOUNDARY);

		DataOutputStream outputStream = new DataOutputStream(
				conn.getOutputStream());
		
		/*
		StringBuilder sb = new StringBuilder();
		sb.append(PREFIX);
		sb.append(BOUNDARY);
		sb.append(LINEND);
		sb.append("Content-Disposition: form-data; name=userid" + LINEND);
		sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
		sb.append(LINEND);
		sb.append("admin");
		sb.append(LINEND);

		sb.append(PREFIX);
		sb.append(BOUNDARY);
		sb.append(LINEND);
		sb.append("Content-Disposition: form-data; name=ftpserverid" + LINEND);
		sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
		sb.append(LINEND);
		sb.append(2);
		sb.append(LINEND);

		sb.append(PREFIX);
		sb.append(BOUNDARY);
		sb.append(LINEND);
		sb.append("Content-Disposition: form-data; name=cwtype" + LINEND);
		sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
		sb.append(LINEND);
		sb.append(1);
		sb.append(LINEND);

		outputStream.write(sb.toString().getBytes());
		
		*/

		if (fileName != null) {
			StringBuilder sb1 = new StringBuilder();
			sb1.append(PREFIX);
			sb1.append(PREFIX);
			sb1.append(PREFIX);
			sb1.append(BOUNDARY);
			sb1.append(LINEND);
			sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\""
					+ file.getName() + "\"" + LINEND);
			//application/octet-stream
			sb1.append("Content-Type: application/octet-stream; charset="
					+ CHARSET + LINEND);
			sb1.append(LINEND);
			System.out.println(sb1.toString());
			outputStream.write(sb1.toString().getBytes());

			DataInputStream is = new DataInputStream(new FileInputStream(file));
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			is.close();
			outputStream.write(LINEND.getBytes());
		}
		
		byte[] end_data = (PREFIX + PREFIX + PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
		System.out.println(new String(end_data));
		outputStream.write(end_data);
		outputStream.flush();
		outputStream.close();

		int res = conn.getResponseCode();
		InputStream in = null;
		StringBuilder sb2 = new StringBuilder();
		if (res == 200) {
			in = conn.getInputStream();
			int ch;
			while ((ch = in.read()) != -1) {
				sb2.append((char) ch);
			}
			in.close();
		}
		conn.disconnect();
		
		
		return sb2.toString();
	}

	public static void main(String[] args) {
		try {
			String result = upload("http://192.168.0.208:9080/uploadfile?userid=admin&ftpserverid=2&cwtype=1",
					"D:\\test12.mp4");
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
