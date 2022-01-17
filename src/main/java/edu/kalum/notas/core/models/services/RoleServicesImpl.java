package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.dao.IRoleDao;
import edu.kalum.notas.core.models.entities.Role;
import edu.kalum.notas.core.models.services.IRoleServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServicesImpl implements IRoleServices {
    @Autowired
    private IRoleDao roleDao;

    public List<Role> findAll() {
        return this.roleDao.findAll();
    }

    public Role findById(Long id) {
        return this.roleDao.findById(id).orElse(null);
    }

    public Role save(Role role) {
        return (Role) this.roleDao.save(role);
    }

    public void deleteById(Long id) {
        this.roleDao.deleteById(id);
    }

    public void delete(Role role) {
        this.roleDao.delete(role);
    }
}