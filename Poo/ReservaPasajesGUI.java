import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReservaPasajesGUI {

    public void mostrar(Stage stage){

        VBox container = new VBox(12);
        container.setPadding(new Insets(25));
        container.setAlignment(Pos.TOP_LEFT);

        Label titulo = new Label("PATAGONIA WELLBOAT");

        Label lblCliente = new Label("CLIENTE:");
        ComboBox<Cliente> cbCliente = new ComboBox<>();
        cbCliente.getItems().addAll(Cliente.clientesGlobales);

        Label lblDestino = new Label("DESTINO:");
        ComboBox<String> destino = new ComboBox<>();
        destino.getItems().addAll("Calbuco","Maillen","Isla Puluqui","Achao",
                "Quinchao","Dalcahue","Curaco de Vélez","Queilen",
                "Chonchi","Melinka","Quellón");

        Label lblFecha = new Label("FECHA:");
        DatePicker fecha = new DatePicker();

        Label lblHorario = new Label("HORARIOS:");
        ComboBox<String> horarios = new ComboBox<>();
        horarios.getItems().addAll("09:00","12:00","15:00","18:00");

        Label lblAsiento = new Label("ASIENTO:");
        ComboBox<String> asiento = new ComboBox<>();
        asiento.getItems().addAll("1A","2A","3A","4A","5A","10B","11B","12A","12B","15C");

        Label lblPrecio = new Label("PRECIO: $0");

        destino.setOnAction(e -> {
            int precio = obtenerPrecio(destino.getValue());
            lblPrecio.setText("PRECIO: $" + precio);
        });

        Button confirmar = new Button("CONFIRMAR");
        Button volver = new Button("VOLVER");

        confirmar.setOnAction(e -> {
            if (cbCliente.getValue() == null){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Debes seleccionar un cliente.");
                a.showAndWait();
                return;
            }

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("Reserva realizada para " + cbCliente.getValue().getNombre());
            a.showAndWait();
        });

        volver.setOnAction(e -> new MainMenu().mostrar(stage));

        container.getChildren().addAll(
                titulo, lblCliente, cbCliente,
                lblDestino, destino,
                lblFecha, fecha,
                lblHorario, horarios,
                lblAsiento, asiento,
                lblPrecio, confirmar, volver
        );

        VBox root = new VBox(container);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private int obtenerPrecio(String destino){
        if (destino == null) return 0;
        switch (destino) {
            case "Calbuco":
            case "Maillen": return 15000;
            case "Isla Puluqui": return 20000;
            case "Achao":
            case "Quinchao": return 25000;
            case "Dalcahue":
            case "Curaco de Vélez": return 30000;
            case "Queilen":
            case "Chonchi": return 40000;
            case "Melinka":
            case "Quellón": return 50000;
        }
        return 0;
    }
}
