package com.ironhack.midterm_project.controller.account.dto;



public class CheckingSecretKeyDTO {

    private String SecretKey;

    public CheckingSecretKeyDTO() {
    }

    public CheckingSecretKeyDTO(String secretKey) {
        SecretKey = secretKey;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }
}
