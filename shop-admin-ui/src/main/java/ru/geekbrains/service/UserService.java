package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.model.User;
import ru.geekbrains.repo.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<User> filterByAge(Integer minAge, Integer maxAge, String username, Pageable pageable) {
//        if (minAge==0 && maxAge==0)
//            return repository.findAll();
//        if (minAge==0)
//            return repository.findByAgeGreaterThan(minAge);
//        if (maxAge==0)
//            return repository.findByAgeLessThan(maxAge);
        return repository.findByAgeGreaterThanEqualAndAgeLessThanEqualAndUsernameLike(minAge, maxAge, username+"%", pageable);
    }

    @Transactional
    public void save(User user) {
      //  user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.toString());
        repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Transactional
    public void editUser(User user){
        Optional<User> userFromDB = repository.findByUsername(user.getUsername());
        if(userFromDB.isPresent()){
            User u = userFromDB.get();
            u.setAge(user.getAge());
            u.setEmail(user.getEmail());
            u.setUsername(user.getUsername());
            System.out.println("id="+u.getId());
            repository.save(u);
        }
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
