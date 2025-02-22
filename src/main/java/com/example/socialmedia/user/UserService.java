// Suggested code may be subject to a license. Learn more: ~LicenseLog:1885703639.
// Suggested code may be subject to a license. Learn more: ~LicenseLog:2055267791.
package com.example.socialmedia.user;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.socialmedia.repository.UserRepository;

@Service
public class UserService {
    // private static List<User> users = new ArrayList<>(); 
    // public static int userCount = 0;

    // static {
    //     users.add(new User(++userCount, "Vini", LocalDate.now().minusYears(24)));
    //     users.add(new User(++userCount, "Cama", LocalDate.now().minusYears(22)));
    //     users.add(new User(++userCount, "Jude", LocalDate.now().minusYears(21)));
    // }
    public UserRepository userRepository;

    UserService(UserRepository userRepository ){
        this.userRepository = userRepository;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        // users.add(user);
        // return user;
        return userRepository.save(user);
    }

    public User findUser(Long id) {
        // Predicate<User> predicate = user -> user.getId() == id;
        // return users.stream().filter(predicate).findFirst().orElse(null);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        return null;
    }

    public void deleteUser(Long id){
        // Predicate<User> predicate = user -> user.getId() == id;
        // users.removeIf(predicate);
        userRepository.deleteById(id);
        return ;
    }

    // public User updateUser(User user) {
    //     // deleteUser(user.getId());
    //     // users.add(user);
    //     // return user;
    //     return userRepository.save(user);
    // }
    
}