package com.example.socialmedia.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UserController{
    private UserService userService;
    
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity< List<User> > getAllUsers(){
        System.out.println("providing all users");
        return ResponseEntity.ok(userService.findAll());
    } 

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> getUser(@PathVariable Long id){
        System.out.println("ID " + id);
        User user = userService.findUser(id);
        if(user == null)
            throw new UserNotFoundException("User doesn't exist with id: " + id);

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
        
        return ResponseEntity.ok(entityModel);
        // return ResponseEntity.ok(user);
    } 

    // @PostMapping("/users")
    // public ResponseEntity<User> createUser(@Valid @RequestBody User user){
    //     User savedUser = userService.save(user);
    //     URI location = ServletUriComponentsBuilder.fromCurrentRequest()
    //                     .path("/{id}")
    //                     .buildAndExpand(savedUser.getId())
    //                     .toUri();
    //     return ResponseEntity.created(location).build();
    // }

    @PostMapping("/users")
    public ResponseEntity<EntityModel<User>> createUser(@Valid @RequestBody User user){
        User savedUser = userService.save(user);
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getUser(savedUser.getId()));
        URI location = linkBuilder.toUri();
        EntityModel<User> entityModel = EntityModel.of(user);
        entityModel.add(linkBuilder.withRel("user-link"));
        return ResponseEntity.created(location).body(entityModel);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }
    }