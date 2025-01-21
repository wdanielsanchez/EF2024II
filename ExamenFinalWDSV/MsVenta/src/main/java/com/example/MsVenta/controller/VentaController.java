package com.example.MsVenta.controller;

import java.util.List;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.MsVenta.router.RouterApi;


import com.example.MsVenta.model.VentaModel;
import com.example.MsVenta.service.VentaService;



@RestController
@RequestMapping (RouterApi.VENTA)
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Método para listar estudiantes
    @GetMapping("/findAll")
    public List<VentaModel> findAll() {
        return ventaService.findAll();
    }

    // Crear estudiante
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody VentaModel model) {   
        VentaModel createdVenta = ventaService.add(model);
        return ResponseEntity.ok(createdVenta);
    }

    // Buscar estudiante por ID
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) { // Usar Long
        VentaModel venta = ventaService.findById(id);
        if (venta != null) {
            return ResponseEntity.ok(venta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar estudiante
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody VentaModel model) { // Usar Long
        VentaModel updatedVenta= ventaService.update(id, model);
        if (updatedVenta != null) {
            return ResponseEntity.ok(updatedVenta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar estudiante
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) { // Usar Long
        boolean isDeleted = ventaService.delete(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
