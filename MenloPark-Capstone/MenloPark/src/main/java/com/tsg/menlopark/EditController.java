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
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(value = "/editor/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post createPost(@Valid @RequestBody Post post) {
        preparePost(post);
        dao.addPost(post);
        return post;
    }

    @RequestMapping(value = "/editor/post/{postId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int postId) {
        dao.deletePost(postId);
    }

    @RequestMapping(value = "/editor/post/{postId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable int postId, @Valid @RequestBody Post post) {
        post.setPostId(postId);
        preparePost(post);
        dao.updatePost(post);
    }
    //make this url match in ajax method
    // [ss] i don't see the need to put {comment} in the string - just add it to the post under field editorComments

    @RequestMapping(value = "/editor/post/{postId}/{comment}/reject", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void rejectPost(@PathVariable int postId, @PathVariable String comment) {
        dao.rejectPost(postId, comment);
    }

    @RequestMapping(value = "/editor/post/{postId}/approve", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approvePost(@PathVariable int postId) {
        dao.approvePost(postId);
    }
    
    // helper to set various fields on the post before sending to dao
    private Post preparePost(Post post) {
        if (post.getPostDateTime() == null) {
            post.setPostDateTime(LocalDateTime.now());
        }

        if (post.getExpDateTime() == null) {
            post.setExpDateTime(LocalDateTime.parse("9999-12-31T00:00:00"));
        }
        post.setUserId(dao.getUserByName(post.getUserName()).getUserId());
        return post;
    }
    
    //</editor-fold>

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Category REST endpoints">
    @RequestMapping(value = "/editor/category", method = RequestMethod.POST)
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

    @RequestMapping(value = "/editor/category/{categoryId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int categoryId) {
        dao.deleteCategory(categoryId);
    }

    @RequestMapping(value = "/editor/category/{categoryId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable int categoryId, @Valid @RequestBody Category category) {
        category.setCategoryId(categoryId);
        dao.updateCategory(category);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tag REST endpoints">
    @RequestMapping(value = "/editor/tag", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Tag createTag(@Valid @RequestBody Tag tag) {
        try {
            return dao.addTag(tag);
        } catch (DataIntegrityViolationException ex) {
            return dao.getTagById(tag.getName()); // if tag exists, return existing tag instead
        }
    }

    @RequestMapping(value = "/editor/tags/{tagId}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getAllTags(@PathVariable int tagId) {
        ArrayList<Tag> tags = (ArrayList<Tag>) dao.getAllTags();
        ArrayList<String> tagNames = new ArrayList<>();
        tags.stream().forEach((tag) -> {
            tagNames.add(tag.getName());
        });
        return tagNames;
    }

    @RequestMapping(value = "/editor/tag/{tagId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable int tagId) {
        dao.deleteTag(tagId);
    }

    @RequestMapping(value = "/editor/tag/{tagId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTag(@PathVariable int tagId, @Valid @RequestBody Tag tag) {
        tag.setTagId(tagId);
        dao.updateTag(tag);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Page REST endpoints">
    @RequestMapping(value = "/editor/page", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Page createPage(@Valid @RequestBody Page page) {
        page.setDisplayIndex(dao.getHighestDisplayIndex() + 1);
        dao.addPage(page);
        return page;
    }

    @RequestMapping(value = "/admin/page/{pageId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePage(@PathVariable int pageId) {
        dao.deletePage(pageId);
    }

    @RequestMapping(value = "/editor/page/{pageId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePage(@PathVariable int pageId, @Valid @RequestBody Page page) {
        page.setPageId(pageId);
        dao.updatePage(page);
    }

    @RequestMapping(value = "/admin/page/index/{pageId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePageDisplayIndex(@PathVariable int pageId, @RequestBody Page page) {
        Page currentPage = dao.getPageById(pageId);
        currentPage.setDisplayIndex(page.getDisplayIndex());
        currentPage.setParentId(page.getParentId());
        dao.updatePage(currentPage);
    }
    //</editor-fold>
}
