package com.ironhack.midterm_project.service.user.impl;

import com.ironhack.midterm_project.model.user.Admin;
import com.ironhack.midterm_project.repository.user.AdminRepository;
import com.ironhack.midterm_project.service.user.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public void update(Long id, Admin admin) {
        Admin admin1 = adminRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));
        admin.setId(id);
        adminRepository.save(admin);
    }
    public void delete(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));
        adminRepository.delete(admin);
    }
}
