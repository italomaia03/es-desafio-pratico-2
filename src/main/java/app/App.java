package app;

import app.controllers.ClienteController;
import app.controllers.ProjetoController;
import app.models.Cliente;
import app.models.Projeto;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class App {
    private final ProjetoController projetoController;
    private final ClienteController clienteController;

    public App() {
        this.clienteController = new ClienteController();
        this.projetoController = new ProjetoController();
    }

    public void mostrarClientes() throws SQLException, ParseException {
        List<Cliente> clientes = this.clienteController.getAll();

        System.out.printf("%s Clientes %s%n", "=".repeat(3), "=".repeat(3));
        System.out.println("ID, Nome");
        clientes.forEach(System.out::println);
    }

    public void mostrarProjetos() throws SQLException, ParseException {
        List<Projeto> projetos = this.projetoController.getAllProjetos();

        System.out.printf("%s Projetos %s%n", "=".repeat(10), "=".repeat(10));
        System.out.println("ID, Nome, Local, Escopo Inicial, Data Inicial Prevista, Concluído, Cliente");
        projetos.forEach(System.out::println);
    }

    public void mostrarCliente(Scanner input) throws SQLException, ParseException {
        System.out.printf("%s Cliente %s%n", "=".repeat(3), "=".repeat(3));
        System.out.print("Infome o ID do cliente: ");
        Long idCliente = input.nextLong();
        System.out.println("ID, Nome");

        System.out.println(this.clienteController.get(idCliente));
    }

    public void mostrarProjeto(Scanner input) throws SQLException, ParseException {
        System.out.printf("%s Projeto %s%n", "=".repeat(10), "=".repeat(10));
        System.out.print("Infome o ID do projeto: ");
        Long idProjeto = input.nextLong();
        System.out.println("ID, Nome, Local, Escopo Inicial, Data Inicial Prevista, Concluído, Cliente");


        System.out.println(this.projetoController.getProjeto(idProjeto));
    }

    public void criarNovoCliente(Scanner input) throws SQLException, ParseException {
        System.out.printf("%s Cadastro de Clientes %s%n", "=".repeat(10), "=".repeat(10));
        System.out.print("Informe o nome do cliente: ");

        System.out.println(this.clienteController.createCliente(new Cliente(input.nextLine())));
    }

    public void criarNovoProjeto(Scanner input) throws SQLException, ParseException {
        String nomeProjeto, localizacaoProjeto, escopoInicialProjeto, dataInicioProjeto;
        System.out.printf("%s Cadastro de Projetos %s%n", "=".repeat(10), "=".repeat(10));
        System.out.print("Informe o nome do projeto: ");
        nomeProjeto = input.nextLine();
        System.out.print("Informe a localização do projeto: ");
        localizacaoProjeto = input.nextLine();
        System.out.print("Informe o escopo inicial do projeto: ");
        escopoInicialProjeto = input.nextLine();
        System.out.print("Informe a data de início prevista\nEx.: 31-12-2013: ");
        dataInicioProjeto = input.nextLine();
        System.out.println("Informe o ID do cliente do projeto: ");
        Long clienteProjetoId = input.nextLong();

        System.out.println(this.projetoController.createProjeto(new Projeto(nomeProjeto,
                localizacaoProjeto,
                escopoInicialProjeto,
                dataInicioProjeto,
                clienteProjetoId)));
    }

    public void editarCliente(Scanner input) throws SQLException, ParseException, NullPointerException {
        System.out.printf("%s Editar Cadastro de Clientes %s%n", "=".repeat(10), "=".repeat(10));
        System.out.print("Informe o ID do cliente: ");
        Long idCliente = input.nextLong();
        input.nextLine();
        Cliente cliente = clienteController.get(idCliente);
        System.out.print("Informe o novo nome do cliente: ");
        String nomeCliente = input.nextLine();
        cliente.setNome(nomeCliente);

        System.out.println(this.clienteController.updateCliente(idCliente, cliente));
    }

    public void editarProjeto(Scanner input) throws ParseException, SQLException {
        System.out.printf("%s Editar Cadastro de Projetos %s%n", "=".repeat(10), "=".repeat(10));
        System.out.print("Informe o ID do projeto: ");
        Long idProjeto = input.nextLong();
        input.nextLine();

        Projeto projeto = this.projetoController.getProjeto(idProjeto);

        String nomeProjeto, localizacaoProjeto, escopoInicialProjeto, dataInicioProjeto;
        System.out.print("Informe o novo nome do projeto: ");
        nomeProjeto = input.nextLine();
        System.out.print("Informe a nova localização do projeto: ");
        localizacaoProjeto = input.nextLine();
        System.out.print("Informe o novo escopo inicial do projeto: ");
        escopoInicialProjeto = input.nextLine();
        System.out.print("Informe a nova data de início prevista\nEx.: 31-12-2013: ");
        dataInicioProjeto = input.nextLine();
        System.out.print("Informe a situação de conclusão\n(S -> conclído, N -> não concluído)");
        boolean isConcluido = input.nextLine().equals("S");
        System.out.println("Informe o novo ID do cliente do projeto: ");
        Long clienteProjetoId = input.nextLong();

        projeto.setNomeProjeto(nomeProjeto);
        projeto.setLocalizacaoProjeto(localizacaoProjeto);
        projeto.setEscopoInicial(escopoInicialProjeto);
        projeto.setDataInicioPrevista(dataInicioProjeto);
        projeto.setIsFinalizado(isConcluido);
        projeto.setClienteProjetoId(clienteProjetoId);

        System.out.println(
                this.projetoController.updateProjeto(idProjeto, projeto)
        );
    }

    public void removerCliente(Scanner input) throws SQLException, ParseException {
        System.out.printf("%s Remover Clientes %s%n", "=".repeat(10), "=".repeat(10));
        System.out.print("Informe o ID do cliente: ");
        Long idCliente = input.nextLong();
        this.clienteController.get(idCliente);

        System.out.println(this.clienteController.removerCliente(idCliente));
    }

    public void removerProjeto(Scanner input) throws SQLException, ParseException {
        System.out.printf("%s Remover Projetos %s%n", "=".repeat(10), "=".repeat(10));
        System.out.print("Informe o ID do projeto: ");
        Long idProjeto = input.nextLong();
        this.projetoController.getProjeto(idProjeto);

        System.out.println(this.projetoController.removerProjeto(idProjeto));
    }

    public void mostrarMenu() {
        System.out.printf("%s Gerenciador de Projetos %s%n", "=".repeat(10), "=".repeat(10));
        System.out.println("Operações:");
        String[] operacoes = {"Cadastrar clientes", "Cadastrar projetos", "Mostrar todos os clientes", "Mostrar todos os projetos", "Mostrar cliente", "Mostrar projeto", "Editar cliente", "Editar projeto", "Remover cliente", "Remover projeto"};
        for (int i = 0; i < operacoes.length; i++) {
            System.out.printf("%d -> %s%n", i + 1, operacoes[i]);
        }
        System.out.println("11 -> Sair");
        System.out.print("Informe a operação que deseja realizar: ");
    }

    public void run(Scanner input) {
        boolean isOperando = true;
        int operacao;
        while (isOperando) {
            try {
                mostrarMenu();
                operacao = input.nextInt();
                input.nextLine();
                switch (operacao) {
                    case 1 -> criarNovoCliente(input);
                    case 2 -> criarNovoProjeto(input);
                    case 3 -> mostrarClientes();
                    case 4 -> mostrarProjetos();
                    case 5 -> mostrarCliente(input);
                    case 6 -> mostrarProjeto(input);
                    case 7 -> editarCliente(input);
                    case 8 -> editarProjeto(input);
                    case 9 -> removerCliente(input);
                    case 10 -> removerProjeto(input);
                    case 11 -> {
                        System.out.println("Fechando aplicação...");
                        isOperando = false;
                    }
                    default -> System.err.println("Opção inválida. Escolha números de 1 a 10");
                }
            } catch (SQLException | ParseException | NullPointerException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}






