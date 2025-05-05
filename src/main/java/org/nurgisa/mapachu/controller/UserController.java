package org.nurgisa.mapachu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.dto.UserDTO;
import org.nurgisa.mapachu.exception.InvalidUserException;
import org.nurgisa.mapachu.model.User;
import org.nurgisa.mapachu.service.UserService;
import org.nurgisa.mapachu.util.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findAll().stream()
                .map(UserDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(UserDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new InvalidUserException("user not found")
                );
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidUserException(ErrorResponse.getErrorMessage(bindingResult));
        }

        if (userService.existsByUsername(dto.getUsername())) {
            throw new InvalidUserException("username already exists");
        }

        User saved = userService.save(dto.toEntity());
        return ResponseEntity.ok(UserDTO.fromEntity(saved));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id,
                                              @Valid @RequestBody UserDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new InvalidUserException(ErrorResponse.getErrorMessage(bindingResult));
        }

        return userService.update(id, dto.toEntity())
                .map(UserDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new InvalidUserException("user not found")
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        if (!userService.deleteById(id)) {
            throw new InvalidUserException("user not found");
        }

        return ResponseEntity.ok().build();
    }
}