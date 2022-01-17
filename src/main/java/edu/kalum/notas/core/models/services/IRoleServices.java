package edu.kalum.notas.core.models.services;


import edu.kalum.notas.core.models.entities.Role;

import java.util.List;

public interface IRoleServices {
    List<Role> findAll();

    Role findById(Long paramLong);

    Role save(Role paramRole);

    void deleteById(Long paramLong);

    void delete(Role paramRole);
}
