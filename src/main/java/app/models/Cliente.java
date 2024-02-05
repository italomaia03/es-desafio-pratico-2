package app.models;

public class Cliente {
    private Long idCliente;
    private String nome;

    public Cliente(Long idCliente, String nome) throws NullPointerException{
        this.idCliente = idCliente;
        this.nome = nome;
        validarCliente();
    }

    public Cliente(String nome) {
        this(null, nome);
    }

    private void validarCliente() throws NullPointerException{
        if (this.nome == null){
            throw new NullPointerException("Nome do cliente não pode ser vazio");
        }
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return String.format("%d, %s", this.idCliente, this.nome);
    }
}
