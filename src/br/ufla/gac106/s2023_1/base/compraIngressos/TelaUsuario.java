package br.ufla.gac106.s2023_1.base.compraIngressos;

import javax.swing.JLabel;
import javax.swing.JPanel;
public class TelaUsuario extends JanelaBase{
    private EscolhaEvento janelaEscolhaEvento;
   
    public TelaUsuario(){
        super("Venda de ingressos", "", 600, 600, false, null, true, false);
        janelaEscolhaEvento = new EscolhaEvento();
    }
    
    @Override
    protected JPanel criarPainelCentral() {
        JPanel painelCentral = new JPanel();
        JLabel label = new JLabel("Bem-vindo ao Aplicativo de Compra de Ingressos!");
        label.setFont(label.getFont().deriveFont(24.0f));
        label.setHorizontalAlignment(JLabel.CENTER);
        painelCentral.add(label);
        return painelCentral;
    }

    @Override
    protected boolean aoAvancar() {
        janelaEscolhaEvento.setVisible(true);
        return false;
    }
    
}
