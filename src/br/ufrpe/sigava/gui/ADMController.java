/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import com.jfoenix.controls.JFXButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Thiago
 */
public class ADMController implements Initializable {

    @FXML
    private Pane pane_Aluno;
    @FXML
    private Pane pane_Disciplina;
    @FXML
    private Pane pane_Professor;
    @FXML
    private JFXButton btn_Aluno;
    @FXML
    private JFXButton btn_Disciplina;
    @FXML
    private JFXButton btn_Professor;
    @FXML
    private VBox vbox_Aluno;
    @FXML
    private JFXButton btn_Atualizar_Aluno;
    @FXML
    private JFXButton btn_Buscar_Aluno;
    @FXML
    private JFXButton btn_Cadastrar_Aluno;
    @FXML
    private JFXButton btn_Remover_Aluno;
    @FXML
    private VBox vbox_Professor;
    @FXML
    private JFXButton btn_Atualizar_Disciplina;
    @FXML
    private JFXButton btn_Buscar_Disciplina;
    @FXML
    private JFXButton btn_Cadastrar_Disciplina;
    @FXML
    private JFXButton btn_Remover_Disciplina;
    @FXML
    private VBox vbox_Disciplina;
    @FXML
    private JFXButton btn_Atualizar_Aluno11;
    @FXML
    private JFXButton btn_Buscar_Aluno11;
    @FXML
    private JFXButton btn_Cadastrar_Aluno11;
    @FXML
    private JFXButton btn_Remover_Aluno11;
    @FXML
    private JFXButton btn_Ass_Aluno_Disc;
    @FXML
    private JFXButton btn_Ass_Prof_Disc;
    @FXML
    private Pane pane_Default;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    public void handleClicks(ActionEvent event){ 
        if(event.getSource() == btn_Aluno){ 
           
            pane_Aluno.toFront(); 
            vbox_Aluno.toFront(); 
        } 
        if(event.getSource() == btn_Professor){ 
            
            pane_Professor.toFront(); 
            vbox_Professor.toFront(); 
        } 
        if(event.getSource() == btn_Disciplina){ 
            
            pane_Disciplina.toFront(); 
            vbox_Disciplina.toFront(); 
        } 
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
    
}