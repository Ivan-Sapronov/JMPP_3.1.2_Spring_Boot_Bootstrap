package ru.sapronov.spring.boot.dto;

import lombok.Data;
import java.util.List;

/**
 * @author Ivan Sapronov on 22.02.2021
 * @project spring-boot-bootstrap
 */
@Data
public class UserDto {
    private Long id;
    private String username;
    private String firstname;
    private String surname;
    private int age;
    private String email;
    private String password;
    private List<String> roles;
}
