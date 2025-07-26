package com.cn.melville.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class BingImageApi {

    private static final String API_URL = "https://bing.cherryred.asia/api/getList?pageSize=200&currentPage=1";

    public static List<ImageDownloader.ImageInfo> getImageList() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String jsonResponse = response.body();
            return parseImageList(jsonResponse);
        } else {
            System.err.println("API 请求失败，状态码: " + response.statusCode());
            return Collections.emptyList();
        }
    }

    private static List<ImageDownloader.ImageInfo> parseImageList(String jsonResponse) {
        Gson gson = new Gson();
        TypeToken<ApiResponse> typeToken = new TypeToken<ApiResponse>() {};

        ApiResponse apiResponse = gson.fromJson(jsonResponse, typeToken.getType());
        if (apiResponse != null && apiResponse.getList() != null) {
            return apiResponse.getList();
        } else {
            System.err.println("JSON 解析失败或列表为空");
            return Collections.emptyList();
        }
    }

    public static List<ImageDownloaderVirtualThreads.ImageInfo> getImageListNew() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String jsonResponse = response.body();
            return parseImageListNew(jsonResponse);
        } else {
            System.err.println("API 请求失败，状态码: " + response.statusCode());
            return Collections.emptyList();
        }
    }

    private static List<ImageDownloaderVirtualThreads.ImageInfo> parseImageListNew(String jsonResponse) {
        Gson gson = new Gson();
        TypeToken<ApiResponseNew> typeToken = new TypeToken<ApiResponseNew>() {};

        ApiResponseNew apiResponse = gson.fromJson(jsonResponse, typeToken.getType());
        if (apiResponse != null && apiResponse.getList() != null) {
            return apiResponse.getList();
        } else {
            System.err.println("JSON 解析失败或列表为空");
            return Collections.emptyList();
        }
    }

    static class ApiResponse {
        private List<ImageDownloader.ImageInfo> list;

        public List<ImageDownloader.ImageInfo> getList() {
            return list;
        }

        public void setList(List<ImageDownloader.ImageInfo> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "ApiResponse{" +
                    "list=" + list +
                    '}';
        }
    }

    static class ApiResponseNew {
        private List<ImageDownloaderVirtualThreads.ImageInfo> list;

        public List<ImageDownloaderVirtualThreads.ImageInfo> getList() {
            return list;
        }

        public void setList(List<ImageDownloaderVirtualThreads.ImageInfo> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "ApiResponse{" +
                    "list=" + list +
                    '}';
        }
    }
}

