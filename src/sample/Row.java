package sample;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Row {
    public final StringProperty colZero = new SimpleStringProperty();
    public final StringProperty colA = new SimpleStringProperty();
    public final StringProperty colB = new SimpleStringProperty();
    public final StringProperty colC = new SimpleStringProperty();
    public final StringProperty colD = new SimpleStringProperty();
    public final StringProperty colE = new SimpleStringProperty();
    public final StringProperty colF = new SimpleStringProperty();
    public final StringProperty colG = new SimpleStringProperty();
    public final StringProperty colH = new SimpleStringProperty();

    public Row() {
    }

    public Row(String colZero, String colA, String colB, String colC, String colD, String colE, String colF, String colG, String colH) {
        setColZero(colZero); //colZero = new SimpleStringProperty(colZero);
        setColA(colA);
        setColB(colB);
        setColC(colC);
        setColD(colD);
        setColE(colE);
        setColF(colF);
        setColG(colG);
        setColH(colH);
    }

    public String getColZero() {
        return colZero.get();
    }

    public final StringProperty colZeroProperty() {
        return this.colZero;
    }

    public void setColZero(String colZero) {
        this.colZero.set(colZero);
    }

    public String getColA() {
        return colA.get();
    }

    public final StringProperty colAProperty() {
        return colA;
    }

    public void setColA(String colA) {
        this.colA.set(colA);
    }

    public String getColB() {
        return colB.get();
    }

    public final StringProperty colBProperty() {
        return colB;
    }

    public void setColB(String colB) {
        this.colB.set(colB);
    }

    public String getColC() {
        return colC.get();
    }

    public final StringProperty colCProperty() {
        return colC;
    }

    public void setColC(String colC) {
        this.colC.set(colC);
    }

    public String getColD() {
        return colD.get();
    }

    public final StringProperty colDProperty() {
        return colD;
    }

    public void setColD(String colD) {
        this.colD.set(colD);
    }

    public String getColE() {
        return colE.get();
    }

    public final StringProperty colEProperty() {
        return colE;
    }

    public void setColE(String colE) {
        this.colE.set(colE);
    }

    public String getColF() {
        return colF.get();
    }

    public final StringProperty colFProperty() {
        return colF;
    }

    public void setColF(String colF) {
        this.colF.set(colF);
    }

    public String getColG() {
        return colG.get();
    }

    public final StringProperty colGProperty() {
        return colG;
    }

    public void setColG(String colG) {
        this.colG.set(colG);
    }

    public String getColH() {
        return colH.get();
    }

    public final StringProperty colHProperty() {
        return colH;
    }

    public void setColH(String colH) {
        this.colH.set(colH);
    }
}
