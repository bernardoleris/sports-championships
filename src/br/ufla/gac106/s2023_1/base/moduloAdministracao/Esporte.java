package br.ufla.gac106.s2023_1.base.moduloAdministracao;

public class Esporte {
    private String nomeEsporte;
    private String categoria; // coletivo ou individual
    private int numJogadores;

    public Esporte(String nomeEsporte, String categoria, int numJogadores) {
        this.nomeEsporte = nomeEsporte;
        this.categoria = categoria;
        this.numJogadores = numJogadores;
    }

    public String getNomeEsporte() {
        return nomeEsporte;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getNumJogadores() {
        return numJogadores;
    }

    @Override
    public String toString() {
        return this.nomeEsporte + "-" + this.categoria + "-" + this.numJogadores;
    }

}
