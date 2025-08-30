package com.clinica.clinica_app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.clinica.clinica_app.models.Role;
import com.clinica.clinica_app.models.User;

@Service
public class UserService {
    
    private List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    public User createUser(String firstName, String lastName, String email, 
                          String password, Role role, String documentId, 
                          LocalDate birthDate, String gender, String address, 
                          String phoneNumber) {
        
        User user = new User();
        user.setId(nextId++);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setDocumentId(documentId);
        user.setBirthDate(birthDate);
        user.setGender(gender);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User getUserById(Long id) {
        Optional<User> user = users.stream()
                                .filter(u -> u.getId().equals(id))
                                .findFirst();
        return user.orElse(null);
    }

    public boolean deleteUserById(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

    public List<User> getUsersByRole(Role role) {
        return users.stream()
                   .filter(user -> user.getRole() == role)
                   .toList();
    }

    public User getUserByEmail(String email) {
        return users.stream()
                   .filter(user -> user.getEmail().equalsIgnoreCase(email))
                   .findFirst()
                   .orElse(null);
    }

    public User getUserByDocumentId(String documentId) {
        return users.stream()
                   .filter(user -> user.getDocumentId().equals(documentId))
                   .findFirst()
                   .orElse(null);
    }
}