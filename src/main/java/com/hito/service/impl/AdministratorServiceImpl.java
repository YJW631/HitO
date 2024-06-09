package com.hito.service.impl;

import com.hito.mapper.AdministratorMapper;
import com.hito.service.AdministratorService;
import com.hito.vo.Administrator;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator login(String username, String password) {
        return administratorMapper.findByUsernameAndPassword(username,password);
    }

    @Override
    public Administrator findAdministratorByToken(String token) {
        return administratorMapper.findByToken(token);
    }
}
