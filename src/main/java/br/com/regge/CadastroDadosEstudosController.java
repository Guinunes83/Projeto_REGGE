package br.com.regge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CadastroDadosEstudosController {

    // --- Variáveis da Seção GERAL ---
    @FXML
    private TextField tfNomeEstudo;

    @FXML
    private TextField tfProtocoloEstudo;

    @FXML
    private TextField tfPatrocinador;

    @FXML
    private TextField tfnomePi;
    
    @FXML
    private TextField tfCroPagador;

    @FXML
    private TextField tfNomeCoordenador;

    @FXML
    private TextField tfPatologia;

    @FXML
    private ComboBox cbTipoEstudo;

    @FXML
    private ComboBox cbViaMedicamento;

    @FXML
    private ComboBox cbStatusRecrutamento;

    @FXML
    private ComboBox tfNCentro;

    // --- Método do Botão ---
    @FXML
    void onBtnCadastrarClicked(ActionEvent event) {
        // 1. Ler o que o usuário digitou
        String protocolo = tfProtocolo.getText();
        String pi = tfPI.getText();
        String patrocinador = tfPatrocinador.getText();
        String pagador = tfPagador.getText();
        String coordenador = tfCoordenador.getText();

        // 2. Imprimir no terminal para testar
        System.out.println("=== Novo Estudo Cadastrado ===");
        System.out.println("Protocolo: " + protocolo);
        System.out.println("PI: " + pi);
        System.out.println("Patrocinador: " + patrocinador);
        System.out.println("Pagador: " + pagador);
        System.out.println("Coordenador: " + coordenador);
        
        // Dica: Futuramente, aqui entrará o código para salvar no SQLite!
    }
}