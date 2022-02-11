package com.regexlms.regexlms.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers(){
        return usersRepository.findAll();
    }

    public void addNewUsers(Users users) {
        Optional<Users> usersOptional = usersRepository.findUsersByEmail(users.getEmail());

        if(usersOptional.isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        usersRepository.save(users);
    }

    public void deleteUsers(Long userId) {

        boolean exists = usersRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("User with id: "+userId+"does not exists");
        }
        usersRepository.deleteById(userId);
    }
    @Transactional
    public void updateUsers(Long userId,String name, String surname, String email, String middle_name, String username,String gender) {
        Users users = usersRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id "+userId+" does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(users.getName(),name)){
            users.setName(name);
        }
        else {
            throw new IllegalStateException("Invalid name");
        }
        if(email != null && email.length() > 0 && !Objects.equals(users.getEmail(),email)){

            Optional<Users> userOptional = usersRepository.findUsersByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("Email Taken");
            }
            users.setEmail(email);
        }
        else {
            throw new IllegalStateException("Invalid Email");
        }
        if(surname != null && surname.length() > 0 && !Objects.equals(users.getSurname(),surname)){
            users.setSurname(surname);
        }
        else {
            throw new IllegalStateException("Invalid surname");
        }
        if(middle_name != null && middle_name.length() > 0 && !Objects.equals(users.getMiddle_name(),middle_name)){
            users.setMiddle_name(middle_name);
        }
        else {
            throw new IllegalStateException("Invalid middle_name");
        }

        if(gender != null && gender.length() > 0 && !Objects.equals(users.getGender(),gender)){
            users.setGender(gender);
        }
        else {
            throw new IllegalStateException("Invalid gender");
        }
        if(username != null && username.length() > 0 && !Objects.equals(users.getUsername(),username)){
            users.setUsername(username);
        }
        else {
            throw new IllegalStateException("Invalid username");
        }
    }
}
