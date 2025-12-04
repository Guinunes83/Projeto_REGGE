package br.com.regge;

import java.io.IOException; // Importa a classe para tratar erros de arquivo

import javafx.event.ActionEvent;
import javafx.fxml.FXML; // Importa a classe do evento de clique
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private BorderPane borderPanePrincipal;

    // 2. Método de exemplo que o Maven criou (podemos apagar depois)
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    /**
     * *@param event
     */
    @FXML
    private void onMenuDesvioProtocoloClicked(ActionEvent event) {
        System.out.println("Clicou em 'Desvio de Protocolo'!");
        try {
            // Carrega o FXML (que agora é o VBox raiz)
            Parent formDesvio = FXMLLoader.load(getClass().getResource("DesvioProtocolo.fxml"));

            // Cria uma nova janela.
            Stage novaJanela = new Stage();
            novaJanela.setTitle("Desvio De Protocolo");

            // Cria a cena e coloca a janela
            Scene cena = new Scene(formDesvio);
            novaJanela.setScene(cena);

            novaJanela.show();

            // Mostra a janela
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param event
     */
    @FXML
    private void onMenuReuniaoCEPClicked(ActionEvent event) {
        System.out.println("Clicou em Reunião CEP!");

        try {
            Parent formReuniaoCEP = FXMLLoader.load(getClass().getResource("ReuniaoCEP.fxml"));

            Stage novaJanela = new Stage();
            novaJanela.setTitle("Reunião CEP");

            Scene cena = new Scene(formReuniaoCEP);
            novaJanela.setScene(cena);

            novaJanela.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param event
     */
    @FXML
    private void onMenuDadosEstudosClicked(ActionEvent event) {
        System.out.println("Clicou em Dados Estudos");

        try {
            Parent formDadosEstudos = FXMLLoader.load(getClass().getResource("DadosEstudos.fxml"));

            Stage novaJanela = new Stage();
            novaJanela.setTitle("Dados Dos Estudos");

            Scene cena = new Scene(formDadosEstudos);
            novaJanela.setScene(cena);
            novaJanela.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMenuCadastroDadosEstudosClicked(ActionEvent event) {
        System.out.println("Clicou em Cadastro Dados Estudos");

        try {
            Parent formCadastroDadosEstudo = FXMLLoader.load(getClass().getResource("CadastroDadosEstudos.fxml"));

            Stage novaJanela = new Stage();
            novaJanela.setTitle("Cadastro Dados dos Estudos");

            Scene cena = new Scene(formCadastroDadosEstudo);
            novaJanela.setScene(cena);
            novaJanela.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMenuCadastroDadosMonitoresClicked(ActionEvent event) {
        System.out.println("Clicou em Cadastro Dados Monitores");

        try {
            Parent formCadastroDadosMonitores = FXMLLoader.load(getClass().getResource("CadastroDadosMonitores.fxml"));

            Stage novaJanela = new Stage();
            novaJanela.setTitle("Cadastro Dados dos Monitores");

            Scene cena = new Scene(formCadastroDadosMonitores);
            novaJanela.setScene(cena);
            novaJanela.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMenuCadastroDadosPIClicked(ActionEvent event) {
        System.out.println("Clicou em Cadastro Dados PI");

        try {
            Parent formCadastroDadosPI = FXMLLoader.load(getClass().getResource("CadastroDadosPI.fxml"));

            Stage novaJanela = new Stage();
            novaJanela.setTitle("Cadastro Dados do PI");

            Scene cena = new Scene(formCadastroDadosPI);
            novaJanela.setScene(cena);
            novaJanela.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMenuEstoqueKitsClicked(ActionEvent event) {
        System.out.println("Clicou em Estoque Kits");

        try {
            Parent formEstoqueKits = FXMLLoader.load(getClass().getResource("EstoqueKits.fxml"));
            Stage novaJanela = new Stage();
            novaJanela.setTitle("Estoque Kits");

            Scene cena = new Scene(formEstoqueKits);
            novaJanela.setScene(cena);
            novaJanela.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMenuBlocoDeNotasClicked(ActionEvent event) {
        try {
            Parent formBloco = FXMLLoader.load(getClass().getResource("BlocoDeNotas.fxml"));
            Stage novaJanela = new Stage();
            novaJanela.setTitle("Bloco de Notas");

            Scene cena = new Scene(formBloco);
            novaJanela.setScene(cena);
            novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
