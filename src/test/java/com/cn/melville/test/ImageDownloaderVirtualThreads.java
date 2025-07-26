package com.cn.melville.test;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cn.hutool.core.date.TimeInterval;


// 使用虚拟线程下载图片
// 需要 JDK 19 或更高版本
// 确保在运行时使用 --enable-preview 选项启用虚拟线程
// 例如：java --enable-preview -cp target/classes com.cn.melville.test.ImageDownloaderVirtualThreads
// 该代码使用虚拟线程来下载图片，避免了传统的线程池管理和资源消耗问题，适合处理大量 I/O 密集型任务
public class ImageDownloaderVirtualThreads {
    private static final String BASE_URL = "https://bing.cherryred.asia";
    private static final String OUTPUT_DIR = "output_images";
    private static final int NUM_THREADS = 36; // 线程数（未使用，因为使用虚拟线程）
    private static final Pattern INVALID_FILENAME_CHARS = Pattern.compile("[<>:\"/\\\\|?*\\x00-\\x1F]");
    private static final int MAX_FILENAME_LENGTH = 255;

    public static void main(String[] args) throws Exception {
        TimeInterval timer = new TimeInterval();
        // 开始计时
        timer.start();
        // 获取图片列表
        List<ImageInfo> imageList = BingImageApi.getImageListNew();

        // 使用虚拟线程
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // 提交下载任务
            for (int i = 0; i < imageList.size(); i++) {
                ImageInfo image = imageList.get(i);
                final int index = i + 1;
                executor.submit(() -> {
                    try {
                        downloadImage(image, index);
                    } catch (IOException e) {
                        System.err.println("图片 " + index + " 下载失败: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
            }
        } // try-with-resources ensures executor is shutdown

        System.out.println("所有图片处理完成。");
        long interval = timer.interval();
        System.out.println("耗时 (毫秒): " + interval);
    }

    private static void downloadImage(ImageInfo image, int index) throws IOException {
        String imageUrl = image.getUrl().getUhd();
        String title = image.getTitle();

        if (imageUrl != null) {
            String filename = sanitizeFilename(title) + ".jpg";
            Path savePath = Paths.get(OUTPUT_DIR, filename);

            try {
                // 创建目录（如果不存在）
                Files.createDirectories(Paths.get(OUTPUT_DIR));

                URL url = new URL(BASE_URL + imageUrl);
                try (InputStream in = url.openStream();
                     OutputStream out = Files.newOutputStream(savePath)) {
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
                System.out.println("图片 " + index + " 已成功下载并保存到: " + savePath);
            } catch (IOException e) {
                System.err.println("图片 " + index + " 下载失败: " + e.getMessage());
                throw e; // 重新抛出异常，让外层处理
            }
        } else {
            System.out.println("图片 " + index + " 未包含数据");
        }
    }

    // 清理文件名
    private static String sanitizeFilename(String filename) {
        Matcher matcher = INVALID_FILENAME_CHARS.matcher(filename);
        String sanitizedFilename = matcher.replaceAll(""); // 移除非法字符
        sanitizedFilename = sanitizedFilename.trim(); // 移除首尾空格

        if (sanitizedFilename.endsWith(".")) {
            sanitizedFilename = sanitizedFilename.substring(0, sanitizedFilename.length() - 1);
        }

        return sanitizedFilename.substring(0, Math.min(sanitizedFilename.length(), MAX_FILENAME_LENGTH)); // 截断文件名
    }

    // 内部类，表示图片信息
    static class ImageInfo {
        private String title;
        private Urls url;

        public String getTitle() {
            return title;
        }

        public Urls getUrl() {
            return url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(Urls url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "ImageInfo{" +
                    "title='" + title + '\'' +
                    ", url=" + url +
                    '}';
        }
    }

    static class Urls {
        private String uhd;

        public String getUhd() {
            return uhd;
        }

        public void setUhd(String uhd) {
            this.uhd = uhd;
        }

        @Override
        public String toString() {
            return "Urls{" +
                    "uhd='" + uhd + '\'' +
                    '}';
        }
    }

}
