package com.ironhack.midterm_project.service.user.interfaces;

import com.ironhack.midterm_project.model.user.ThirdParty;

public interface ThirdPartyService {
    void update(Long id, ThirdParty thirdParty);
    void delete(Long id);
}
