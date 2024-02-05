package app.controllers;

import app.models.Projeto;
import app.services.ProjetoService;
import app.services.interfaces.IProjetoService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ProjetoController {
    IProjetoService projetoService;

    public ProjetoController() {
        projetoService = new ProjetoService();
    }

    public String createProjeto(Projeto projeto) throws SQLException, ParseException {
        return projetoService.save(projeto);
    }

    public List<Projeto> getAllProjetos() throws SQLException, ParseException {
        return projetoService.getAll();
    }

    public Projeto getProjeto(Long idProjeto) throws SQLException, ParseException {
        return projetoService.get(idProjeto);
    }

    public String updateProjeto(Long idProjeto, Projeto projeto) throws SQLException {
        return projetoService.update(idProjeto, projeto);
    }

    public String removerProjeto(Long idProjeto) throws SQLException {
        return projetoService.delete(idProjeto);
    }
}
