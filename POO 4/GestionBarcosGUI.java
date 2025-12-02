import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GestionBarcosGUI {

    private final ObservableList<Barco> barcos = FXCollections.observableArrayList();

    public void mostrar(Stage stage) {

        // --- TÍTULO ---
        Label titulo = new Label("Gestión de Barcos");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label subtitulo = new Label("Administración de flota: Patagonia Wellboat");
        subtitulo.setFont(Font.font("Arial", 14));

        VBox header = new VBox(5, titulo, subtitulo);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10, 0, 20, 0));

        // --- TABLA ---
        TableView<Barco> tabla = new TableView<>();
        tabla.setItems(barcos);
        tabla.setPrefHeight(250);

        TableColumn<Barco, String> colNombre = new TableColumn<>("Nombre / Tipo");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(200);

        TableColumn<Barco, String> colTipo = new TableColumn<>("Servicio");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colTipo.setPrefWidth(130);

        TableColumn<Barco, Integer> colCapPasajeros = new TableColumn<>("Pasajeros");
        colCapPasajeros.setCellValueFactory(new PropertyValueFactory<>("capacidadPasajeros"));
        colCapPasajeros.setPrefWidth(100);

        TableColumn<Barco, Double> colCapCarga = new TableColumn<>("Carga (kg)");
        colCapCarga.setCellValueFactory(new PropertyValueFactory<>("capacidadCarga"));
        colCapCarga.setPrefWidth(110);

        TableColumn<Barco, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colEstado.setPrefWidth(130);

        tabla.getColumns().addAll(
                colNombre,
                colTipo,
                colCapPasajeros,
                colCapCarga,
                colEstado
        );

        // --- FORMULARIO ---

        // Combo con los 3 barcos predefinidos
        ComboBox<String> cbNombre = new ComboBox<>();
        cbNombre.getItems().addAll(
                "Catamarán Liviano",
                "Ferry Mediano",
                "Wellboat de Gran Capacidad"
        );
        cbNombre.setPromptText("Seleccione el barco");

        // Tipo de servicio (puedes adaptarlo si quieres)
        ComboBox<String> cbTipo = new ComboBox<>();
        cbTipo.getItems().addAll("Pasajeros", "Carga", "Mixto", "Wellboat");
        cbTipo.setPromptText("Tipo de servicio");

        TextField txtCapPasajeros = new TextField();
        txtCapPasajeros.setPromptText("Capacidad pasajeros");
        txtCapPasajeros.setEditable(false);

        TextField txtCapCarga = new TextField();
        txtCapCarga.setPromptText("Capacidad de carga (kg)");
        txtCapCarga.setEditable(false);

        ComboBox<String> cbEstado = new ComboBox<>();
        cbEstado.getItems().addAll("Operativo", "En mantención", "Fuera de servicio");
        cbEstado.setPromptText("Estado");

        // Cuando cambias el nombre del barco, se rellenan las capacidades
        cbNombre.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                txtCapPasajeros.clear();
                txtCapCarga.clear();
                return;
            }

            switch (newVal) {
                case "Catamarán Liviano":
                    txtCapPasajeros.setText("20");
                    txtCapCarga.setText("1000");
                    break;
                case "Ferry Mediano":
                    txtCapPasajeros.setText("50");
                    txtCapCarga.setText("3500");
                    break;
                case "Wellboat de Gran Capacidad":
                    txtCapPasajeros.setText("80");
                    txtCapCarga.setText("5000");
                    break;
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(8);

        form.add(new Label("Barco:"), 0, 0);
        form.add(cbNombre, 1, 0);

        form.add(new Label("Tipo servicio:"), 0, 1);
        form.add(cbTipo, 1, 1);

        form.add(new Label("Cap. pasajeros:"), 2, 0);
        form.add(txtCapPasajeros, 3, 0);

        form.add(new Label("Cap. carga (kg):"), 2, 1);
        form.add(txtCapCarga, 3, 1);

        form.add(new Label("Estado:"), 2, 2);
        form.add(cbEstado, 3, 2);

        // --- BOTONES ---
        Button btnAgregar = new Button("Agregar");
        Button btnActualizar = new Button("Actualizar");
        Button btnEliminar = new Button("Eliminar");
        Button btnLimpiar = new Button("Limpiar");
        Button btnVolver = new Button("Volver al menú");

        btnAgregar.setStyle("-fx-background-color: #198754; -fx-text-fill: white;");
        btnActualizar.setStyle("-fx-background-color: #0d6efd; -fx-text-fill: white;");
        btnEliminar.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
        btnLimpiar.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white;");
        btnVolver.setStyle("-fx-background-color: #343a40; -fx-text-fill: white;");

        HBox acciones = new HBox(10, btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnVolver);
        acciones.setAlignment(Pos.CENTER_RIGHT);
        acciones.setPadding(new Insets(15, 0, 0, 0));

        // --- LAYOUT GENERAL ---
        VBox center = new VBox(15, tabla, form, acciones);
        center.setPadding(new Insets(15));
        center.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 15, 0, 0, 4);"
        );

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(center);
        root.setPadding(new Insets(25));
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #e5f2ff, #ffffff);"
        );

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Gestión de Barcos - Patagonia Wellboat");
        stage.show();

        // --- LÓGICA DE BOTONES ---

        // Cargar datos del barco seleccionado al formulario
        tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cbNombre.setValue(newSel.getNombre());
                cbTipo.setValue(newSel.getTipo());
                txtCapPasajeros.setText(String.valueOf(newSel.getCapacidadPasajeros()));
                txtCapCarga.setText(String.valueOf(newSel.getCapacidadCarga()));
                cbEstado.setValue(newSel.getEstado());
            }
        });

        // Agregar
        btnAgregar.setOnAction(e -> {
            try {
                String nombre = cbNombre.getValue();
                String tipo = cbTipo.getValue();
                String estado = cbEstado.getValue();

                if (nombre == null || tipo == null || estado == null) {
                    mostrarAlerta("Debes seleccionar barco, tipo de servicio y estado.");
                    return;
                }

                int capPasajeros = Integer.parseInt(txtCapPasajeros.getText());
                double capCarga = Double.parseDouble(txtCapCarga.getText());

                Barco barco = new Barco(
                        nombre,
                        tipo,
                        capPasajeros,
                        capCarga,
                        estado
                );

                barcos.add(barco);
                limpiarFormulario(cbNombre, cbTipo,
                        txtCapPasajeros, txtCapCarga, cbEstado, tabla);

            } catch (NumberFormatException ex) {
                mostrarAlerta("Las capacidades se rellenan automáticamente según el barco seleccionado.");
            }
        });

        // Actualizar
        btnActualizar.setOnAction(e -> {
            Barco seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta("Selecciona un barco de la tabla para actualizar.");
                return;
            }

            try {
                seleccionado.setNombre(cbNombre.getValue());
                seleccionado.setTipo(cbTipo.getValue());
                seleccionado.setCapacidadPasajeros(
                        Integer.parseInt(txtCapPasajeros.getText())
                );
                seleccionado.setCapacidadCarga(
                        Double.parseDouble(txtCapCarga.getText())
                );
                seleccionado.setEstado(cbEstado.getValue());

                tabla.refresh();
                limpiarFormulario(cbNombre, cbTipo,
                        txtCapPasajeros, txtCapCarga, cbEstado, tabla);

            } catch (NumberFormatException ex) {
                mostrarAlerta("Las capacidades deben ser numéricas.");
            }
        });

        // Eliminar
        btnEliminar.setOnAction(e -> {
            Barco seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta("Selecciona un barco para eliminar.");
                return;
            }
            barcos.remove(seleccionado);
            limpiarFormulario(cbNombre, cbTipo,
                    txtCapPasajeros, txtCapCarga, cbEstado, tabla);
        });

        // Limpiar
        btnLimpiar.setOnAction(e ->
                limpiarFormulario(cbNombre, cbTipo,
                        txtCapPasajeros, txtCapCarga, cbEstado, tabla)
        );

        // Volver al menú principal
        btnVolver.setOnAction(e -> {
            MainMenu menu = new MainMenu();
            menu.mostrar(stage);
        });
    }

    private void limpiarFormulario(ComboBox<String> cbNombre, ComboBox<String> cbTipo,
                                   TextField txtCapPasajeros, TextField txtCapCarga,
                                   ComboBox<String> cbEstado, TableView<Barco> tabla) {
        cbNombre.setValue(null);
        cbTipo.setValue(null);
        txtCapPasajeros.clear();
        txtCapCarga.clear();
        cbEstado.setValue(null);
        tabla.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
