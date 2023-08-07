package br.ufla.gac106.s2023_1.base.moduloAdministracao;

import java.util.ArrayList;

public class Amador extends Campeonato {
    private String exigeExameMedico;
    private String idadeMinima;

    public Amador(String nomeCamp, String dataCamp, String descricaoCamp, String modalidade, String entidadeOrg,
            String quantidadeEquipes, Esporte esporte, Arena arena, ArrayList<Partida> partidas,
            String exigeExameMedico, String idadeMinima) {
        super(nomeCamp, dataCamp, descricaoCamp, modalidade, entidadeOrg, quantidadeEquipes, esporte, arena, partidas,
                false);
        this.exigeExameMedico = exigeExameMedico;
        this.idadeMinima = idadeMinima;
    }

    public String getExigeExameMedico() {
        return exigeExameMedico;
    }

    public String getIdadeMinima() {
        return idadeMinima;
    }

    public String descricaoCompleta() {
        return super.descricaoCompleta() + "É um campeonato amador. " + "Exige exame medico: "
                + this.getExigeExameMedico() + ". Idade minima: " + this.getIdadeMinima() + "." + "\n";
    }

    public void setexigeMedico(String resposta) {
        this.exigeExameMedico = resposta;
    }

    public void setIdadeMinima(String resposta) {
        this.idadeMinima = resposta;
    }
}
