/*Sources
    https://docs.oracle.com/javase/8/javafx/api/overview-summary.html
    https://www.youtube.com/watch?v=FLkOX4Eez6o
    https://www.callicoder.com/javafx-registration-form-gui-tutorial/
    https://blog.idrsolutions.com/2014/04/use-external-css-files-javafx/
*/
package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.lang.String;
import java.io.File;
import javafx.scene.image.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.*;

public class Main extends Application {

    Scene scene1, scene2;
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        File f = new File("src\\sample\\stylesheet.css");
        Image i = new Image(getClass().getResourceAsStream("images/lock.png"));

        // Create the registration form grid pane
        GridPane gridPane = generateForm();
        // Add UI controls to the registration form grid pane
        addHomeControls(gridPane);
        // Create a scene with registration form grid pane as the root node

        Button button1= new Button("Create a profile");
        button1.setPrefHeight(40);
        button1.setDefaultButton(true);
        button1.setPrefWidth(160);
        button1.setOnAction(e -> primaryStage.setScene(scene2));

        VBox layout1= new VBox();
        layout1.getChildren().addAll(gridPane, button1);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 800, 500);
        // Gets css file used for so styling

        scene1.getStylesheets().clear();
        scene1.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        GridPane gridPane2 = generateForm();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane2);

        //Scene 2
        Button button2= new Button("Home");
        button2.setPrefHeight(40);
        button2.setDefaultButton(true);
        button2.setPrefWidth(100);
        button2.setOnAction(e -> primaryStage.setScene(scene1));
        VBox layout2= new VBox();
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(gridPane2, button2);
        scene2= new Scene(layout2,800,510);

        scene2.getStylesheets().clear();
        scene2.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        // Set the scene in primary stage
        window.setScene(scene1);
        window.setTitle("Personal Password Manager");
        window.initStyle(StageStyle.DECORATED);
        window.getIcons().add(i);
        window.show();
    }


    private GridPane generateForm() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addHomeControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Protect your passwords, people!");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Username : ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);


        // Add Password Label
        Label passwordLabel = new Label("Master PW : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);


        // Add Submit Button
        Button loginButton = new Button("Login");
        loginButton.setPrefHeight(40);
        loginButton.setDefaultButton(true);
        loginButton.setPrefWidth(100);
        gridPane.add(loginButton, 0, 4, 2, 1);
        GridPane.setHalignment(loginButton, HPos.CENTER);
        GridPane.setMargin(loginButton, new Insets(20, 0,0,0));

    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Enter a Username : ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);


        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 2);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 2);

        // Add Re-enter Password Label
        Label passwordLabel2 = new Label("Re-enter Password : ");
        gridPane.add(passwordLabel2, 0, 3);

        // Add Password Field
        PasswordField passwordField2 = new PasswordField();
        passwordField2.setPrefHeight(40);
        gridPane.add(passwordField2, 1, 3);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,0,0));

        String connectionUrl = "jdbc:mysql://localhost:3306/new_schema?user=root&password=fireflea431!";

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                    return;
                }
                else{
                    try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
                        String username = nameField.getText();
                        System.out.println(username);
                        String SQL = "INSERT INTO `new_schema`.`test` (`LastName`) VALUES ('" + username + "');";
                        System.out.println(SQL);
                        stmt.executeUpdate(SQL);

                    }
                    // Handle any errors that may have occurred.
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a master password");
                    return;
                }

                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + nameField.getText());
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void main(String[] args) {

        // System.out.println("TEST");
        // Create a variable for the connection string.
        // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        launch(args);
    }
}