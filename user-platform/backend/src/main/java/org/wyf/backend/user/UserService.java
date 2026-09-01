package org.wyf.backend.user;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 新增用户
     */
    public UserEntity register(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("用户名已存在: " + user.getUsername());
        }

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        
        return userRepository.save(user);
    }
    

    /**
     * 更新用户
     */
    public UserEntity update(UserEntity user) {
        if (!userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("用户不存在: " + user.getId());
        }
        return userRepository.save(user);
    }

    /**
     * 删除用户
     */
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("用户不存在: " + id);
        }
        userRepository.deleteById(id);
    }

    /**
     * 列出所有用户
     */
    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }

    /**
     * 根据ID获取用户
     */
    public UserEntity getById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
    }

    /**
     * 用户登录：验证用户名和密码
     */
    public UserEntity login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + username));
        
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("密码错误");
        }
        
        return user;
    }

    /**
     * 用户修改密码
     */ 
    public void changePassword(Long id, String newPassword) {
        UserEntity user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
        user.setPassword(newPassword);
        userRepository.save(user);  // save 会自动更新已有记录
    }
}