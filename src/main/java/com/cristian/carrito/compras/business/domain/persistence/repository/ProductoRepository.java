package com.cristian.carrito.compras.business.domain.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristian.carrito.compras.business.domain.persistence.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
