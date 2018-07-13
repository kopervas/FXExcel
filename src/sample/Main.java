package sample;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.function.Function;

public class Main extends Application {
    private static TableView<Row> table = new TableView<>();
    private static ObservableList<Formula> formuls = FXCollections.observableArrayList();
    private static char firstChar;
    private static Formula f;

    @Override
    public void start(Stage primaryStage) {
        ObservableList<Formula> formuls = FXCollections.observableArrayList();

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

        //table.getItems().addAll(
        ObservableList<Row> rows = FXCollections.observableArrayList(
                new Row("1", "", "", "", "", "", "", "", ""),
                new Row("2", "", "", "", "", "", "", "", ""),
                new Row("3", "", "", "", "", "", "", "", ""),
                new Row("4", "", "", "", "", "", "", "", ""),
                new Row("5", "", "", "", "", "", "", "", ""),
                new Row("6", "", "", "", "", "", "", "", ""),
                new Row("7", "", "", "", "", "", "", "", ""),
                new Row("8", "", "", "", "", "", "", "", ""),
                new Row("9", "", "", "", "", "", "", "", ""),
                new Row("10", "", "", "", "", "", "", "", "")

        );
        table.setItems(rows);

        /**
         * Таблиці присвоюється обробник події - клавіатурного вводу значення в комірку
         */
        table.setOnKeyPressed(event -> {
            String s = " ";
            TablePosition<Row, ?> pos = table.getFocusModel().getFocusedCell();
            //rows.get(1).getColC().replaceAll("", "replace");
            //(rows.get(pos.getRow())).setColC("N");                            //написано лише для перевірки
            if (pos != null) {

                switch (pos.getColumn()) {                                      //далі йде код отримання String з комірки
                    case 1:
                        s = table.getSelectionModel().getSelectedItem().getColA();      //власне так отримуємо String
                        System.out.println(s);
                        break;
                    case 2:
                        s = table.getSelectionModel().getSelectedItem().getColB();
                        System.out.println(s);
                        break;
                    case 3:
                        s = table.getSelectionModel().getSelectedItem().getColC();
                        System.out.println(s);
                        break;
                    case 4:
                        s = table.getSelectionModel().getSelectedItem().getColD();
                        System.out.println(s);
                        break;
                    case 5:
                        s = table.getSelectionModel().getSelectedItem().getColE();
                        System.out.println(s);
                        break;
                    case 6:
                        s = table.getSelectionModel().getSelectedItem().getColF();
                        System.out.println(s);
                        break;
                    case 7:
                        s = table.getSelectionModel().getSelectedItem().getColG();
                        System.out.println(s);
                        break;
                    case 8:
                        s = table.getSelectionModel().getSelectedItem().getColH();
                        System.out.println(s);
                        break;
                }
                //org.mariuszgromada.math.mxparser.Function f = new org.mariuszgromada.math.mxparser.Function();
               /* Expression e = new Expression(s);
                System.out.println("Result = "+e.calculate());*/    // - вся дія по обробці отриманого

                table.edit(pos.getRow(), pos.getTableColumn());
                //System.out.println(table.getSelectionModel().getSelectedItem());

                System.out.println("Row index: "+pos.getRow()+", Col index: "+pos.getColumn());
                //System.out.println((rows.get(pos.getRow())).setColC("N"));//getColC());
                //(rows.get(pos.getRow())).setColC("N");
            }
        });
        f = new Formula();
        Scene scene = new Scene(new BorderPane(table), 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Метод створення колонки в таблиці.
     * @param title - назва колонки
     * @param property - property, пов'язана зі StringProperty класу Row
     * @return - повертається колонка
     */
    private TableColumn<Row, String> createColumn(String title, Function<Row, StringProperty> property) {
        TableColumn<Row, String> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));

        col.setCellFactory(column -> new EditCell(property));
        col.setPrefWidth(35);
        return col;
    }

    /**
     * Клас редагування комірки. Перевизначаються методи
     * startEdit(), cancelEdti(), commitEdit()
     */
    private static class EditCell extends TableCell<Row, String> {

        private final TextField textField = new TextField();

        private Function<Row, StringProperty> property;

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
            //На даний момент - 15.06.18, - це головна проблема. Треба додати до ObservableArray<Formula> formuls
            //Для цього потрібно отримати текствоий рядок з комірки і значення її індексів по колонці і рядку.
            if(isFormula(textField.getText()))
            {
                //TablePosition<Row, ?> pos = (textField.getClip()).get;
                //formuls.add(new Formula(1,1,textField.getText()));
                f.action(textField.getText());
            }
        }

        @Override
        public void startEdit() {
            super.setStyle("-fx-border-width: 0.5; -fx-border-color: #ffffff; -fx-background-color: lightskyblue");
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
                if(isFormula(text)) {                                   //ось цей блок коду власне виконує обчислення
                    text = text.substring(1);                           //в комірці і повертає у неї значення
                    /*Expression e = new Expression(text);
                    Double d = e.calculate();*/
                    f = new Formula(text, table);
                    text = f.action(text);
                    System.out.println("Result = " + text);
                    cellProperty.set(text);
                    super.setStyle("-fx-border-width: 0.5; -fx-border-color: #000000; -fx-background-color: #ffffff");
            }
            else
                cellProperty.set(text);

            super.setStyle("-fx-border-width: 0.5; -fx-border-color: #000000; -fx-background-color: #ffffff");
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        /**
         * Метод, який перевіряє, чи є введений рядок формулою
         * Повертає true, якщо перший символ у рядку - "=" і якщо
         * в рядку є хоча б мінімально необхідна кількість символів
         * для формули - 4.
         * @param text
         * @return boolean
         */
        private boolean isFormula(String text){
            //firstChar = text.charAt(0);
            //if((firstChar == '=') && (text.length() != 0)) {
              if(text.indexOf("=") == 0 && text.length() > 3){
                return true;
            }
            return false;
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
