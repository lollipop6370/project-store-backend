package com.shop.service.impl;

import com.shop.mapper.BackendProductMapper;
import com.shop.pojo.Product;
import com.shop.service.BackendProductService;
import com.shop.utils.Result;
import com.shop.utils.ResultCodeEnum;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static java.lang.Math.ceil;

@Service
public class BackendProductServiceImpl implements BackendProductService {

    @Value("${myHost}")
    private String host;
    @Value("${imagePath}")
    private String imagePath;
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
    @CacheEvict(value = "product_detail", key = "#product.pid")
    public Result backendProductEdit(Product product){
        backendProductMapper.backendProductEdit(product);
        return Result.ok(null);
    }
    @CacheEvict(value = "product_detail", key = "#pid")
    public Result backendProductDel(Integer pid){
        backendProductMapper.backendProductDel(pid);
        return Result.ok(null);
    }

    /**
     * 新增商品
     * @param file
     * @param product
     * @return
     */
    public Result backendProductImg(MultipartFile file, Product product){
        backendProductMapper.backendProductNew(product);   //返回主鍵至pid

        //確認目錄
        Path uploadPath = Paths.get(imagePath);
        if (!Files.exists(uploadPath)){
            try {
                Files.createDirectories(uploadPath);
                System.out.println("生成資料夾" + uploadPath.toAbsolutePath());
            }catch (IOException e){
                e.printStackTrace();
                return Result.build(null,500,"資料夾創建失敗");
            }
        }
        // 獲取原文件名
        String fileName = file.getOriginalFilename();
        //文件保存到指定目錄，圖片名以productPid命名
        Path filePath = uploadPath.resolve(String.valueOf(product.getPid()) + ".jpg");
        //壓縮圖片並保存
        try{
            File originalFile = new File(imagePath + fileName);
            file.transferTo(originalFile);

            Thumbnails.of(originalFile)
                    .size(250, 250)  //調整大小，不會破壞長寬比例
                    .toFile(filePath.toFile());
            //Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            System.out.println("保存圖片失敗");
            return Result.build(null, 500,"文件上傳失敗");
        }

        //商品詳細資料寫入資料庫
        String s = "http://" + host + ":8002/images/" + product.getPid() + ".jpg";
        product.setImage(s);
        backendProductMapper.backendProductEdit(product);
        return Result.ok(null);
    }
}
