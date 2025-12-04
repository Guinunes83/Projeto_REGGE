package br.com.regge;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class BlocoDeNotasController { // Nome da classe atualizado

    @FXML
    private VBox vboxLista;

    @FXML
    public void initialize() {
        // Cria uma linha inicial assim que abrir
        adicionarNovaLinha();
    }

    @FXML
    private void onBtnAdicionarClicked() {
        adicionarNovaLinha();
    }

    private void adicionarNovaLinha() {
        // 1. Container da linha (HBox)
        HBox linhaContainer = new HBox(10);
        linhaContainer.setAlignment(Pos.CENTER_LEFT);
        linhaContainer.getStyleClass().add("linha-pauta"); // Usa o CSS que criamos
        linhaContainer.setPrefHeight(40);

        // 2. CheckBox
        CheckBox checkBox = new CheckBox();

        // 3. StackPane para empilhar Texto e Risco
        StackPane pilhaTexto = new StackPane();
        HBox.setHgrow(pilhaTexto, Priority.ALWAYS);

        // 3a. Campo de Texto
        TextField textField = new TextField();
        textField.setPromptText("Escreva aqui...");
        textField.getStyleClass().add("texto-manuscrito"); // Usa a fonte de mão do CSS
        textField.setMaxWidth(Double.MAX_VALUE);

        // 3b. Linha de Risco
        Line risco = new Line();
        risco.setStrokeWidth(2);
        risco.setVisible(false); // Começa invisível
        
        // Liga o tamanho do risco ao tamanho do texto
        risco.endXProperty().bind(pilhaTexto.widthProperty()); 

        // 4. Lógica do Risco
        checkBox.selectedProperty().addListener((obs, antigo, novo) -> {
            if (novo) {
                risco.setVisible(true);
                textField.setStyle("-fx-text-fill: #aaaaaa;"); // Texto cinza
            } else {
                risco.setVisible(false);
                textField.setStyle("-fx-text-fill: #333333;"); // Texto normal
            }
        });

        // 5. Montagem
        pilhaTexto.getChildren().addAll(textField, risco);
        linhaContainer.getChildren().addAll(checkBox, pilhaTexto);
        vboxLista.getChildren().add(linhaContainer);
    }
}