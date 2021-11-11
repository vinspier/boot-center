package com.vinspier.search.model.param;

import lombok.Data;

@Data
public class UserParam {

    private Long id;

    private String username;

    private Integer age;

    private Integer ageMin;

    private Integer ageMax;

    private String phone;

}
