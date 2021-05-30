import java.sql.*;
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

    public List<Data> getAllDetailsFromTable() throws CustomException {
        List<Data> contactDetails = new ArrayList<>();
        String sql = "select * from contact_details;";
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
}