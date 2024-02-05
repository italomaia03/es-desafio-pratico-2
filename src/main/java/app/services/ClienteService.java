package app.services;

import app.models.Cliente;
import app.repository.dao.ClienteDAO;
import app.repository.dao.interfaces.IClienteDAO;
import app.services.interfaces.IClientService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ClienteService implements IClientService {
    private IClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public String save(Cliente cliente) throws SQLException {
        if(!clienteDAO.save(cliente)) {
            return "Cliente criado com sucesso.";
        }
        return null;
    }

    @Override
    public Cliente get(Long id) throws SQLException, ParseException, NullPointerException {
        Cliente cliente = clienteDAO.get(id);
        if (cliente == null) {
            throw new NullPointerException(String.format("Não há cliente com o ID %d cadastrado.", id));
        }
        return cliente;
    }

    @Override
    public List<Cliente> getAll() throws SQLException, ParseException {
        return clienteDAO.getAll();
    }

    @Override
    public String update(Long id, Cliente cliente) throws SQLException {
        return !clienteDAO.update(id, cliente) ? "Cliente " + cliente.getNome() + " foi atualizado com sucesso." :
                "O cliente informado não pôde ser atualizado.";
    }

    @Override
    public String delete(Long id) throws SQLException {
        return !clienteDAO.delete(id) ? "Cliente deletado com sucesso." :
                "O cliente informado não pôde ser deletado.";
    }
}
