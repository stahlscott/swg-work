/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.menlopark.dao;

import com.tsg.menlopark.dto.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class CmsDaoTest {

    private CmsDao dao;
    private User user1;
    private User user2;
    private User user3;

    private Post post1;
    private Post post2;
    private Post post3;
    private Post post4;

    private Category category1;
    private Category category2;
    private Category category3;

    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    private Page page1;
    private Page page2;
    private Page page3;

    public CmsDaoTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("cmsDao", CmsDao.class);

        JdbcTemplate cleaner = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        cleaner.execute("delete from post_category");
        cleaner.execute("delete from post_tag");
        cleaner.execute("delete from category");
        cleaner.execute("delete from page");
        cleaner.execute("delete from post");
        cleaner.execute("delete from tag");
        cleaner.execute("delete from user");

        user1 = new User();
        user1.setName("admin");
        user1.setPassword("admin");
        user1.setRoleId(1);

        user2 = new User();
        user2.setName("editor");
        user2.setPassword("editor");
        user2.setRoleId(2);

        user3 = new User();
        user3.setName("writer");
        user3.setPassword("writer");
        user3.setRoleId(3);

        post1 = new Post();
        post1.setPostTitle("My first post");
        post1.setPostContent("Hello World!");
        post1.setPostDateTime(LocalDateTime.parse("2016-04-01T00:00:00"));
        post1.setExpDateTime(LocalDateTime.parse("9999-12-31T00:00:00"));
        post1.setFlaggedForReview(true); // should NOT be visible
        post1.setDraft(true);
        post1.setEditorComments("You really need more content than this.");
        post1.setCategoryIds(new int[0]);
        post1.setTagIds(new int[0]);
        post1.setCategoryNames(new String[0]);
        post1.setTagNames(new String[0]);

        post2 = new Post();
        post2.setPostTitle("My second post");
        post2.setPostContent("Hello-er World!!!!!!!");
        post2.setPostDateTime(LocalDateTime.parse("2001-01-01T00:00:00"));
        post2.setExpDateTime(LocalDateTime.parse("1753-01-01T00:00:00")); // should NOT be visible
        post2.setFlaggedForReview(true);
        post2.setDraft(false);
        post2.setEditorComments("");
        post2.setCategoryIds(new int[0]);
        post2.setTagIds(new int[0]);
        post2.setCategoryNames(new String[0]);
        post2.setTagNames(new String[0]);

        post3 = new Post();
        post3.setPostTitle("My third post");
        post3.setPostContent("Hello-est World! Spooky!!");
        post3.setPostDateTime(LocalDateTime.parse("2011-02-19T00:00:00"));
        post3.setExpDateTime(LocalDateTime.parse("2020-10-31T00:00:00")); // should be visible
        post3.setFlaggedForReview(false);
        post3.setDraft(false);
        post3.setEditorComments("This is your best work yet.");
        post3.setCategoryIds(new int[0]);
        post3.setTagIds(new int[0]);
        post3.setCategoryNames(new String[0]);
        post3.setTagNames(new String[0]);

        post4 = new Post();
        post4.setPostTitle("My fourth post");
        post4.setPostContent("Anothe serious professional blog post full of garbage.");
        post4.setPostDateTime(LocalDateTime.parse("2006-01-01T00:00:00"));
        post4.setExpDateTime(LocalDateTime.parse("9999-12-31T00:00:00")); // should be visible
        post4.setFlaggedForReview(false);
        post4.setDraft(false);
        post4.setEditorComments("Looks great!");
        post4.setCategoryIds(new int[0]);
        post4.setTagIds(new int[0]);
        post4.setCategoryNames(new String[0]);
        post4.setTagNames(new String[0]);

        category1 = new Category();
        category1.setName("products");

        category2 = new Category();
        category2.setName("reviews");

        category3 = new Category();
        category3.setName("sales");

        tag1 = new Tag();
        tag1.setName("first");

        tag2 = new Tag();
        tag2.setName("second");

        tag3 = new Tag();
        tag3.setName("third");

        page1 = new Page();
        page1.setPageName("first");
        page1.setPageContent("This page has a lot of stuff");
        page1.setDisplayIndex(0);
        //page1.setUserId(user1.getUserId());
        //page1.setParentId(page1.getPageId());

        page2 = new Page();
        page2.setDisplayIndex(1);
        page2.setPageName("second");
        page2.setPageContent("This page has even more stuff");
//        page2.setUserId(user1.getUserId());

        page3 = new Page();
        page3.setDisplayIndex(2);
        page3.setPageName("third");
        page3.setPageContent("this doesnt' have jack");
//        page3.setUserId(user1.getUserId());

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void addGetUpdateDeletePost() {
        preReqAddUsersAndPosts();

        // add one post, check that db can retrieve it
        dao.addPost(post1);
        assertEquals(post1, dao.getPostById(post1.getPostId()));
        // delete post, check that it doesn't exist when try to retrieve it
        dao.deletePost(post1.getPostId());
        assertNull(dao.getPostById(post1.getPostId()));

        dao.addPost(post1);
        dao.addPost(post2);
        dao.addPost(post3);

        // check that 2nd post adds and is deleted correctly
        assertEquals(post2, dao.getPostById(post2.getPostId()));
        dao.deletePost(post2.getPostId());
        assertNull(dao.getPostById(post2.getPostId()));

        // check that posts 1 and 3 are unaffected
        assertEquals(post1, dao.getPostById(post1.getPostId()));
        assertEquals(post3, dao.getPostById(post3.getPostId()));

        // test update on post 1
        post1.setEditorComments("Changed my mind. Looks great");
        dao.updatePost(post1);
        assertEquals(post1, dao.getPostById(post1.getPostId()));
    }

    @Test
    public void TEST_getAllPosts() {
        user1 = dao.addUser(user1);
        user2 = dao.addUser(user2);
        user3 = dao.addUser(user3);

        post1.setUserId(user1.getUserId());
        post2.setUserId(user2.getUserId());
        post3.setUserId(user3.getUserId());
        post4.setUserId(user2.getUserId());

        post1.setUserName(user1.getName());
        post2.setUserName(user2.getName());
        post3.setUserName(user3.getName());
        post4.setUserName(user2.getName());

        dao.addPost(post1);
        dao.addPost(post2);
        dao.addPost(post3);
        dao.addPost(post4);

        // Method to Test
        List<Post> postList = dao.getAllPosts();

        // Acceptance Criteria (get all posts; should be 4, in desc date order)
        assertEquals(4, postList.size());

        Post prevPost = null;
        for (Post p : postList) {
            if (prevPost != null) {
                assertTrue(prevPost.getPostDateTime().isAfter(p.getPostDateTime()));
            }
            prevPost = p;
        }
    }

    @Test
    public void listsOfPosts() {
        preReqAddUsersAndPosts();

        dao.addPost(post1);
        dao.addPost(post2);
        dao.addPost(post3);
        dao.addPost(post4);

        // get all posts; should be 4, in desc date order
        List<Post> postList = dao.getAllPosts();
        assertEquals(4, postList.size());
        assertEquals(post1, postList.get(0));
        assertEquals(post3, postList.get(1));
        assertEquals(post4, postList.get(2));
        assertEquals(post2, postList.get(3));

        // get all visible posts; should only be 2, should be in desc date order
        postList = dao.getAllVisiblePosts();
        assertEquals(2, postList.size());
        assertEquals(post3, postList.get(0));
        assertEquals(post4, postList.get(1));

        // get 1 visible post starting at position #2
        postList = dao.getNextVisiblePosts(2, 1);
        assertEquals(1, postList.size());
        assertEquals(post4, postList.get(0));

        // should only return only the 2 possible ones
        assertEquals(2, dao.getNextVisiblePosts(1, 10).size());

        // test incorrect requests to return size 0
        // or null if negative criteria entered
        assertEquals(0, dao.getNextVisiblePosts(5, 5).size());
        assertNull(dao.getNextVisiblePosts(-5, 10));
    }

    @Test
    public void addGetUpdateDeleteCategory() {
        // Pre-Requisites
        dao.addCategory(category1);
        dao.addCategory(category2);
        dao.addCategory(category3);

        // check that 1 category is correctly retrieved
        assertEquals(3, dao.getAllCategories().size());
        assertEquals(category1, dao.getCategoryById(category1.getCategoryId()));

        // delete category
        dao.deleteCategory(category1.getCategoryId());
        assertEquals(2, dao.getAllCategories().size());

        // retrieve remaining categories by id OR name
        assertEquals(category2, dao.getCategoryById(category2.getCategoryId()));
        assertEquals(category2, dao.getCategoryById(category2.getName()));
        assertEquals(category3, dao.getCategoryById(category3.getCategoryId()));
        assertEquals(category3, dao.getCategoryById(category3.getName()));

        // re-add
        dao.addCategory(category1);

        // update category should be successful, still 3 rows
        category2.setName("promotions");
        dao.updateCategory(category2);
        assertEquals(3, dao.getAllCategories().size());
        assertEquals(category2, dao.getCategoryById(category2.getCategoryId()));
    }

    @Test
    public void addPostCategories() {
        // Pre-req
        preReqAddUsersAndPosts();

        dao.addCategory(category1);
        dao.addCategory(category2);
        dao.addCategory(category3);

        // set categoryIds[] on posts
        post1.setCategoryIds(new int[]{category1.getCategoryId(), category2.getCategoryId()});
        post2.setCategoryIds(new int[]{});
        post3.setCategoryIds(new int[]{category2.getCategoryId()});
        post4.setCategoryIds(new int[]{category1.getCategoryId(), category2.getCategoryId(), category3.getCategoryId()});

        post1.setCategoryNames(new String[]{category1.getName(), category2.getName()});
        post2.setCategoryNames(new String[]{});
        post3.setCategoryNames(new String[]{category2.getName()});
        post4.setCategoryNames(new String[]{category1.getName(), category2.getName(), category3.getName()});

        // add posts to dao
        dao.addPost(post1);
        dao.addPost(post2);
        dao.addPost(post3);
        dao.addPost(post4);

        // check getting a list of categories by post id
        // also check that it returns in alpha order
        List<Category> categoryList = dao.getCategoriesByPostId(post4.getPostId());
        assertEquals(3, categoryList.size());
        assertEquals(category1, categoryList.get(0));
        assertEquals(category2, categoryList.get(1));
        assertEquals(category3, categoryList.get(2));

        // check getting a zero list on a post with no assigned categories
        categoryList = dao.getCategoriesByPostId(post2.getPostId());
        assertEquals(0, categoryList.size());

        // check that the correct category is returned on a 1-item list
        categoryList = dao.getCategoriesByPostId(post3.getPostId());
        assertEquals(1, categoryList.size());
        assertEquals(category2, categoryList.get(0));

        // check that all posts with category 2 return in desc order
        List<Post> postList = dao.getPostsByCategoryId(category2.getCategoryId());
        assertEquals(3, postList.size());
        assertEquals(post1, postList.get(0));
        assertEquals(post3, postList.get(1));
        assertEquals(post4, postList.get(2));

        // get a list of all posts with this category (only 1)
        postList = dao.getPostsByCategoryId(category3.getCategoryId());
        assertEquals(1, postList.size());
        assertEquals(post4, postList.get(0));

        // should return 2 visible posts that have listed category and are chronologically ordered
        postList = dao.getVisiblePostsByCategoryId(category2.getCategoryId());
        assertEquals(2, postList.size());
        assertEquals(post3, postList.get(0));
        assertEquals(post4, postList.get(1));

        // get 1 post starting at post 2 of visible posts
        postList = dao.getNextVisiblePostsByCategoryId(category2.getCategoryId(), 2, 1);
        assertEquals(1, postList.size());
        assertEquals(post4, postList.get(0));

    }

    @Test
    public void addGetUpdateDeleteTag() {
        // Pre-Requisites
        dao.addTag(tag1);
        dao.addTag(tag2);
        dao.addTag(tag3);

        // check that 1 tag is correctly retrieved
        Tag result = dao.getTagById(tag1.getTagId());
        assertEquals(tag1, result);
        assertEquals(3, dao.getAllTags().size());

        // delete tag
        dao.deleteTag(tag1.getTagId());

        // retrieve remaining tags by id OR name
        assertEquals(2, dao.getAllTags().size());
        assertEquals(tag2, dao.getTagById(tag2.getTagId()));
        assertEquals(tag2, dao.getTagById(tag2.getName()));
        assertEquals(tag3, dao.getTagById(tag3.getTagId()));
        assertEquals(tag3, dao.getTagById(tag3.getName()));

        // re-add
        dao.addTag(tag1);

        // update tag should be successful, still 3 rows
        tag2.setName("events");
        dao.updateCategory(category2);
        assertEquals(3, dao.getAllTags().size());
        assertEquals(tag1, dao.getTagById(tag1.getTagId()));
    }

    @Test
    public void addPostTags() {
        preReqAddUsersAndPosts();

        dao.addTag(tag1);
        dao.addTag(tag2);
        dao.addTag(tag3);

        // set tagIds[] on posts
        post1.setTagIds(new int[]{tag1.getTagId(), tag2.getTagId()});
        post2.setTagIds(new int[]{});
        post3.setTagIds(new int[]{tag2.getTagId()});
        post4.setTagIds(new int[]{tag1.getTagId(), tag2.getTagId(), tag3.getTagId()});

        post1.setTagNames(new String[]{tag1.getName(), tag2.getName()});
        post2.setTagNames(new String[]{});
        post3.setTagNames(new String[]{tag2.getName()});
        post4.setTagNames(new String[]{tag1.getName(), tag2.getName(), tag3.getName()});

        // add posts to dao
        dao.addPost(post1);
        dao.addPost(post2);
        dao.addPost(post3);
        dao.addPost(post4);

        // check getting a list of tags by post id
        // also check that it returns in alpha order
        List<Tag> tagList = dao.getTagsByPostId(post4.getPostId());
        assertEquals(3, tagList.size());
        assertEquals(tag1, tagList.get(0));
        assertEquals(tag2, tagList.get(1));
        assertEquals(tag3, tagList.get(2));

        // check getting a zero list on a post with no assigned tags
        tagList = dao.getTagsByPostId(post2.getPostId());
        assertEquals(0, tagList.size());

        // check that the correct tag is returned on a 1-item list
        tagList = dao.getTagsByPostId(post3.getPostId());
        assertEquals(1, tagList.size());
        assertEquals(tag2, tagList.get(0));

        // check that all posts with tag 2 return in desc order
        List<Post> postList = dao.getPostsByTagId(tag2.getTagId());
        assertEquals(3, postList.size());
        assertEquals(post1, postList.get(0));
        assertEquals(post3, postList.get(1));
        assertEquals(post4, postList.get(2));

        // get a list of all posts with this tag (only 1)
        postList = dao.getPostsByTagId(tag3.getTagId());
        assertEquals(1, postList.size());
        assertEquals(post4, postList.get(0));

        // should return 2 visible posts that have listed tag and are chronologically ordered
        postList = dao.getVisiblePostsByTagId(tag2.getTagId());
        assertEquals(2, postList.size());
        assertEquals(post3, postList.get(0));
        assertEquals(post4, postList.get(1));

        // get 1 post starting at post 2 of visible posts
        postList = dao.getNextVisiblePostsByTagId(tag2.getTagId(), 2, 1);
        assertEquals(1, postList.size());
        assertEquals(post4, postList.get(0));

        // check tag cloud/top tags function - should return 3 tags
        List<Tag> tagCloud = dao.getTopTags();
        assertEquals(3, tagCloud.size());

        // set numberOfOccurences as expected
        tag1.setNumberOfOccurences(2);
        tag2.setNumberOfOccurences(3);
        tag3.setNumberOfOccurences(1);
        
        // check that occurences are added as expected
        assertEquals(tag1, tagCloud.get(tagCloud.indexOf(tag1)));
        assertEquals(tag2, tagCloud.get(tagCloud.indexOf(tag2)));
        assertEquals(tag3, tagCloud.get(tagCloud.indexOf(tag3)));
    }

    private void preReqAddUsersAndPosts() {
        // Pre-req - add users
        user1 = dao.addUser(user1);
        user2 = dao.addUser(user2);
        user3 = dao.addUser(user3);

        // set post ids and names to user ids and names
        post1.setUserId(user1.getUserId());
        post2.setUserId(user2.getUserId());
        post3.setUserId(user3.getUserId());
        post4.setUserId(user2.getUserId());

        post1.setUserName(user1.getName());
        post2.setUserName(user2.getName());
        post3.setUserName(user3.getName());
        post4.setUserName(user2.getName());
    }

    @Test
    public void addGetDeletePagesTest() {
        preReqAddUsersAndPosts();

        //set up pages, add pages
        page1.setUserId(user1.getUserId());
        page1 = dao.addPage(page1);

        assertEquals(page1, dao.getPageById(page1.getPageId()));

        page2.setUserId(user1.getUserId());
        dao.addPage(page2);
        
        assertEquals(page2, dao.getPageById(page2.getPageId()));
        
        page3.setUserId(user1.getUserId());
        dao.addPage(page3);
        
        assertEquals(page3, dao.getPageById(page3.getPageId()));
        
        //should be 3 pages
        
        assertEquals(3, dao.getAllPages().size());
        
        //delete pages one at a time
        
        dao.deletePage(page3.getPageId());
        
        assertNull(dao.getPageById(page3.getPageId()));
        
        assertEquals(2, dao.getAllPages().size());
        
        //is it actually page 2 left?
        assertEquals(page2, dao.getPageById(page2.getPageId()));
        
        dao.deletePage(page2.getPageId());
        
        assertNull(dao.getPageById(page2.getPageId()));
        
        assertEquals(1, dao.getAllPages().size());
        
        //is it actually page 1 left?
        assertEquals(page1, dao.getPageById(page1.getPageId()));
        
        dao.deletePage(page1.getPageId());
        assertNull(dao.getPageById(page1.getPageId()));
        assertEquals(0, dao.getAllPages().size());
        
    }
    
    @Test
    public void updatePageTest() {
        preReqAddUsersAndPosts();
        
        page1.setUserId(user1.getUserId());
        page2.setUserId(user1.getUserId());
        page3.setUserId(user1.getUserId());
        
        dao.addPage(page1);
        dao.addPage(page2);
        dao.addPage(page3);
        
        assertEquals(page1, dao.getPageById(page1.getPageId()));
        assertEquals(page2, dao.getPageById(page2.getPageId()));
        assertEquals(page3, dao.getPageById(page3.getPageId()));
        
        //update the page
        String updated = "different content";
        
        page1.setPageContent(updated);
        dao.updatePage(page1);
        
        assertEquals(page1, dao.getPageById(page1.getPageId()));
        assertEquals(updated, page1.getPageContent());
        
        page2.setPageContent(updated);
        dao.updatePage(page2);
        
        assertEquals(page2, dao.getPageById(page2.getPageId()));
        assertEquals(updated, page2.getPageContent());
        
        //make sure page 3 hasnt been updated
        assertNotEquals(updated, page3.getPageContent());
        
        //actually update page3
        page3.setPageContent(updated);
        dao.updatePage(page3);
        
        assertEquals(page3, dao.getPageById(page3.getPageId()));
        assertEquals(updated, page3.getPageContent());
              
        
    }
}
