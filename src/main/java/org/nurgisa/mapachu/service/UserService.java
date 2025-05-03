package org.nurgisa.mapachu.service;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.User;
import org.nurgisa.mapachu.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> update(Long id, User user) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setUsername(user.getUsername());
                    return userRepository.save(existing);
                });
    }

    public boolean deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
