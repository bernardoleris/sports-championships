package br.ufla.gac106.s2023_1.base.compraIngressos;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConferirIngresso extends JanelaBase {
    public ConferirIngresso() {
        super("Compra de ingressos", "", 600, 600, true, null, false, true);
    }

    @Override
    protected JPanel criarPainelCentral() {
        JPanel painelCentral = new JPanel();
        JLabel label = new JLabel("Erro na compra de ingresso. Por favor, tente novamente.");
        painelCentral.add(label);
        aoVoltar();
        return painelCentral;
    }

    @Override
    protected boolean aoFinalizar() {
        System.exit(0);
        return true;
    }

    @Override
    protected boolean aoVoltar() {
        return true;
    }

}
