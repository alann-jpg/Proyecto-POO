import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainMenu extends Application{

    @Override
    public void start(Stage stage){
        mostrar(stage);
    }

    public void mostrar(Stage stage){
        System.out.println(">>> CARGANDO MAINMENU NUEVO <<<");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #e5e5e5;");
        root.setPadding(new Insets(40));

        Label titulo = new Label("PATAGONIA WELLBOAT");
        titulo.setFont(Font.font("Arial", 24));

        Button btnReserva = new Button("Reserva de Pasajes");
        btnReserva.setStyle("-fx-background-color: #0d6efd; -fx-text-fill: white; -fx-font-size: 16; -fx-padding: 10 20;");
        btnReserva.setMaxWidth(250);

        Button btnEncomiendas = new Button("Encomiendas");
        btnEncomiendas.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-size: 16; -fx-padding: 10 20;");
        btnEncomiendas.setMaxWidth(250);

        Button btnBarcos = new Button("Gestión de Barcos");
        btnBarcos.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-size: 16; -fx-padding: 10 20;");
        btnBarcos.setMaxWidth(250);

        Button btnInfo = new Button("Información");
        btnInfo.setStyle("-fx-background-color: #198754; -fx-text-fill: white; -fx-font-size: 16; -fx-padding: 10 20;");
        btnInfo.setMaxWidth(250);

        Button btnClientes = new Button("Clientes (CRUD)");
        btnClientes.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-size: 16; -fx-padding: 10 20;");
        btnClientes.setMaxWidth(250);

        btnReserva.setOnAction(e -> {
            ReservaPasajesGUI gui = new ReservaPasajesGUI();
            gui.mostrar(stage);
        });

        btnEncomiendas.setOnAction(e -> new EncomiendaGUI().mostrar(stage));

        btnBarcos.setOnAction(e -> System.out.println("Pantalla CRUD Barcos (aún no implementada)"));

        btnInfo.setOnAction(e -> new PantallaInformacion().mostrar(stage));

        btnClientes.setOnAction(e -> new PantallaClientes().mostrar(stage));

        root.getChildren().addAll(
                titulo,
                btnReserva,
                btnEncomiendas,
                btnBarcos,
                btnInfo,
                btnClientes
        );

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Menú Principal - Patagonia Wellboat");
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
