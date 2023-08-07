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

    /*
     * private void gerarPdfIngressos() {
     * // Cria o objeto do documento PDF
     * Document document = new Document();
     * try {
     * // Cria o escritor do documento
     * PdfWriter.getInstance(document, new FileOutputStream("ingressos.pdf"));
     * 
     * // Ajusta o tamanho da página
     * document.setPageSize(PageSize.A4);
     * 
     * // Abre o documento
     * document.open();
     * 
     * // Adiciona os ingressos ao documento
     * for (Ingresso ingresso : ingressos) {
     * document.add(new Paragraph("Nome do evento: " + ingresso.getNomeEvento()));
     * document.add(new Paragraph("Data: " + ingresso.getData()));
     * document.add(new Paragraph("Local: " + ingresso.getLocal()));
     * document.add(new Paragraph("-------------------------------------------"));
     * }
     * 
     * // Fecha o documento
     * document.close();
     * System.out.println("O arquivo PDF com os ingressos foi gerado com sucesso.");
     * } catch (DocumentException | FileNotFoundException e) {
     * e.printStackTrace();
     * }
     * }
     */
}
