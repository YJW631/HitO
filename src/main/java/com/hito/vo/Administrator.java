package com.hito.vo;

import lombok.Data;

@Data
public class Administrator {
    private Integer id;
    private String username;
    private String password;
    private String administratorToken;
}
