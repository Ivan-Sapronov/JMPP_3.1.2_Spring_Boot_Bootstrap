package ru.sapronov.spring.boot.services;

import ru.sapronov.spring.boot.models.Role;

/**
 * @author Ivan Sapronov on 20.02.2021
 * @project spring-boot-bootstrap
 */
public interface RoleService {

    void deleteRole(long id);

    Role getRoleByName(String role);
}
