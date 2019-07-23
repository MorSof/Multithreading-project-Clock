//Mor Soferian

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClockStartStopJavaFx extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		ClockPane clock = new ClockPane();
		Tick tick = new Tick(clock);
		Thread moveingClockThread = new Thread(tick);
		moveingClockThread.start();
		HBox hBox = new HBox(5);
		Button btStop = new Button("Stop");
		Button btStart = new Button("Start");
		hBox.getChildren().addAll(btStop, btStart);
		hBox.setAlignment(Pos.CENTER);
		BorderPane pane = new BorderPane();
		pane.setCenter(clock);
		pane.setBottom(hBox);
		Scene scene = new Scene(pane, 250, 300);
		primaryStage.setTitle("ClockStartStop");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});
		btStart.setOnAction(e -> {
			tick.play();
		});
		btStop.setOnAction(e -> {
			tick.pause();
		});
		clock.widthProperty().addListener(ov -> {
			clock.setW(pane.getWidth());
		});
		clock.heightProperty().addListener(ov -> {
			clock.setH(pane.getHeight());
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
