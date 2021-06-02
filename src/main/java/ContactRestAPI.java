import java.util.ArrayList;
import java.util.List;

public class ContactRestAPI {

    List<ContactData> dataList;
    public ContactRestAPI(List<ContactData> dataList) {
        this.dataList = new ArrayList<>(dataList);
    }

    public long countEntries() {
        return this.dataList.size();
    }

    public void addEmployeeToList(ContactData contactData) {
        this.dataList.add(contactData);
    }

    public ContactData getContact(String name) {
        return this.dataList.stream().filter(dataItem -> dataItem.firstname.equals(name)).findFirst().orElse(null);
    }

    public void updateContact(String name, String phonenumber) {
        ContactData contactData = this.getContact(name);
        if(contactData != null) {
            contactData.phonenumber = phonenumber;
        }
    }

}
