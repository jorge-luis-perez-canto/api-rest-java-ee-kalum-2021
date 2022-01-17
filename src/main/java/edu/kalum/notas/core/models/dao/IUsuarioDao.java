package edu.kalum.notas.core.models.dao;

import edu.kalum.notas.core.models.entities.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String paramString);

    @Query("select u from Usuario u where u.apellidos like %?1% ")
    List<Usuario> findLikeApellidos(String paramString);

    Usuario findByEmail(String paramString);
}
