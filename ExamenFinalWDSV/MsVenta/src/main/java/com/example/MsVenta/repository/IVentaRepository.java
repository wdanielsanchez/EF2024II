package com.example.MsVenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MsVenta.model.VentaModel;


public interface IVentaRepository extends JpaRepository<VentaModel, Long> {
    // Métodos personalizados si los hay
}