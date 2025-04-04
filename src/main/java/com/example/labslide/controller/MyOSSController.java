package com.example.labslide.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class MyOSSController {
    
    @CrossOrigin
    @RequestMapping("/myoss/policy")
    public Map<String, String> policy() {
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessId = "LTAI5tCtKBngWj3W8etfEMyh";
        String accessKey = "SBtjXgLLO95j5Ikx0otXgvXwNcLIwE";
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-nanjing.aliyuncs.com";
        // 填写Bucket名称，例如examplebucket。
        String bucket = "labslidenew";
        // 填写Host地址，格式为https://bucketname.endpoint。
        String host = "https://" + bucket + "." + endpoint;
        // 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下。
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateform = simpleDateFormat.format(new Date());
        String dir = "myslide/" + dateform + "/";
    
        // 创建ossClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
//        https://labslide.oss-cn-nanjing.aliyuncs.com/slide/2023-02-17/9352152000.pdf
//        https://labslide.oss-cn-nanjing.aliyuncs.com/slide/2023-02-17/9352152000.pdf
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);
    
            //String ext = fileName.substring(fileName.lastIndexOf("."));
            Calendar calendar = Calendar.getInstance();
            calendar.set(2022, 10, 1,0,0,0);
            TimeZone tz = TimeZone.getTimeZone("GMT");
            calendar.setTimeZone(tz); // 注意，此处设置时区，不然会有8小时的误差
            long res = System.currentTimeMillis() - calendar.getTimeInMillis();
            String key = String.valueOf(res) + ".pdf";
            
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessId", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("key", key);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));
            return respMap;

        
           
        
        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        }
        return null;
    }
}
