/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import br.ufrpe.sigava.exceptions.AlunoNaoExisteException;
import br.ufrpe.sigava.exceptions.DisciplinaNaoExisteException;
import br.ufrpe.sigava.exceptions.ProfessorNaoExisteException;
import br.ufrpe.sigava.negocio.ServidorSigava;
import br.ufrpe.sigava.negocio.beans.Disciplina;
import br.ufrpe.sigava.negocio.beans.pessoa.Aluno;
import br.ufrpe.sigava.negocio.beans.pessoa.ComparadorPessoa;
import br.ufrpe.sigava.negocio.beans.pessoa.Professor;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CamilaNunes
 */
public class AssociarProfessorController implements Initializable {

    @FXML
    private TableColumn<Professor, String> tb_CellName;
    @FXML
    private TableColumn<Professor, String> tb_CellCPF;
    @FXML
    private JFXButton btn_Add;
    @FXML
    private JFXButton btn_Cancel;
    @FXML
    private JFXTextField txt_ProcurarProfessor; 
    @FXML
    private TableView<Professor> table_Professor;
 
    private ObservableList<Professor> masterData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Professor, String> tb_CellEmail;
    @FXML
    private TableColumn<Professor, LocalDate> tb_CellDataNasc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tb_CellCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tb_CellName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tb_CellDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tb_CellEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        masterData.addAll(ServidorSigava.getIstance().listarProfessores());
        FilteredList <Professor> filteredData = new FilteredList<>(masterData, p -> true);
            txt_ProcurarProfessor.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();       
           
            if (person.getNome().toLowerCase().contains(lowerCaseFilter)) {
                return true;  
            } else if (person.getCpf().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
            }); 
        });
            
        SortedList<Professor> sortedData = new SortedList<>(filteredData);
        sortedData.setComparator(new ComparadorPessoa());
        table_Professor.setItems(sortedData.sorted());
    }
    @FXML
    private void cancel_Close(ActionEvent event) {
        Stage stage = (Stage) btn_Cancel.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void adicionar(ActionEvent event) { 
        if(event.getSource() == btn_Add){
            Professor professor = table_Professor.getSelectionModel().getSelectedItem();
            Disciplina disciplina = ADMController.getDisciplina();
            try{
                if (!ServidorSigava.getIstance().existeProfessorDisciplina(disciplina, professor)){
                    ServidorSigava.getIstance().cadastrarProfessorDisciplina(disciplina, professor);
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setContentText("Professor "+professor.getNome()+" cadastrado na disciplina "+disciplina.getNome()+"!");
                    alerta.show();
                    ADMController.listaDisciplinas();
                }else{
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setContentText("Professor "+professor.getNome()+" já cadastrado!");
                    alerta.show();
                }
            }
            catch (DisciplinaNaoExisteException e){
                
            }catch (ProfessorNaoExisteException e2){
                
            }
        }
    }
}
            
    

