package bugslife.MainClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BugsLife extends Application {

    public static Stage stg;
    static Scanner in = new Scanner(System.in);
    static MainPage m = deserialize();

    @Override
    public void start(Stage primaryStage) throws Exception {
        User.userCount = m.getLastUserCount();
        Project.projectCount =  m.getLastProjectNum();
        stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bugslife/FXML/LoginPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/images/bugsIcon.png")));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @Override
    public void stop(){
        serialize(m);
    }
    
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public void closeWindow() {
        stg.close();
    }

    private static void serialize(MainPage m) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(m);
        String path = "data.json";

        try {
            FileWriter writer = new FileWriter(path);
            writer.write(json);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("JSON file successful created.");
    }

    private static MainPage deserialize() {
        Gson gson = new Gson();
        try {
            return gson.fromJson(new FileReader("data.json"), MainPage.class);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean canLogin(String user, String password) {
        for (User u : m.getUsers()) {
            if (u.getUsername().equals(user) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public MainPage getM() {
        return m;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
