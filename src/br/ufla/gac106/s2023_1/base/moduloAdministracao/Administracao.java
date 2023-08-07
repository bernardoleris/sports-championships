package br.ufla.gac106.s2023_1.base.moduloAdministracao;

import java.io.FileWriter;
import java.util.ArrayList;
import br.ufla.gac106.s2023_1.base.compraIngressos.TelaUsuario;

public class Administracao {
    private ArrayList<Campeonato> campeonatos;
    private ArrayList<Arena> arenas;
    // vetor que serve para auxiliar na comparação de horarios
    private ArrayList<Partida> partidasTotal;
    private ArrayList<Esporte> esportes;

    // construtor de Administracao
    public Administracao() {
        partidasTotal = new ArrayList<Partida>();
        campeonatos = new ArrayList<Campeonato>();
        arenas = new ArrayList<Arena>();
        esportes = new ArrayList<Esporte>();
        Esporte futebol = new Esporte("futebol", "coletivo", 26);
        Esporte futsal = new Esporte("futsal", "coletivo", 14);
        Esporte natacao = new Esporte("natacao", "individual", 6);
        Esporte voleibol = new Esporte("voleibol", "coletivo", 24);
        esportes.add(futebol);
        esportes.add(voleibol);
        esportes.add(futsal);
        esportes.add(natacao);
    }

    public void salvarDados() {
        try {
            FileWriter arq = new FileWriter("campeonatos.txt", false);

            for (Campeonato campeonato : campeonatos) {
                arq.write(campeonato.dadosSalvos() + "\n");
            }
            arq.close();
        } catch (Exception e) {
        }
    }

    public void gerarInterface() {
        TelaUsuario janela = new TelaUsuario();
        janela.setVisible(true);
    }

    // metodo para montar o campeonato utilizando as partidas criadas
    public void montarCampeonato(String nomeCamp, String dataCamp, String descricaoCamp, String modalidade,
            String entidadeOrg, String quantidadeEquipes, int numEsporte, Arena arena, String fator1, String fator2,
            int nivel) {
        boolean arenaExiste = false;
        Esporte esporte = qualEsporte(numEsporte);
        ArrayList<Partida> partidas = new ArrayList<>();
        // caso exista uma arena com esse nome, não será adicionada ao vetor
        for (Arena i : arenas) {
            if (arena.getNomeArena().equalsIgnoreCase(i.getNomeArena())) {
                arenaExiste = true;
            }
        }
        if (arenaExiste == false) {
            arenas.add(arena);
        }
        if (nivel == 1) {
            Campeonato campeonato = new Amador(nomeCamp, dataCamp, descricaoCamp, modalidade, entidadeOrg,
                    quantidadeEquipes, esporte, arena, partidas, fator1, fator2);
            campeonatos.add(campeonato);
        } else {
            Campeonato campeonato = new Profissional(nomeCamp, dataCamp, descricaoCamp, modalidade, entidadeOrg,
                    quantidadeEquipes, esporte, arena, partidas, fator1, fator2);
            campeonatos.add(campeonato);
        }
    }

    // metodo para montar a partida
    public Boolean montarPartida(String confronto, String horario, String nome, String data) {
        // vetor que retorna as partidas criadas no momento
        Partida partida = new Partida(confronto, horario);
        for (Campeonato i : campeonatos) {
            if (i.getNomeCamp().equals(nome)) {
                if (compararHoraData(horario, data) == false) {
                    i.adicionarPartida(partida);
                    return true;
                }
            }
        }
        return false;
    }

    // metodo que verifica se o campeonato possui alguma partida marcada nesse mesmo
    // horario ou data
    public boolean compararHoraData(String horario, String data) {
        for (Campeonato i : campeonatos) {
            if (i.getDataInicioCamp().equalsIgnoreCase(data)) {
                for (Partida x : i.getPartidas()) {
                    if (horario.equals(x.getHorario())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // metodo para exibir a descricao completa do campeonato conforme o nome
    public String exibirCampeonato(String nome) {
        for (Campeonato i : campeonatos) {
            if (i.getNomeCamp().equalsIgnoreCase(nome)) {
                return descricaoCompleta(i);
            }
        }
        return "Campenato nao cadastrado";
    }

    // método para verificar se a arena está sendo utilizada por algum campeonato
    public boolean arenaUtilizada(String nomeArena) {
        for (Campeonato campeonato : campeonatos) {
            if (campeonato.ArenaUtilizada(nomeArena)) {
                return true;
            }
        }
        return false;
    }

    // método para apagar arena
    public void apagarArena(String nomeArena) {
        for (int i = 0; i < arenas.size(); i++) {
            if (nomeArena.equals(arenas.get(i).getNomeArena())) {
                arenas.remove(i);
            }
        }
    }

    // método para escolha do esporte
    public Esporte qualEsporte(int numEsporte) {
        return esportes.get(numEsporte - 1);
    }

    // metodo para montar a descricao completa do campeonato
    public String descricaoCompleta(Campeonato campeonato) {
        return campeonato.descricaoCompleta();
    }

    // metodo para exibir todos os campeonatos e as descricoes completas de cada um
    public String exibirCampeonatos() {
        if (campeonatos.size() > 0) {
            String lista = "";
            for (int i = 0; i < campeonatos.size(); i++) {
                lista += campeonatos.get(i).descricaoCompleta();
            }
            return lista;
        } else {
            return "Não há campeonatos registrados";
        }
    }

    // metodo para apagar um campeonato, conforme o nome
    public Boolean deletarCampeonato(String nome) {
        // booleano para saber se o campeonato existe e foi apagadou ou nao
        if (campeonatos.size() > 0) {
            for (int i = 0; i < campeonatos.size(); i++) {
                if (campeonatos.get(i).getNomeCamp().equals(nome)) {
                    campeonatos.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    // método para apagar a partida no campeonato
    public boolean apagarPartida(String NomePartida, String nomeCampeonato) {
        for (int i = 0; i < campeonatos.size(); i++) {
            if (campeonatos.get(i).getNomeCamp().equals(nomeCampeonato)
                    && campeonatos.get(i).deletarPartida(NomePartida) == true) {
                apagarPartidaGeral(NomePartida);
                return true;
            }
        }
        return false;
    }

    // metodo para apagar partida no vetor de partidas total
    private void apagarPartidaGeral(String NomePartida) {
        for (int i = 0; i < partidasTotal.size(); i++) {
            if (partidasTotal.get(i).getConfronto().equals(NomePartida)) {
                partidasTotal.remove(i);
            }
        }
    }

    private Campeonato procurarCampeonato(String nome) {
        for (Campeonato i : campeonatos) {
            if (i.getNomeCamp().equals(nome)) {
                return i;
            }
        }
        return null;
    }

    // metodo para alterar informacoes de algum campeonato espeficico
    public Boolean modificarCampeonato(String nome, int opcao, String texto) {
        Campeonato campeonato = procurarCampeonato(nome);
        if (opcao == 1) {
            campeonato.setNomeCamp(texto);
            return true;
        } else if (opcao == 2) {
            campeonato.setDataCamp(texto);
            return true;
        } else if (opcao == 3) {
            campeonato.setDescricaoCamp(texto);
            return true;
        } else if (opcao == 4) {
            campeonato.setEntidadeOrg(texto);
            return true;
        } else if (opcao == 5) {
            campeonato.setModalidade(texto);
            return true;
        } else if (opcao == 6) {
            campeonato.setQuantidadeEquipes(texto);
            return true;
        } else if (opcao == 7 && !campeonatoProfissional(nome)) {
            Amador amador = (Amador) campeonato;
            amador.setexigeMedico(texto);
            return true;
        } else if (opcao == 8 && !campeonatoProfissional(nome)) {
            Amador amador = (Amador) campeonato;
            amador.setIdadeMinima(texto);
            return true;
        } else if (opcao == 7 && campeonatoProfissional(nome)) {
            Profissional profissional = (Profissional) campeonato;
            profissional.setPatrocinador(texto);
            return true;
        } else if (opcao == 8 && campeonatoProfissional(nome)) {
            Profissional profissional = (Profissional) campeonato;
            profissional.setpremioTotal(texto);
            return true;
        }
        return false;
    }

    // metodo para analisar existencia da arena
    public boolean existeArena() {
        if (arenas.size() > 0) {
            return true;
        }
        return false;
    }

    public String exibirArenas(boolean detalhar) {
        String lista = "";
        if (arenas.size() > 0) {
            for (Arena i : arenas) {
                lista += i.getNomeArena() + "\n";
                if (detalhar) {
                    lista += i.getCidadeArena() + "\n" + i.getCapacidadeArena() + "\n" + "\n";
                }
            }
        } else {
            lista += "Nao existem arenas cadastradas";
        }
        return lista;
    }

    public Arena encontrarArena(String nome) {
        for (Arena i : arenas) {
            if (i.getNomeArena().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return null;
    }

    public Arena montarArena(String nomeArena, String CidadeArena, int CapacidadeArena) {
        Arena arena;
        arena = new Arena(nomeArena, CidadeArena, CapacidadeArena);
        return arena;
    }

    // procurar o campeonato e retornar a data que ele ocorre
    public String getDataCamp(String nome) {
        for (Campeonato i : campeonatos) {
            if (i.getNomeCamp().equalsIgnoreCase(nome)) {
                return i.getDataInicioCamp();
            }
        }
        return null;
    }

    public boolean existeCampeonato(String nome) {
        for (Campeonato i : campeonatos) {
            if (i.getNomeCamp().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean campeonatoProfissional(String nome) {
        for (Campeonato i : campeonatos) {
            if (i.getNomeCamp().equalsIgnoreCase(nome) && i.campeonatoProfissional()) {
                return true;
            }
        }
        return false;
    }
}