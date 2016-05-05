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
public class Tag {
    private int tagId;
    @NotEmpty(message = "You must enter a tag name.")
    @Length(max = 140, message="Tag must be no more than 140 characters.")
    private String name;
    private int numberOfOccurences;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfOccurences() {
        return numberOfOccurences;
    }

    public void setNumberOfOccurences(int numberOfOccurences) {
        this.numberOfOccurences = numberOfOccurences;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.tagId;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + this.numberOfOccurences;
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
        final Tag other = (Tag) obj;
        if (this.tagId != other.tagId) {
            return false;
        }
        if (this.numberOfOccurences != other.numberOfOccurences) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    
}
