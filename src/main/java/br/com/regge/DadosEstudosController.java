package br.com.regge;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class DadosEstudosController {

    // --- Componentes da Tela ---
    @FXML
    private ChoiceBox<String> nomeEstudo;

    @FXML
    private TextField protocoloEstudo;

    @FXML
    private TextField nomeMembroEstudo; // Usaremos este campo para o PATROCINADOR por enquanto

    @FXML
    private TextField statusRecrutEstudo;

    @FXML
    public void initialize() {
        // 1. Pegar a lista de dados
        ObservableList<Estudo> listaDeEstudos = BancoDeDadosFake.getEstudos();

        // 2. Preencher o Menu
        for (Estudo estudo : listaDeEstudos) {
            nomeEstudo.getItems().add(estudo.getNome());
        }

        // 3. Ouvinte
        nomeEstudo.setOnAction(event -> preencherCampos());
    }

    private void preencherCampos() {
        String nomeSelecionado = nomeEstudo.getValue();
        if (nomeSelecionado == null) return;

        ObservableList<Estudo> lista = BancoDeDadosFake.getEstudos();
        
        for (Estudo estudo : lista) {
            if (estudo.getNome().equals(nomeSelecionado)) {
                // --- ATUALIZADO COM OS NOVOS CAMPOS ---
                
                protocoloEstudo.setText(estudo.getProtocolo());
                
                // Mapeando Patrocinador para o campo que antes era Membro
                nomeMembroEstudo.setText(estudo.getPatrocinador());
                
                statusRecrutEstudo.setText(estudo.getRecrutamento());
                
                // DICA: Se você adicionar mais TextFields na tela (Scene Builder)
                // para mostrar o PI, Email, Monitor, etc, é só adicionar as linhas aqui:
                // exemplo: campoPI.setText(estudo.getPi());
                
                break;
            }
        }
    }
}