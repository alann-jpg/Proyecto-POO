import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaInformacion{

    public void mostrar(Stage stage){

        Label info = new Label(
            "Información del sistema:\n\n" +
            "Gerente: Carlos Soto\n" +
            "Asistentes:\n" +
            "- Maria Espinoza\n" +
            "- Fernando Gutierrez\n" +
            "- José Ojeda"
        );

        Button volver = new Button("Volver al Menú Principal");

        volver.setOnAction(e -> {
            MainMenu main = new MainMenu();
            main.mostrar(stage); 
        });

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER_LEFT); 
        root.getChildren().addAll(info, volver);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Información");
        stage.show();
    }
}
