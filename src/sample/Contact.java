package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by dlocke on 11/30/16.
 */
public class Contact {

    //variables
    String name;
    String phone;
    String email;

    //constructor
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    //output to list view
    @Override
    public String toString() {
        return (name + ",  " + phone + ",  " + email);
    }

    public static void saveContact(ObservableList<Contact> contacts) throws IOException{
        JsonSerializer s = new JsonSerializer();
        String json = s.include("*").serialize(contacts);

        File f = new File("controller.json");
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    public static Contact loadContact() throws FileNotFoundException {
        File f = new File("controller.json");
        Scanner s = new Scanner(f);
        s.useDelimiter("\\Z");
        String contents = s.next();

        JsonParser p = new JsonParser();
        return p.parse(contents, Contact.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}//end class Contact
