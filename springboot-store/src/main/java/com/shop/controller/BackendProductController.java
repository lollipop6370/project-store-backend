package com.shop.controller;

import com.shop.pojo.Product;
import com.shop.service.BackendProductService;
import com.shop.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/backend/product")
@ResponseBody
@CrossOrigin
public class BackendProductController {
    private BackendProductService backendProductService;
    BackendProductController(BackendProductService backendProductService){
        this.backendProductService = backendProductService;
    }
    /**
     * 獲取商品資訊
     * @param currentPage :Integer
     * @param pageSize :Integer
     * @return Product
     */
    @GetMapping("")
    public Result backendProduct(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        Result result = backendProductService.backendProduct(currentPage,pageSize);
        return result;
    }
    /**
     * 獲取商品總頁數
     * @param pageSize:Integer
     * @return Integer
     */
    @GetMapping("/page")
    public Result backendProductPage(@RequestParam("pageSize") Integer pageSize){
        Result result = backendProductService.backendProductPage(pageSize);
        return result;
    }
    /**
     * 編輯商品資訊
     * @param product :Product
     * @return null
     */
    @PostMapping("/edit")
    public Result backendProductEdit(@RequestBody Product product){
        Result result = backendProductService.backendProductEdit(product);
        return result;
    }
    /**
     * 刪除商品
     * @param id :Integer
     * @return null
     */
    @DeleteMapping("")
    public Result backendProductDel(@RequestParam("id") Integer id){
        Result result = backendProductService.backendProductDel(id);
        return result;
    }
    /**
     * 接收圖片檔案
     * @param file :MultipartFile
     * @return null
     */
    @PostMapping("/new/img")
    public Result backendProductImg(@RequestParam("file")MultipartFile file){

        Result result = backendProductService.backendProductImg(file);
        return result;
    }
}
