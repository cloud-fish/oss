package org.wyf.backend.user;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 注册新用户：先查重，再保存
     */
    public UserEntity register(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("用户名已存在: " + user.getUsername());
        }
        return userRepository.save(user);
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
}