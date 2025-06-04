package com.zxst.shoop.controller;


import com.zxst.shoop.service.FavoriteService;
import com.zxst.shoop.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/favoretise")
public class FavoriteController extends BaseController {

    @Resource
    private FavoriteService favoriteService;

    //添加收藏
    @RequestMapping("/addFavoretise")
    public JsonResult addFavoretise(Integer pid, HttpSession session){
        Integer userId = getUserId(session);
        String userName = getUserName(session);
        int fid  = favoriteService.addFavoretise(pid,userId,userName);
        if (fid>0) {
            return  new JsonResult(OK,fid);
        }else{
            return  new JsonResult(FAIL,null);
        }
    }

    @RequestMapping("/dropFavoretise")
    public JsonResult dropFavoretise(Integer fid){
        int flag   = favoriteService.dropFavoretise(fid);
        if (fid>0) {
            return  new JsonResult(OK);
        }else{
            return  new JsonResult(FAIL);
        }
    }

}
