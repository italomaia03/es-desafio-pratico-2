package app.models;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Projeto {
    private Long idProjeto;
    private String nomeProjeto;
    private String localizacaoProjeto;
    private String escopoInicial;
    private Date dataInicioPrevista;
    private Boolean isFinalizado;
    private Long clienteProjetoId;
    
    
    
    public Projeto(Long idProjeto, String nomeProjeto, String localizacaoProjeto, String escopoInicial,
        String dataInicioPrevista, Boolean isFinalizado, Long clienteProjetoId) throws ParseException, NullPointerException {

      this.idProjeto = idProjeto;
      this.nomeProjeto = nomeProjeto;
      this.localizacaoProjeto = localizacaoProjeto;
      this.escopoInicial = escopoInicial;
      this.dataInicioPrevista = formatarData(dataInicioPrevista);
      this.isFinalizado = isFinalizado;
      this.clienteProjetoId = clienteProjetoId;
      validarProjeto();
    }

    private Date formatarData(String data) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(data);
    }
    public Projeto(String nomeProjeto, String localizacaoProjeto, String escopoInicial, String dataInicioPrevista, Long clienteProjetoId) throws ParseException {
        this(null, nomeProjeto, localizacaoProjeto, escopoInicial, dataInicioPrevista,false, clienteProjetoId);
    }

    private void validarProjeto() throws NullPointerException {
        List<String> atributosNull = new ArrayList<>();
        String mensagemErro = "Atributos que não podem ficar vazios: \n\t";
        Field[] atributos = this.getClass().getDeclaredFields();
        Arrays.stream(atributos)
                        .filter(atributo -> {
                            try {
                                return atributo.get(this) == null && !atributo.getName().contains("id");
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .forEach(atributo -> atributosNull.add(atributo.getName()));
        if (!atributosNull.isEmpty()) {
            for(String atributo : atributosNull){
                mensagemErro += atributo + "\n\t";
            }
            throw new NullPointerException(mensagemErro);
        }

    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }
    
    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }
    
    public String getLocalizacaoProjeto() {
        return localizacaoProjeto;
    }

    public void setLocalizacaoProjeto(String localizacaoProjeto) {
        this.localizacaoProjeto = localizacaoProjeto;
    }

    public Boolean getFinalizado() {
        return isFinalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        isFinalizado = finalizado;
    }

    public Long getClienteProjetoId() {
        return clienteProjetoId;
    }

    public void setClienteProjetoId(Long clienteProjetoId) {
        this.clienteProjetoId = clienteProjetoId;
    }

    public String getEscopoInicial() {
        return escopoInicial;
    }
    
    public void setEscopoInicial(String escopoInicial) {
        this.escopoInicial = escopoInicial;
    }
    
    public Date getDataInicioPrevista() {
        return dataInicioPrevista;
    }
    
    public void setDataInicioPrevista(String dataInicioPrevista) throws ParseException {
        this.dataInicioPrevista = formatarData(dataInicioPrevista);
    }

    public void setIsFinalizado(boolean isFinalizado) {
        this.isFinalizado = isFinalizado;
    }
    
    public boolean getIsFinalizado() {
        return isFinalizado;
    }

    public Long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto) {
        this.idProjeto = idProjeto;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %s, %s, %d",
                this.idProjeto,
                this.nomeProjeto,
                this.localizacaoProjeto,
                this.escopoInicial,
                new SimpleDateFormat("dd-MM-yyyy").format(this.dataInicioPrevista),
                this.isFinalizado ? "Concluído" : "Não Concluído",
                this.clienteProjetoId);
    }
}
