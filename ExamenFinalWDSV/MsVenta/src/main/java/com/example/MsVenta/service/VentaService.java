package com.example.MsVenta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsVenta.model.VentaModel;
import com.example.MsVenta.repository.IVentaRepository;


@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository repository;

    @Override
    public List<VentaModel> findAll() {
        return repository.findAll();
    }

    @Override
    public VentaModel findById(Long id) {
        Optional<VentaModel> estudianteOpt = repository.findById(id);
        return estudianteOpt.orElse(null);
    }

    @Override
    public VentaModel add(VentaModel model) {
        return repository.save(model);
    }

    @Override
    public VentaModel update(Long id, VentaModel model) {
        Optional<VentaModel> optionalVenta = repository.findById(id);
        if (optionalVenta.isPresent()) {
            VentaModel venta = optionalVenta.get();
            // Actualiza los campos necesarios
            
            venta.setProducto(model.getProducto());
            venta.setPrecio(model.getPrecio());
            venta.setCantidad(model.getCantidad());
            // Si tienes otros campos, actualízalos aquí

            return repository.save(venta);
        } else {
            return null; // O maneja el caso de no encontrado según tu lógica
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

