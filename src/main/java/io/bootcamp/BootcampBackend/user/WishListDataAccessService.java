package io.bootcamp.BootcampBackend.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class WishListDataAccessService implements WishListDAO {

    private final JdbcTemplate jdbcTemplate;

    public WishListDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<WishList> selectWishListByUserID(int id) {
        String sql = """
                SELECT * FROM WishList WHERE user_id = ?
                """;

        List<WishList> wishlist = jdbcTemplate.query(sql, getWishListRowMapper(), id);
        if (wishlist.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(wishlist.get(0));
    }


    @Override
    public List<WishList> selectAllWishList() {
        String sql = """
                SELECT * FROM WishList
                """;
        return null;

    }

    @Override
    public int deleteWishList(int id) {
        return 0;
    }

    @Override
    public int insertWishList(WishList wishList) {
        return 0;
    }

    private RowMapper<WishList> getWishListRowMapper() {
        return (resultSet, i) -> {
            return new WishList(
                    resultSet.getInt("id"),
                    resultSet.getInt("course_id") ,
                    resultSet.getInt("user_id"));
        };
    }
}

