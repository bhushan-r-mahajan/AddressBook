import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
}
