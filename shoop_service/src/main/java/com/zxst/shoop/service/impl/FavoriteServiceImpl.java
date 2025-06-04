package com.zxst.shoop.service.impl;

import com.zxst.shoop.entity.Favorite;
import com.zxst.shoop.entity.Product;
import com.zxst.shoop.mapper.FavoriteMapper;
import com.zxst.shoop.mapper.ProductMapper;
import com.zxst.shoop.service.FavoriteService;
import com.zxst.shoop.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteMapper favoriteMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public int addFavoretise(Integer pid, Integer userId,String userName) {
        Favorite favorite = new Favorite();
        favorite.setPid(pid);
        favorite.setUid(userId);

        Product product = productMapper.getInfoById(pid);

        favorite.setImage(product.getImage());
        favorite.setPrice(product.getPrice());
        favorite.setTitle(product.getTitle());
        favorite.setCreateTime(new Date());
        favorite.setCreateUser(userName);

        favoriteMapper.addFavoretise(favorite);

        return  favorite.getFid();
    }

    @Override
    public int dropFavoretise(Integer fid) {
        return favoriteMapper.dropFavoretise(fid);
    }
}
