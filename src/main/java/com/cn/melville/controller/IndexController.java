package com.cn.melville.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author qiuci
 */
@Controller
public class IndexController {

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

}
