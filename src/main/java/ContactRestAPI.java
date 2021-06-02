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
}
