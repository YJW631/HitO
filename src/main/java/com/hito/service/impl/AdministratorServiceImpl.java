package com.hito.service.impl;

import com.hito.mapper.AdministratorMapper;
import com.hito.service.AdministratorService;
import com.hito.vo.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;

    /**
     * 登录
     */
    @Override
    public String login(String username, String hash2, String verificationCode) {
        String hash1_db = administratorMapper.selectByUsername(username);
        if (hash1_db == "") {
            return "";
        }
        String hash2_nd = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest((hash1_db + verificationCode).getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : md5) {
                sb.append(String.format("%02x", b));
            }
            hash2_nd = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (hash2_nd.equals(hash2)) {
            return hash1_db;
        }
        return "";
    }

    /**
     * 根据token查询管理员
     */
    @Override
    public Administrator findAdministratorByToken(String token) {
        return administratorMapper.findByToken(token);
    }

    /**
     * 根据username获取token
     */
    @Override
    public String getToken(String username) {
        return administratorMapper.selectTokenByUsername(username);
    }
}
