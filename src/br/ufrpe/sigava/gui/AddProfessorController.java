/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.ProfessorJaExisteException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import br.ufrpe.sigava.gui.Controller;
import br.ufrpe.sigava.negocio.IServidorSigava;
import br.ufrpe.sigava.negocio.ServidorSigava;
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author helto
 */
public class AddProfessorController implements Initializable {

    @FXML
    private DatePicker calendar_AddProfessor;
    @FXML
    private JFXTextField txt_EmailProfessor;
    @FXML
    private JFXTextField txt_NomeProfessor;
    @FXML
    private JFXPasswordField passfield_PassProfessor;
    @FXML
    private JFXPasswordField passfield_ConfSenhaProfessor;
    @FXML
    private JFXComboBox<String> combobox_SexoProfessor;
    @FXML
    private JFXTextField txt_CPFProfessor;
    @FXML
    private JFXButton btn_Add;
    @FXML
    private JFXButton btn_Cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox_SexoProfessor.getItems().add(new String ("Masculino"));
        combobox_SexoProfessor.getItems().add(new String ("Feminino"));
        Biblioteca.AlteracaoCorMouse(btn_Add);
        Biblioteca.AlteracaoCorMouse(btn_Cancel);
    }    

    @FXML
    private void OnAction_SexoProfessor(ActionEvent event) {
    }

    @FXML
    private void add_Aluno(ActionEvent event) {
        IServidorSigava servidor = ServidorSigava.getIstance();
        String nome, cpf, email;
        String senha = null;
        char sexo;
        if(event.getSource() == btn_Add){
            LocalDate dataAniversario = calendar_AddProfessor.getValue();
            nome = txt_NomeProfessor.getText();
            cpf = txt_CPFProfessor.getText();
            email = txt_EmailProfessor.getText();
            if(combobox_SexoProfessor.getSelectionModel().getSelectedItem().equalsIgnoreCase("Masculino")){
                sexo = 'm';
            }else sexo = 'f';
            try{
                while(!passfield_PassProfessor.getText().equals(passfield_ConfSenhaProfessor.getText())){
                    Alert alertSenhaIncorreta = new Alert(Alert.AlertType.ERROR);
                    alertSenhaIncorreta.setTitle("SENHAS DIFERENTES");
                    alertSenhaIncorreta.setContentText("Senhas não conferem, digite novamente!");
                    alertSenhaIncorreta.show();
                    passfield_PassProfessor.setText("");
                    passfield_ConfSenhaProfessor.setText("");
                }                              
                Alert alertCadastro = new Alert(Alert.AlertType.CONFIRMATION);
                alertCadastro.setTitle("CADASTRO");
                alertCadastro.setContentText("Deseja Cadastrar o aluno?");
                Optional<ButtonType> result = alertCadastro.showAndWait();
                AddAluno add = new AddAluno();
                
                if(result.get() == ButtonType.OK){
                    servidor.cadastrarProfessor(nome, email, sexo, dataAniversario, senha, cpf);
                    Alert alertCadastrado = new Alert(Alert.AlertType.INFORMATION);
                    alertCadastrado.setTitle("CONFIRMAÇÃO DE CADASTRO");
                    alertCadastrado.setContentText("Aluno cadastrado com sucesso!");
                    Optional<ButtonType> result1 = alertCadastrado.showAndWait();
                    
                    if(result1.get() == ButtonType.OK){
                        calendar_AddProfessor.setValue(null);
                        txt_CPFProfessor.setText("");
                        txt_EmailProfessor.setText("");
                        txt_NomeProfessor.setText("");
                        passfield_ConfSenhaProfessor.setText("");
                        passfield_ConfSenhaProfessor.setText("");
                        combobox_SexoProfessor.setValue(null);
                    }
                }else{
                    calendar_AddProfessor.setValue(null);
                    txt_CPFProfessor.setText("");
                    txt_EmailProfessor.setText("");
                    txt_NomeProfessor.setText("");
                    passfield_ConfSenhaProfessor.setText("");
                    passfield_ConfSenhaProfessor.setText("");
                    combobox_SexoProfessor.setValue(null);
                }
                
            }catch(ProfessorJaExisteException e){
                Alert alertProfJaExiste = new Alert(Alert.AlertType.WARNING);
                alertProfJaExiste.setTitle("ALUNO JÁ EXISTE");
                alertProfJaExiste.setContentText(e.getMessage());
                Optional<ButtonType> result = alertProfJaExiste.showAndWait();
                if(result.get() == ButtonType.OK){
                    calendar_AddProfessor.setValue(null);
                    txt_CPFProfessor.setText("");
                    txt_EmailProfessor.setText("");
                    txt_NomeProfessor.setText("");
                    passfield_ConfSenhaProfessor.setText("");
                    passfield_ConfSenhaProfessor.setText("");
                    combobox_SexoProfessor.setValue(null);
                }
            }catch(IllegalArgumentException e1){
                Alert alertInvalido = new Alert(Alert.AlertType.ERROR);
                alertInvalido.setTitle("Erro no cadastro");
                alertInvalido.setContentText(e1.getMessage());
                Optional<ButtonType> result1 = alertInvalido.showAndWait();
                if(result1.get() == ButtonType.OK){
                    calendar_AddProfessor.setValue(null);
                    txt_CPFProfessor.setText("");
                    txt_EmailProfessor.setText("");
                    txt_NomeProfessor.setText("");
                    passfield_ConfSenhaProfessor.setText("");
                    passfield_ConfSenhaProfessor.setText("");
                    combobox_SexoProfessor.setValue(null);
                }
            }
        } 
    }

    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
        stage.close();
    }
    
}
