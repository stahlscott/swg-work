/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.menlopark;

import com.tsg.menlopark.dao.CmsDao;
import com.tsg.menlopark.dto.Category;
import com.tsg.menlopark.dto.Page;
import com.tsg.menlopark.dto.Post;
import com.tsg.menlopark.dto.Tag;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
@Controller
public class DisplayController {

    private final CmsDao dao;

    @Inject
    public DisplayController(CmsDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String displayPostsPage() {
        return "displayPosts";
    }

    @RequestMapping(value = "/post/display/{postId}", method = RequestMethod.GET)
    public String displaySinglePostPage(Model model, @PathVariable int postId) {
        Post post = dao.getVisiblePostById(postId);
        if (post != null) {
            model.addAttribute("postId", postId);
        } else {
            model.addAttribute("postId", "null"); // make this into a 404 not found page?
        }
        return "singlePost";
    }

    @RequestMapping(value = "category/display/{categoryId}", method = RequestMethod.GET)
    public String displaySingleCategoryPage(@PathVariable int categoryId) {
        return "singleCategory";
    }

    @RequestMapping(value = "tag/display/{tagId}", method = RequestMethod.GET)
    public String displaySingleTagPage(@PathVariable int tagId) {
        return "singleTag";
    }

    @RequestMapping(value = "tag/display/name/{tagName}", method = RequestMethod.GET)
    public String displaySingleTagPage(@PathVariable String tagName) {
        return "singleTag";
    }

    @RequestMapping(value = "page/display/{pageId}", method = RequestMethod.GET)
    public String displayPage(Model model, @PathVariable int pageId) {
        return "singlePage";
    }

    @RequestMapping(value = "/adminConsole", method = RequestMethod.GET)
    public String displayAdminConsole() {
        return "adminConsole";
    }

    // <editor-fold defaultstate="collapsed" desc="Post REST endpoints">
    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public Post getPost(@PathVariable int postId) {
        return dao.getPostById(postId);
    }

    @RequestMapping(value = "/posts/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts() {
        return dao.getAllPosts();
    }

    @RequestMapping(value = "/posts/visible", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllVisiblePosts() {
        return dao.getAllVisiblePosts();
    }

    @RequestMapping(value = "/posts/visible/size", method = RequestMethod.GET)
    @ResponseBody
    public Integer getVisiblePostsSize() {
        return dao.getAllVisiblePostsSize();
    }
    
    @RequestMapping(value = "/posts/visible/headers", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getVisiblePostHeaders() {
        return dao.getVisiblePostHeaders();
    }

    @RequestMapping(value = "/posts/visible/{startingPost}/{numberOfPosts}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getNextVisiblePosts(@PathVariable int startingPost, @PathVariable int numberOfPosts) {
        return dao.getNextVisiblePosts(startingPost, numberOfPosts);
    }

    @RequestMapping(value = "/category/id/{categoryId}/posts/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getPostsByCategory(@PathVariable int categoryId) {
        return dao.getPostsByCategoryId(categoryId);
    }

    @RequestMapping(value = "/category/id/{categoryId}/posts/visible", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getVisiblePostsByCategory(@PathVariable int categoryId) {
        return dao.getVisiblePostsByCategoryId(categoryId);
    }

    @RequestMapping(value = "/category/id/{categoryId}/posts/visible/size", method = RequestMethod.GET)
    @ResponseBody
    public Integer getVisiblePostsByCategorySize(@PathVariable int categoryId) {
        return dao.getVisiblePostsByCategoryIdSize(categoryId);
    }

    @RequestMapping(value = "/category/id/{categoryId}/posts/visible/{startingPost}/{numberOfPosts}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getNextVisiblePostsByCategory(@PathVariable int categoryId, @PathVariable int startingPost, @PathVariable int numberOfPosts) {
        return dao.getNextVisiblePostsByCategoryId(categoryId, startingPost, numberOfPosts);
    }

    @RequestMapping(value = "/tag/id/{tagId}/posts/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getPostsByTag(@PathVariable int tagId) {
        return dao.getPostsByTagId(tagId);
    }

    @RequestMapping(value = "/tag/id/{tagId}/posts/visible", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getVisiblePostsByTag(@PathVariable int tagId) {
        return dao.getVisiblePostsByTagId(tagId);
    }

    @RequestMapping(value = "/tag/id/{tagId}/posts/visible/size", method = RequestMethod.GET)
    @ResponseBody
    public Integer getVisiblePostsByTagSize(@PathVariable int tagId) {
        return dao.getVisiblePostsByTagIdSize(tagId);
    }

    @RequestMapping(value = "/tag/id/{tagId}/posts/visible/{startingPost}/{numberOfPosts}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getNextVisiblePostsByTag(@PathVariable int tagId, @PathVariable int startingPost, @PathVariable int numberOfPosts) {
        return dao.getNextVisiblePostsByTagId(tagId, startingPost, numberOfPosts);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Category REST endpoints">
    @RequestMapping(value = "/category/id/{categoryId}", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategory(@PathVariable int categoryId) {
        return dao.getCategoryById(categoryId);
    }

    @RequestMapping(value = "/category/name/{categoryName}", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategory(@PathVariable String categoryName) {
        return dao.getCategoryById(categoryName);
    }

    @RequestMapping(value = "/categories/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAllCategories() {
        return dao.getAllCategories();
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tag REST endpoints">
    @RequestMapping(value = "/tag/id/{tagId}", method = RequestMethod.GET)
    @ResponseBody
    public Tag getTag(@PathVariable int tagId) {
        return dao.getTagById(tagId);
    }

    @RequestMapping(value = "/tag/name/{tagName}", method = RequestMethod.GET)
    @ResponseBody
    public Tag getTag(@PathVariable String tagName) {
        return dao.getTagById(tagName);
    }

    @RequestMapping(value = "/tags/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getAllTags() {
        return dao.getAllTags();
    }

    @RequestMapping(value = "/tags/top", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getTopTags() {
        return dao.getTopTags();
    }

    @RequestMapping(value = "/page/{pageId}", method = RequestMethod.GET)
    @ResponseBody
    public Page getPage(@PathVariable int pageId) {
        return dao.getPageById(pageId);
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Page REST endpoints">
    @RequestMapping(value = "/pages/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Page> getAllPages() {
        return dao.getAllPages();
    }
    
    @RequestMapping(value ="/pages/parents", method=RequestMethod.GET)
    @ResponseBody
    public List<Page> getOnlyParentPages() {
        return dao.getParentPages();
    }
    //</editor-fold>
}
