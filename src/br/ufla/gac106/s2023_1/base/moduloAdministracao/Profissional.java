package br.ufla.gac106.s2023_1.base.moduloAdministracao;

import java.util.ArrayList;

public class Profissional extends Campeonato {
    private String patrocinador;
    private String premioTotal;

    // Construtor (além dos atributos específicos, podemos reutilizar o construtor
    // da superclasse usando "super")
    public Profissional(String nomeCamp, String dataCamp, String descricaoCamp, String modalidade, String entidadeOrg,
            String quantidadeEquipes, Esporte esporte, Arena arena, ArrayList<Partida> partidas, String patrocinador,
            String premioTotal) {
        super(nomeCamp, dataCamp, descricaoCamp, modalidade, entidadeOrg, quantidadeEquipes, esporte, arena, partidas,
                true);
        this.patrocinador = patrocinador;
        this.premioTotal = premioTotal;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public String getPremioTotal() {
        return premioTotal;
    }

    public String descricaoCompleta() {
        return super.descricaoCompleta() + "É um campeonato profissional. " + "Patrocinador(es): "
                + this.getPatrocinador() + ". Prêmio total: " + this.getPremioTotal() + "." + "\n";
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public void setpremioTotal(String premioTotal) {
        this.premioTotal = premioTotal;
    }
}
