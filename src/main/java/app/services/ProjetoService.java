package app.services;

import app.models.Projeto;
import app.repository.dao.ProjectDAO;
import app.repository.dao.interfaces.IProjetoDAO;
import app.services.interfaces.IProjetoService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ProjetoService implements IProjetoService {
    private IProjetoDAO projetoDao;

    public ProjetoService () {
        projetoDao = new ProjectDAO();
    }
    @Override
    public String save(Projeto projeto) throws SQLException {
        return !projetoDao.save(projeto) ? "Projeto criado com sucesso." :
                "O novo projeto não pôde ser criado.";
    }

    @Override
    public Projeto get(Long id) throws SQLException, ParseException {
        Projeto projeto = this.projetoDao.get(id);
        if (projeto == null) {
            throw new NullPointerException(String.format("Não há projeto com o ID %d cadastrado.", id));
        }
        return projetoDao.get(id);
    }

    @Override
    public List<Projeto> getAll() throws SQLException, ParseException {
        return projetoDao.getAll();
    }

    @Override
    public String update(Long id, Projeto projeto) throws SQLException {
        return !projetoDao.update(id, projeto) ? "Projeto " + projeto.getNomeProjeto() + " atualizado com sucesso." :
                "Projeto " + projeto.getNomeProjeto() + " não pôde ser atualizado";
    }

    @Override
    public String delete(Long id) throws SQLException {
        return !projetoDao.delete(id) ? "O projeto foi removido com sucesso.":
                "O projeto informado não pôde ser removido.";
    }
}
