package br.ufla.gac106.s2023_1.base.moduloAdministracao;

public class Partida {
    private String confronto;
    private String horario;
    private int ingressosVendidos;

    public Partida(String confronto, String horario) {
        this.confronto = confronto;
        this.horario = horario;
        this.ingressosVendidos = 0;
    }

    public String getConfronto() {
        return confronto;
    }

    public String getHorario() {
        return horario;
    }

    public String descricaoPartida() {
        return this.confronto + "/" + this.horario;
    }

    public void venderIngresso(int ingressosVendidos) {
        this.ingressosVendidos += ingressosVendidos;
    }

    @Override
    public String toString() {
        return this.confronto + "-" + this.horario;
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

}
