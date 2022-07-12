package com.ironhack.midterm_project.controller.user.impl;

import com.ironhack.midterm_project.controller.user.interfaces.AdminController;
import com.ironhack.midterm_project.model.user.Admin;
import com.ironhack.midterm_project.repository.user.AdminRepository;
import com.ironhack.midterm_project.security.CustomUserDetails;
import com.ironhack.midterm_project.service.user.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminControllerImpl implements AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> findAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return adminRepository.findAll();
    }

    @GetMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Admin findById(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable(name = "id") Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        String password = passwordEncoder.encode(optionalAdmin.get().getPassword());
        return optionalAdmin.get();
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin store(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid Admin admin) {
        return adminRepository.save(admin);
    }

    @PutMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id, @RequestBody @Valid Admin admin) {
        adminService.update(id, admin);
    }

    @DeleteMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        adminService.delete(id);
    }
}
