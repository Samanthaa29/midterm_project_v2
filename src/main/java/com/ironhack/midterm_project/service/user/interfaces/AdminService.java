package com.ironhack.midterm_project.service.user.interfaces;

import com.ironhack.midterm_project.model.user.Admin;

public interface AdminService {
    void update(Long id, Admin admin);
    void delete(Long id);
}
