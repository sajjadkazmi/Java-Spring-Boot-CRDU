package com.regexlms.regexlms.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    @GetMapping
    public List<Users> getUsers(){
        return usersService.getUsers();
    }
    @PostMapping
    public void registerNewUsers(@RequestBody Users users){
        usersService.addNewUsers(users);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUsers(@PathVariable("userId") Long userId ){
        usersService.deleteUsers(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUsers(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String middle_name,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String gender
    ){
        usersService.updateUsers(userId,name,email,surname,middle_name,username,gender);
    }
}
