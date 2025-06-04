package com.zxst.shoop.controller;

import com.zxst.shoop.service.MenuService;
import com.zxst.shoop.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @RequestMapping("/showAllMenu")
    public JsonResult showAllMenu(){
        return  menuService.showAllMenu();
    }
}
