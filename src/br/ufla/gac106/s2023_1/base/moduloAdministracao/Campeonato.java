package br.ufla.gac106.s2023_1.base.moduloAdministracao;

import java.util.ArrayList;

public class Campeonato {
    private String nomeCamp;
    private String dataInicioCamp;
    private String descricaoCamp;
    private String modalidade;
    private String entidadeOrg;
    private String quantidadeEquipes;
    private Esporte esporte;
    private boolean profissional;
    private Arena arena;
    private ArrayList<Partida> partidas;

    public Campeonato(String nomeCamp, String dataCamp, String descricaoCamp, String modalidade, String entidadeOrg,
            String quantidadeEquipes, Esporte esporte, Arena arena, ArrayList<Partida> partidas, boolean profissional) {
        this.nomeCamp = nomeCamp;
        this.dataInicioCamp = dataCamp;
        this.descricaoCamp = descricaoCamp;
        this.modalidade = modalidade;
        this.entidadeOrg = entidadeOrg;
        this.quantidadeEquipes = quantidadeEquipes;
        this.esporte = esporte;
        this.arena = arena;
        this.partidas = partidas;
        this.profissional = profissional;
    }

    public void AtribuirArena(String NomeArena, String CidadeArena, int CapacidadeArena) {
        this.arena = new Arena(NomeArena, CidadeArena, CapacidadeArena);
    }

    public boolean campeonatoProfissional() {
        return profissional;
    }

    public boolean deletarPartida(String nomePartida) {
        for (int i = 0; i < partidas.size(); i++) {
            if (partidas.get(i).getConfronto().equals(nomePartida)) {
                partidas.remove(i);
                return true;
            }
        }
        return false;
    }

    private String tipoCampeonato() {
        if (campeonatoProfissional()) {
            return "Profissional";
        }
        return "Amador";
    }

    public String dadosSalvos() {
        return nomeCamp + "," + dataInicioCamp + "," + descricaoCamp + "," + modalidade + "," + entidadeOrg + ","
                + quantidadeEquipes + "," + esporte.getNomeEsporte() + "," + esporte.getNumJogadores() + ","
                + esporte.getCategoria() + "," + arena.getNomeArena() + "," + arena.getCidadeArena() + ","
                + arena.getCapacidadeArena() + "," + descricaoPartidasGeral() + tipoCampeonato() + ";";
    }

    public String descricaoCompleta() {
        return nomeCamp + "\n" + dataInicioCamp + "\n" + descricaoCamp + "\n" + modalidade + "\n" + entidadeOrg + "\n"
                + quantidadeEquipes + "\n" + esporte.getNomeEsporte() + "\n" + "Será realizado na arena "
                + arena.getNomeArena() + ", localizada na cidade de " + arena.getCidadeArena()
                + " e que possui capacidade de "
                + arena.getCapacidadeArena() + " pessoas." + "\n" + descricaoPartidasGeral();
    }

    public boolean ArenaUtilizada(String nomeArena) {
        if (arena.getNomeArena().equals(nomeArena)) {
            return true;
        }
        return false;
    }

    public void adicionarPartida(Partida partida) {
        partidas.add(partida);
    }

    public String getNomeCamp() {
        return nomeCamp;
    }

    public String getDataInicioCamp() {
        return dataInicioCamp;
    }

    public void setNomeCamp(String nomeCamp) {
        this.nomeCamp = nomeCamp;
    }

    public void setDataCamp(String dataCamp) {
        this.dataInicioCamp = dataCamp;
    }

    public void setDescricaoCamp(String descricaoCamp) {
        this.descricaoCamp = descricaoCamp;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public void setEntidadeOrg(String entidadeOrg) {
        this.entidadeOrg = entidadeOrg;
    }

    public void setQuantidadeEquipes(String quantidadeEquipes) {
        this.quantidadeEquipes = quantidadeEquipes;
    }

    public String descricaoPartidasGeral() {
        String lista = "";
        for (int i = 0; i < this.partidas.size(); i++) {
            lista += partidas.get(i).descricaoPartida() + ",";
        }
        return lista;
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

}
