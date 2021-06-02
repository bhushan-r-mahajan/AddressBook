import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AddressBookRestTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:3000";
    }
    private ContactData[] getEmployeeDetails() {
        Response response = RestAssured.get(RestAssured.baseURI + "/contacts");
        System.out.println("Employees in JSON Server are: \n" + response.asString());
        return new Gson().fromJson(response.asString(), ContactData[].class);
    }

    @Test
    void givenContactsInJSONServer_WhenFetched_ShouldMatchCount() {
        ContactData[] contactData = getEmployeeDetails();
        ContactRestAPI contactRestAPI = new ContactRestAPI(Arrays.asList(contactData));
        long entries = contactRestAPI.countEntries();
        Assertions.assertEquals(1, entries);
    }
}
