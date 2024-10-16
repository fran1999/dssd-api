package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.Users;

public interface UserService {
    public Users save(Users users);
    public Iterable<Users> getAllUsers();
}
