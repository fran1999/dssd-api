package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.Users;
import com.dssd.BackendApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }

    @Override
    public Iterable<Users> getAllUsers() {
        try{
            return this.userRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
