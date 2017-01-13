package com.liang.test.file;

import java.io.File;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.ZeroCopyConsumer;
import org.apache.http.nio.client.methods.ZeroCopyPost;

public class HttpAsyncClientFileUpload {
	public static void main(String[] args) throws Exception {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		try {
			httpclient.start();
			File upload = new File("D:\\test12.mp4");
			File download = new File("D:\\111.log");
			ZeroCopyPost httpost = new ZeroCopyPost("http://192.168.0.208:9080/uploadfile?userid=admin&ftpserverid=2&cwtype=1", upload,
                    ContentType.create("text/plain"));
			ZeroCopyConsumer<HttpResponse> consumer = new ZeroCopyConsumer<HttpResponse>(download) {

                @Override
                protected HttpResponse process(
                        final HttpResponse response,
                        final File file,
                        final ContentType contentType) throws Exception {
                    if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                        throw new ClientProtocolException("Upload failed: " + response.getStatusLine());
                    }
                    return response;
                }
				

            };
            Future<HttpResponse> future = httpclient.execute(httpost, consumer, null);
            HttpResponse response = future.get();
            System.out.println(response.getStatusLine());
            System.out.println("Shutting down");
		} finally {
			httpclient.close();
		}
		System.out.println("Done");
	}
}
