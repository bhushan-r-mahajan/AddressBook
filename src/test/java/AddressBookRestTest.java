import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class AddressBookRestTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:3000";
    }
    private ContactData[] getContactDetails() {
        Response response = RestAssured.get(RestAssured.baseURI + "/contacts");
        System.out.println("Employees in JSON Server are: \n" + response.asString());
        return new Gson().fromJson(response.asString(), ContactData[].class);
    }

    private Response addContactToJSONServer(ContactData contactData) {
        String employeeJSON = new Gson().toJson(contactData);
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.body(employeeJSON);
        return requestSpecification.post(RestAssured.baseURI + "/contacts");
    }

    @Test
    void givenContactsInJSONServer_WhenFetched_ShouldMatchCount() {
        ContactData[] contactData = getContactDetails();
        ContactRestAPI contactRestAPI = new ContactRestAPI(Arrays.asList(contactData));
        long entries = contactRestAPI.countEntries();
        Assertions.assertEquals(1, entries);
    }

    @Test
    void givenANewContact_WhenAdded_ShouldMatchCount() {
        ContactRestAPI contactRestAPI;
        ContactData[] dataArray = getContactDetails();
        contactRestAPI = new ContactRestAPI(Arrays.asList(dataArray));

        ContactData contactData;
        contactData = new ContactData(0, "vijay", "darade", "gangapur rd", "nashik", "maharashtra", 422013, "9999999999", "vijay@gmail.com");
        Response response = addContactToJSONServer(contactData);

        contactData = new Gson().fromJson(response.asString(), ContactData.class);
        contactRestAPI.addEmployeeToList(contactData);
        System.out.println("<<<<<<<<<- After Adding Into JSON Server ->>>>>>>>>\n" + getContactDetails());
        long entries = contactRestAPI.countEntries();
        Assertions.assertEquals(2, entries);
    }

    @Test
    void givenMultipleContacts_WhenAdded_ShouldMatchCount() {
        ContactRestAPI contactRestAPI;
        ContactData[] dataArray = getContactDetails();
        contactRestAPI = new ContactRestAPI(Arrays.asList(dataArray));

        ContactData[] arrayOfData = {
                new ContactData(0, "vijay", "darade", "gangapur rd", "nashik", "maharashtra", 422013, "9999999999", "vijay@gmail.com"),
                new ContactData(0, "shashank", "adka", "gangapur rd", "nashik", "maharashtra", 422013, "8888888888", "shashank@gmail.com"),
                new ContactData(0, "yash", "kulkarni", "gangapur rd", "nashik", "maharashtra", 422013, "6666666666", "yash@gmail.com"),
        };
        for (ContactData contactData : arrayOfData) {
            Response response = addContactToJSONServer(contactData);
            contactData = new Gson().fromJson(response.asString(), ContactData.class);
            contactRestAPI.addEmployeeToList(contactData);
        }

        System.out.println("<<<<<<<<<- After Adding Into JSON Server ->>>>>>>>>");
        getContactDetails();
        long entries = contactRestAPI.countEntries();
        Assertions.assertEquals(5, entries);
    }

    @Test
    void givenUpdateQuery_WhenUpdated_ShouldReturn200ResponseCode() {
        ContactRestAPI contactRestAPI;
        ContactData[] dataArray = getContactDetails();
        contactRestAPI = new ContactRestAPI(Arrays.asList(dataArray));

        contactRestAPI.updateContact("bhushan", "3333333333");
        ContactData contactData = contactRestAPI.getContact("bhushan");

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        String contactJSON = new Gson().toJson(contactData);
        requestSpecification.body(contactJSON);
        Response response = requestSpecification.put(RestAssured.baseURI + "/contacts/" + contactData.id);

        System.out.println("After Updating we have: \n" + response.asString());
        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode);
    }
}
