import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReservaPasajesGUI {

    public void mostrar(Stage stage) {

        VBox container = new VBox(12);
        container.setPadding(new Insets(25));
        container.setAlignment(Pos.TOP_LEFT);
        container.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-background-radius: 10;");
        container.setPrefWidth(500);

        Label titulo = new Label("PATAGONIA WELLBOAT");
        titulo.setFont(Font.font("Arial", 20));

        Separator separator = new Separator();

        // --- CAMPOS ---

        // DESTINO
        Label lblDestino = new Label("DESTINO:");
        ComboBox<String> destino = new ComboBox<>();
        destino.getItems().addAll(
                "Calbuco", "Maillen", "Isla Puluqui", "Achao",
                "Quinchao", "Dalcahue", "Curaco de Vélez",
                "Queilen", "Chonchi", "Melinka", "Quellón"
        );
        destino.setPrefWidth(250);

        // FECHA
        Label lblFecha = new Label("FECHA:");
        DatePicker fecha = new DatePicker();

        // HORARIOS
        Label lblHorario = new Label("HORARIOS DISPONIBLES:");
        ComboBox<String> horarios = new ComboBox<>();
        horarios.getItems().addAll("09:00", "12:00", "15:00", "18:00");

        // ASIENTO
        Label lblAsiento = new Label("ASIENTO:");
        ComboBox<String> asiento = new ComboBox<>();
        asiento.getItems().addAll("1A", "2A", "3A", "4A", "5A",
                                  "10B", "11B", "12A", "12B", "15C");

        // PRECIO (se actualiza según destino)
        Label lblPrecio = new Label("PRECIO: $0");

        destino.setOnAction(e -> {
            int precio = obtenerPrecio(destino.getValue());
            lblPrecio.setText("PRECIO: $" + precio);
        });

        // --- BOTONES ---
        Button confirmar = new Button("CONFIRMAR RESERVA");
        confirmar.setStyle("-fx-background-color: #0d6efd; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 15;");
        confirmar.setMaxWidth(Double.MAX_VALUE);

        Button volver = new Button("VOLVER");
        volver.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 15;");
        volver.setMaxWidth(Double.MAX_VALUE);

        volver.setOnAction(e -> {
            MainMenu menu = new MainMenu();
            menu.start(stage);
        });

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #e5e5e5;");

        container.getChildren().addAll(
                titulo, separator,
                lblDestino, destino,
                lblFecha, fecha,
                lblHorario, horarios,
                lblAsiento, asiento,
                lblPrecio,
                confirmar, volver
        );

        root.getChildren().add(container);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Reserva de Pasajes");
        stage.show();
    }

    // --------------------------
    // PRECIOS SEGÚN DESTINO
    // --------------------------
    private int obtenerPrecio(String destino) {
        switch (destino) {
            case "Calbuco":
            case "Maillen":
                return 15000;

            case "Isla Puluqui":
                return 20000;

            case "Achao":
            case "Quinchao":
                return 25000;

            case "Dalcahue":
            case "Curaco de Vélez":
                return 30000;

            case "Queilen":
            case "Chonchi":
                return 40000;

            case "Melinka":
            case "Quellón":
                return 50000;
        }
        return 0;
    }
}
