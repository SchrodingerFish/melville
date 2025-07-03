package com.cn.melville.repository.impl;

import com.cn.melville.exception.BusinessException;
import com.cn.melville.model.User;
import com.cn.melville.repository.UserRepository;
import com.cn.melville.repository.mapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    @Qualifier("primaryPgJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Override
    public User findById(Integer id) {
        String sql = "SELECT * FROM public.\"user\" WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        } catch (BusinessException e) {
            throw new BusinessException(404, "未找到用户ID： " + id);
        } catch (Exception e) { // 捕获更通用的 Exception
            log.error("Database access error", e);
            throw new RuntimeException("无法获取用户ID： " + id, e); // 重新抛出一个 RuntimeException 或自定义的更合适的异常
        }
    }
    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM public.\"user\"";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
    @Override
    public int save(User user) {
        String sql = "INSERT INTO public.\"user\" (username, email, password, age, isadmin, address, introduction, sex, phone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), user.getAge(), user.getIsadmin(), user.getAddress(), user.getIntroduction(), user.getSex(), user.getPhone());
    }
    @Override
    public int update(User user) {
        String sql = "UPDATE public.\"user\" SET username = ?, email = ?, password = ?, age = ?, isadmin = ?, address = ?, introduction = ?, sex = ?, phone = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), user.getAge(), user.getIsadmin(), user.getAddress(), user.getIntroduction(), user.getSex(), user.getPhone(), user.getId());
    }
    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM public.\"user\" WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
