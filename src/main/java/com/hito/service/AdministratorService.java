package com.hito.service;

import com.hito.vo.Administrator;
import com.hito.vo.User;

import java.util.List;

public interface AdministratorService {
    String login(String username, String hash2, String verificationCode);

    Administrator findAdministratorByToken(String token);

    String getToken(String username);
}
