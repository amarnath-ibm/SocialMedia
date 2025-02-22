// Suggested code may be subject to a license. Learn more: ~LicenseLog:153930587.
// Suggested code may be subject to a license. Learn more: ~LicenseLog:3666754269.
package com.example.socialmedia.user;

import java.time.LocalDate;
import java.util.List;

import com.example.socialmedia.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate ID
    private Long id;

    @Size(min=3, message = "Name should contain atleaset 3 characters")
    private String name;

    @Past
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public User(){
        
    }

    public User( String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
   
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}