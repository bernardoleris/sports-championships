package br.ufla.gac106.s2023_1.base.compraIngressos;

import java.util.ArrayList;

import javax.swing.*;

import br.ufla.gac106.s2023_1.base.moduloAdministracao.Partida;

import java.util.List;

public class EscolhaAtividade extends JanelaBase {
    private EscolhaIngresso janelaEscolhaIngresso;
    private JList<String> listaPartidas;
    private CampeonatosPos campeonato;

    public EscolhaAtividade(CampeonatosPos campeonatosPos) {
        super("Compra de ingressos", "", 600, 600, true, null, true, false);
        this.campeonato = campeonatosPos;
        CarregarDados();
    }

    @Override
    protected JPanel criarPainelCentral() {
        // Aqui você cria o painel central da sua janela e retorna ele
        JPanel painelCentral = new JPanel();
        // Adicione seus componentes ao painel central aqui
        // Exemplo:
        JLabel label = new JLabel("ESCOLHA A PARTIDA:");
        listaPartidas = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listaPartidas);

        painelCentral.add(label);
        painelCentral.add(scrollPane);

        return painelCentral;
    }

    private void CarregarDados() {
        carregarJList(listaPartidas, converterPartidaParaListaString(this.campeonato.getPartidas()));
    }

    private List<String> converterPartidaParaListaString(List<Partida> partidas) {
        List<String> nomesEventos = new ArrayList<>();
        for (Partida partida : partidas) {
            nomesEventos.add(partida.toString());
        }
        return nomesEventos;
    }

    @Override
    protected boolean aoAvancar() {
        janelaEscolhaIngresso = new EscolhaIngresso(
                this.campeonato.getPartidas().get(this.listaPartidas.getSelectedIndex()), this.campeonato.getArena());
        janelaEscolhaIngresso.setVisible(true);
        setVisible(false);
        return false;
    }

}
