package br.com.regge;

import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DesvioProtocoloController {

    @FXML
    private TextField estudosDesvioP;

    @FXML
    private TextField piDesvioP;

    @FXML
    private TextField nCentroDesvioP;

    // --- CORREÇÃO AQUI ---
    // O erro dizia que no FXML isso é um ComboBox, então mudamos aqui para combinar
    @FXML
    private ComboBox<String> nomePacienteDesvioP; 
    // ---------------------

    @FXML
    private TextField nPacienteDesvioP;

    @FXML
    private DatePicker dataOcorrenciaDesvioP;

    @FXML
    private DatePicker dataDesvioP;

    @FXML
    private TextArea descricaoDesvioP;

    @FXML
    void onBtnCadastrarDesvioP(ActionEvent event) {
        
        String estudo = estudosDesvioP.getText();
        String pi = piDesvioP.getText();
        String centro = nCentroDesvioP.getText();
        String nPaciente = nPacienteDesvioP.getText();
        String descricao = descricaoDesvioP.getText();

        // --- CORREÇÃO NA LEITURA ---
        // ComboBox não usa getText(), usa getValue()
        String paciente = "";
        if (nomePacienteDesvioP.getValue() != null) {
            paciente = nomePacienteDesvioP.getValue();
        } else {
            // Se for um ComboBox editável onde se pode digitar, usamos getEditor().getText()
            // Mas vamos tentar getValue() primeiro
            paciente = "Não selecionado"; 
        }

        String dataOcorrencia = "";
        if (dataOcorrenciaDesvioP.getValue() != null) {
            dataOcorrencia = dataOcorrenciaDesvioP.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        String dataDesvio = "";
        if (dataDesvioP.getValue() != null) {
            dataDesvio = dataDesvioP.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

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
    
    @FXML
    public void initialize() {
        // Se quiser adicionar pacientes de teste para o ComboBox funcionar:
        if (nomePacienteDesvioP != null) {
            nomePacienteDesvioP.getItems().addAll("Paciente 01", "Paciente 02", "Paciente 03");
        }
    }
}