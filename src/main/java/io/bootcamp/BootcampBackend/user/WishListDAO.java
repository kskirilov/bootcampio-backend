package io.bootcamp.BootcampBackend.user;

import io.bootcamp.BootcampBackend.course.Course;

import java.util.List;
import java.util.Optional;

public interface WishListDAO {
    Optional<WishList> selectWishListByUserID (int id);
    List<WishList> selectAllWishList();
    int deleteWishList(int id);
    int insertWishList (WishList wishList);
}

