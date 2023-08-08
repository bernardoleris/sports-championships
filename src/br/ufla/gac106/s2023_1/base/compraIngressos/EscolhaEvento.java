package br.ufla.gac106.s2023_1.base.compraIngressos;

import javax.swing.*;
import br.ufla.gac106.s2023_1.base.moduloAdministracao.Partida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EscolhaEvento extends JanelaBase {
    private JList<String> listaCampeonatos;
    private EscolhaAtividade janelaEscolhaAtividade;
    private List<CampeonatosPos> eventos;

    public EscolhaEvento() {
        super("Compra de ingressos", "", 600, 600, true, null, true, false);
    }

    @Override
    protected JPanel criarPainelCentral() {
        JPanel painelCentral = new JPanel();
        JLabel label = new JLabel("ESCOLHA O CAMPEONATO:");
        listaCampeonatos = new JList<>();
        eventos = new ArrayList<CampeonatosPos>();
        JScrollPane scrollPane = new JScrollPane(listaCampeonatos);

        painelCentral.add(label);
        painelCentral.add(scrollPane);
        eventos = lerEventosDoArquivo("campeonatos.txt");

        carregarJList(listaCampeonatos, converterPrimeirosDadosParaListaString(eventos));

        return painelCentral;
    }

    @Override
    protected boolean aoAvancar() {
        int campeonatoSelecionado = listaCampeonatos.getSelectedIndex();
        CampeonatosPos campeonatoSelecionadoObjeto = eventos.get(campeonatoSelecionado);
        janelaEscolhaAtividade = new EscolhaAtividade(campeonatoSelecionadoObjeto);
        janelaEscolhaAtividade.setVisible(true);
        setVisible(false);
        return false;
    }

    private CampeonatosPos criarCampeonatosPos(String[] dados) {
        if (dados.length >= 3) {
            String nome = dados[0];
            String data = dados[1];
            String descricao = dados[2];
            String modalidade = dados[3];
            String entidadeOrg = dados[4];
            String quantidadeEquipes = dados[5];
            String nomeEsporte = dados[6];
            String numeroJogadores = dados[7];
            String categoria = dados[8];
            String nomeArena = dados[9];
            String cidadeArena = dados[10];
            String capadicadeArena = dados[11];
            List<Partida> partidas = new ArrayList<>();
            for (int i = 12; i < dados.length - 1; i++) {
                String[] partida = dados[i].split("/");
                partidas.add(new Partida(partida[0], partida[1]));
            }
            String tipo = dados[dados.length - 1];
            // Outros atributos do campeonato, se necessário...

            return new CampeonatosPos(nome, data, descricao, modalidade, entidadeOrg, quantidadeEquipes, nomeEsporte,
                    numeroJogadores, categoria, nomeArena, cidadeArena, capadicadeArena, partidas, tipo);
        }
        return null;
    }

    private List<CampeonatosPos> lerEventosDoArquivo(String arquivo) {
        List<CampeonatosPos> eventos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campeonatos = linha.split(";");
                for (String campeonato : campeonatos) {
                    String[] dados = campeonato.split(",");
                    CampeonatosPos evento = criarCampeonatosPos(dados);
                    if (evento != null) {
                        eventos.add(evento);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    private List<String> converterPrimeirosDadosParaListaString(List<CampeonatosPos> eventos) {
        List<String> nomesEventos = new ArrayList<>();
        for (CampeonatosPos campeonato : eventos) {

            nomesEventos.add(campeonato.obterDadosIniciais());
        }
        return nomesEventos;
    }

}
