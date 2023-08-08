package br.ufla.gac106.s2023_1.base.compraIngressos;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import br.ufla.gac106.s2023_1.base.moduloAdministracao.Arena;
import br.ufla.gac106.s2023_1.base.moduloAdministracao.Partida;

public class EscolhaIngresso extends JanelaBase {

    private ConferirIngresso janelaConferirIngresso;
    private Partida partida;
    private JSpinner spinner;
    private Arena arena;

    public EscolhaIngresso(Partida partida, Arena arena) {
        super("Venda de ingressos", "", 600, 600, true, null, true, false);
        this.partida = partida;
        this.arena = arena;
    }

    @Override
    protected JPanel criarPainelCentral() {
        // Aqui você cria o painel central da sua janela e retorna ele
        JPanel painelCentral = new JPanel();
        // Adicione seus componentes ao painel central aqui
        // Exemplo:
        JLabel label = new JLabel("ESCOLHA A QUANTIDADE DE INGRESSOS PARA COMPRAR:" + "\n" + "VALOR: R$60,00");
        spinner = new JSpinner();
        painelCentral.add(spinner);
        painelCentral.add(label);

        return painelCentral;
    }

    @Override
    protected boolean aoAvancar() {
        int quantidadeIngressosComprados = Integer.parseInt(this.spinner.getValue().toString());
        if (quantidadeIngressosComprados < this.arena.getCapacidadeArena()) {
            this.partida.venderIngresso(quantidadeIngressosComprados);
            gerarPDF(quantidadeIngressosComprados);
            aoFinalizar();
        } else if (quantidadeIngressosComprados > this.arena.getCapacidadeArena() || quantidadeIngressosComprados<0) {
            janelaConferirIngresso = new ConferirIngresso();
        }
        janelaConferirIngresso.setVisible(true);
        setVisible(false);
        return false;
    }

    @Override
    protected boolean aoFinalizar() {
        System.exit(0);
        return true;
    }

    private void gerarPDF(int quantidadeIngressosComprados) {
        String nomeArquivo = "ingressos_comprados.pdf";

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo)).setPdfVersion(PdfWriter.VERSION_1_7); // Adicione
                                                                                                                     // esta
                                                                                                                     // linha
                                                                                                                     // para
                                                                                                                     // definir
                                                                                                                     // a
                                                                                                                     // versão
                                                                                                                     // do
                                                                                                                     // PDF
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo)).setFullCompression(); // Adicione esta
                                                                                                     // linha para
                                                                                                     // definir o nível
                                                                                                     // de compressão

            document.open();

            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
            int valorTotal = quantidadeIngressosComprados * 60;
            String conteudoPDF = "Quantidade de ingressos comprados: " + quantidadeIngressosComprados + "\n"
                    + "Valor total: R$" + valorTotal;
            document.add(new Paragraph(conteudoPDF, font));

            document.close();
            JOptionPane.showMessageDialog(this, "PDF gerado com sucesso! Verifique o arquivo " + nomeArquivo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar o PDF: " + e.getMessage());
        }
    }

}
