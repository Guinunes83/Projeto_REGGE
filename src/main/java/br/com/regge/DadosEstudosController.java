package br.com.regge;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox; // Mudei de ComboBox para ChoiceBox
import javafx.scene.control.TextField;

public class DadosEstudosController {

    // --- Componentes da Tela ---
    @FXML
    private ChoiceBox<String> nomeEstudo; // Agora é um ChoiceBox

    @FXML
    private TextField protocoloEstudo;

    @FXML
    private TextField nomeMembroEstudo;

    @FXML
    private TextField statusRecrutEstudo;

    @FXML
    public void initialize() {
        // 1. Pegar a lista de dados do nosso Banco Fake
        ObservableList<Estudo> listaDeEstudos = BancoDeDadosFake.getEstudos();

        // 2. Preencher o Menu (ChoiceBox) apenas com os nomes
        for (Estudo estudo : listaDeEstudos) {
            nomeEstudo.getItems().add(estudo.getNome());
        }

        // 3. O Ouvinte (Listener)
        // Quando seleciona um item, chama o método preencher
        nomeEstudo.setOnAction(event -> preencherCampos());
    }

    private void preencherCampos() {
        // Descobre qual nome foi selecionado
        String nomeSelecionado = nomeEstudo.getValue();

        if (nomeSelecionado == null) {
            return;
        }

        // Procura no Banco Fake o estudo que tem esse nome
        ObservableList<Estudo> lista = BancoDeDadosFake.getEstudos();

        for (Estudo estudo : lista) {
            if (estudo.getNome().equals(nomeSelecionado)) {
                // ENCONTROU! Preenche os textos
                protocoloEstudo.setText(estudo.getProtocolo());
                nomeMembroEstudo.setText(estudo.getMembro());
                statusRecrutEstudo.setText(estudo.getStatus());
                break;
            }
        }
    }
}
