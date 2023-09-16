package com.videoappbackend.Controller;

import com.videoappbackend.Entity.UserData;
import com.videoappbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")


public class UserController {

@Autowired
  public UserRepository userRepository;


    @PostMapping("/UserLogin")
    public ResponseEntity<?> userLogin(@RequestBody UserData obj) {
        var user = userRepository.findByEmail(obj.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        if (user.getPassword().equals(obj.getPassword())) {
            return new ResponseEntity<>( user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( "Invalid Password", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/UpdatePassword/{email}")
    public ResponseEntity<?> updatePassword (@PathVariable String email, @RequestBody UserData obj){
        var admin = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Email not found"));
        admin.setPassword(obj.getPassword());
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

}
