package emprestes.ds.app;

import emprestes.ds.domain.IStack;
import emprestes.ds.domain.data.Stack;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Objects.requireNonNull;

/**
 * JavaFX application that visualizes stack operations and their history.
 */
public class StackVisualizerApp extends Application {

    private final StackHistory history;
    private final FlowPane historyPane = new FlowPane();
    private final Label statusLabel = new Label("Ready");

    /** Creates the production application composition. */
    public StackVisualizerApp() {
        this(new Stack<>());
    }

    StackVisualizerApp(IStack<String> stack) {
        history = new StackHistory(stack, stack);
    }

    /**
     * Application entry point.
     *
     * @param args startup arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and shows the main application window.
     *
     * @param stage primary JavaFX stage
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Stack Visualizer");

        var root = new BorderPane();
        root.setPadding(new Insets(18));
        root.setCenter(buildHistoryPane());
        root.setRight(buildFormPane());
        root.setTop(buildHeader());

        captureSnapshot("Empty stack");

        var scene = new Scene(root, 1180, 720);
        scene.getStylesheets().add(requireNonNull(getClass().getResource("/stack.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Builds the scrollable area that renders snapshot cards.
     *
     * @return history pane node
     */
    private Node buildHistoryPane() {
        historyPane.setPadding(new Insets(12));
        historyPane.setHgap(16);
        historyPane.setVgap(16);
        historyPane.setPrefWrapLength(920);

        var scroll = new ScrollPane(historyPane);
        scroll.setFitToWidth(true);
        scroll.setPannable(true);
        scroll.getStyleClass().add("history-scroll");
        return scroll;
    }

    /**
     * Builds the side panel with stack operation controls.
     *
     * @return form panel
     */
    private VBox buildFormPane() {
        var title = new Label("Operations");
        title.getStyleClass().add("panel-title");

        var instructions = new Label("Enter a value and choose Push or Pop.\nUse commas to submit multiple values.");
        instructions.setWrapText(true);

        var valueField = new TextField();
        valueField.setPromptText("value or list: 1,2,3");

        var pushButton = new Button("Push");
        var popButton = new Button("Pop");
        pushButton.setMaxWidth(Double.MAX_VALUE);
        popButton.setMaxWidth(Double.MAX_VALUE);

        pushButton.setOnAction(e -> {
            var raw = valueField.getText().trim();
            if (raw.isEmpty()) {
                statusLabel.setText("Nothing to push");
                valueField.requestFocus();
                return;
            }
            history.push(Arrays.asList(raw.split(",")), "Push " + raw);
            valueField.clear();
            valueField.requestFocus();
            statusLabel.setText("Push completed");
            renderHistory();
        });

        popButton.setOnAction(e -> {
            var popped = history.pop("Pop");
            statusLabel.setText(popped == null ? "Empty stack" : "Pop: " + popped);
            renderHistory();
            valueField.requestFocus();
        });

        var form = new VBox(10, title, instructions, valueField, pushButton, popButton, new Separator(), new Label("Log"), statusLabel);
        form.setAlignment(Pos.TOP_LEFT);
        form.setPadding(new Insets(14));
        form.setPrefWidth(260);
        form.getStyleClass().add("side-panel");

        return form;
    }

    /**
     * Builds the header section of the page.
     *
     * @return header container
     */
    private HBox buildHeader() {
        var title = new Label("Stack Operations");
        title.setFont(Font.font("Work Sans", 26));

        var subtitle = new Label("Complete state history after every operation.");
        subtitle.getStyleClass().add("muted");

        var box = new VBox(4, title, subtitle);
        var container = new HBox(box);
        container.setPadding(new Insets(4, 0, 14, 0));
        return container;
    }

    /**
     * Captures and stores a snapshot of the current stack state.
     *
     * @param label operation label associated with the snapshot
     */
    private void captureSnapshot(String label) {
        history.capture(label);
        renderHistory();
    }

    /**
     * Re-renders every snapshot card in the history pane.
     */
    private void renderHistory() {
        historyPane.getChildren().clear();

        var snapshots = history.snapshots();
        for (int i = 0; i < snapshots.size(); i++) {
            var snapshot = snapshots.get(i);
            historyPane.getChildren().add(buildStateCard(i + 1, snapshot));
        }
    }

    /**
     * Builds one visual card for a stack snapshot.
     *
     * @param index snapshot sequence number
     * @param snapshot snapshot data
     * @return card component
     */
    private VBox buildStateCard(int index, StackSnapshot snapshot) {
        var title = new Label("#" + index + " • " + snapshot.label());
        title.getStyleClass().add("card-title");

        var stackBox = new VBox(8);
        stackBox.setAlignment(Pos.BOTTOM_CENTER);
        stackBox.setPrefWidth(140);

        var values = snapshot.values();
        if (values.isEmpty()) {
            var empty = new Label("∅");
            empty.getStyleClass().add("empty");
            stackBox.getChildren().add(empty);
        } else {
            var reversed = new java.util.ArrayList<>(values);
            Collections.reverse(reversed);
            for (int i = 0; i < reversed.size(); i++) {
                stackBox.getChildren().add(buildBlock(reversed.get(i), i));
            }
        }

        var card = new VBox(10, title, stackBox);
        card.getStyleClass().add("state-card");
        card.setPadding(new Insets(12));
        return card;
    }

    /**
     * Builds one stack block rectangle with text.
     *
     * @param value block text
     * @param indexFromTop index used to vary color by depth
     * @return block node
     */
    private StackPane buildBlock(String value, int indexFromTop) {
        var height = 30;
        var rect = new Rectangle(120, height);
        rect.setArcWidth(10);
        rect.setArcHeight(10);
        var hue = 210 - (indexFromTop * 10);
        rect.setFill(Color.hsb(hue, 0.55, 0.88));
        rect.setStroke(Color.rgb(30, 35, 44, 0.35));

        var label = new Label(value);
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-font-weight: 700;");

        var pane = new StackPane(rect, label);
        pane.setMaxWidth(140);
        return pane;
    }
}
