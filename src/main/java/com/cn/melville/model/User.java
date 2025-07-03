package com.cn.melville.model;

import jakarta.validation.constraints.*;

public class User {


    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 50, message = "用户名必须在3到50个字符之间")
    private String username;

    @Email(message = "邮箱格式不正确")
    private String email;

    @Size(min = 8, message = "密码长度不能少于8个字符")
    private String password;

    @Min(value = 0, message = "年龄必须大于或等于0")
    @Max(value = 150, message = "年龄必须小于或等于150")
    private Integer age;

    private Integer isadmin;

    @Size(max = 200, message = "地址长度不能超过200个字符")
    private String address;

    @Size(max = 500, message = "简介长度不能超过500个字符")
    private String introduction;

    private Integer sex;

    @Pattern(regexp = "^\\d{11}$", message = "手机号格式不正确")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
