import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaInformacion {

    public void mostrar(Stage stage) {

        Label info = new Label(
            "Información del sistema:\n\n" +
            "Gerente: Carlos Soto\n" +
            "Asistentes:\n" +
            "- Maria Espinoza\n" +
            "- Fernando Gutierrez\n" +
            "- José Ojeda"
        );
        info.setStyle("-fx-font-size: 16px;");

        Button volver = new Button("Volver al Menú Principal");
        volver.setStyle("-fx-font-size: 14px;");

        volver.setOnAction(e -> new MainMenu().mostrar(stage));

        VBox root = new VBox(25);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER); // CENTRAMOS TODO
        root.getChildren().addAll(info, volver);

        Scene scene = new Scene(root, 500, 350); // Ventana más agradable
        stage.setScene(scene);
        stage.setTitle("Información");
        stage.show();
    }
}
