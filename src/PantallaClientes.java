import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaClientes{

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    public void mostrar(Stage stage){

        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre del cliente");

        TextField edadField = new TextField();
        edadField.setPromptText("Edad");

        Button agregar = new Button("Crear Cliente");
        Button eliminar = new Button("Eliminar Cliente");
        Button volver = new Button("Volver al Menú Principal");

        ListView<Cliente> lista = new ListView<>(clientes);
        VBox.setVgrow(lista, Priority.ALWAYS);

        lista.setCellFactory(param -> new ListCell<Cliente>(){
            @Override
            protected void updateItem(Cliente c, boolean empty) {
                super.updateItem(c, empty);
                if (empty || c == null) setText(null);
                else setText(c.getNombre() + " - " + c.getEdad() + " años");
            }
        });

        agregar.setOnAction(e -> {
            try {
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                clientes.add(new Cliente(nombre, edad));
                nombreField.clear();
                edadField.clear();
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Edad inválida");
                a.showAndWait();
            }
        });

        eliminar.setOnAction(e -> {
            Cliente seleccionado = lista.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                clientes.remove(seleccionado);
            }
        });

        volver.setOnAction(e -> {
            MainMenu main = new MainMenu();
            main.mostrar(stage);
        });

        VBox root = new VBox(10, nombreField, edadField, agregar, eliminar, lista, volver);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.setTitle("CRUD Clientes");
        stage.show();
    }
}
