package top.codesky.forcoder.dao;

import org.springframework.stereotype.Repository;
import top.codesky.forcoder.model.entity.User;

@Repository
public interface UserDao {
    User findByUsername(String username);
}
