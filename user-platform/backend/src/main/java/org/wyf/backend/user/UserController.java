package org.wyf.backend.user;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 还是构造器注入，和 Service 里的套路一模一样
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/health")
    public String health() {
        return "This is a user health check";
    }

    // POST /users —— 注册
    @PostMapping                    
    public UserEntity register(@RequestBody UserEntity user) {
        return userService.register(user);
    }

    //
    @GetMapping("/list")
    public List<UserEntity> list() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public UserEntity get(@PathVariable Long id) {
        return userService.getById(id);
    }
}

