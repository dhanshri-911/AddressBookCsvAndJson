import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

    public class Main {
        static Scanner scanner = new Scanner(System.in);
        HashMap<Integer, AddressBookMain> addressbookNum = new HashMap<>();

        public void addMultipleAddressBook() throws IOException, ParseException {

            while (true) {

                System.out.println("Enter the \n1)to access address book\n2)0 to exit");
                int option = scanner.nextInt();
                switch (option) {
                    case 0:
                        System.out.println("Exiting from addressbooks");
                        System.exit(0);
                        break;

                    case 1:
                        System.out.println("Enter the addressbook number");
                        int N = scanner.nextInt();
                        System.out.printf("welcome to addressbook_%d\n", N);
                        if (addressbookNum.containsKey(N)) {
                            System.out.printf("addressbook_%d is already present you cannot add one more time\n", N);
                            break;
                        } else {
                            AddressBookMain addr = new AddressBookMain();
                            addr.addMultiplePerson();
                            addressbookNum.put(N, addr);
                            break;
                        }

                    default:
                        System.out.println("select valid option");
                        break;
                }

            }
        }

        public static void main(String[] args) throws IOException, ParseException {
            System.out.println("Start with Displaying Welcome to Address Book ");
            Main main = new Main();
            AddressBookMain addressBookMain = new AddressBookMain();
            addressBookMain.readPeopleFromFile();
            main.addMultipleAddressBook();
        }

    }


