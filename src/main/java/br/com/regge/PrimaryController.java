package br.com.regge;

import java.io.IOException; // Importa a classe para tratar erros de arquivo

import javafx.event.ActionEvent;
import javafx.fxml.FXML; // Importa a classe do evento de clique
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private TabPane tabPanePrincipal; // Certifique-se que o fx:id no FXML é igual!

    // 2. Método de exemplo que o Maven criou (podemos apagar depois)
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    /**
     * *@param event
     */
    @FXML
    void onMenuDesvioProtocoloClicked(ActionEvent event) {
        System.out.println("1. O botão foi clicado!");

        if (tabPanePrincipal == null) {
            System.out.println("ERRO FATAL: tabPanePrincipal está NULO! Verifique o fx:id no Scene Builder.");
            return; // Para tudo aqui
        }
        System.out.println("2. TabPane encontrado com sucesso.");

        try {
            System.out.println("3. Tentando carregar o arquivo FXML...");
            Parent formDesvio = FXMLLoader.load(getClass().getResource("DesvioProtocolo.fxml"));
            System.out.println("4. Arquivo FXML carregado!");

            Tab novaAba = new Tab("Desvio de Protocolo");
            novaAba.setContent(formDesvio);
            novaAba.setClosable(true);

            tabPanePrincipal.getTabs().add(novaAba);
            tabPanePrincipal.getSelectionModel().select(novaAba);
            System.out.println("5. Aba adicionada com sucesso!");

        } catch (IOException e) {
            System.out.println("ERRO AO CARREGAR ARQUIVO: " + e.getMessage());
            e.printStackTrace(); // Mostra o erro detalhado vermelho
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
