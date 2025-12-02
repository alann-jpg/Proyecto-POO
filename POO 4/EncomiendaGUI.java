import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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

        // ========= FONDO GENERAL =========
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(25));
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #004e92, #000428);" // Azul marino degradado
        );

        // ========= CABECERA =========
        Label titulo = new Label("Envío de Encomiendas");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label subtitulo = new Label("Módulo de gestión de encomiendas • Patagonia Wellboat");
        subtitulo.setStyle("-fx-font-size: 13px; -fx-text-fill: #e3f2fd;");

        VBox header = new VBox(5, titulo, subtitulo);
        header.setAlignment(Pos.TOP_LEFT);
        header.setPadding(new Insets(5, 5, 20, 5));

        root.setTop(header);

        // ========= FORMULARIO PRINCIPAL EN CARD =========
        ComboBox<String> cbDestino = new ComboBox<>();
        cbDestino.getItems().addAll(preciosKilo.keySet());
        cbDestino.setPromptText("Seleccione destino");
        cbDestino.setMaxWidth(Double.MAX_VALUE);

        TextField txtPeso = new TextField();
        txtPeso.setPromptText("Peso (kg)");
        txtPeso.setMaxWidth(Double.MAX_VALUE);

        TextField txtRemitente = new TextField();
        txtRemitente.setPromptText("Nombre completo del remitente");
        txtRemitente.setMaxWidth(Double.MAX_VALUE);

        TextField txtDestinatario = new TextField();
        txtDestinatario.setPromptText("Nombre completo del destinatario");
        txtDestinatario.setMaxWidth(Double.MAX_VALUE);

        TextArea txtDescripcion = new TextArea();
        txtDescripcion.setPromptText("Contenido del paquete");
        txtDescripcion.setPrefRowCount(3);
        txtDescripcion.setWrapText(true);
        txtDescripcion.setMaxWidth(Double.MAX_VALUE);

        Label lblPrecioKg = new Label("Precio por kg: -");
        lblPrecioKg.setStyle("-fx-font-size: 13px; -fx-text-fill: #37474f;");

        Label lblTotal = new Label("PRECIO TOTAL: -");
        lblTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1b5e20;");

        Label lblInfo = new Label(
                "Información del servicio:\n" +
                "• Los viajes dependen de las condiciones meteorológicas.\n" +
                "• El valor depende del destino y peso.\n" +
                "• Tipos de barcos disponibles:\n" +
                "   - Catamarán Liviano: 20 pasajeros, 1.000 kg\n" +
                "   - Ferry Mediano: 50 pasajeros, 3.500 kg\n" +
                "   - Wellboat Gran Capacidad: 80 pasajeros, 5.000 kg"
        );
        lblInfo.setWrapText(true);
        lblInfo.setStyle("-fx-font-size: 12px; -fx-text-fill: #455a64;");

        // EVENTOS PARA ACTUALIZAR PRECIOS
        cbDestino.setOnAction(e -> actualizarPrecio(cbDestino, txtPeso, lblPrecioKg, lblTotal));
        txtPeso.setOnKeyReleased(e -> actualizarPrecio(cbDestino, txtPeso, lblPrecioKg, lblTotal));

        // ========= BOTONES =========

        Button btnConfirmar = new Button("CONFIRMAR ENVÍO");
        btnConfirmar.setMaxWidth(Double.MAX_VALUE);
        btnConfirmar.setStyle(
                "-fx-background-color: #ff9800;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10 15;" +
                "-fx-background-radius: 10;"
        );

        btnConfirmar.setOnAction(e -> {
            if (cbDestino.getValue() == null ||
                txtPeso.getText().isEmpty() ||
                txtRemitente.getText().isEmpty() ||
                txtDestinatario.getText().isEmpty()) {

                alert("Debes completar todos los campos obligatorios.");
                return;
            }
            alert("Envío registrado correctamente para el destino: " + cbDestino.getValue());
        });

        Button btnVolver = new Button("VOLVER AL MENÚ");
        btnVolver.setMaxWidth(Double.MAX_VALUE);
        btnVolver.setStyle(
                "-fx-background-color: #546e7a;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 8 12;" +
                "-fx-background-radius: 10;"
        );

        btnVolver.setOnAction(e -> {
            MainMenu menu = new MainMenu();
            menu.mostrar(stage);
        });

        HBox botones = new HBox(10, btnConfirmar, btnVolver);
        botones.setAlignment(Pos.CENTER_RIGHT);

        // CARD (tarjeta blanca)
        VBox card = new VBox(12,
                new Label("Datos del envío:"),
                cbDestino,
                txtPeso,
                txtRemitente,
                txtDestinatario,
                txtDescripcion,
                lblPrecioKg,
                lblTotal,
                new Separator(),
                lblInfo,
                new Separator(),
                botones
        );

        card.setPadding(new Insets(20));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.30), 16, 0, 0, 6);"
        );
        card.setMaxWidth(450);

        VBox centerWrapper = new VBox(card);
        centerWrapper.setAlignment(Pos.CENTER);

        root.setCenter(centerWrapper);

        // ========= FOOTER ULAGOS =========
        Label footer = new Label("Proyecto desarrollado para ULAGOS");
        footer.setStyle("-fx-text-fill: #bbdefb; -fx-font-size: 12px;");

        HBox footerBox = new HBox(footer);
        footerBox.setAlignment(Pos.CENTER_RIGHT);
        footerBox.setPadding(new Insets(10, 5, 0, 5));

        root.setBottom(footerBox);

        // ========= ESCENA =========
        Scene scene = new Scene(root, 800, 650);
        stage.setScene(scene);
        stage.setTitle("Envío de Encomiendas • Patagonia Wellboat");
        stage.show();
    }

    private void actualizarPrecio(ComboBox<String> cbDestino, TextField txtPeso,
                                  Label lblPrecioKg, Label lblTotal) {

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
