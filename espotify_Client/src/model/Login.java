package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Login extends Application {


	
	public Login(){}

	public Login(Stage primaryStage){
		start(primaryStage);
	}

	class WindowButtons extends HBox {

        public WindowButtons() {
            Button closeBtn = new Button("X");

            closeBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });

            this.getChildren().add(closeBtn);
        }
    }

	//Interficie en JavaFx porque ya tenia algo parecido en el Proyecto de PP
	@Override
	public void start(Stage primaryStage) {
		AnchorPane root = new AnchorPane();
		StackPane subfroot = new StackPane();
		root.getStyleClass().add("pane");
		primaryStage.setTitle("Iniciar Sesión");


		GridPane Ugrid = new GridPane();
		GridPane grid = new GridPane();
		GridPane subgrid = new GridPane();

		Ugrid.setAlignment(Pos.CENTER);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		subgrid.setHgap(100);
		subgrid.setVgap(10);
		
		subgrid.setAlignment(Pos.CENTER);
		grid.add(subgrid, 0, 1);
		grid.getStyleClass().add("fons");
		grid.setStyle("-fx-padding: 30 80 30 80;");
		
		JFXButton cancel = new JFXButton("Cancelar".toUpperCase());
		JFXButton inicia = new JFXButton("Iniciar".toUpperCase());
		TextField  usuariBox = new TextField ();
		subgrid.add(usuariBox, 0, 0);
		PasswordField pwBox = new PasswordField();
		StackPane PWBox = new StackPane();
		StackPane usuariBoxS = new StackPane();

		subgrid.setHgap(30);
		subgrid.setVgap(10);

		HBox hbInicia = new HBox(10);
		inicia.getStyleClass().add("button-raised");
		hbInicia.setAlignment(Pos.CENTER);
		hbInicia.getChildren().add(inicia);
		subgrid.add(hbInicia, 0, 3);
		Label la = new Label("");
		subgrid.add(la, 0, 2);


		pwBox.setPromptText("Contrasenya");
		pwBox.getStyleClass().add("Ptext-field");
		//byte[] pass = contra.getBytes();
		PWBox.getChildren().add(pwBox);
		PWBox.setAlignment(Pos.CENTER);
		subgrid.add(PWBox, 0, 1);
		
		usuariBox.setPromptText("Usuari");
		usuariBox.getStyleClass().add("Ptext-field");
		//byte[] pass = contra.getBytes();
		usuariBoxS.getChildren().add(usuariBox);
		usuariBoxS.setAlignment(Pos.CENTER);
		subgrid.add(usuariBoxS, 0, 0);
		
		inicia.setOnAction(ef -> {
			String contra = pwBox.getText();
			String usuari = usuariBox.getText();
			try {
				System.out.println("[CLIENT] - Petici— de connexi—...");
				Socket sServidor = new Socket("localhost", 34567);
				DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
				doStream.writeUTF("user:"+usuari+"/"+contra);
				System.out.println("Enviat");
				sServidor.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		
		Ugrid.add(grid, 0, 0);
		subfroot.getChildren().add(Ugrid);
		AnchorPane.setTopAnchor(subfroot, 250.0);
		AnchorPane.setLeftAnchor(subfroot, 145.0);
		Label l = new Label("eSpotyfai");
		AnchorPane.setTopAnchor(l, 100.0);
		AnchorPane.setLeftAnchor(l, 210.0);
		l.setStyle("-fx-text-fill: white;-fx-font-size:46px;-fx-font-family:\"Courier\";-fx-effect: dropshadow( three-pass-box , black , 3, 0.0 , 0 ,2);");
		
		root.getChildren().addAll(subfroot);
		root.getChildren().addAll(l);
		
		Scene scene1 = new Scene(root, 600,600);
		scene1.getStylesheets().add("view/Seleccio.css");

		primaryStage.setResizable(false);
		primaryStage.setScene(scene1);
		cancel.setOnAction(ed -> {
			primaryStage.setScene(scene1);
		});

        primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		
	}

	
}



