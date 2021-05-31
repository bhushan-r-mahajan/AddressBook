import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AddressBookDBTest {

    @Test
    void giveDataFromADatabase_WhenRetrieved_ShouldMatchTheCount() throws CustomException {
        List<Data> contactDetails;
        AddressBookDB addressBookDB = new AddressBookDB();
        contactDetails = addressBookDB.getAllDetailsFromTable();
        System.out.println("The Number of Entries in DataBase are = " + contactDetails.size());
        Assertions.assertEquals(3, contactDetails.size());
    }

    @Test
    void givenNewSalary_WhenUpdated_ShouldPassTestAndBeInSync() throws CustomException {
        AddressBookDB addressBookDB = new AddressBookDB();
        int result = addressBookDB.updateContactDetailsInDB("amol", "2222222222");
        Assertions.assertEquals(1, result);
    }

    @Test
    void givenDate_WhenGiven_ShouldRetrieveEmployeeData() throws CustomException {
        AddressBookDB addressBookDB = new AddressBookDB();
        List<Data> contactDetails;
        LocalDate from = LocalDate.of(2020, 01, 01);
        LocalDate to = LocalDate.now();
        contactDetails = addressBookDB.getContactDetailsAccordingToDate(from, to);
        Assertions.assertEquals(3, contactDetails.size());
    }

    @Test
    void givenCityNaME_WhenFound_ShouldReturnNoOfContactsFromGivenCity() throws CustomException {
        AddressBookDB addressBookDB = new AddressBookDB();
        List<Data> contactDetails;
        contactDetails = addressBookDB.getContactDetailsAccordingToCity("nashik");
        Assertions.assertEquals(2, contactDetails.size());
    }

    @Test
    void givenANewEntry_WhenQueryExecuted_ShouldAddNewContactInDB() throws CustomException {
        AddressBookDB addressBookDB = new AddressBookDB();
        List<Data> contactDetails;
        addressBookDB.addNewContactToDB("bhushan", "mahajan", "ayodhya colony", "pune",  "maharashtra", 422014, "6666666666", "bhushan@gmail.com", LocalDate.now());
        contactDetails = addressBookDB.getAllDetailsFromTable();
        Assertions.assertEquals(4, contactDetails.size());
    }

    @Test
    void givenMultipleEmployees_WhenAddedToDB_ShouldMatchCount() throws CustomException {
        AddressBookDB addressBookDB = new AddressBookDB();
        List<Data> contactDataList;
        Data[]  contactArray = {
                new Data(0, "bhushan", "mahajan", "ayodhya colony", "pune",  "maharashtra", 422014, "6666666666", "bhushan@gmail.com", LocalDate.now()),
                new Data(0, "bhushan", "mahajan", "ayodhya colony", "pune",  "maharashtra", 422014, "6666666666", "bhushan@gmail.com", LocalDate.now()),
                new Data(0, "bhushan", "mahajan", "ayodhya colony", "pune",  "maharashtra", 422014, "6666666666", "bhushan@gmail.com", LocalDate.now()),
                new Data(0, "bhushan", "mahajan", "ayodhya colony", "pune",  "maharashtra", 422014, "6666666666", "bhushan@gmail.com", LocalDate.now()),
                new Data(0, "bhushan", "mahajan", "ayodhya colony", "pune",  "maharashtra", 422014, "6666666666", "bhushan@gmail.com", LocalDate.now()),
                new Data(0, "bhushan", "mahajan", "ayodhya colony", "pune",  "maharashtra", 422014, "6666666666", "bhushan@gmail.com", LocalDate.now()),
        };
        Instant startThread = Instant.now();
        addressBookDB.addMultipleContacts(Arrays.asList(contactArray));
        Instant endThread = Instant.now();
        System.out.println("Time Taken to Execute : " + Duration.between(startThread, endThread));
        contactDataList = addressBookDB.getAllDetailsFromTable();
        Assertions.assertEquals(7, contactDataList.size());
    }
}
