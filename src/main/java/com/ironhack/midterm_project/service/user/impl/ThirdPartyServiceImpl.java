package com.ironhack.midterm_project.service.user.impl;

import com.ironhack.midterm_project.model.user.ThirdParty;
import com.ironhack.midterm_project.repository.user.ThirdPartyRepository;
import com.ironhack.midterm_project.service.user.interfaces.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    public void update(Long id, ThirdParty thirdParty) {
        ThirdParty thirdParty1 = thirdPartyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party not found"));
        thirdParty.setId(id);
        thirdPartyRepository.save(thirdParty);
    }
    public void delete(Long id) {
        ThirdParty thirdParty = thirdPartyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party not found"));
        thirdPartyRepository.delete(thirdParty);
    }
}
