package app.controllers;

import app.models.Cliente;
import app.services.ClienteService;
import app.services.interfaces.IClientService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ClienteController {
    IClientService clientService;

    public ClienteController() {
        this.clientService = new ClienteService();
    }

    public String createCliente(Cliente cliente) throws SQLException, ParseException {
        return clientService.save(cliente);
    }

    public Cliente get(Long idCliente) throws SQLException, ParseException, NullPointerException {
        return clientService.get(idCliente);
    }

    public List<Cliente> getAll() throws SQLException, ParseException {
        return clientService.getAll();
    }

    public String updateCliente(Long idCliente, Cliente cliente) throws SQLException {
        return this.clientService.update(idCliente, cliente);
    }

    public String removerCliente(Long idCliente) throws SQLException {
        return this.clientService.delete(idCliente);
    }
}
