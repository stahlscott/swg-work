/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.menlopark.dao;

import com.tsg.menlopark.dto.*;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public interface CmsDao {
    public User addUser(User user);
    public void deleteUser(int userId);
    public void updateUser(User user);
    public User getUserById(int userId);
    public User getUserByName(String userName);
    public List<User> getAllUsers();
    
    public Post addPost(Post post);
    public void deletePost(int postId);
    public void updatePost(Post post);
    public Post getPostById(int postId);
    public List<Post> getAllPosts();
    public List<Post> getAllFlaggedPosts();
    public List<Post> getAllDraftPosts();
    public List<Post> getAllReviewPosts();
    public Post getVisiblePostById(int postId);
    public List<Post> getAllVisiblePosts();
    public List<Post> getAllPostHeaders();
    public List<Post> getVisiblePostHeaders();
    public int getAllVisiblePostsSize();
    public List<Post> getNextVisiblePosts(int startingPost, int numberOfPosts);
    public List<Post> getPostsByCategoryId(int categoryId);
    public List<Post> getVisiblePostsByCategoryId(int categoryId);
    public int getVisiblePostsByCategoryIdSize(int categoryId);
    public List<Post> getNextVisiblePostsByCategoryId( int categoryId, int startingPost, int numberOfPosts);
    public List<Post> getPostsByTagId(int tagId);
    public List<Post> getVisiblePostsByTagId(int tagId);
    public int getVisiblePostsByTagIdSize(int tagId);
    public List<Post> getNextVisiblePostsByTagId(int tagId, int startingPost, int numberOfPosts);
    public void rejectPost(int postId, String comment);
    public void approvePost(int postId);
    
    public Category addCategory(Category category);
    public void deleteCategory(int categoryId);
    public void updateCategory(Category category);
    public Category getCategoryById(int categoryId);
    public Category getCategoryById(String name);
    public List<Category> getAllCategories();
    public List<Category> getCategoriesByPostId(int postId);
    
    public Tag addTag(Tag tag);
    public void deleteTag(int tagId);
    public void updateTag(Tag tag);
    public Tag getTagById(int tagId);
    public Tag getTagById(String name);
    public List<Tag> getAllTags();
    public List<Tag> getTagsByPostId(int postId);
    public List<Tag> getTopTags();
    
    public Page addPage(Page page);
    public void deletePage(int pageId);
    public void updatePage(Page page);
    public Page getPageById(int pageId);
    public List<Page> getAllPages();
    public Integer getHighestDisplayIndex();
    public List<Page> getParentPages();
}
