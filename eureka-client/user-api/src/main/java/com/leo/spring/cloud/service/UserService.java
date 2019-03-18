package com.leo.spring.cloud.service;

import com.leo.spring.cloud.domain.User;

import java.util.Collection;

public interface UserService {
    /**
     * 保存用户
     *
     * @param user User
     * @return 如果成功的话，返回<code>true</code>
     * 否则返回<code>false</code>
     */
    boolean save(User user);

    /**
     * 查询所有的用户对象
     *
     * @return 不会返回<code>null</code>
     */
    Collection<User> findAll();
}
