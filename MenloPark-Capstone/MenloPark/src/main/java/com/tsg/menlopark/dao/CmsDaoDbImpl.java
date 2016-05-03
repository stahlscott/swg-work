/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.menlopark.dao;

import com.tsg.menlopark.dto.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class CmsDaoDbImpl implements CmsDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final int MAX_VISIBLE_CHARACTERS = 1500;

    // <editor-fold desc="SQL Statements">
    private static final String SQL_LAST_INDEX
            = "select LAST_INSERT_ID()";

    private static final String SQL_ORDER_BY_DATE_DESC
            = " order by post_datetime desc";

    private static final String SQL_ORDER_BY_CATEGORY_NAME_ASC
            = " order by category_name asc";

    // <editor-fold defaultstate="collapsed" desc="User SQL statements">
    // Users
    private static final String SQL_INSERT_USER
            = "insert into user (username, password, enabled) values (?, ?, ?)";

    private static final String SQL_DELETE_USER
            = "delete from user where user_id = ?";

    private static final String SQL_UPDATE_USER
            = "update user set username = ?, password = ?, enabled = ? where user_id = ?";

    private static final String SQL_SELECT_ALL_USERS
            = "select * from user";

    private static final String SQL_SELECT_USER
            = "select * from user where user_id = ?";

    private static final String SQL_SELECT_USER_BY_USERNAME = "select * from user where username = ?";

    private static final String SQL_SELECT_USER_NAME_BY_POST_ID
            = "select username from user u join post p where p.user_id = u.user_id and p.post_id = ?";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Post SQL statements">
    // Posts
    private static final String SQL_INSERT_POST
            = "insert into post (user_id, post_title, post_content, post_datetime, expire_datetime, "
            + "flagged_for_review, draft, editor_comments) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_POST
            = "delete from post where post_id = ?";

    private static final String SQL_UPDATE_POST
            = "update post set user_id = ?, post_title = ?, post_content = ?, post_datetime = ?, "
            + "expire_datetime = ?, flagged_for_review = ?, draft = ?, editor_comments = ? where post_id = ?";

    private static final String SQL_SELECT_ALL_POSTS
            = "select * from post" + SQL_ORDER_BY_DATE_DESC;

    private static final String SQL_SELECT_POST
            = "select * from post where post_id = ?";

    private static final String SQL_SELECT_FLAGGED_FRAGMENT
            = "flagged_for_review = true or draft = true";

    private static final String SQL_SELECT_ALL_DRAFT_POSTS = "select * from post where draft = true";

    private static final String SQL_SELECT_ALL_REVIEW_POSTS = "select * from post where flagged_for_review = true";

    private static final String SQL_SELECT_VISIBLE_POST_WHERE_FRAGMENT
            = "flagged_for_review = false and draft = false and post_datetime < CURRENT_TIMESTAMP() and expire_datetime > CURRENT_TIMESTAMP()";

    private static final String SQL_SELECT_ALL_VISIBLE_POSTS
            = "select * from post where " + SQL_SELECT_VISIBLE_POST_WHERE_FRAGMENT + SQL_ORDER_BY_DATE_DESC;

    private static final String SQL_SELECT_ALL_FLAGGED_POSTS
            = "select * from post where " + SQL_SELECT_FLAGGED_FRAGMENT + SQL_ORDER_BY_DATE_DESC;

    private static final String SQL_SELECT_VISIBLE_POST_HEADERS
            = "select post_id, user_id, post_title, post_datetime, expire_datetime, flagged_for_review, draft from post where "
            + SQL_SELECT_VISIBLE_POST_WHERE_FRAGMENT + SQL_ORDER_BY_DATE_DESC;

    private static final String SQL_SELECT_ALL_POST_HEADERS
            = "select post_id, user_id, post_title, post_datetime, expire_datetime, flagged_for_review, draft from post"
            + SQL_ORDER_BY_DATE_DESC;

    private static final String SQL_SELECT_NEXT_VISIBLE_POSTS
            = SQL_SELECT_ALL_VISIBLE_POSTS + " LIMIT ?, ?";

    private static final String SQL_SELECT_VISIBLE_POST
            = "select * from post where " + SQL_SELECT_VISIBLE_POST_WHERE_FRAGMENT + " and post_id = ?";

    private static final String SQL_SELECT_POSTS_BY_CATEGORY_ID
            = "select * from post p join post_category pc on category_id where p.post_id = pc.post_id and pc.category_id = ?"
            + SQL_ORDER_BY_DATE_DESC;

    private static final String SQL_SELECT_ALL_VISIBLE_POSTS_BY_CATEGORY
            = "select * from post p join post_category pc on category_id where p.post_id = pc.post_id and pc.category_id = ? and "
            + SQL_SELECT_VISIBLE_POST_WHERE_FRAGMENT + SQL_ORDER_BY_DATE_DESC;

    private static final String SQL_SELECT_NEXT_VISIBLE_POSTS_BY_CATEGORY
            = SQL_SELECT_ALL_VISIBLE_POSTS_BY_CATEGORY + " LIMIT ?, ?";

    private static final String SQL_SELECT_POSTS_BY_TAG_ID
            = "select * from post p join post_tag pt on tag_id where p.post_id = pt.post_id and pt.tag_id = ?"
            + SQL_ORDER_BY_DATE_DESC;

    private static final String SQL_SELECT_ALL_VISIBLE_POSTS_BY_TAG
            = "select * from post p join post_tag pt on tag_id where p.post_id = pt.post_id and pt.tag_id = ? and "
            + SQL_SELECT_VISIBLE_POST_WHERE_FRAGMENT + SQL_ORDER_BY_DATE_DESC;

    private static final String SQL_SELECT_NEXT_VISIBLE_POSTS_BY_TAG
            = SQL_SELECT_ALL_VISIBLE_POSTS_BY_TAG + " LIMIT ?, ?";

    private static final String SQL_REJECT_POST = "UPDATE `post` SET `editor_comments` = ? WHERE post_id = ?";

    private static final String SQL_APPROVE_POST = "UPDATE `post` SET `flagged_for_review`= false WHERE post_id = ?";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Post_Category, Post_Tag SQL statements">
    private static final String SQL_INSERT_POST_CATEGORY
            = "insert into post_category (post_id, category_id) values (?, ?)";

    private static final String SQL_INSERT_POST_TAG
            = "insert into post_tag (post_id, tag_id) values (?, ?)";

    private static final String SQL_DELETE_POST_CATEGORY_BY_POST_ID
            = "delete from post_category where post_id = ?";

    private static final String SQL_DELETE_POST_TAG_BY_POST_ID
            = "delete from post_tag where post_id = ?";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Category SQL statements">
    // Categories
    private static final String SQL_INSERT_CATEGORY
            = "insert into category (category_name) values (?)";

    private static final String SQL_DELETE_CATEGORY
            = "delete from category where category_id = ?";

    private static final String SQL_UPDATE_CATEGORY
            = "update category set category_name = ? where category_id = ?";

    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from category" + SQL_ORDER_BY_CATEGORY_NAME_ASC;

    private static final String SQL_SELECT_CATEGORY
            = "select * from category where category_id = ?";

    private static final String SQL_SELECT_CATEGORY_BY_NAME
            = "select * from category where category_name = ?" + SQL_ORDER_BY_CATEGORY_NAME_ASC;

    private static final String SQL_SELECT_CATEGORY_BY_POST_ID
            = "select * from category c join post_category pc on post_id where c.category_id = pc.category_id "
            + "and pc.post_id = ?" + SQL_ORDER_BY_CATEGORY_NAME_ASC;

    private static final String SQL_SELECT_CATEGORY_NAME_BY_POST_ID
            = "select category_name from category c join post_category pc on post_id where c.category_id = pc.category_id "
            + "and pc.post_id = ?" + SQL_ORDER_BY_CATEGORY_NAME_ASC;

    private static final String SQL_SELECT_POST_CATEGORY_CATEGORY_ID_BY_POST_ID
            = "select category_id from post_category where post_id = ?";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tag SQL statements">
    // Tags
    private static final String SQL_INSERT_TAG
            = "insert into tag (tag_name) values (?)";

    private static final String SQL_DELETE_TAG
            = "delete from tag where tag_id = ?";

    private static final String SQL_UPDATE_TAG
            = "update tag set tag_name = ? where tag_id = ?";

    private static final String SQL_SELECT_ALL_TAGS
            = "select * from tag order by tag_id";

    private static final String SQL_SELECT_TAG
            = "select * from tag where tag_id = ?";

    private static final String SQL_SELECT_TAG_BY_NAME
            = "select * from tag where tag_name = ?";

    private static final String SQL_SELECT_TAG_BY_POST_ID
            = "select * from tag t join post_tag pt on post_id where t.tag_id = pt.tag_id and pt.post_id = ?";

    private static final String SQL_SELECT_TAG_NAME_BY_POST_ID
            = "select tag_name from tag t join post_tag pt on post_id where t.tag_id = pt.tag_id and pt.post_id = ?";

    private static final String SQL_SELECT_POST_TAG_TAG_ID_BY_POST_ID
            = "select tag_id from post_tag where post_id = ?";

    private static final String SQL_SELECT_POST_TAG_TAG_ID_ALL_INSTANCES
            = "select tag_id from post_tag order by tag_id";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Page SQL statements">
    private static final String SQL_INSERT_PAGE
            = "insert into page (user_id, page_name, page_content, display_index, parent_id) values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_PAGE
            = "delete from page where page_id = ?";

    private static final String SQL_UPDATE_PAGE
            = "update page set user_id = ?, page_name = ?, page_content = ?, display_index = ?, parent_id = ? where page_id = ?";

    private static final String SQL_SELECT_ALL_PAGES
            = "select * from page order by parent_id asc, display_index asc"; // TODO is display_index unique? also should page_content/post_content be blob?

    private static final String SQL_SELECT_PAGE
            = "select * from page where page_id = ?";

    private static final String SQL_SELECT_HIGHEST_INDEX
            = "select max(display_index) from page";

    private static final String SQL_SELECT_PARENT_PAGES
            = "select * from page where parent_id is null";

    // </editor-fold>
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="User methods">
    // Users
    // =====
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getUsername(), user.getPassword(), user.getRoleId());
        user.setUserId(jdbcTemplate.queryForObject(SQL_LAST_INDEX, Integer.class));
        return user;
    }

    @Override
    public void deleteUser(int userId) {
        jdbcTemplate.update(SQL_DELETE_USER, userId);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER, user.getUsername(), user.getPassword(), user.getUserId());
    }

    @Override
    public User getUserById(int userId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getUserByName(String userName) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME, new UserMapper(), userName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Post methods">
    // Posts
    // =====
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post addPost(Post post) {
        jdbcTemplate.update(SQL_INSERT_POST, post.getUserId(), post.getPostTitle(), post.getPostContent(),
                post.getPostDateTime().toString(), post.getExpDateTime().toString(), post.isFlaggedForReview(), post.isDraft(),
                post.getEditorComments());
        post.setPostId(jdbcTemplate.queryForObject(SQL_LAST_INDEX, Integer.class));
        insertPostCategory(post);
        insertPostTag(post);
        return post;
    }

    @Override
    public void deletePost(int postId) {
        jdbcTemplate.update(SQL_DELETE_POST, postId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updatePost(Post post) {
        jdbcTemplate.update(SQL_UPDATE_POST, post.getUserId(), post.getPostTitle(), post.getPostContent(),
                post.getPostDateTime().toString(), post.getExpDateTime().toString(), post.isFlaggedForReview(), post.isDraft(),
                post.getEditorComments(), post.getPostId());
        jdbcTemplate.update(SQL_DELETE_POST_CATEGORY_BY_POST_ID, post.getPostId());
        jdbcTemplate.update(SQL_DELETE_POST_TAG_BY_POST_ID, post.getPostId());
        insertPostCategory(post);
        insertPostTag(post);
    }

    @Override
    public void rejectPost(int postId, String comment) {
        jdbcTemplate.update(SQL_REJECT_POST, comment, postId);
    }

    @Override
    public void approvePost(int postId) {
        jdbcTemplate.update(SQL_APPROVE_POST, postId);
//        Post post;
//        post.setFlaggedForReview(false);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post getPostById(int postId) {
        return buildDisplayablePost(SQL_SELECT_POST, postId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post getVisiblePostById(int postId) {
        return buildDisplayablePost(SQL_SELECT_VISIBLE_POST, postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return formatPostList(SQL_SELECT_ALL_POSTS);
    }

    @Override
    public List<Post> getAllFlaggedPosts() {
        return formatPostList(SQL_SELECT_ALL_FLAGGED_POSTS);
    }

    @Override
    public List<Post> getAllDraftPosts() {
        return formatPostList(SQL_SELECT_ALL_DRAFT_POSTS);
    }

    @Override
    public List<Post> getAllReviewPosts() {
        return formatPostList(SQL_SELECT_ALL_REVIEW_POSTS);
    }

    /**
     * Returns a list of all posts that aren't flagged for review, draft status, or outside of date range.
     *
     * @return List<Post>
     */
    @Override
    public List<Post> getAllVisiblePosts() {
        return formatPostList(SQL_SELECT_ALL_VISIBLE_POSTS);
    }

    @Override
    public List<Post> getVisiblePostHeaders() {
        return jdbcTemplate.query(SQL_SELECT_VISIBLE_POST_HEADERS, new PostHeaderMapper());
    }

    @Override
    public List<Post> getAllPostHeaders() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POST_HEADERS, new PostHeaderMapper());
    }

    @Override
    public int getAllVisiblePostsSize() {
        return getAllVisiblePosts().size();
    }

    /**
     * Returns the next visible numberOfPosts posts from startingPost (1 being newest).
     *
     * @param startingPost as 1-base, starting from newest
     * @param numberOfPosts number of posts to return
     * @return List<Post>
     */
    @Override
    public List<Post> getNextVisiblePosts(int startingPost, int numberOfPosts) {
        return formatNextPostList(SQL_SELECT_NEXT_VISIBLE_POSTS, startingPost, numberOfPosts);
    }

    @Override
    public List<Post> getPostsByCategoryId(int categoryId) {
        return formatPostList(SQL_SELECT_POSTS_BY_CATEGORY_ID, categoryId);
    }

    @Override
    public List<Post> getVisiblePostsByCategoryId(int categoryId) {
        return formatPostList(SQL_SELECT_ALL_VISIBLE_POSTS_BY_CATEGORY, categoryId);
    }

    @Override
    public int getVisiblePostsByCategoryIdSize(int categoryId) {
        return getVisiblePostsByCategoryId(categoryId).size();
    }

    @Override
    public List<Post> getNextVisiblePostsByCategoryId(int categoryId, int startingPost, int numberOfPosts) {
        return formatNextPostList(SQL_SELECT_NEXT_VISIBLE_POSTS_BY_CATEGORY, categoryId, startingPost, numberOfPosts);
    }

    @Override
    public List<Post> getPostsByTagId(int tagId) {
        return formatPostList(SQL_SELECT_POSTS_BY_TAG_ID, tagId);
    }

    @Override
    public List<Post> getVisiblePostsByTagId(int tagId) {
        return formatPostList(SQL_SELECT_ALL_VISIBLE_POSTS_BY_TAG, tagId);
    }

    @Override
    public int getVisiblePostsByTagIdSize(int tagId) {
        return getVisiblePostsByTagId(tagId).size();
    }

    @Override
    public List<Post> getNextVisiblePostsByTagId(int tagId, int startingPost, int numberOfPosts) {
        return formatNextPostList(SQL_SELECT_NEXT_VISIBLE_POSTS_BY_TAG, tagId, startingPost, numberOfPosts);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Category methods">
    // Categories
    // ==========
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category addCategory(Category category) {
        jdbcTemplate.update(SQL_INSERT_CATEGORY, category.getName());
        category.setCategoryId(jdbcTemplate.queryForObject(SQL_LAST_INDEX, Integer.class));
        return category;
    }

    @Override
    public void deleteCategory(int categoryId) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY, category.getName(), category.getCategoryId());
    }

    @Override
    public Category getCategoryById(int categoryId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY, new CategoryMapper(), categoryId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Category getCategoryById(String name) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY_BY_NAME, new CategoryMapper(), name);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES, new CategoryMapper());
    }

    @Override
    public List<Category> getCategoriesByPostId(int postId) {
        return jdbcTemplate.query(SQL_SELECT_CATEGORY_BY_POST_ID, new CategoryMapper(), postId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tag methods">
    // Tags
    // ====
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tag addTag(Tag tag) {
        jdbcTemplate.update(SQL_INSERT_TAG, tag.getName());
        tag.setTagId(jdbcTemplate.queryForObject(SQL_LAST_INDEX, Integer.class));
        return tag;
    }

    @Override
    public void deleteTag(int tagId) {
        jdbcTemplate.update(SQL_DELETE_TAG, tagId);
    }

    @Override
    public void updateTag(Tag tag) {
        jdbcTemplate.update(SQL_UPDATE_TAG, tag.getName(), tag.getTagId());
    }

    @Override
    public Tag getTagById(int tagId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_TAG, new TagMapper(), tagId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Tag getTagById(String name) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_TAG_BY_NAME, new TagMapper(), name);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(SQL_SELECT_ALL_TAGS, new TagMapper());
    }

    @Override
    public List<Tag> getTagsByPostId(int postId) {
        return jdbcTemplate.query(SQL_SELECT_TAG_BY_POST_ID, new TagMapper(), postId);
    }

    /**
     * Generates a map of Tags and the number of their occurrences across all posts.
     *
     * @return Map<Tag, Integer>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Tag> getTopTags() {
        List<Tag> tagList = getAllTags();
        List<Integer> instanceList = jdbcTemplate.queryForList(SQL_SELECT_POST_TAG_TAG_ID_ALL_INSTANCES, Integer.class);

        List<Tag> topTagList = new ArrayList<>();

        for (Tag tag : tagList) {
            int tagCount = 0;

            if (instanceList.size() == 0) {
                break;
            }

            while (tag.getTagId() == instanceList.get(0)) {
                tagCount++;
                instanceList.remove(0);

                if (instanceList.size() == 0) {
                    break;
                }

            }
            tag.setNumberOfOccurences(tagCount);
            topTagList.add(tag);
        }

        return topTagList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Page methods">
    // Pages
    // =====
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Page addPage(Page page) {
        jdbcTemplate.update(SQL_INSERT_PAGE, page.getUserId(), page.getPageName(), page.getPageContent(),
                page.getDisplayIndex(), page.getParentId());
        page.setPageId(jdbcTemplate.queryForObject(SQL_LAST_INDEX, Integer.class));
        return page;
    }

    @Override
    public void deletePage(int pageId) {
        jdbcTemplate.update(SQL_DELETE_PAGE, pageId);
    }

    @Override
    public void updatePage(Page page) {
        jdbcTemplate.update(SQL_UPDATE_PAGE, page.getUserId(), page.getPageName(), page.getPageContent(),
                page.getDisplayIndex(), page.getParentId(), page.getPageId());
    }

    @Override
    public Page getPageById(int pageId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PAGE, new PageMapper(), pageId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Page> getAllPages() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PAGES, new PageMapper());
    }

    @Override
    public List<Page> getParentPages() {
        return jdbcTemplate.query(SQL_SELECT_PARENT_PAGES, new PageMapper());
    }

    @Override
    public Integer getHighestDisplayIndex() {
        Integer highestIndex = jdbcTemplate.queryForObject(SQL_SELECT_HIGHEST_INDEX, Integer.class);
        return (highestIndex == null) ? 0 : highestIndex;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Helper methods">
    // Helpers
    // =======
    private List<Post> formatPostList(String sqlStatement) {
        List<Post> postList = jdbcTemplate.query(sqlStatement, new PostMapper());
        for (Post post : postList) {
            buildPost(post);
        }
        return postList;
    }

    private List<Post> formatPostList(String sqlStatement, int columnId) {
        List<Post> postList = jdbcTemplate.query(sqlStatement, new PostMapper(), columnId);
        for (Post post : postList) {
            buildPost(post);
        }
        return postList;
    }

    private List<Post> formatNextPostList(String sqlStatement, int startingPost, int numberOfPosts) {
        if (startingPost > 0 && numberOfPosts > 0) {
            List<Post> nextPostList = jdbcTemplate.query(sqlStatement, new PostMapper(), startingPost - 1, numberOfPosts);

            for (Post post : nextPostList) {
                buildPost(post);
            }

            if (nextPostList.size() > 0) {
                for (int i = 0; i < nextPostList.size(); i++) {
                    Post currentPost = nextPostList.get(i);
                    if (currentPost.getPostContent().length() > MAX_VISIBLE_CHARACTERS) {
                        currentPost.setPostContent(currentPost.getPostContent().substring(0, MAX_VISIBLE_CHARACTERS) + "...");
                    }
                }
            }
            return nextPostList;
        } else {
            return null;
        }
    }

    private List<Post> formatNextPostList(String sqlStatement, int columnId, int startingPost, int numberOfPosts) {
        if (startingPost > 0 && numberOfPosts > 0) {
            List<Post> nextPostList = jdbcTemplate.query(sqlStatement, new PostMapper(), columnId, startingPost - 1, numberOfPosts);

            for (Post post : nextPostList) {
                buildPost(post);
            }

            if (nextPostList.size() > 0) {
                for (int i = 0; i < nextPostList.size(); i++) {
                    Post currentPost = nextPostList.get(i);
                    if (currentPost.getPostContent().length() > MAX_VISIBLE_CHARACTERS) {
                        currentPost.setPostContent(currentPost.getPostContent().substring(0, MAX_VISIBLE_CHARACTERS) + "...");
                    }
                }
            }
            return nextPostList;
        } else {
            return null;
        }
    }

    private void insertPostCategory(Post post) {
        final int postId = post.getPostId();
        final int[] categoryIds = post.getCategoryIds();

        jdbcTemplate.batchUpdate(SQL_INSERT_POST_CATEGORY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, postId);
                ps.setInt(2, categoryIds[i]);
            }

            @Override
            public int getBatchSize() {
                return categoryIds.length;
            }
        });
    }

    private int[] getCategoryIdsForPost(Post post) {
        List<Integer> categoryIds = jdbcTemplate.queryForList(SQL_SELECT_POST_CATEGORY_CATEGORY_ID_BY_POST_ID, new Integer[]{post.getPostId()}, Integer.class);
        int[] idArray = new int[categoryIds.size()];
        for (int i = 0; i < categoryIds.size(); i++) {
            idArray[i] = categoryIds.get(i);
        }
        return idArray;
    }

    private String[] getCategoryNamesForPostFromIds(int[] categoryIds) {
//        List<String> categoryNames = jdbcTemplate.queryForList(SQL_SELECT_CATEGORY_NAME_BY_POST_ID, new Integer[]{post.getPostId()}, String.class);
        String[] categoryNames = new String[categoryIds.length];
        for (int i = 0; i < categoryIds.length; i++) {
            categoryNames[i] = getCategoryById(categoryIds[i]).getName();
        }
        return categoryNames;
    }

    private void insertPostTag(Post post) {
        final int postId = post.getPostId();
        final int[] tagIds = post.getTagIds();

        jdbcTemplate.batchUpdate(SQL_INSERT_POST_TAG, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, postId);
                ps.setInt(2, tagIds[i]);
            }

            @Override
            public int getBatchSize() {
                return tagIds.length;
            }
        });
    }

    private int[] getTagIdsForPost(Post post) {
        List<Integer> tagIds = jdbcTemplate.queryForList(SQL_SELECT_POST_TAG_TAG_ID_BY_POST_ID, new Integer[]{post.getPostId()}, Integer.class);
        int[] idArray = new int[tagIds.size()];
        for (int i = 0; i < tagIds.size(); i++) {
            idArray[i] = tagIds.get(i);
        }
        return idArray;
    }

    private String[] getTagNamesForPostFromIds(int[] tagIds) {
//        List<String> tagNames = jdbcTemplate.queryForList(SQL_SELECT_TAG_NAME_BY_POST_ID, new Integer[]{post.getPostId()}, String.class);
        String[] tagNames = new String[tagIds.length];
        for (int i = 0; i < tagIds.length; i++) {
            tagNames[i] = getTagById(tagIds[i]).getName();
        }
        return tagNames;
    }

    private Post buildDisplayablePost(String sqlStatement, int postId) {
        try {
            Post post = jdbcTemplate.queryForObject(sqlStatement, new PostMapper(), postId);
            buildPost(post);
            return post;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private Post buildPost(Post post) {
        post.setCategoryIds(getCategoryIdsForPost(post));
        post.setTagIds(getTagIdsForPost(post));
        post.setCategoryNames(getCategoryNamesForPostFromIds(post.getCategoryIds()));
        post.setTagNames(getTagNamesForPostFromIds(post.getTagIds()));
        post.setUserName(jdbcTemplate.queryForObject(SQL_SELECT_USER_NAME_BY_POST_ID, String.class, post.getPostId()));
        return post;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Mappers">
    // Mappers
    // =======
    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));
            return user;
        }
    }

    private static final class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
            Post post = new Post();
            post.setPostId(rs.getInt("post_id"));
            post.setUserId(rs.getInt("user_id"));
            post.setPostTitle(rs.getString("post_title"));
            post.setPostContent(rs.getString("post_content"));
            post.setPostDateTime(rs.getTimestamp("post_datetime").toLocalDateTime());
            post.setExpDateTime(rs.getTimestamp("expire_datetime").toLocalDateTime());
            post.setFlaggedForReview(rs.getBoolean("flagged_for_review"));
            post.setDraft(rs.getBoolean("draft"));
            post.setEditorComments(rs.getString("editor_comments"));
            return post;
        }
    }

    private static final class PostHeaderMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
            Post post = new Post();
            post.setPostId(rs.getInt("post_id"));
            post.setUserId(rs.getInt("user_id"));
            post.setPostTitle(rs.getString("post_title"));
            post.setPostDateTime(rs.getTimestamp("post_datetime").toLocalDateTime());
            post.setExpDateTime(rs.getTimestamp("expire_datetime").toLocalDateTime());
            post.setFlaggedForReview(rs.getBoolean("flagged_for_review"));
            post.setDraft(rs.getBoolean("draft"));
            return post;
        }

    }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category cat = new Category();
            cat.setCategoryId(rs.getInt("category_id"));
            cat.setName(rs.getString("category_name"));
            return cat;
        }
    }

    private static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag tag = new Tag();
            tag.setTagId(rs.getInt("tag_id"));
            tag.setName(rs.getString("tag_name"));
            return tag;
        }
    }

    private static final class PageMapper implements RowMapper<Page> {

        @Override
        public Page mapRow(ResultSet rs, int i) throws SQLException {
            Page page = new Page();
            page.setPageId(rs.getInt("page_id"));
            page.setUserId(rs.getInt("user_id"));
            page.setPageName(rs.getString("page_name"));
            page.setPageContent(rs.getString("page_content"));
            page.setDisplayIndex(rs.getInt("display_index"));
            page.setParentId(rs.getInt("parent_id"));
            return page;
        }
    }
    // </editor-fold>
}
