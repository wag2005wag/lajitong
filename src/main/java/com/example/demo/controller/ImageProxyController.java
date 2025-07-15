package com.example.demo.controller;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/proxy")
public class ImageProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 代理B站图片请求
     * @param url 图片原始URL（如https://i0.hdslb.com/...）
     * @return 图片二进制内容
     */
    @GetMapping("/image")
    public ResponseEntity<byte[]> proxyBilibiliImage(@RequestParam String url) {
        try {
            // 1. 解码URL（处理可能的编码问题）
            String decodedUrl = url.startsWith("//") ? "https:" + url : url;
            
            // 2. 设置请求头，模拟从B站内部访问
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
            headers.set("Referer", "https://www.bilibili.com/"); // 关键：告诉B站请求来自B站内部
            
            // 3. 发送请求获取图片
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<byte[]> response = restTemplate.exchange(
                decodedUrl,
                HttpMethod.GET,
                requestEntity,
                byte[].class
            );
            
            // 4. 将B站的响应头和内容返回给前端
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(response.getHeaders().getContentType());
            responseHeaders.setCacheControl(CacheControl.maxAge(3600, java.util.concurrent.TimeUnit.SECONDS));
            
            return new ResponseEntity<>(
                response.getBody(),
                responseHeaders,
                HttpStatus.OK
            );
        } catch (Exception e) {
            // 处理异常（如图片不存在、网络错误等）
            System.err.println("代理图片失败: " + url + " - " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}