import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GerenciaGUI {

    public void mostrar(Stage stage){

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Panel de Gerencia • Patagonia Wellboat");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

       
        Label lblResumen = new Label("Resumen Diario");
        lblResumen.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField txtFechaResumen = new TextField();
        txtFechaResumen.setPromptText("Fecha (DD/MM/AAAA)");

        TextField txtPasajerosDia = new TextField();
        txtPasajerosDia.setPromptText("Pasajeros transportados");

        TextField txtCargaDia = new TextField();
        txtCargaDia.setPromptText("Carga transportada (kg)");

        TextField txtVentaPasajes = new TextField();
        txtVentaPasajes.setPromptText("Ventas por pasajes ($)");

        TextField txtVentaEncomiendas = new TextField();
        txtVentaEncomiendas.setPromptText("Ventas por encomiendas ($)");

        Button btnGuardarResumen = new Button("Guardar Resumen del Día");
        btnGuardarResumen.setOnAction(e -> alert("Resumen guardado para la fecha: " + txtFechaResumen.getText()));

        
        Label lblExtra = new Label("Programar Viaje Extraordinario");
        lblExtra.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField txtDestinoExtra = new TextField();
        txtDestinoExtra.setPromptText("Destino del viaje");

        TextField txtFechaExtra = new TextField();
        txtFechaExtra.setPromptText("Fecha del viaje");

        TextField txtBarcoExtra = new TextField();
        txtBarcoExtra.setPromptText("Nombre de la embarcación");

        TextField txtMotivoExtra = new TextField();
        txtMotivoExtra.setPromptText("Motivo del viaje extraordinario");

        Button btnProgramarExtra = new Button("Programar Viaje Extraordinario");
        btnProgramarExtra.setOnAction(e -> alert("Viaje extraordinario programado a " + txtDestinoExtra.getText()));

       
        Label lblDescuento = new Label("Aplicar Descuento Especial");
        lblDescuento.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField txtPrecioOriginal = new TextField();
        txtPrecioOriginal.setPromptText("Precio original ($)");

        TextField txtPorcentaje = new TextField();
        txtPorcentaje.setPromptText("Descuento (%)");

        Label lblResultado = new Label("Precio con descuento: -");

        Button btnCalcular = new Button("Aplicar Descuento");
        btnCalcular.setOnAction(e -> {
            try {
                int precio = Integer.parseInt(txtPrecioOriginal.getText());
                int porc = Integer.parseInt(txtPorcentaje.getText());
                int finalPrecio = precio - (precio * porc / 100);
                lblResultado.setText("Precio con descuento: $" + finalPrecio);
            } catch (Exception ex) {
                alert("Error: ingresa valores válidos.");
            }
        });

        
        Button btnVolver = new Button("VOLVER");
        btnVolver.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white;");
        btnVolver.setOnAction(e -> new MainMenu().mostrar(stage));

        
        VBox resumenBox = new VBox(10, lblResumen, txtFechaResumen, txtPasajerosDia, txtCargaDia,
                txtVentaPasajes, txtVentaEncomiendas, btnGuardarResumen);

        VBox extraBox = new VBox(10, lblExtra, txtDestinoExtra, txtFechaExtra, txtBarcoExtra,
                txtMotivoExtra, btnProgramarExtra);

        VBox descuentoBox = new VBox(10, lblDescuento, txtPrecioOriginal, txtPorcentaje, btnCalcular, lblResultado);

        root.getChildren().addAll(
                titulo,
                resumenBox,
                extraBox,
                descuentoBox,
                btnVolver
        );

        Scene scene = new Scene(root, 600, 800);
        stage.setScene(scene);
        stage.setTitle("Panel de Gerencia");
        stage.setMaximized(true);
        stage.show();
    }

    private void alert(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
