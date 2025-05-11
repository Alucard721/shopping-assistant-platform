package com.niit.interact.service;

import com.niit.interact.api.DTO.LoginDTO;
import com.niit.interact.api.DTO.RegisterDTO;
import com.niit.interact.entity.User;

public interface AuthService {

    User authenticate(LoginDTO loginDTO);

    User register(RegisterDTO registerDTO);


}
