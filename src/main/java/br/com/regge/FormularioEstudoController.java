package br.com.regge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormularioEstudoController {

    @FXML private Label lblTitulo;
    @FXML private TextField tfNomeEstudo;
    @FXML private TextField tfProtocoloEstudo;
    @FXML private TextField tfPatrocinador;
    @FXML private TextField tfnomePi;
    @FXML private TextField tfCroPagador;
    @FXML private TextField tfNomeCoordenador;
    @FXML private TextField tfPatologia;
    @FXML private TextField tfRecrutamento;
    @FXML private TextField tfNCentro;
    
    // --- NOVOS CAMPOS DO SEU LAYOUT ---
    @FXML private TextField tfCaae;
    @FXML private TextField tfPataformaSusar;
    @FXML private TextField tfLoginSenhaPlataformaSusar;
    @FXML private TextField tfNomeMonitor;
    @FXML private TextField tfFuncaoMonitor;
    @FXML private TextField tfEmailMonitor;

    private Estudo estudoAtual;

    public void setEstudo(Estudo estudo) {
        this.estudoAtual = estudo;

        if (estudo != null) {
            // MODO EDIÇÃO: Carregar dados
            lblTitulo.setText("Editar Estudo: " + estudo.getNome());
            tfNomeEstudo.setText(estudo.getNome());
            tfProtocoloEstudo.setText(estudo.getProtocolo());
            tfPatrocinador.setText(estudo.getPatrocinador());
            tfnomePi.setText(estudo.getPi());
            tfCroPagador.setText(estudo.getCro());
            tfNomeCoordenador.setText(estudo.getCoordenador());
            tfPatologia.setText(estudo.getPatologia());
            tfRecrutamento.setText(estudo.getRecrutamento());
            tfNCentro.setText(estudo.getNumeroCentro());
            
            // Novos Campos
            tfCaae.setText(estudo.getCaae());
            tfNomeMonitor.setText(estudo.getMonitor());
            tfEmailMonitor.setText(estudo.getEmailMonitor());
            
            // Campos que ainda não temos no objeto Estudo (usar placeholder ou criar no Estudo.java depois)
            // tfPataformaSusar.setText(...);
            
        } else {
            // MODO CADASTRO: Limpar
            lblTitulo.setText("Cadastrar Novo Estudo");
            limparCampos();
        }
    }

    @FXML
    void onBtnSalvar(ActionEvent event) {
        // Coletar dados básicos
        String nome = tfNomeEstudo.getText();
        String protocolo = tfProtocoloEstudo.getText();
        String patrocinador = tfPatrocinador.getText();
        String pi = tfnomePi.getText();
        String cro = tfCroPagador.getText();
        String coordenador = tfNomeCoordenador.getText();
        String patologia = tfPatologia.getText();
        String recrutamento = tfRecrutamento.getText();
        String centro = tfNCentro.getText();
        
        // Coletar novos dados
        String caae = tfCaae.getText();
        String monitor = tfNomeMonitor.getText();
        String emailMonitor = tfEmailMonitor.getText();
        
        // Dummy data para campos que faltam mapear
        String dummy = "-"; 

        if (estudoAtual == null) {
            // Cadastrar
            Estudo novo = new Estudo(nome, protocolo, patrocinador, pi, cro, coordenador, 
                                     patologia, recrutamento, centro, caae, monitor, emailMonitor, 
                                     dummy, dummy, dummy);
            BancoDeDadosFake.adicionarEstudo(novo);
        } else {
            // Editar (Remover antigo e adicionar novo)
            BancoDeDadosFake.getEstudos().remove(estudoAtual);
            Estudo editado = new Estudo(nome, protocolo, patrocinador, pi, cro, coordenador, 
                                        patologia, recrutamento, centro, caae, monitor, emailMonitor, 
                                        dummy, dummy, dummy);
            BancoDeDadosFake.adicionarEstudo(editado);
        }
        fecharJanela();
    }

    @FXML
    void onBtnCancelar(ActionEvent event) {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) tfNomeEstudo.getScene().getWindow();
        stage.close();
    }

    private void limparCampos() {
        if(tfNomeEstudo != null) tfNomeEstudo.clear();
        if(tfProtocoloEstudo != null) tfProtocoloEstudo.clear();
        // ... limpar os outros
    }
}