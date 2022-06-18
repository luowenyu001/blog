
package com.lwy.controller.front;


import com.lwy.pojo.Blog;
import com.lwy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 前端：首页（controller）
 */
@Controller
public class FrontIndexController {
    @Autowired
    BlogService blogService;            //blog对象的：业务层

    //1、将（推荐、最近修改时间） 的（前5篇文章）， 显示在index上：无序分页
    @RequestMapping(value = {"/", "/index"})
    public String index(Model model){
        List<Blog> blogs = blogService.queryRecoUpdFive();//查询：选推荐、最新的5篇文章

        model.addAttribute("blogs", blogs);             //传递：5篇推荐文章
        return "front/index";                             //跳转：首页
    }

}