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

    // Dica: Adicionei <String> nos ComboBox para evitar avisos amarelos (warnings)
    @FXML
    private ComboBox<String> cbTipoEstudo;

    @FXML
    private ComboBox<String> cbViaMedicamento;

    @FXML
    private ComboBox<String> cbStatusRecrutamento;

    @FXML
    private ComboBox<String> tfNCentro;

    // --- Método do Botão ---
    @FXML
    void onBtnCadastrarClicked(ActionEvent event) {
        // 1. Ler o que o usuário digitou (Usando os nomes CORRETOS das variáveis acima)
        
        // Cuidado: tfProtocoloEstudo pode ser nulo se não tiver fx:id no FXML, 
        // mas assumindo que está ligado, vamos pegar o texto.
        String protocolo = (tfProtocoloEstudo != null) ? tfProtocoloEstudo.getText() : "";
        String pi = (tfnomePi != null) ? tfnomePi.getText() : "";
        String patrocinador = (tfPatrocinador != null) ? tfPatrocinador.getText() : "";
        String pagador = (tfCroPagador != null) ? tfCroPagador.getText() : "";
        String coordenador = (tfNomeCoordenador != null) ? tfNomeCoordenador.getText() : "";

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