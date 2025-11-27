import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class EncomiendaGUI {

    private static final Map<String, Integer> preciosKilo = new HashMap<>();
    static {
        preciosKilo.put("Calbuco", 4000);
        preciosKilo.put("Maillen", 4000);
        preciosKilo.put("Isla Puluqui", 5000);
        preciosKilo.put("Achao", 6000);
        preciosKilo.put("Quinchao", 6000);
        preciosKilo.put("Dalcahue", 8000);
        preciosKilo.put("Curaco de Vélez", 8000);
        preciosKilo.put("Queilen", 10000);
        preciosKilo.put("Chonchi", 10000);
        preciosKilo.put("Melinka", 12000);
        preciosKilo.put("Quellón", 12000);
    }

    public void mostrar(Stage stage) {

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label titulo = new Label("Envío de Encomiendas");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        ComboBox<String> cbDestino = new ComboBox<>();
        cbDestino.getItems().addAll(preciosKilo.keySet());
        cbDestino.setPromptText("Seleccione destino");

        TextField txtPeso = new TextField();
        txtPeso.setPromptText("Peso (kg)");

        TextField txtRemitente = new TextField();
        txtRemitente.setPromptText("Nombre completo del remitente");

        TextField txtDestinatario = new TextField();
        txtDestinatario.setPromptText("Nombre completo del destinatario");

        TextArea txtDescripcion = new TextArea();
        txtDescripcion.setPromptText("Contenido del paquete");
        txtDescripcion.setPrefRowCount(3);

        Label lblPrecioKg = new Label("Precio por kg: -");
        Label lblTotal = new Label("PRECIO TOTAL: -");

        // Mensaje con información de disponibilidad de barcos
        Label lblInfo = new Label(
                "Información: Los pasajeros pueden contactar a la empresa para solicitar una reserva.\n" +
                "Viajes diarios, horarios sujetos a condiciones meteorológicas.\n" +
                "Tipos de barcos:\n" +
                "- Catamarán Liviano: 20 pasajeros, 1000kg carga\n" +
                "- Ferry Mediano: 50 pasajeros, 3500kg carga\n" +
                "- Wellboat Gran Capacidad: 80 pasajeros, 5000kg carga"
        );
        lblInfo.setWrapText(true);

        // Actualizar precio automáticamente
        cbDestino.setOnAction(e -> actualizarPrecio(cbDestino, txtPeso, lblPrecioKg, lblTotal));
        txtPeso.setOnKeyReleased(e -> actualizarPrecio(cbDestino, txtPeso, lblPrecioKg, lblTotal));

        Button btnConfirmar = new Button("CONFIRMAR ENVÍO");
        btnConfirmar.setStyle("-fx-background-color: #0d6efd; -fx-text-fill: white; -fx-font-size: 14px;");
        btnConfirmar.setOnAction(e -> {
            if (cbDestino.getValue() == null || txtPeso.getText().isEmpty() ||
                txtRemitente.getText().isEmpty() || txtDestinatario.getText().isEmpty()) {

                alert("Debes completar todos los campos.");
                return;
            }
            alert("Envío registrado correctamente para " + cbDestino.getValue());
        });

        Button btnVolver = new Button("VOLVER");
        btnVolver.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-size: 14px;");
        btnVolver.setOnAction(e -> stage.close());

        root.getChildren().addAll(
                titulo, cbDestino, txtPeso, txtRemitente, txtDestinatario, txtDescripcion,
                lblPrecioKg, lblTotal, lblInfo, btnConfirmar, btnVolver
        );

        stage.setScene(new Scene(root, 500, 700));
        stage.setTitle("Envío de Encomiendas");
        stage.show();
    }

    private void actualizarPrecio(ComboBox<String> cbDestino, TextField txtPeso, Label lblPrecioKg, Label lblTotal) {
        String destino = cbDestino.getValue();
        if (destino == null) return;

        int precioKg = preciosKilo.get(destino);
        lblPrecioKg.setText("Precio por kg: $" + precioKg);

        try {
            int kg = Integer.parseInt(txtPeso.getText());
            int total = kg * precioKg;
            lblTotal.setText("PRECIO TOTAL: $" + total);
        } catch (Exception ex) {
            lblTotal.setText("PRECIO TOTAL: -");
        }
    }

    private void alert(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
