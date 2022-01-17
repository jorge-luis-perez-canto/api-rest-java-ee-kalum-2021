package edu.kalum.notas.core.models.dao;

import edu.kalum.notas.core.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDao extends JpaRepository<Role, Long> {
}
