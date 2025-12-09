package br.com.regge;

import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DesvioProtocoloController {

    // --- Seus Campos (IDs exatos) ---

    @FXML
    private TextField estudosDesvioP;

    @FXML
    private TextField piDesvioP;

    @FXML
    private TextField nCentroDesvioP;

    @FXML
    private TextField nomePacienteDesvioP;

    @FXML
    private TextField nPacienteDesvioP;

    @FXML
    private DatePicker dataOcorrenciaDesvioP;

    @FXML
    private DatePicker dataDesvioP;

    @FXML
    private TextArea descricaoDesvioP;

    // --- Ação do Botão ---
    // Certifique-se que no Scene Builder o "On Action" do botão é: onBtnCadastrarDesvioP
    @FXML
    void onBtnCadastrarDesvioP(ActionEvent event) {
        
        // 1. Ler os dados (Get Text)
        String estudo = estudosDesvioP.getText();
        String pi = piDesvioP.getText();
        String centro = nCentroDesvioP.getText();
        String paciente = nomePacienteDesvioP.getText();
        String nPaciente = nPacienteDesvioP.getText();
        String descricao = descricaoDesvioP.getText();

        // 2. Ler as Datas (com proteção para não dar erro se estiver vazio)
        String dataOcorrencia = "";
        if (dataOcorrenciaDesvioP.getValue() != null) {
            dataOcorrencia = dataOcorrenciaDesvioP.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        String dataDesvio = "";
        if (dataDesvioP.getValue() != null) {
            dataDesvio = dataDesvioP.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        // 3. Imprimir no Console (Teste)
        System.out.println("===================================");
        System.out.println("NOVO DESVIO CADASTRADO COM SUCESSO");
        System.out.println("===================================");
        System.out.println("Estudo: " + estudo);
        System.out.println("PI: " + pi);
        System.out.println("Centro Nº: " + centro);
        System.out.println("Paciente: " + paciente + " (Nº " + nPaciente + ")");
        System.out.println("Data Ocorrência: " + dataOcorrencia);
        System.out.println("Data Desvio: " + dataDesvio);
        System.out.println("Descrição: " + descricao);
        System.out.println("===================================");
    }
}