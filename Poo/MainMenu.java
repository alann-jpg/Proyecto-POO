import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage stage){
        stage.setMaximized(true);
        mostrar(stage);
    }

    public void mostrar(Stage stage){

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(25));
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #0d47a1, #42a5f5);"
        );

        Image logoImg = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoView = new ImageView(logoImg);
        logoView.setFitHeight(70);
        logoView.setPreserveRatio(true);

        Label titulo = new Label("PATAGONIA WELLBOAT");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titulo.setStyle("-fx-text-fill: white;");

        Label subtitulo = new Label("Sistema de Gestión de Operaciones Marítimas");
        subtitulo.setFont(Font.font("Arial", 14));
        subtitulo.setStyle("-fx-text-fill: #e3f2fd;");

        VBox textosHeader = new VBox(5, titulo, subtitulo);
        textosHeader.setAlignment(Pos.CENTER_LEFT);

        HBox header = new HBox(15, logoView, textosHeader);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(10, 10, 20, 10));

        root.setTop(header);

        Label lblMenu = new Label("Menú principal");
        lblMenu.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label lblDescripcion = new Label("Seleccione un módulo:");
        lblDescripcion.setFont(Font.font("Arial", 13));

        VBox infoTop = new VBox(5, lblMenu, lblDescripcion);

        Button btnReserva = new Button("Reserva de Pasajes");
        btnReserva.setMaxWidth(Double.MAX_VALUE);
        btnReserva.setStyle(
                "-fx-background-color: #0d6efd;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10 18;" +
                "-fx-background-radius: 12;"
        );

        Button btnEncomiendas = new Button("Encomiendas");
        btnEncomiendas.setMaxWidth(Double.MAX_VALUE);
        btnEncomiendas.setStyle(
                "-fx-background-color: #6c757d;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10 18;" +
                "-fx-background-radius: 12;"
        );

        Button btnBarcos = new Button("Gestión de Barcos");
        btnBarcos.setMaxWidth(Double.MAX_VALUE);
        btnBarcos.setStyle(
                "-fx-background-color: #198754;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10 18;" +
                "-fx-background-radius: 12;"
        );

        Button btnInfo = new Button("Información");
        btnInfo.setMaxWidth(Double.MAX_VALUE);
        btnInfo.setStyle(
                "-fx-background-color: #17a2b8;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10 18;" +
                "-fx-background-radius: 12;"
        );

        Button btnClientes = new Button("Clientes");
        btnClientes.setMaxWidth(Double.MAX_VALUE);
        btnClientes.setStyle(
                "-fx-background-color: #dc3545;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10 18;" +
                "-fx-background-radius: 12;"
        );

        Button btnGerencia = new Button("Panel de Gerencia");
        btnGerencia.setMaxWidth(Double.MAX_VALUE);
        btnGerencia.setStyle(
                "-fx-background-color: #ff9800;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10 18;" +
                "-fx-background-radius: 12;"
        );

        VBox botones = new VBox(12, btnReserva, btnEncomiendas, btnBarcos, btnInfo, btnClientes, btnGerencia);

        VBox card = new VBox(15, infoTop, botones);
        card.setPadding(new Insets(20));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;"
        );
        card.setMaxWidth(420);

        StackPane centerPane = new StackPane(card);
        centerPane.setAlignment(Pos.CENTER);

        root.setCenter(centerPane);

        Label footer = new Label("Proyecto desarrollado para ULAGOS");
        footer.setFont(Font.font("Arial", 11));
        footer.setStyle("-fx-text-fill: #bbdefb;");

        HBox footerBox = new HBox(footer);
        footerBox.setAlignment(Pos.CENTER_RIGHT);
        footerBox.setPadding(new Insets(10, 10, 0, 10));

        root.setBottom(footerBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menú Principal • Patagonia Wellboat");
        stage.setMaximized(true);
        stage.show();

        btnReserva.setOnAction(e -> new ReservaPasajesGUI().mostrar(stage));
        btnEncomiendas.setOnAction(e -> new EncomiendaGUI().mostrar(stage));
        btnBarcos.setOnAction(e -> new GestionBarcosGUI().mostrar(stage));
        btnInfo.setOnAction(e -> new PantallaInformacion().mostrar(stage));
        btnClientes.setOnAction(e -> new PantallaClientes().mostrar(stage));
        btnGerencia.setOnAction(e -> new GerenciaGUI().mostrar(stage));
    }

    public static void main(String[] args){
        launch();
    }
}
