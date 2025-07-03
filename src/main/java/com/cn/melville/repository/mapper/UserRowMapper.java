package com.cn.melville.repository.mapper;

import com.cn.melville.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setAge(rs.getInt("age"));
        user.setIsadmin(rs.getInt("isadmin"));
        user.setAddress(rs.getString("address"));
        user.setIntroduction(rs.getString("introduction"));
        user.setSex(rs.getInt("sex"));
        user.setPhone(rs.getString("phone"));
        return user;
    }
}
