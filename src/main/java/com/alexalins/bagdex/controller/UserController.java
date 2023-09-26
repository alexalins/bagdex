package com.alexalins.bagdex.controller;

import com.alexalins.bagdex.domain.dto.UserDTO;
import com.alexalins.bagdex.domain.model.User;
import com.alexalins.bagdex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity get() {
        List<UserDTO> list = userService.getUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/info")
    public UserDTO userInfo(@AuthenticationPrincipal User user) {
        return UserDTO.create(user);
    }

    @PostMapping("/cadastro")
    public ResponseEntity save(@RequestBody User user) {
        UserDTO u = userService.save(user);
        return ResponseEntity.created(getUri(u.getId())).build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
