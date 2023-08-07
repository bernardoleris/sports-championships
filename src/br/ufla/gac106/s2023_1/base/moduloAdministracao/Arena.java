package br.ufla.gac106.s2023_1.base.moduloAdministracao;

public class Arena {
    private String nomeArena;
    private String cidadeArena;
    private int capacidadeArena;

    public Arena(String nomeArena, String cidadeArena, int capacidadeArena) {
        this.nomeArena = nomeArena;
        this.cidadeArena = cidadeArena;
        this.capacidadeArena = capacidadeArena;
    }

    public String getNomeArena() {
        return nomeArena;
    }

    public String getCidadeArena() {
        return cidadeArena;
    }

    public int getCapacidadeArena() {
        return capacidadeArena;
    }

    @Override
    public String toString() {
        return this.nomeArena + "-" + this.cidadeArena + "-" + this.capacidadeArena;
    }
}
