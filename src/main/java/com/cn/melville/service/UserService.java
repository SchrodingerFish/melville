package com.cn.melville.service;

import com.cn.melville.model.User;
import com.cn.melville.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * @author qiuci
 */
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public int save(User user) {
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(System.out::println);
            throw new IllegalArgumentException("Invalid user data: " + errors.getAllErrors());
        }
        if(user.getIsadmin()==null){
            user.setIsadmin(0);
        }if(user.getSex()==null){
            user.setSex(0);
        }
        return userRepository.save(user);
    }

    @Transactional
    public int update(User user) {
        return userRepository.update(user);
    }

    @Transactional
    public int deleteById(Integer id) {
        return userRepository.deleteById(id);
    }

}
