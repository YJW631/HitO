package com.hito.service;

import com.hito.vo.Administrator;
import com.hito.vo.User;

import java.util.List;

public interface AdministratorService {
    Administrator login(String username, String password);

    Administrator findAdministratorByToken(String token);

}
