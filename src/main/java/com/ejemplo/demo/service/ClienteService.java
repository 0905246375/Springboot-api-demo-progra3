package com.ejemplo.demo.service;

import com.ejemplo.demo.domain.model.Cliente;
import com.ejemplo.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cliente no encontrado con id " + id)
                );
    }

    @Transactional
    public Cliente actualizar(Long id, String nombre, String dpi) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cliente no encontrado con id " + id)
                );

        cliente.setNombre(nombre);
        cliente.setDpi(dpi);

        return cliente; 
    }
    
}