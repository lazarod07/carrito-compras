package com.cristian.carrito.compras.business.domain.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristian.carrito.compras.business.domain.persistence.entity.Carrito;
import com.cristian.carrito.compras.business.domain.persistence.entity.Usuario;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

	Optional<Carrito> findByUsuario(Usuario usuario);

}
