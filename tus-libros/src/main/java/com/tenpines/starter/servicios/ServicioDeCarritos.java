package com.tenpines.starter.servicios;

import com.tenpines.starter.modelo.Carrito;
import com.tenpines.starter.modelo.Cliente;
import com.tenpines.starter.repositorios.RepositorioDeCarritos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicioDeCarritos {

    @Autowired
    private RepositorioDeCarritos repo;

    @Autowired
    private ServicioDeCliente servicioDeCliente;

    @Transactional
    public void almacenar(Carrito carrito) {
        repo.save(carrito);
    }



    public Carrito buscarElCarrito(Long id) {return repo.findOne(id);}

    public List<Carrito> mostrarCarritos(){ return repo.findAll();}

    public Carrito nuevoCarrito(Cliente unCliente) {
        if(unCliente == null){
            throw new RuntimeException(mensajeDeErrorCuandoQuieroCrearUnCarritoConUsuarioInvalido());
        }
        Carrito carrito = Carrito.crearCarrito(servicioDeCliente.buscarElCliente(unCliente.getId()));
        almacenar(carrito);
        return carrito;
    }

    public static String mensajeDeErrorCuandoQuieroCrearUnCarritoConUsuarioInvalido() {
        return "Para crear un carrito debe estar logueado con un usuario valido";
    }

}
