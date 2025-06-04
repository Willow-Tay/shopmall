package com.zxst.shoop.mapper;

import com.zxst.shoop.entity.Favorite;
import org.apache.ibatis.annotations.Param;

public interface FavoriteMapper {
    boolean addFavoretise(Favorite favorite);

    int dropFavoretise(@Param("fid") Integer fid);
}
