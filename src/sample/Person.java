package sample;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    public Person(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    public final StringProperty firstNameProperty() {
        return this.firstName;
    }

    public final java.lang.String getFirstName() {
        return this.firstNameProperty().get();
    }

    public final void setFirstName(final java.lang.String firstName) {
        this.firstNameProperty().set(firstName);
    }

    public final StringProperty lastNameProperty() {
        return this.lastName;
    }

    public final java.lang.String getLastName() {
        return this.lastNameProperty().get();
    }

    public final void setLastName(final java.lang.String lastName) {
        this.lastNameProperty().set(lastName);
    }

    public final StringProperty emailProperty() {
        return this.email;
    }

    public final java.lang.String getEmail() {
        return this.emailProperty().get();
    }

    public final void setEmail(final java.lang.String email) {
        this.emailProperty().set(email);
    }


}