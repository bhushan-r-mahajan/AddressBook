import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ALL")

public class AddressBookFileIO {
    public static String File = "address_book.txt";
    public static String CSVFile = "address_book.csv";
    public static String JSONFile = "address_book.json";

    public static void main(String[] args) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        AddressBookFileIO io = new AddressBookFileIO();
        Data[] contactsArr = {
                new Data("bhushan", "mahajan", "ggp rd", "nashik", "maharashtra", "4222222", "9999999999", "bhushan@gmail.com"),
                new Data("abhijit", "patil", "ggp rd", "nashik", "maharashtra", "4220003", "9999999999", "abhijit@gmail.com"),
                new Data("vijay", "darade", "ggp rd", "nashik", "maharashtra", "422009", "9999999999", "vijay@gmail.com")
        };
        io.writeDataToFile(Arrays.asList(contactsArr));
        io.writeDataToCSVFile(Arrays.asList(contactsArr));
        io.readContacts();
        io.readContactsOfCSVFile();
    }

    public void writeDataToFile(List<Data> contacts) {
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

    public void writeDataToCSVFile(List<Data> contacts) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(CSVFile));
        ) {
            StatefulBeanToCsv<Data> beanToCsv = new StatefulBeanToCsvBuilder(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
            beanToCsv.write(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readContacts() {
        System.out.println("<----- The Content Of Txt file is ----->");
        try {
            Files.lines(new File("address_book.txt").toPath()).forEach(System.out::println);
            long entries = Files.lines(new File("address_book.txt").toPath()).count();
            System.out.println("The Number of entries in Txt File are: " + entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readContactsOfCSVFile() {
        System.out.println("<----- The Content Of CSV file is ----->");
        try {
            Files.lines(new File("address_book.csv").toPath()).forEach(System.out::println);
            long entries = Files.lines(new File("address_book.csv").toPath()).count();
            System.out.println("The Number of entries in CSVFile are: " + entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
