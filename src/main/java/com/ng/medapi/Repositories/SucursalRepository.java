package com.ng.medapi.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ng.medapi.Models.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long>{
    List<Sucursal> findByNombreContainingIgnoreCase(String nombre);
}
