package br.ufla.gac106.s2023_1.base.compraIngressos;

import java.util.List;

import br.ufla.gac106.s2023_1.base.moduloAdministracao.Arena;
import br.ufla.gac106.s2023_1.base.moduloAdministracao.Esporte;
import br.ufla.gac106.s2023_1.base.moduloAdministracao.Partida;

public class CampeonatosPos {
    private String nome;
    private String data;
    private String descricao;
    private String modalidade;
    private String entidadeOrg;
    private String quantidadeEquipes;
    private Esporte esporte;
    private Arena arena;
    private List<Partida> partidas;
    // private boolean profissional;

    public CampeonatosPos(String nome, String data, String descricao, String modalidade, String entidadeOrg,
            String quantidadeEquipes, String nomeEsporte, String numeroJogadores, String categoriaEsporte,
            String nomeArena, String cidadeArena, String capacidadeArena, List<Partida> partidas, String tipo) {
        this(nome, data, descricao);
        this.modalidade = modalidade;
        this.entidadeOrg = entidadeOrg;
        this.quantidadeEquipes = quantidadeEquipes;
        this.esporte = new Esporte(nomeEsporte, categoriaEsporte, Integer.parseInt(numeroJogadores));
        this.arena = new Arena(nomeArena, cidadeArena, Integer.parseInt(capacidadeArena));
        this.partidas = partidas;
        /*
         * if (tipo.equals("Profissional")) {
         * this.profissional = true;
         * } else {
         * this.profissional = false;
         * }
         */
    }

    public CampeonatosPos(String nome, String data, String descricao) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
    }

    public List<Partida> getPartidas() {
        return this.partidas;
    }

    public Arena getArena() {
        return this.arena;
    }

    @Override
    public String toString() {
        return nome + "-" + data + "-" + descricao + "-" + modalidade + "-" + entidadeOrg +
                "-" + quantidadeEquipes + "-" + esporte.toString() + "-" + arena.toString() + toStringListPartidas();
    }

    private String toStringListPartidas() {
        String texto = "";
        System.out.println(partidas.size());
        for (Partida partida : partidas) {
            texto.concat("-" + partida.toString());
        }
        return texto;
    }

    public String obterDadosIniciais() {
        return nome + "-" + data + "-" + descricao;
    }

}
