/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.ComparadorPessoa;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helto
 */
public class ListarAlunosProfessorController implements Initializable {

     @FXML
    private TableView<Aluno> table_Aluno;
    @FXML
    private TableColumn<Aluno, String> tb_CellName;
    @FXML
    private TableColumn<Aluno, String> tb_CellEmail;
    @FXML
    private TableColumn<Aluno, String> tb_CellCPF;
    @FXML
    private TableColumn<Aluno, LocalDate> tb_CellDataNasc;
    @FXML
    private JFXTextField txt_ProcurarAluno;
    @FXML
    private JFXButton btn_Cancel;
    
    private ObservableList<Aluno> masterData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tb_CellCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tb_CellName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tb_CellDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tb_CellEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        table_Aluno.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        System.out.println(ProfessorController.getDisciplina().getAlunos().toString());
        masterData.clear();
        masterData.addAll(ProfessorController.getDisciplina().getAlunos());
        FilteredList <Aluno> filteredData = new FilteredList<>(masterData, p -> true);
            txt_ProcurarAluno.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(person -> {
               // Se não houver filtro, retorna toda a lista.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                    String lowerCaseFilter = newValue.toLowerCase();

                if (person.getNome().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  // filtro no nome.
                } else if (person.getCpf().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // filtro no CPF.
                }
                else if (person.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // filtro no CPF.
                }
                return false; // Se não houve filtro.
            });
        }); 
        SortedList<Aluno> sortedData = new SortedList<>(filteredData);
        sortedData.setComparator(new ComparadorPessoa());
        table_Aluno.setItems(sortedData.sorted());
                
    }    

    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
        stage.close();
    }
    
}
