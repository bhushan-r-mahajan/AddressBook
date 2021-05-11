import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MultipleAddressBook {
    List <Data> contacts = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    int index;
    String firstName;
    String name;

    public MultipleAddressBook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean checkName() {
        System.out.print("Enter First Name: ");
        firstName = input.nextLine();
        for (index = 0; index < contacts.size(); index++) {
            if (firstName.equals(contacts.get(index).getFirstName())) {
                System.out.println("Contact name found");
                return true;
            }
        }
        return false;
    }
    public void addContact() {
        if (!checkName()) {
            System.out.print("Enter Last Name: ");
            String lastName = input.nextLine();
            System.out.print("Enter Address: ");
            String address = input.nextLine();
            System.out.print("Enter City: ");
            String city = input.nextLine();
            System.out.print("Enter State: ");
            String state = input.nextLine();
            System.out.print("Enter Pin Code: ");
            String zip = input.nextLine();
            System.out.print("Enter Phone Number: ");
            String phone = input.nextLine();
            System.out.print("Enter Email ID: ");
            String email = input.nextLine();
            contacts.add(new Data(firstName, lastName, address, city, state, zip, phone, email));
        }
    }
    public void editContact() {
        if (checkName()) {
            System.out.print("Enter New Name: ");
            contacts.get(index).setFirstName(input.nextLine());
            System.out.print("Enter New Address: ");
            contacts.get(index).setAddress(input.nextLine());
            System.out.print("Enter New City: ");
            contacts.get(index).setCity(input.nextLine());
            System.out.print("Enter New State: ");
            contacts.get(index).setState(input.nextLine());
            System.out.print("Enter New Pin Code: ");
            contacts.get(index).setZip(input.nextLine());
            System.out.print("Enter New Phone Number: ");
            contacts.get(index).setPhoneNumber(input.nextLine());
            System.out.print("Enter New Email ID: ");
            contacts.get(index).setEmailId(input.nextLine());
            System.out.println("Successfully Edited The Contact");
        } else {
            System.out.println("Name not Found!!");
        }
    }
    public void deleteContact() {
        System.out.println("Enter the name of contact You want to Delete: ");
        for (index = 0; index < contacts.size(); index++) {
            if (checkName()) {
                contacts.remove(index);
                System.out.println("Successfully Deleted The Contact");
            }
        }
    }
    public void printContacts() {
        System.out.println("Contacts in address book are: ");
        if (contacts.isEmpty()) {
            System.out.println("No Contact Found");
        }
        for (Data showContacts : contacts) {
            System.out.println("\n" + showContacts + "\n");
        }
    }

    public void searchContactWithCity() {
        System.out.println("Enter the city you want to search: ");
        String city = input.nextLine();
        System.out.println("Names of people who are from " + city + " are: ");
        List<Data> cities = contacts.stream().filter(contacts -> city.equals(contacts.getCity())).collect(Collectors.toList());
        System.out.println(cities);
        System.out.println("Number of People form " + city + " are: "+ cities.stream().count());
    }
    public void searchContactWithState() {
        System.out.println("Enter the state you want to search: ");
        String state = input.nextLine();
        System.out.println("Names of people who are from " + state + " are: ");
        List<Data> states = contacts.stream().filter(contacts -> state.equals(contacts.getState())).collect(Collectors.toList());
        System.out.println(states);
        System.out.println("Number of People form " + state + " are: "+ states.stream().count());
    }
}
