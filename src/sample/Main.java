package sample;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.function.Function;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        TableView<Row> table = new TableView<>();

        table.getSelectionModel().setCellSelectionEnabled(true);
        table.setEditable(true);

        table.getColumns().add(createColumn(" ", Row::colZeroProperty));
        table.getColumns().add(createColumn("A", Row::colAProperty));
        table.getColumns().add(createColumn("B", Row::colBProperty));
        table.getColumns().add(createColumn("C", Row::colCProperty));
        table.getColumns().add(createColumn("D", Row::colDProperty));
        table.getColumns().add(createColumn("E", Row::colEProperty));
        table.getColumns().add(createColumn("F", Row::colFProperty));
        table.getColumns().add(createColumn("G", Row::colGProperty));
        table.getColumns().add(createColumn("H", Row::colHProperty));

        table.getItems().addAll(
                //new Row(" ", "", "", "", "", "", "", "", ""),
                new Row("1", "", "", "", "", "", "", "", ""),
                new Row("2", "", "", "", "", "", "", "", ""),
                new Row("3", "", "", "", "", "", "", "", ""),
                new Row("4", "", "", "", "", "", "", "", ""),
                new Row("5", "", "", "", "", "", "", "", ""),
                new Row("6", "", "", "", "", "", "", "", ""),
                new Row("7", "", "", "", "", "", "", "", ""),
                new Row("8", "", "", "", "", "", "", "", "")

        );

        table.setOnKeyPressed(event -> {
            TablePosition<Row, ?> pos = table.getFocusModel().getFocusedCell();
            if (pos != null) {
                table.edit(pos.getRow(), pos.getTableColumn());
            }
        });

        Scene scene = new Scene(new BorderPane(table), 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableColumn<Row, String> createColumn(String title, Function<Row, StringProperty> property) {
        TableColumn<Row, String> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));

        col.setCellFactory(column -> new EditCell(property));

        return col;
    }

    private static class EditCell extends TableCell<Row, String> {

        private final TextField textField = new TextField();

        private final Function<Row, StringProperty> property;

        EditCell(Function<Row, StringProperty> property) {
            this.property = property;

            textProperty().bind(itemProperty());
            setGraphic(textField);
            setContentDisplay(ContentDisplay.TEXT_ONLY);

            textField.setOnAction(evt -> {
                commitEdit(textField.getText());
            });
            textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (!isNowFocused) {
                    commitEdit(textField.getText());
                }
            });
        }

        @Override
        public void startEdit() {
            super.startEdit();
            textField.setText(getItem());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.requestFocus();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        @Override
        public void commitEdit(String text) {
            super.commitEdit(text);
            Row row = getTableView().getItems().get(getIndex());
            StringProperty cellProperty = property.apply(row);
            cellProperty.set(text);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
