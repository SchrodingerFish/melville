package com.cn.melville.controller;

import com.cn.melville.common.Result;
import com.cn.melville.service.impl.OrderService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qiuci
 */
@Controller
public class IndexController {

    @Autowired
    private OrderService orderService;

    /**
     * Index page
     *
     * @return
     */
    @ApiOperationSupport(author = "xiaoymin@foxmail.com")
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Melville");
        return "index";
    }


    @GetMapping("/order/create")
    @ResponseBody
    public Result<String> createOrder(String orderId) {
        orderService.createOrder(orderId);
        return Result.success("订单创建成功"); // 返回订单确认页面
    }

}
