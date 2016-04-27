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
import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
@Controller
public class EditController {

    private final CmsDao dao;

    @Inject
    public EditController(CmsDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String displayEditPostsPage() {
        return "editPosts";
    }

    @RequestMapping(value = {"/page/edit"}, method = RequestMethod.GET)
    public String displayEditPagesPage(Model model) {
        model.addAttribute("pageList", dao.getAllPages());
        return "editPage";
    }

    // <editor-fold defaultstate="collapsed" desc="Post REST endpoints">
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post createPost(@Valid @RequestBody Post post) {

        if (post.getPostDateTime() == null) {
            post.setPostDateTime(LocalDateTime.now());
        }

        if (post.getExpDateTime() == null) {
            post.setExpDateTime(LocalDateTime.parse("9999-12-31T00:00:00"));
        }

        dao.addPost(post);
        return post;
    }

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int postId) {
        dao.deletePost(postId);
    }

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable int postId, @Valid @RequestBody Post post) {
        post.setPostId(postId);
        dao.updatePost(post);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Category REST endpoints">
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Category createCategory(@Valid @RequestBody Category category) {
        try {
            if (!category.getName().equals("")) {
                return dao.addCategory(category);
            } else {
                return null;
            }
        } catch (DataIntegrityViolationException ex) {
            return null;
        }
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int categoryId) {
        dao.deleteCategory(categoryId);
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable int categoryId, @Valid @RequestBody Category category) {
        category.setCategoryId(categoryId);
        dao.updateCategory(category);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tag REST endpoints">
    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Tag createTag(@Valid @RequestBody Tag tag) {
        try {
            return dao.addTag(tag);
        } catch (DataIntegrityViolationException ex) {
            return dao.getTagById(tag.getName()); // if tag exists, return existing tag instead
        }
    }

    @RequestMapping(value = "/tag/{tagId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable int tagId) {
        dao.deleteTag(tagId);
    }

    @RequestMapping(value = "/tag/{tagId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTag(@PathVariable int tagId, @Valid @RequestBody Tag tag) {
        tag.setTagId(tagId);
        dao.updateTag(tag);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Page REST endpoints">
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Page createPage(@Valid @RequestBody Page page) {
        page.setDisplayIndex(dao.getHighestDisplayIndex() + 1);
        dao.addPage(page);
        return page;
    }

    @RequestMapping(value = "/page/{pageId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePage(@PathVariable int pageId) {
        dao.deletePost(pageId);
    }

    @RequestMapping(value = "/page/{pageId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePage(@PathVariable int pageId, @Valid @RequestBody Page page) {
        page.setPageId(pageId);
        dao.updatePage(page);
    }

    @RequestMapping(value = "/page/index/{pageId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePageDisplayIndex(@PathVariable int pageId, @RequestBody Page page) {
        Page currentPage = dao.getPageById(pageId);
        currentPage.setDisplayIndex(page.getDisplayIndex());
        currentPage.setParentId(page.getParentId());
        dao.updatePage(currentPage);
    }
    //</editor-fold>
}
