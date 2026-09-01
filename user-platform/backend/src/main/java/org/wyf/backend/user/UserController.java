package org.wyf.backend.user;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 还是构造器注入，和 Service 里的套路一模一样
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 健康检查
     */

    @GetMapping("/health")
    public String health() {
        return "This is a user health check";
    }

    /**
     * 注册用户
     */
    @PostMapping("/register")                    
    public UserEntity register(@RequestBody UserEntity user) {
        return userService.register(user);
    }

    /**
     * 列出所有用户
     */
    @GetMapping("/list")
    public List<UserEntity> list() {
        return userService.listAll();
    }

    /**
     * 根据ID获取用户
     */
    @GetMapping("/{id}")
    public UserEntity get(@PathVariable Long id) {
        return userService.getById(id);
    }

    /**
     * 更新用户
     */
    @PostMapping("/update")
    public UserEntity update(@RequestBody UserEntity user) {
        return userService.update(user);
    }
    
    /**
     * 删除用户
     */
    @PostMapping("/delete")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    /**
     * 用户登录
     */ 
    @PostMapping("/login")
    public UserEntity login(@RequestBody UserEntity user) {
        return userService.login(user.getUsername(), user.getPassword());
    }
}

