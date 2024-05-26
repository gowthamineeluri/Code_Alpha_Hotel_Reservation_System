import java.util.List;
import java.util.Scanner;

public class User {

    public static User user;

    private String username;

    private String password;

    private List<Room> myBookedRooms;

    private List<Hotel> myBookedHotels;

    public User() {
    }

    public User(List<Room> myBookedRooms, String password, String username) {
        this.myBookedRooms = myBookedRooms;
        this.password = password;
        this.username = username;
    }


    public static void registerUser(){

        user=new User();
        Scanner scanner = new Scanner(System.in);

        String username = take_username();

        String password = take_password();

        user.setUsername(username);
        user.setPassword(password);

        loginUser();
    }

    public static void loginUser(){
        System.out.println();
        System.out.println("Please Login");
        String username = take_username();
        String password = take_password();

        try {
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                HotelReservationSystem.mainMenu();

            }else {
                System.out.println("Incorrect username or password...please try again");
                loginUser();
            }
        }catch (NullPointerException e){
            System.out.println("You are not registered as a customer please register yourself first by entering the below details");
            registerUser();
        }

    }

    public static String take_username() {

        Scanner scanner = new Scanner(System.in);
        String username = "";
        while (true) {
            System.out.println("Enter username (minimum 4 characters, must start with an alphabet):");
            username = scanner.nextLine();
            if (username != null && !username.isBlank() && username.length() >= 4 && Character.isAlphabetic(username.charAt(0))) {
                break;
            } else {
                System.out.println("Invalid username. Please enter a valid username with at least 4 characters and starting with an alphabet.");
            }
        }
        return username;
    }

    public static String take_password() {

        Scanner scanner = new Scanner(System.in);

        String password = "";
        while (true) {
            System.out.println("Enter password (minimum 4 characters):");
            password = scanner.nextLine();
            if (password != null && !password.isBlank() && password.length() >= 4) {
                break;
            } else {
                System.out.println("Invalid password. Please enter a password with at least 4 characters.");
            }
        }
        return password;
    }

    public List<Room> getMyBookedRooms() {
        return myBookedRooms;
    }

    public void setMyBookedRooms(List<Room> myBookedRooms) {
        this.myBookedRooms = myBookedRooms;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "myBookedRooms=" + myBookedRooms +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public List<Hotel> getMyBookedHotels() {
        return myBookedHotels;
    }

    public void setMyBookedHotels(List<Hotel> myBookedHotels) {
        this.myBookedHotels = myBookedHotels;
    }
}
