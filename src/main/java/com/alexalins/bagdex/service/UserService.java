package com.alexalins.bagdex.service;

import com.alexalins.bagdex.domain.dto.UserDTO;
import com.alexalins.bagdex.domain.model.User;
import com.alexalins.bagdex.repository.UserRepository;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return null;
        }
        return UserDTO.create(user);
    }

    public UserDTO save(User user) {
        if(getUserByEmail(user.getEmail()) != null) {
            Assert.isNull(user.getEmail(), "Não foi possivel cadastrar, usuário já existe");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setSenha(encoder.encode(user.getSenha()));
        return UserDTO.create(userRepository.save(user));
    }
}
