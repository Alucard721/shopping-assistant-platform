package com.niit.management.controller;

import com.niit.management.entity.User;
import com.niit.management.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping("/users")
    public String getAllStu(Model model){
        model.addAttribute("users", userservice.getAllUser());
        return "users";
    }

    //创建添加学生页面
    @GetMapping("/users/addUser")
    public String addUserForm(Model model){
        User User = new User();
        model.addAttribute("user", User);
        return "addUser";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute User User){
        userservice.addUser(User);
        return "redirect:/users";
    }

    //创建修改页面
    @GetMapping("/users/updUser/{id}")
    public String updateUserForm(@PathVariable Integer id, Model model){
        model.addAttribute("user", userservice.getUserById(id));
        return "updUser";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Integer id,
                             @ModelAttribute("user") User User,
                             Model model){
        //先找到要修改的学生
        User existingUser = userservice.getUserById(id);
        //更新信息
        existingUser.setId(id);
        existingUser.setNickName(User.getNickName());
        existingUser.setLoginName(User.getLoginName());
        existingUser.setPassword(User.getPassword());

        userservice.updateUser(existingUser);

        return "redirect:/users";

    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Integer id){
        userservice.deleteUserById(id);
        return  "redirect:/users";
    }
}
