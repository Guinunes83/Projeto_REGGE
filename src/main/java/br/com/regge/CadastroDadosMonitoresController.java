package br.com.regge;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadastroDadosMonitoresController {

    //Campos formulário de cadastro de monitores
    @FXML
    private TextField nomeMonitor;
    @FXML
    private ComboBox<String> funcaoMonitor;
    @FXML
    private ComboBox<String> estudoMonitor;
    @FXML
    private TextField croMonitor;
    @FXML
    private TextField patrocinadorEstudo;
    @FXML
    private TextField telefoneMonitor;
    @FXML
    private TextField emailMonitor;
    @FXML
    private TextField loginWindows;
    @FXML
    private TextField senhaWindows;
    @FXML
    private TextField loginTasy;
    @FXML
    private TextField senhaTasy;
    @FXML
    private Button btnCadastrarMonitor;
    @FXML
    private Label lblAvisoErro;
    //Tabela de visualização de dados dos monitores
    @FXML
    private TableView<DadosMonitores> tabDadosMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colNomeMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colFuncaoMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colEstudoMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colCroMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colPatrocinadorMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colContatoMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colEmailMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colLoginWindowsMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colSenhaWindowsMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colLoginTasyMonitor;
    @FXML
    private TableColumn<DadosMonitores, String> colSenhaTasyMonitor;
    @FXML
    private TableColumn<DadosMonitores, Void> colAcoes;

    @FXML
    public void inicialize() {
        // Aviso de erro se cadastrado com campos faltando.
        if (lblAvisoErro != null) {
            lblAvisoErro.setVisible(false);
            lblAvisoErro.setText("Preencha os campos obrigatórios!");
        }

        //Configuração da tabela com colunas fixas
        if (colAcoes != null) {
            colAcoes.setPrefwidth(90);
            colAcoes.setMinWidth(90);
            colAcoes.setMaxWidth(90);
            colAcoes.setResizable(false);
        }

        private void configurarTabela() {
            colNomeMonitor.setCellValueFactory(new PropertyValueFactory<>("Nome"));
            colFuncaoMonitor.setCellValueFactory(new PropertyValueFactory<>("Funcao"));
            colEstudoMonitor.setCellValueFactory(new PropertyValueFactory<>("Estudo"));
            colCroMonitor.setCellValueFactory(new PropertyValueFactory<>("Cro"));
            colPatrocinadorMonitor.setCellValueFactory(new PropertyValueFactory<>("Patrocinador"));
            colContatoMonitor.setCellValueFactory(new PropertyValueFactory<>("Contato"));
            colEmailMonitor.setCellFactory(new PropertyValueFactory<>("Email"));
            colLoginWindowsMonitor.setCellValueFactory(new PropertyValueFactory<>("LoginWindows"));
            colSenhaWindowsMonitor.setCellValueFactory(new PropertyValueFactory<>("SenhaWindows"));
            colLoginTasyMonitor.setCellValueFactory(new PropertyValueFactory<>("LoginTasy"));
            colSenhaTasyMonitor.setCellValueFactory(new PropertyValueFactory<>("SenhaTasy"));
            colAcoes.setCellValueFactory(new PropertyValueFactory<>("Ação"));
        }

        private void configurarColunaAcoes() {
            if(colAcoes != null) {
                return;
            }
        }



        configurarTabela();
        configurarColunaAcoes();

    }
}