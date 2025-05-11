package com.niit.interact.serviceImpl;

import com.niit.interact.api.DTO.LoginDTO;
import com.niit.interact.api.DTO.RegisterDTO;
import com.niit.interact.entity.User;
import com.niit.interact.repository.UserRepository;
import com.niit.interact.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User authenticate(LoginDTO loginDTO) {
        User user = userRepository.findByLoginName(loginDTO.getLoginName());
        if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User register(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("前后密码不符");
        }

        if (userRepository.findByLoginName(registerDTO.getLoginName()) != null) {
            throw new IllegalArgumentException("用户名已存在");
        }

        User newUser = new User();
        newUser.setLoginName(registerDTO.getLoginName());
        newUser.setPassword(registerDTO.getPassword());

        return userRepository.save(newUser);
    }
}
