import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaClientes {

    private ObservableList<Cliente> clientes =
            FXCollections.observableArrayList(Cliente.clientesGlobales);

    public void mostrar(Stage stage){

        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre del cliente");

        TextField edadField = new TextField();
        edadField.setPromptText("Edad");

        Button agregar = new Button("Crear Cliente");
        Button eliminar = new Button("Eliminar Cliente");
        Button volver = new Button("Volver");

        ListView<Cliente> lista = new ListView<>(clientes);
        VBox.setVgrow(lista, Priority.ALWAYS);

        lista.setCellFactory(param -> new ListCell<Cliente>(){
            protected void updateItem(Cliente c, boolean empty){
                super.updateItem(c, empty);
                if (empty || c == null) setText(null);
                else setText(c.getNombre() + " - " + c.getEdad());
            }
        });

        agregar.setOnAction(e -> {
            try {
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                Cliente nuevo = new Cliente(nombre, edad);

                clientes.add(nuevo);
                Cliente.clientesGlobales.add(nuevo);

                nombreField.clear();
                edadField.clear();
            } catch (Exception ex){
                new Alert(Alert.AlertType.ERROR, "Edad inválida").showAndWait();
            }
        });

        eliminar.setOnAction(e -> {
            Cliente c = lista.getSelectionModel().getSelectedItem();
            if (c != null){
                clientes.remove(c);
                Cliente.clientesGlobales.remove(c);
            }
        });

        volver.setOnAction(e -> new MainMenu().mostrar(stage));

        VBox root = new VBox(10, nombreField, edadField, agregar, eliminar, lista, volver);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
