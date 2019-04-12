package top.codesky.forcoder.dao;

import org.springframework.stereotype.Repository;
import top.codesky.forcoder.domain.entity.User;

@Repository
public interface UserDao {
    User findByUsername(String username);
}
