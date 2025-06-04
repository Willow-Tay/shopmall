package com.zxst.shoop.controller;

import com.mysql.cj.exceptions.CJConnectionFeatureNotAvailableException;
import com.zxst.shoop.entity.User;
import com.zxst.shoop.service.UserService;
import com.zxst.shoop.util.JsonResult;
import com.zxst.shoop.util.PageResult;
import com.zxst.shoop.util.QueryPageBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody  QueryPageBean queryPageBean) {
        return  userService.findPage(queryPageBean);
    }

    //改变用户的状态
    @RequestMapping("/updateUserStatus")
    public JsonResult updateUserStatus(Integer uid) {
        //根据用户id获取用户信息
        User user = userService.showOneUser(uid);
        Integer isDelete = user.getIsDelete();
        if (isDelete == 1) {
            user.setIsDelete(0);
        }else{
            user.setIsDelete(1);
        }
        user.setModifiedTime(new java.util.Date());
        boolean flag = userService.eeditUserInfo(user);
        if (flag) {
            return new JsonResult(200);
        }else{
            return new JsonResult(300);
        }
    }
}
