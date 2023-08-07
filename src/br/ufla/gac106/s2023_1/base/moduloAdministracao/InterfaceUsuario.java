package br.ufla.gac106.s2023_1.base.moduloAdministracao;

import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner entrada;
    private Administracao administracao;

    public InterfaceUsuario() {
        entrada = new Scanner(System.in);
        administracao = new Administracao();
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n-------- MENU --------");
            System.out.println("Escolha uma opção:");
            System.out.println("1: Criar campeonato.");
            System.out.println("2: Alterar campeonato.");
            System.out.println("3: Excluir campeonato.");
            System.out.println("4: Listar campeonatos.");
            System.out.println("5: Detalhar campeonato.");
            System.out.println("6: Adicionar partidas ao campeonato.");
            System.out.println("7: Listar arenas.");
            System.out.println("8: Deletar arena.");
            System.out.println("9: Deletar partida.");
            System.out.println("10: Sair.");
            opcao = Integer.parseInt(entrada.nextLine());
            switch (opcao) {
                case 1:
                    criarCampeonato();
                    break;
                case 2:
                    alterarCampeonato();
                    break;
                case 3:
                    excluirCampeonato();
                    break;
                case 4:
                    listarCampeonatos();
                    break;
                case 5:
                    detalharCampeonatos();
                    break;
                case 6:
                    inserirPartidas();
                    break;
                case 7:
                    listarArenas();
                    break;
                case 8:
                    apagarArena();
                    break;
                case 9:
                    apagarPartida();
                    break;
                case 10:
                    salvarDados();
                    gerarInterface();
                    System.out.println("Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 10);
    }

    public void gerarInterface(){
        administracao.gerarInterface();
    }

    public void salvarDados() {
        administracao.salvarDados();
    }

    private void listarArenas() {
        System.out.println(administracao.exibirArenas(true));
    }

    public void apagarArena() {
        String nomeArena = pedirString("Digite o nome da arena");
        if (administracao.encontrarArena(nomeArena) != null) {
            if (!administracao.arenaUtilizada(nomeArena)) {
                administracao.apagarArena(nomeArena);
                System.out.println("Arena deletado com sucesso.");
            } else {
                System.out.println("Não é possível apagar a arena, ela está sendo utilizada.");
            }
        } else {
            System.out.println("Arena não registrada.");
        }
    }

    public void apagarPartida() {
        String nomeCampeonato = pedirString("Digite o campeonato em que está a partida");
        String nomePartida = pedirString("Digite a partida que deseja apagar");
        if (administracao.apagarPartida(nomePartida, nomeCampeonato) == true) {
            System.out.println("Partida deletado com sucesso.");
        } else {
            System.out.println("Erro, partida não deletada.");
        }

    }

    private void inserirPartidas() {
        int opcao;
        do {
            String nome = pedirString("Digite o nome do campeonato o qual deseja adicionar partidas");
            if (administracao.existeCampeonato(nome) == true) {
                String confronto = pedirString("Digite o confronto");
                String horario = pedirString("Digite o horário do confronto");
                if (administracao.montarPartida(confronto, horario, nome, administracao.getDataCamp(nome)) == true) {
                    System.out.println("A partida foi adicionada.");
                } else {
                    System.out.println("Já existe uma partida marcada no horário escolhido.");
                }
                System.out.println("1- Adicionar mais partidas");
                System.out.println("2- Sair");
                opcao = Integer.parseInt(entrada.nextLine());
            } else {
                System.out.println("Esse campeonato ainda não foi registrado.");
                opcao = 2;
            }
        } while (opcao != 2);
    }

    private void criarCampeonato() {
        String nome = pedirString("Digite o nome do campeonato a ser registrado");
        System.out
                .println("Digite o número da opção que deseja escolher para o campeonato (1-Amador, 2- Profissional)");
        int nivel = Integer.parseInt(entrada.nextLine());
        boolean selecionado = false;
        String exigeExame = null;
        String idadeMinima = null;
        String patrocinador = null;
        String premioTotal = null;
        do {
            if (nivel == 1) {
                exigeExame = pedirString("O campeonato exige exame medico?");
                idadeMinima = pedirString("Digite a idade minima para participacao no campeonato");
                selecionado = true;
            } else if (nivel == 2) {
                patrocinador = pedirString("Digite o nome do(s) patrocinador(es)");
                premioTotal = pedirString("Digite o valor da premiacao total");
                selecionado = true;
            } else {
                System.out.println("A opção selecionada não existe.");
            }
        } while (selecionado == false);

        String data = pedirString("Digite a data de realização do campeonato(formato: xx/xx/xx)");
        String descricao = pedirString("Digite a descricao do campeonato");
        String modalidade = pedirString("Digite a modalidade do campeonato(masculino/feminino)");
        String entidade = pedirString("Digite o nome da entidade organizacional que deseja realizar o campeonato");
        String quantidadeEquipes = pedirString("Digite a quantidade de equipes que deseja registrar no campeonato");
        int numEsporte;

        do {
            System.out.print(
                    "Digite o número do esporte que deseja realizar o campeonato (1-Futebol, 2- Voleibol, 3- Futsal, 4-Natação):");
            numEsporte = Integer.parseInt(entrada.nextLine());
        } while (numEsporte < 1 || numEsporte > 4);
        Arena arena;
        int opcao;
        if (administracao.existeArena() == true) {
            System.out.println("Digite o numero da opcao: 1-Escolher arena existente, 2- Criar arena.");
            opcao = Integer.parseInt(entrada.nextLine());
        } else {
            System.out.println("Não existem arenas registradas. Por favor, faça o registro de alguma." + "\n");
            opcao = 2;
        }
        switch (opcao) {
            case 1:
                System.out.println("Arenas: " + administracao.exibirArenas(false));
                boolean escolheuArena = false;
                do {
                    String nomeArena = pedirString("Digite o nome da arena escolhida");
                    Arena arenaEscolhida = administracao.encontrarArena(nomeArena);
                    if (arenaEscolhida == null) {
                        System.out.println("Arena escolhida não registrada. Digite novamente.");
                    } else {
                        if (nivel == 1) {
                            administracao.montarCampeonato(nome, data, descricao, modalidade, entidade,
                                    quantidadeEquipes, numEsporte, administracao.encontrarArena(nomeArena), exigeExame,
                                    idadeMinima, nivel);
                            System.out.println("Campeonato registrado com sucesso!");
                            escolheuArena = true; // sai do laço após encontrar uma arena válida
                        } else if (nivel == 2) {
                            administracao.montarCampeonato(nome, data, descricao, modalidade, entidade,
                                    quantidadeEquipes, numEsporte, administracao.encontrarArena(nomeArena),
                                    patrocinador, premioTotal, nivel);
                            System.out.println("Campeonato registrado com sucesso!");
                            escolheuArena = true; // sai do laço após encontrar uma arena válida
                        }
                    }
                } while (!escolheuArena);
                break;
            case 2:
                arena = criarArena();
                if (nivel == 1) {
                    administracao.montarCampeonato(nome, data, descricao, modalidade, entidade, quantidadeEquipes,
                            numEsporte, arena, exigeExame, idadeMinima, nivel);
                    System.out.println("Campeonato registrado com sucesso!");
                } else if (nivel == 2) {
                    administracao.montarCampeonato(nome, data, descricao, modalidade, entidade, quantidadeEquipes,
                            numEsporte, arena, patrocinador, premioTotal, nivel);
                    System.out.println("Campeonato registrado com sucesso!");
                }
                break;
        }
    }

    private void listarCampeonatos() {
        System.out.println(administracao.exibirCampeonatos());
    }

    private void detalharCampeonatos() {
        String nome = pedirString("Digite o nome do campeonato que deseja saber as informações detalhadas");
        System.out.println(administracao.exibirCampeonato(nome));
    }

    private String pedirString(String instrucao) {
        System.out.print(instrucao + ": ");
        String informacao = entrada.nextLine();
        return informacao;
    }

    private void excluirCampeonato() {
        String nome = pedirString("Digite o nome do campeonato que deseja ser deletado");
        if (administracao.deletarCampeonato(nome) == true) {
            System.out.println("O campeonato foi apagado.");
        } else {
            System.out.println("O campeonato escolhido ainda não foi registrado.");
        }
    };

    private void alterarCampeonato() {
        // variavel para armazenar a nova mudanca
        String nome = pedirString("Digite o nome do campeonato o qual deseja fazer alterações");
        if (administracao.existeCampeonato(nome)) {
            boolean profissional = administracao.campeonatoProfissional(nome);
            System.out.println(txtOpcoesCampeonato(nome, profissional));
            int opcao = Integer.parseInt(entrada.nextLine());
            String texto = txtAlterarCamp(profissional, opcao);
            if (administracao.modificarCampeonato(nome, opcao, texto) == true) {
                System.out.println("Modificação concluída.");
            } else {
                System.out.println("Erro na modificação.");
            }
        } else {
            System.out.println("Campeonato nao existe");
        }
    }

    private String txtOpcoesCampeonato(String nome, boolean profissional) {
        String textocomum = ("Qual dado do campeonato deseja alterar? (1- Nome, 2- Data, 3- Descrição, 4- Entidade organizacional, 5- Modalidade, 6- Quantidade de equipes");

        if (!profissional) {
            String textoAmador = (", 7- Exige exame médico, 8- Idade mínima)");
            textoAmador = textocomum + textoAmador;
            return textoAmador;
        }
        String textoPro = (", 7- Patrocinadores, 8- Prêmio)");
        textoPro = textocomum + textoPro;
        return textoPro;
    }

    private String txtAlterarCamp(boolean profissional, int opcao) {
        if (opcao == 1) {
            return pedirString("Digite o novo nome");
        } else if (opcao == 2) {
            return pedirString("Digite a nova data");
        } else if (opcao == 3) {
            return pedirString("Digite a nova descrição");
        } else if (opcao == 4) {
            return pedirString("Digite a nova entidade organizacional");
        } else if (opcao == 5) {
            return pedirString("Digite a nova modalidade");
        } else if (opcao == 6) {
            return pedirString("Digite a nova quantidade de equipes");
        } else if (opcao == 7 && !profissional) {
            return pedirString("Digite se exige exame (sim ou nao)");
        } else if (opcao == 8 && !profissional) {
            return pedirString("Digite a nova idade mínima");
        } else if (opcao == 7 && profissional) {
            return pedirString("Digite o novo patrocinador");
        } else if (opcao == 8 && profissional) {
            return pedirString("Digite a nova premiação");
        }
        System.out.println("A opção selecionada não existe.");
        return "";
    }

    private Arena criarArena() {
        String nomeArena = pedirString("Digite qual o nome da arena do campeonato");
        String CidadeArena = pedirString("Digite em qual cidade a arena se encontra");
        System.out.println("Digite a capacidade da arena:");
        int CapacidadeArena = Integer.parseInt(entrada.nextLine());
        return administracao.montarArena(nomeArena, CidadeArena, CapacidadeArena);
    }

}
