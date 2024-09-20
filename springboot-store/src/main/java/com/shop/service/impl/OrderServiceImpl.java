package com.shop.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.mapper.OrderMapper;
import com.shop.pojo.Order;
import com.shop.pojo.OrderItems;
import com.shop.service.OrderService;
import com.shop.utils.AES256Util;
import com.shop.utils.JwtProvider;
import com.shop.utils.Result;
import com.shop.utils.SHA256Util;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.ceil;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;
    private JwtProvider jwtProvider;
    private String hashKey = "hSpo15gOQ5iAewQqMExQ1nfBuqlD76yb";
    private String hashIV = "C75Va66tZRG5FIpP";
    OrderServiceImpl(OrderMapper orderMapper, JwtProvider jwtProvider){
        this.orderMapper = orderMapper;
        this.jwtProvider = jwtProvider;
    }

    public Result<Integer> newOrder(Order order) {
        orderMapper.newOrder(order);
        return Result.ok(order.getOid());
    }

    public Result newOrderItems(List<OrderItems> orderItems) {
        orderMapper.newOrderItems(orderItems);
        return Result.ok(null);
    }

    public MultiValueMap createPayment(Order order){
        String tradeInfo = buildTradeInfo(order);
        String tradeSha = generateCheckValue(tradeInfo);

        MultiValueMap<String, String> paymentParams = new LinkedMultiValueMap<>();
        paymentParams.add("MerchantID", "MS154036018");
        paymentParams.add("TradeInfo", tradeInfo);
        paymentParams.add("TradeSha", tradeSha);
        paymentParams.add("Version","2.0");

        return paymentParams;
    }

    public Result readOrder(Integer currentPage, Integer pageSize, String token){
        Long uid = jwtProvider.getUserId(token);
        Integer offset = (currentPage - 1) * pageSize;
        List<Order> orders = orderMapper.readOrder(pageSize, offset, uid);
        return Result.ok(orders);
    }
    public Result getOrderDetail(Integer oid){
        List<OrderItems> data = orderMapper.getOrderDetail(oid);
        return Result.ok(data);
    }
    public Result getOrderPageCount(Integer pageSize, String token){
        Long uid = jwtProvider.getUserId(token);
        Integer count = orderMapper.getOrderCount(uid);
        return Result.ok(ceil(Double.valueOf(count)/pageSize));
    }
    public Result delNoPayOrder(Integer oid){
        orderMapper.delNoPayOrder(oid);
        return Result.ok(null);
    }
    public String payFeedBack(String tradeInfo){
        String data = "";
        Map<String,Object> dataMap = new HashMap<>();
        String status = "";
        //解密
        try {
            data = AES256Util.decrypt(AES256Util.hexToBytes(tradeInfo), hashKey, hashIV);
            Map<String,Object> m = jsonStringToMap(data);
            status = m.get("Status").toString();
            dataMap = (Map<String,Object>) m.get("Result");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(status.equals("SUCCESS")){
            Integer oid = Integer.valueOf(dataMap.get("MerchantOrderNo").toString());
            orderMapper.paySuccess(oid);
            return "http://34.44.233.80/";
        }

        return "";
    }

    //填入資訊並AES256加密
    String buildTradeInfo(Order order){
        // 獲取當前時間的 Instant 對象
        Instant now = Instant.now();
        // 從 Unix 纪元到當前時間的秒數
        long epochSeconds = now.getEpochSecond() + 46800;
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.addParameter("MerchantID","MS154036018")
                .addParameter("RespondType","JSON")
                .addParameter("TimeStamp",String.valueOf(epochSeconds))
                .addParameter("Version","2.0")
                .addParameter("MerchantOrderNo",String.valueOf(order.getOid()))
                .addParameter("Amt",String.valueOf(order.getTotalPrice()))
                .addParameter("ItemDesc","test")
                .addParameter("ReturnURL","http://34.44.233.80/")
                .addParameter("NotifyURL","http://34.44.233.80/app-dev/order/payFeedBack")
                .addParameter("CREDIT","1");
        String uri = "";
        try {
            uri = uriBuilder.build().toString();
            uri = uri.replace("?", "");
        }catch (Exception e){
            e.printStackTrace();
        }
        return AES256Util.encrypt(uri,hashKey,hashIV);
    }
    String generateCheckValue(String tradeInfo){
        String s = "HashKey=" + hashKey + "&" + tradeInfo + "&HashIV=" + hashIV;
        return SHA256Util.sha256(s);
    }
    // 將 JSON 字串轉換為 Map
    public Map<String,Object> jsonStringToMap(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, Map.class);
    }

}
