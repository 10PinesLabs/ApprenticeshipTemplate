package com.tenpines.starter.modelo;

import javax.persistence.*;

/**
 * Created by pino on 15/09/16.
 */

@Entity
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column
    public String password;

    //PERSISTENCIA
    private void setId(Long unId) {
        this.id_cliente = unId;
    }

    public Long getId() {
        return id_cliente;
    }

    private void setPassword(String pass){this.password = pass;}

    public String getPassword() {return password;}

    public Cliente(){}

    public static Cliente crearCliente(String password) {
        Cliente cliente = new Cliente();
        cliente.setPassword(password);
        return cliente;
    }
}
