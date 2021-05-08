import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityStatesCollection {
    static List<String> cities = new ArrayList<>();
    static List<String> states = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static List citySearch() {
        cities.add("Nashik, Bhushan");
        cities.add("Nashik, Abhijit");
        cities.add("Mumbai, Akash");
        cities.add("Mumbai, Vijay");
        cities.add("Mumbai, Prathamesh");
        cities.add("Nagpur, Nikhil");
        cities.add("Nagpur, Tushar");
        return cities;
    }

    public static List stateSearch() {
        states.add("Maharashtra, Bhushan");
        states.add("Maharashtra, Abhijit");
        states.add("MadhyaPradesh, Akash");
        states.add("Maharashtra, Vijay");
        states.add("Karnataka, Prathamesh");
        states.add("karnataka, Nikhil");
        states.add("MadhyaPradesh, Tushar");
        return states;
    }

    public static void printCityPerson() {
        List<String> cities = citySearch();
        System.out.print("Enter the City you want to Search: ");
        String searchPersoninCity = input.nextLine();
        System.out.println("People From " + searchPersoninCity + " are: ");
        cities.stream().filter((person) -> person.startsWith(searchPersoninCity)).forEach(System.out::println);
        Long count = cities.stream().filter((person) -> person.startsWith(searchPersoninCity)).count();
        System.out.println("The Count of People/Person from " + searchPersoninCity + " is: " + count);
    }

    public static void printStatePerson() {
        List<String> states = stateSearch();
        System.out.print("Enter the State you want to Search: ");
        String searchPersoninState = input.nextLine();
        System.out.println("People From " + searchPersoninState + " are: ");
        states.stream().filter((person) -> person.startsWith(searchPersoninState)).forEach(System.out::println);
        Long count = states.stream().filter((person) -> person.startsWith(searchPersoninState)).count();
        System.out.println("The Count of People/Person from " + searchPersoninState + " is: " + count);
    }

    public static void main(String[] args) {
        printCityPerson();
        printStatePerson();
    }
}

