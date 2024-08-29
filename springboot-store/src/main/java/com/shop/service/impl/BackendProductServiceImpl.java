package com.shop.service.impl;

import com.shop.mapper.BackendProductMapper;
import com.shop.pojo.Product;
import com.shop.service.BackendProductService;
import com.shop.utils.Result;
import com.shop.utils.ResultCodeEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static java.lang.Math.ceil;

@Service
public class BackendProductServiceImpl implements BackendProductService {

    private BackendProductMapper backendProductMapper;
    BackendProductServiceImpl(BackendProductMapper backendProductMapper){
        this.backendProductMapper = backendProductMapper;
    }
    public Result backendProduct(Integer currentPage, Integer pageSize){
        Integer offset = (currentPage - 1) * pageSize;
        List<Product> data = backendProductMapper.backendProduct(offset,pageSize);
        return Result.ok(data);
    }
    public Result backendProductPage(Integer pageSize){
        Integer count = backendProductMapper.backendProductCount();
        return Result.ok(ceil(Double.valueOf(count)/pageSize));
    }
    public Result backendProductEdit(Product product){
        backendProductMapper.backendProductEdit(product);
        return Result.ok(null);
    }
    public Result backendProductDel(Integer pid){
        backendProductMapper.backendProductDel(pid);
        return Result.ok(null);
    }
    public Result backendProductImg(MultipartFile file, Product product){
        try{
            //確認目錄
            Path uploadPath = Paths.get("images/");
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            //文件保存到指定目錄，圖片名以productPid命名
            Path filePath = uploadPath.resolve(String.valueOf(product.getPid()) + ".jpg");
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            return Result.build(null, 500,"文件上傳失敗");
        }

        //商品詳細資料寫入資料庫
        product.setImage("http://localhost:8080/images/" + product.getPid() + ".jpg");
        backendProductMapper.backendProductNew(product);
        return Result.ok(null);
    }
}
