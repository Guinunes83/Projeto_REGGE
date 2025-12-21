package br.com.regge;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DadosEstudosController {

    // --- Componentes da Tela ---
    @FXML
    private ChoiceBox<String> nomeEstudo;

    @FXML
    private TextField protocoloEstudo;

    @FXML
    private TextField nomeMembroEstudo;

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

        // 3. Ouvinte para preencher campos ao selecionar
        nomeEstudo.setOnAction(event -> preencherCampos());
    }

    private void preencherCampos() {
        String nomeSelecionado = nomeEstudo.getValue();
        if (nomeSelecionado == null) {
            return;
        }

        ObservableList<Estudo> lista = BancoDeDadosFake.getEstudos();

        for (Estudo estudo : lista) {
            if (estudo.getNome().equals(nomeSelecionado)) {

                // Só tenta escrever se o campo foi encontrado no FXML (não é nulo)
                if (protocoloEstudo != null) {
                    protocoloEstudo.setText(estudo.getProtocolo());
                } else {
                    System.out.println("AVISO: Campo 'protocoloEstudo' não encontrado no FXML (falta fx:id)");
                }

                if (nomeMembroEstudo != null) {
                    nomeMembroEstudo.setText(estudo.getPatrocinador());
                }

                if (statusRecrutEstudo != null) {
                    statusRecrutEstudo.setText(estudo.getRecrutamento());
                }

                break;
            }
        }
    }

    // --- AQUI ESTAVA FALTANDO O MÉTODO DO BOTÃO ---
    // O nome deve ser EXATAMENTE igual ao que está no erro do terminal
    @FXML
    void onbtnEditarDadosEstudosClicked(ActionEvent event) {
        System.out.println("Botão Editar Clicado!");

        // 1. Verifica qual estudo está selecionado no ChoiceBox
        String nomeSelecionado = nomeEstudo.getValue();

        if (nomeSelecionado == null || nomeSelecionado.isEmpty()) {
            System.out.println("Selecione um estudo para editar!");
            return;
        }

        // 2. Busca o objeto Estudo real no banco
        Estudo estudoParaEditar = null;
        for (Estudo e : BancoDeDadosFake.getEstudos()) {
            if (e.getNome().equals(nomeSelecionado)) {
                estudoParaEditar = e;
                break;
            }
        }

        // 3. Abre a janela de formulário passando o estudo encontrado
        if (estudoParaEditar != null) {
            abrirFormulario(estudoParaEditar);
        }
    }

    // Método auxiliar para carregar a outra janela
    private void abrirFormulario(Estudo estudo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormularioEstudo.fxml"));
            Parent root = loader.load();

            // Pega o controlador da nova janela e passa o estudo para ele preencher os campos
            FormularioEstudoController controller = loader.getController();
            controller.setEstudo(estudo);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Estudo");

            // Bloqueia a janela de trás enquanto essa estiver aberta
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait(); // Espera fechar

            // (Opcional) Recarregar a lista caso o nome tenha mudado
            // atualizarLista(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
