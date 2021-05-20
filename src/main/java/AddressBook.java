import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")

public class AddressBook {

    List<MultipleAddressBook> AddressBookName = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    int bookNumber = 0;
    String name;

    public static void main(String[] args) {
        AddressBook menu = new AddressBook();
        menu.menu();
    }

    public void multipleAddressBook() {
        System.out.print("Enter the name of Address Book: ");
        name = input.nextLine();
        input.nextLine();
        AddressBookName.add(new MultipleAddressBook(name));
        System.out.println("New Address Book Created with name: " + name);
    }

    public void SelectAddressBook() {
        System.out.println("You are Currently in " + AddressBookName.get(bookNumber) + " AddressBook");
        if (AddressBookName.size() > 0) {

            for (int i = 0; i < AddressBookName.size(); i++)
                System.out.println(i + " - " + AddressBookName.get(i));

            System.out.print("Pick Address Book Number = ");
            bookNumber = Integer.parseInt(input.next());
        }
    }

    public void menu() {
        Scanner input = new Scanner(System.in);
        multipleAddressBook();
        while (true) {
            System.out.println("<---- Address Book ---->");
            System.out.println("1. Add Contact.");
            System.out.println("2. Edit Contact.");
            System.out.println("3. Delete Contact.");
            System.out.println("4. Add Another Address Book: ");
            System.out.println("5. See Contacts: ");
            System.out.println("6. Search Contacts with Same City: ");
            System.out.println("7. Search Contacts with Same State: ");
            System.out.println("8. Print Contacts Sorted By City: ");
            System.out.print("Enter Your Choice: ");

            int choice = input.nextInt();
            switch (choice) {
                case 1: {
                    SelectAddressBook();
                    AddressBookName.get(bookNumber).addContact();
                    break;
                }
                case 2:
                    AddressBookName.get(bookNumber).editContact();
                    break;
                case 3:
                    AddressBookName.get(bookNumber).deleteContact();
                    break;
                case 4: {
                    multipleAddressBook();
                    break;
                }
                case 5: {
                    System.out.println("<---- These are the AddressBooks Present ---->");
                    SelectAddressBook();
                    AddressBookName.get(bookNumber).printContacts();
                    break;
                }
                case 6:
                    AddressBookName.get(bookNumber).searchContactWithCity();
                    break;
                case 7:
                    AddressBookName.get(bookNumber).searchContactWithState();
                    break;
                case 8:
                    AddressBookName.get(bookNumber).printContactsSortedByCity();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}

