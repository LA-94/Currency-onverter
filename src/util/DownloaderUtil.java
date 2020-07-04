package util;

import java.io.*;
import java.net.URL;

public class DownloaderUtil {
    private DownloaderUtil() {

    }
    public static File downloadFile(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        File file = File.createTempFile("data", null);


        try (BufferedInputStream bis = new BufferedInputStream(url.openStream());
             FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, count);
            }
        }
        return file;
    }
}
