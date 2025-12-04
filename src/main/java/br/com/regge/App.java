package br.com.regge;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loadFXML("primary");
        scene = new Scene(root);
        
        try {
            Image icone = new Image(getClass().getResourceAsStream("icone_grupo_elora.png"));
            stage.getIcons().add(icone);
        } catch (Exception e) {
            System.out.println("Ícone não encontrado.");
        }

        stage.setTitle("Projeto REGGE - Sistema de Gestão");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    // --- ADICIONE ISTO ---
    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
    // ---------------------

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}