package com.zxst.shoop.controller;

import com.alibaba.fastjson2.JSON;
import com.zxst.shoop.entity.Address;
import com.zxst.shoop.entity.Order;
import com.zxst.shoop.service.AddressService;
import com.zxst.shoop.service.OrderService;
import com.zxst.shoop.util.JsonResult;
import io.netty.buffer.UnpooledUnsafeDirectByteBuf;
import org.apache.tomcat.Jar;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/orders")
public class OrdersController  extends BaseController{

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private AddressService addressService;

    @Resource
    private OrderService orderService;

    //预支付订单 redis
    @RequestMapping("/createOrder")
    public JsonResult createOrder(Double totalMoney, Integer sendAddress,String nids, HttpSession session){
        System.out.println(totalMoney+"===="+sendAddress+"===="+nids);
        // "50,49"
        String substring = nids.substring(0, nids.lastIndexOf(','));

        Integer userId = getUserId(session);
        String userName = getUserName(session);

        String oid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        Order order = new Order();
        order.setOid(oid);
        order.setUid(userId);
        order.setTotalPrice(totalMoney);
        order.setStatus(0);
        order.setOrderTime(new java.util.Date());
        //通过收获地址的id查询收获地址的信息
        Address address = addressService.getAddressByAid(sendAddress);
        order.setRecvName(address.getName());
        order.setRecvAddress(address.getAddress());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvPhone(address.getPhone());
        order.setRecvArea(address.getAreaName());
        String jsonString = JSON.toJSONString(order);

        Boolean b = redisTemplate.hasKey(userName);
        if (b){
            return  new JsonResult(FAIL,null,"您有未支付的订单");
        }else{
            ValueOperations<String, String> svo = redisTemplate.opsForValue();
            //订单信息
            svo.set(userName, jsonString,30, TimeUnit.MINUTES);
            //存放订单关联的商品信息
            JsonResult jsonResult = new JsonResult(OK,"uids",substring);
            svo.set(userName+"nids", JSON.toJSONString(jsonResult),30, TimeUnit.MINUTES);
            return new JsonResult(OK,null,oid);
        }
    }

    //展示所有用户订单
    @RequestMapping("/showOrdersInfo")
    public List<Order> showOrdersInfo(HttpSession session){
        Integer userId = getUserId(session);
        return  orderService.showOrderInfo(userId);
    }

}
