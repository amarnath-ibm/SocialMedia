package com.example.socialmedia.post;
import com.example.socialmedia.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@NotNull(message = "Title is mandatory")
    @Size(min=2, message = "Title should have atleast 2 characters")
    private String title;
    //@NotNull(message = "Description is mandatory")
    @Size(min=2, message = "Descripiton should have atleast 2 characters")
    private String description;

    @JsonIgnore    
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post() {
    }  

    public Post(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }



}
