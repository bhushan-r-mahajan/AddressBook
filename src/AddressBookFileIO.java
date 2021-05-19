import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AddressBookFileIO {
    public static String File = "address_book.txt";

    public void writeData(List<Data> contacts) {
        StringBuffer buffer = new StringBuffer();
        contacts.forEach(contact -> {
            String contactsString = contact.toString().concat("\n");
            buffer.append(contactsString);
        });
        try {
            Files.write(Paths.get(File), buffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readContacts() {
        try {
            Files.lines(new File("address_book.txt").toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countContacts() {
        long entries = 0;
        try {
            entries = Files.lines(new File("address_book.txt").toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The Number of entries are: " + entries);
        return entries;
    }

    public static void main(String[] args) {
        AddressBookFileIO io = new AddressBookFileIO();
        Data[] contactsArr = {
                new Data("bhushan" , "mahajan", "ggp rd", "nashik", "maharashtra", "4222222", "9999999999", "bhushan@gmail.com"),
                new Data("abhijit" , "patil", "ggp rd", "nashik", "maharashtra", "4220003", "9999999999", "abhijit@gmail.com"),
                new Data("vijay" , "darade", "ggp rd", "nashik", "maharashtra", "422009", "9999999999", "vijay@gmail.com")
        };
        io.writeData(Arrays.asList(contactsArr));
        io.readContacts();
        io.countContacts();
    }
}
