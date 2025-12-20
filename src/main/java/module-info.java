module br.com.regge {
    requires javafx.controls;
    requires javafx.fxml;
    
    // --- ADICIONE ESTAS DUAS LINHAS ---
    requires java.desktop;  // Permite usar o java.awt para abrir o PDF
    requires itextpdf;      // Permite usar a biblioteca que baixamos
    // ----------------------------------

    opens br.com.regge to javafx.fxml;
    exports br.com.regge;
}
