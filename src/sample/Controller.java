package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //fields from SceneBuilder
    @FXML
    TextField name;

    @FXML
    TextField phone;

    @FXML
    TextField email;

    @FXML
    ListView list;

    //user input received and placed into list of "contacts"
    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    //method to add contacts
    public void addContact() throws IOException {

        //ensure contact has all three fields, then create a new contact
        if(name.getText().isEmpty() | phone.getText().isEmpty() | email.getText().isEmpty()){
            System.out.println("Please input in all fields.");
        } else {
            Contact c = new Contact(name.getText(), phone.getText(), email.getText());
            contacts.add(c);
            name.setText("");
            phone.setText("");
            email.setText("");

            //save to JSON file
            try{
                Contact.saveContact(contacts);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //method to remove a contact from the list view
    public void removeContact(){
        Contact name = (Contact) list.getSelectionModel().getSelectedItem();
        contacts.remove(name);

        //save updated list to JSON file
        try{
            Contact.saveContact(contacts);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //initialize list of contacts
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(contacts);
    }

}//end class Controller
