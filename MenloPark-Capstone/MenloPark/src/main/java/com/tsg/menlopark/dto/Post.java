/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.menlopark.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class Post {
    private int postId;
    private int userId;
    private String userName;
    @NotEmpty(message = "You must enter a post title.")
    @Length(max = 140, message="Title must be no more than 140 characters.")
    private String postTitle;
    @NotEmpty(message = "You must enter post content.")
    private String postContent;
    private LocalDateTime postDateTime;
    private LocalDateTime expDateTime;
    private boolean flaggedForReview;
    private boolean draft;
    private String editorComments;
    private int[] tagIds;
    private int[] categoryIds;
    private String[] tagNames;
    private String[] categoryNames;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public LocalDateTime getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(LocalDateTime postDateTime) {
        this.postDateTime = postDateTime;
    }

    public LocalDateTime getExpDateTime() {
        return expDateTime;
    }

    public void setExpDateTime(LocalDateTime expDateTime) {
        this.expDateTime = expDateTime;
    }

    public boolean isFlaggedForReview() {
        return flaggedForReview;
    }

    public void setFlaggedForReview(boolean flaggedForReview) {
        this.flaggedForReview = flaggedForReview;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public String getEditorComments() {
        return editorComments;
    }

    public void setEditorComments(String editorComments) {
        this.editorComments = editorComments;
    }

    public int[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(int[] tagIds) {
        this.tagIds = tagIds;
    }

    public int[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(int[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String[] getTagNames() {
        return tagNames;
    }

    public void setTagNames(String[] tagNames) {
        this.tagNames = tagNames;
    }

    public String[] getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(String[] categoryNames) {
        this.categoryNames = categoryNames;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.postId;
        hash = 11 * hash + this.userId;
        hash = 11 * hash + Objects.hashCode(this.userName);
        hash = 11 * hash + Objects.hashCode(this.postTitle);
        hash = 11 * hash + Objects.hashCode(this.postContent);
        hash = 11 * hash + Objects.hashCode(this.postDateTime);
        hash = 11 * hash + Objects.hashCode(this.expDateTime);
        hash = 11 * hash + (this.flaggedForReview ? 1 : 0);
        hash = 11 * hash + (this.draft ? 1 : 0);
        hash = 11 * hash + Objects.hashCode(this.editorComments);
        hash = 11 * hash + Arrays.hashCode(this.tagIds);
        hash = 11 * hash + Arrays.hashCode(this.categoryIds);
        hash = 11 * hash + Arrays.deepHashCode(this.tagNames);
        hash = 11 * hash + Arrays.deepHashCode(this.categoryNames);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.flaggedForReview != other.flaggedForReview) {
            return false;
        }
        if (this.draft != other.draft) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.postTitle, other.postTitle)) {
            return false;
        }
        if (!Objects.equals(this.postContent, other.postContent)) {
            return false;
        }
        if (!Objects.equals(this.editorComments, other.editorComments)) {
            return false;
        }
        if (!Objects.equals(this.postDateTime, other.postDateTime)) {
            return false;
        }
        if (!Objects.equals(this.expDateTime, other.expDateTime)) {
            return false;
        }
        if (!Arrays.equals(this.tagIds, other.tagIds)) {
            return false;
        }
        if (!Arrays.equals(this.categoryIds, other.categoryIds)) {
            return false;
        }
        if (!Arrays.deepEquals(this.tagNames, other.tagNames)) {
            return false;
        }
        if (!Arrays.deepEquals(this.categoryNames, other.categoryNames)) {
            return false;
        }
        return true;
    }
    
    
    
}
