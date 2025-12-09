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

    private final ObservableList<Barco> barcos =
            FXCollections.observableArrayList(Barco.barcosGlobales);

    public void mostrar(Stage stage){

        Label titulo = new Label("Gestión de Barcos");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        TableView<Barco> tabla = new TableView<>();
        tabla.setItems(barcos);

        TableColumn<Barco, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Barco, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<Barco, Integer> colCapPas = new TableColumn<>("Pasajeros");
        colCapPas.setCellValueFactory(new PropertyValueFactory<>("capacidadPasajeros"));

        TableColumn<Barco, Double> colCapC = new TableColumn<>("Carga");
        colCapC.setCellValueFactory(new PropertyValueFactory<>("capacidadCarga"));

        TableColumn<Barco, String> colEst = new TableColumn<>("Estado");
        colEst.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tabla.getColumns().addAll(colNombre, colTipo, colCapPas, colCapC, colEst);

        ComboBox<String> cbNombre = new ComboBox<>();
        cbNombre.getItems().addAll(
                "Catamarán Liviano",
                "Ferry Mediano",
                "Wellboat de Gran Capacidad"
        );

        ComboBox<String> cbTipo = new ComboBox<>();
        cbTipo.getItems().addAll("Pasajeros", "Carga", "Mixto", "Wellboat");

        TextField txtCapPas = new TextField();
        TextField txtCapCarga = new TextField();

        ComboBox<String> cbEstado = new ComboBox<>();
        cbEstado.getItems().addAll("Operativo", "En mantención", "Fuera de servicio");

        cbNombre.setOnAction(e -> {
            String n = cbNombre.getValue();
            if (n == null) return;
            if (n.equals("Catamarán Liviano")){
                txtCapPas.setText("20");
                txtCapCarga.setText("1000");
            }
            if (n.equals("Ferry Mediano")){
                txtCapPas.setText("50");
                txtCapCarga.setText("3500");
            }
            if (n.equals("Wellboat de Gran Capacidad")){
                txtCapPas.setText("80");
                txtCapCarga.setText("5000");
            }
        });

        Button agregar = new Button("Agregar");
        Button actualizar = new Button("Actualizar");
        Button eliminar = new Button("Eliminar");
        Button volver = new Button("Volver");

        agregar.setOnAction(e -> {
            try {
                String n = cbNombre.getValue();
                String t = cbTipo.getValue();
                String es = cbEstado.getValue();
                int p = Integer.parseInt(txtCapPas.getText());
                double c = Double.parseDouble(txtCapCarga.getText());

                Barco b = new Barco(n, t, p, c, es);
                barcos.add(b);
                Barco.barcosGlobales.add(b);

            } catch (Exception ex) { }
        });

        actualizar.setOnAction(e -> {
            Barco sel = tabla.getSelectionModel().getSelectedItem();
            if (sel == null) return;

            try {
                sel.setNombre(cbNombre.getValue());
                sel.setTipo(cbTipo.getValue());
                sel.setCapacidadPasajeros(Integer.parseInt(txtCapPas.getText()));
                sel.setCapacidadCarga(Double.parseDouble(txtCapCarga.getText()));
                sel.setEstado(cbEstado.getValue());
                tabla.refresh();
            } catch (Exception ex) { }
        });

        eliminar.setOnAction(e -> {
            Barco sel = tabla.getSelectionModel().getSelectedItem();
            if (sel != null) {
                barcos.remove(sel);
                Barco.barcosGlobales.remove(sel);
            }
        });

        volver.setOnAction(e -> new MainMenu().mostrar(stage));

        VBox box = new VBox(10, titulo, tabla, cbNombre, cbTipo, txtCapPas, txtCapCarga, cbEstado,
                agregar, actualizar, eliminar, volver);
        box.setPadding(new Insets(20));

        Scene scene = new Scene(box, 900, 600);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
