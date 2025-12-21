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
    public void start(Stage stage) { // Note que removi o "throws IOException" daqui
        try {
            // Tenta carregar a tela principal
            Parent root = loadFXML("primary");
            scene = new Scene(root);

            // Tenta carregar o ícone
            try {
                Image icone = new Image(getClass().getResourceAsStream("icone_grupo_elora.png"));
                stage.getIcons().add(icone);
            } catch (Exception e) {
                System.out.println("Aviso: Ícone não encontrado (Isso não impede o programa de rodar).");
            }

            stage.setTitle("Projeto REGGE - Sistema de Gestão");
            stage.setScene(scene);

            // Tamanho Fixo e Centralizado
            stage.setWidth(900);
            stage.setHeight(600);
            stage.centerOnScreen();

            stage.show();

        } catch (Exception e) {
            // --- AQUI ESTÁ O SEGREDO ---
            // Se der erro, ele vai gritar no terminal
            System.err.println("\n\n=======================================================");
            System.err.println("ERRO FATAL AO INICIAR O PROGRAMA:");
            System.err.println("=======================================================");
            e.printStackTrace();
            System.err.println("=======================================================\n\n");
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

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