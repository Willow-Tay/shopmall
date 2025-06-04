package com.zxst.shoop.controller;


import com.zxst.shoop.entity.Category;
import com.zxst.shoop.service.CategoryService;
import com.zxst.shoop.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping("/showCategory")
    public JsonResult showCategory(Integer pid) {
        List<Category> categories = categoryService.showFirstMenu(pid);
        return  new JsonResult(200,categories);
    }
}
