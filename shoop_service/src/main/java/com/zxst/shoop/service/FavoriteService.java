package com.zxst.shoop.service;

public interface FavoriteService {
    int addFavoretise(Integer pid, Integer userId,String userName);

    int dropFavoretise(Integer fid);
}
