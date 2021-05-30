import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDB {

    private Connection getConnection() throws CustomException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/address_book?useSSL=false";
        String userName = "root";
        String password = "Bhushan@0611";
        Connection connection;
        try {
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            throw new CustomException("Connection to Database Failed!!");
        }
        return connection;
    }

    private List<Data> getDataFromDBWhenSQLGiven(String sql) throws CustomException {
        List<Data> contactDetails;
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactDetails = getResultSet(resultSet);
        } catch (SQLException e) {
            throw new CustomException("Query Failed!!");
        }
        return contactDetails;
    }

    private List<Data> getResultSet(ResultSet resultSet) throws CustomException {
        List<Data> contactDetails = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zipp = resultSet.getInt("zip");
                String phone = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                contactDetails.add(new Data(id, firstName, lastName, address, city, state, zipp, phone, email));
            }
        } catch (SQLException e) {
            throw new CustomException("Query Failed!!");
        }
        return contactDetails;
    }

    public List<Data> getAllDetailsFromTable() throws CustomException {
        String sql = "select * from contact_details;";
        return this.getDataFromDBWhenSQLGiven(sql);
    }

    public int updateContactDetailsInDB(String firstName, String phone) throws CustomException {
        String sql = String.format("update contact_details set phone_number = '%s' where first_name = '%s';", phone, firstName);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new CustomException("Update Failed!!!");
        }
    }

    public List<Data> getContactDetailsAccordingToDate(LocalDate from, LocalDate to) throws CustomException {
        String sql = String.format("select * from contact_details where add_date between '%s' and '%s'",
                Date.valueOf(from), Date.valueOf(to));
        return this.getDataFromDBWhenSQLGiven(sql);
    }

    public List<Data> getContactDetailsAccordingToCity(String city) throws CustomException {
        String sql = String.format("select * from contact_details where city = '%s'", city);
        return this.getDataFromDBWhenSQLGiven(sql);
    }
}