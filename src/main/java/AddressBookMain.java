import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.stream.JsonParser;

public class AddressBookMain {
    static Scanner scanner = new Scanner(System.in);
    LinkedList<Contacts> person = new LinkedList<>();
    File file1 = new File("AddressBook.json");
    File file2 = new File("AddressBook.csv");
    //File file = new File("AddressBook.csv");


    public void addPerson() throws IOException, ParseException {
        System.out.println("Enter the First Name");
        String fName = scanner.next();
        System.out.println("Enter the Last Name");
        String lName = scanner.next();
        System.out.println("Enter the Address");
        String address = scanner.next();
        System.out.println("Enter the City");
        String city = scanner.next();
        System.out.println("Enter the State");
        String state = scanner.next();
        System.out.println("Enter the Zip");
        String zip = scanner.next();
        System.out.println("Enter the PhoneNumber");
        String phoneNumber = scanner.next();
        System.out.println("Enter the Email");
        String email = scanner.next();
        //creating contacts object and passing arguments inside constructor
        Contacts p = new Contacts(fName, lName, address, city, state, zip, phoneNumber, email);
        //adding p object into the linked list with Contact class data type
        person.add(p);
        //printing person array list
        System.out.println(person);
        readAndWriteJsonFile(p);
        writeFromCsv(p);
        readFromCsv();

    }

    //editing contact by searching names
    public void editPerson() {
        System.out.println("Enter the name to edit");
        String s = scanner.next();

        for (int i = 0; i < person.size(); i++) {
            Contacts p = person.get(i);
            if (s.equals(p.getFirstName())) {
                while (true) {
                    System.out.println("Enter choice to edit 1)firstName\n2)lastName\n3)address\n"
                            + "4)city\n5)state\n6)zip\n7)phoneNumber\n8)email9)exit");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            p.setFirstName(scanner.next());
                            break;
                        case 2:
                            p.setLastName(scanner.next());
                            break;
                        case 3:
                            p.setAddress(scanner.next());
                            break;
                        case 4:
                            p.setCity(scanner.next());
                            break;
                        case 5:
                            p.setState(scanner.next());
                            break;
                        case 6:
                            p.setZip(scanner.next());
                            break;
                        case 7:
                            p.setPhoneNumber(scanner.next());
                            break;
                        case 8:
                            p.setEmail(scanner.next());
                            break;
                        default:
                            System.out.println("select correct choice");
                            break;
                    }// end of switch
                    if (choice == 9)
                        break;
                } // end while
                person.set(i, p);
                System.out.println("person after editing");
                System.out.println(person);

            } // end of if
        } // end of for loop

    }

    // deleting contact details using person name
    public void deletePerson() {
        System.out.println("Enter the name to search and delete");
        String s = scanner.next();
        for (int i = 0; i < person.size(); i++) {
            Contacts p = person.get(i);
            if (s.equals(p.getFirstName())) {
                person.remove(i);
            }
        }
        System.out.println("contact after deletion");
        if (person.isEmpty() != true)
            System.out.println(person);
        else {
            System.out.println("contacts deleted");
        }
    }

    public void addMultiplePerson() throws IOException, ParseException {
        while (true) {
            System.out.println(
                    "Enter the option \n1)To Add Contect\n2)To Edit Contact" + "\n3)To Delete Contact\n4)exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> addPerson();
                case 2 -> editPerson();
                case 3 -> deletePerson();
                case 4 -> System.out.println("exiting");
                default -> System.out.println("invalid option");
            }
            if (option == 4)
                break;
        }

    }

    public static void main(String[] args) throws IOException, ParseException {
        AddressBookMain addressBook = new AddressBookMain();
        System.out.println("Start with Displaying Welcome to Address Book Program ");
        addressBook.addMultiplePerson();
    }

    public void readAndWriteJsonFile(Contacts p) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("FisrtName", p.getFirstName());
        jsonObject.put("LastName", p.getLastName());
        jsonObject.put("Adress", p.getAddress());
        jsonObject.put("City", p.getCity());
        jsonObject.put("State", p.getState());
        jsonObject.put("ContactNumber", p.getPhoneNumber());
        jsonObject.put("Zip", p.getZip());
        jsonObject.put("Email", p.getEmail());
        try {
            FileWriter file = new FileWriter(file1);
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: " + jsonObject);

    }

    public void writeFromCsv(Contacts p) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(file2));
        String[] header = {"FirstName","LastName","Address","State","City","Contact","Zip","Email"};
        csvWriter.writeNext(header);
        String[] person1 = {p.getFirstName(), p.getLastName(),p.getAddress(), p.getCity(),p.getState(),p.getZip(), p.getPhoneNumber(),p.getEmail()};
        csvWriter.writeNext(person1);
        csvWriter.close();
        System.out.println("Data Entered into CSv");

    }

    public void readFromCsv() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(file2));
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
            for (String cell : nextRecord) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }

    }


    public boolean readPeopleFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String name = null;
            while ((name = reader.readLine()) != null) {
                Contacts person1 = new Contacts(name, reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine());
                person.add(person1);        //adds person to the list
                reader.readLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
}
