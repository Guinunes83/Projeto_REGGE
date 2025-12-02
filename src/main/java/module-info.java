module br.com.regge {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens br.com.regge to javafx.fxml;
    exports br.com.regge;
}
