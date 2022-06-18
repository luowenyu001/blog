
package com.lwy.controller.front;

import com.lwy.pojo.Blog;
import com.lwy.pojo.Comment;
import com.lwy.pojo.Tag;
import com.lwy.service.BlogService;
import com.lwy.service.CommentService;
import com.lwy.service.TagService;
import com.lwy.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 前端：博客（controller）
 */
@Controller
public class FrontBlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    CommentService commentService;

    @Autowired
    TagService tagService;

    //跳转：文章页面
    @RequestMapping("/front/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        //1、通过ID获取到：博客
        Blog blog = blogService.queryBlogInfoById(id);

        //2、将markdown转为：html
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));

        //3、给博客：设置评论
        List<Comment> comments = commentService.queryCommentByBlogId(id);

        //4、博客：访问次数加1
        blogService.updateBlogViewsNumById(id);


        //5、给博客：显示标签 (可选)
        List<Tag> tags = tagService.queryTagsByBlogId(id);
        blog.setTags(tags);

        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);
        return "front/blog";
    }
}