package br.com.regge;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    // --- DESVIO DE PROTOCOLO ---
    @FXML
    private void onMenuDesvioProtocoloClicked(ActionEvent event) {
        try {
            // 1. Carrega o arquivo visual
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DesvioProtocolo.fxml"));
            Parent root = loader.load();

            // 2. Cria a nova janela
            Stage stage = new Stage();
            stage.setTitle("Desvio de Protocolo");

            // --- MUDANÇA AQUI: Ajuste Automático ---
            // Ao não colocar números (600, 400), a janela vai usar o tamanho
            // que você desenhou no Scene Builder (PrefWidth/PrefHeight).
            stage.setScene(new Scene(root));

            // Se quiser impedir que o usuário mude o tamanho da janela:
            stage.setResizable(false);

            // 3. Mostra a janela
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- REUNIÃO CEP ---
    @FXML
    private void onMenuReuniaoCEPClicked(ActionEvent event) {
        System.out.println("Clicou em 'Reunião CEP'!");
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("ReuniaoCEP.fxml"));
            Stage novaJanela = new Stage();
            novaJanela.setTitle("Reunião CEP");
            Scene cena = new Scene(fxml);
            novaJanela.setScene(cena);
            novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- DADOS DOS ESTUDOS ---
    @FXML
    private void onMenuDadosEstudosClicked(ActionEvent event) {
        System.out.println("Clicou em 'Dados Estudos'!");
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("DadosEstudos.fxml"));
            Stage novaJanela = new Stage();
            novaJanela.setTitle("Dados Dos Estudos");
            Scene cena = new Scene(fxml);
            novaJanela.setScene(cena);
            novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- CADASTRO DE ESTUDOS ---
    @FXML
    private void onMenuCadastroDadosEstudosClicked(ActionEvent event) {
        System.out.println("Clicou em 'Cadastro Dados Estudos'!");
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("CadastroDadosEstudos.fxml"));
            Stage novaJanela = new Stage();
            novaJanela.setTitle("Cadastro Dados dos Estudos");
            Scene cena = new Scene(fxml);
            novaJanela.setScene(cena);
            novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- BLOCO DE NOTAS ---
    @FXML
    private void onMenuBlocoDeNotasClicked(ActionEvent event) {
        System.out.println("Clicou em 'Bloco de Notas'!");
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("BlocoDeNotas.fxml"));
            Stage novaJanela = new Stage();
            novaJanela.setTitle("Bloco de Notas");
            Scene cena = new Scene(fxml);
            novaJanela.setScene(cena);
            novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- ESTOQUE KITS ---
    @FXML
    private void onMenuEstoqueKitsClicked(ActionEvent event) {
        System.out.println("Clicou em 'Estoque Kits'!");
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("EstoqueKits.fxml"));
            Stage novaJanela = new Stage();
            novaJanela.setTitle("Estoque Kits");
            Scene cena = new Scene(fxml);
            novaJanela.setScene(cena);
            novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
