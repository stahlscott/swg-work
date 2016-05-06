/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.menlopark.dto;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class Page {
    private int pageId;
    private int userId;
    @NotEmpty(message = "You must enter a post name.")
    @Length(max = 140, message="Name must be no more than 140 characters.")
    private String pageName;
    @NotEmpty(message = "You must enter page content.")
    private String pageContent;
    private int displayIndex;
    private Integer parentId; //settable to null, as per database requirements

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int authorId) {
        this.userId = authorId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public int getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(int displayIndex) {
        this.displayIndex = displayIndex;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public int hashCode() { //updated hashcode for Integer object
        int hash = 7;
        hash = 97 * hash + this.pageId;
        hash = 97 * hash + this.userId;
        hash = 97 * hash + Objects.hashCode(this.pageName);
        hash = 97 * hash + Objects.hashCode(this.pageContent);
        hash = 97 * hash + this.displayIndex;
        hash = 97 * hash + Objects.hashCode(this.parentId);
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
        final Page other = (Page) obj;
        if (this.pageId != other.pageId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.displayIndex != other.displayIndex) {
            return false;
        }
        if (!Objects.equals(this.pageName, other.pageName)) {
            return false;
        }
        if (!Objects.equals(this.pageContent, other.pageContent)) {
            return false;
        }
        if (!Objects.equals(this.parentId, other.parentId)) {
            return true;
        }
        return true;
    }
}
