package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.model.Role;
import ru.geekbrains.repo.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional
    public void editRole(Role role){
        Optional<Role> roleFromDB = roleRepository.findByName(role.getName());
        if(roleFromDB.isPresent()){
            Role p = roleFromDB.get();
            p.setName(role.getName());
            roleRepository.save(p);
        }
    }

    @Transactional
    public void deleteRole(long id){
        roleRepository.deleteById(id);
    }

    @Transactional
    public Page<Role> filterName (String partName, Pageable pageable) {
        String pName = partName+"%";
        return roleRepository.findByNameLike(pName, pageable);
    }

    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    public Optional<Role> findById(long id) {
        return roleRepository.findById(id);
    }
}
