package com.accountingapp.controller;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import com.accountingapp.test.XLSReader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class AccountingApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    @FXML private Button okButton;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("RÉGIMEN DE INFORMACIÓN DE COMPRAS Y VENTAS.");

        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/cash_register.png"));
        
        initRootLayout();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AccountingApp.class.getResource("../view/Menu.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectXLS(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
        	XLSReader reader = new XLSReader();
        	reader.readFile(file);
        }
	}
    
    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) okButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    @FXML
    private void clickAbout(ActionEvent event) {
    	Parent root;
        try {
            root = FXMLLoader.load(AccountingApp.class.getResource("../view/AboutDialog/AboutDialog.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Acerca de...");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private void configureFileChooser(FileChooser fileChooser) {
		fileChooser.setTitle("Abrir Archivo XLS");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos MS Excel", "*.xls"));
	}

	/**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}