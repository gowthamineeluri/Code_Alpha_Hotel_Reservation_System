import java.util.*;
import java.util.stream.Collectors;

public class HotelReservationSystem {

    public static ArrayList<Hotel> hotels;
    public static ArrayList<Room> rooms;
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Room> myBookedRooms = new ArrayList<>();
    public static ArrayList<Hotel> myBookedHotels=new ArrayList<>();

    public static void main(String[] args) {
        add_Rooms();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Register");
        System.out.println("2. Login");


        System.out.println("Please choose the operation: ");

        int choice = -1;

        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice, please choose 1 or 2:");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number:");
                scanner.next(); // Clear the invalid input
            }
        }
        switch (choice) {
            case 1:
                User.registerUser();
                break;
            case 2:
                User.loginUser();
                break;
        }


    }

    public static void mainMenu(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. View Hotels");
        System.out.println("2. My Bookings");
        System.out.println("3. Exit");

        System.out.println("Please choose the operation: ");
        int choice = -1;

        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2 || choice==3) {
                    break;
                } else {
                    System.out.println("Invalid choice, please choose 1 or 2:");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number:");
                scanner.next(); // Clear the invalid input
            }
        }
        switch (choice) {
            case 1:
                view_hotels();
                break;
            case 2:
                my_bookings();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }



    public static void add_Rooms() {
        rooms = new ArrayList<>();
        rooms.add(new Room("Single", true, 1000));
        rooms.add(new Room("Double", true, 2000));
        rooms.add(new Room("Deluxe", true, 4000));
        rooms.add(new Room("Family", true, 3000));
        rooms.add(new Room("Family", true, 3000));
        rooms.add(new Room("Single", true, 1000));
        rooms.add(new Room("Single", true, 1000));
        rooms.add(new Room("Double", true, 2000));
        rooms.add(new Room("Deluxe", true, 4000));
        rooms.add(new Room("Double", true, 2000));


        hotels = new ArrayList<>();
        Collections.shuffle(rooms);
        hotels.add(new Hotel("Taj hotel", "Mumbai", rooms.subList(3, 7)));
        Collections.shuffle(rooms);
        hotels.add(new Hotel("Sitara hotel", "Mumbai", rooms.subList(0, 5)));
        Collections.shuffle(rooms);
        hotels.add(new Hotel("Hotel Grand", "Bangalore", rooms.subList(2, 6)));
        Collections.shuffle(rooms);
        hotels.add(new Hotel("Hotel Majesty", "Bangalore", rooms.subList(1, 5)));
        Collections.shuffle(rooms);
        hotels.add(new Hotel("Royal Hotel", "Hyderabad", rooms.subList(4, 9)));
        Collections.shuffle(rooms);
        hotels.add(new Hotel("Hotel Silver", "Hyderabad", rooms.subList(1, 6)));

    }

    public static void view_hotels() {

        if (User.user == null) {
            System.out.println("Seems like you are not registered.Please create a new Account ");
            User.registerUser();

        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Mumbai");
        System.out.println("2. Bangalore");
        System.out.println("3. Hyderabad");

        System.out.println("Please choose the location: ");
        int choice = -1;

        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2 || choice ==3) {
                    break;
                } else {
                    System.out.println("Invalid choice, please choose 1 or 2 or 3:");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number:");
                scanner.next(); // Clear the invalid input
            }
        }

        switch (choice) {
            case 1:
                view_hotels_based_on_location("Mumbai");
                break;
            case 2:
                view_hotels_based_on_location("Bangalore");
                break;
            case 3:
                view_hotels_based_on_location("Hyderabad");
                break;
        }
    }

    public static void view_hotels_based_on_location(String location) {
        System.out.println("These are the hotels available in " + location);
        ArrayList<String> tempHotels = new ArrayList<>();
        int index = 1;
        for (Hotel hotel : hotels) {
            if (hotel.getLocation().equals(location)) {
                String hotelName = hotel.getHotelName();
                System.out.println(index + ". " + hotelName);
                tempHotels.add(hotelName);
                index++;
            }
        }
        int exit = index;
        System.out.println(exit + ". Back");


        int hotelChoice = select_hotel(index, exit);
        System.out.println(hotelChoice);
        String selectedHotel = tempHotels.get(hotelChoice - 1);
        display_rooms_in_selected_hotel(selectedHotel, location);

    }

    public static int select_hotel(int index, int exit) {
        int choice = -1;

        while (true) {
            try {
                System.out.println("Please enter your choice:");
                choice = scanner.nextInt();

                if (choice > 0 && choice < index) {
                    break;
                } else {
                    if (choice == exit) {
                        System.out.println(exit);
                        view_hotels();
                    }
                    System.out.println("Choice should be between 1 and " + (index ));
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number between 1 and " + (index ));
                scanner.next(); // Clear the invalid input
            }
        }

        return choice;
    }

    public static void display_rooms_in_selected_hotel(String selectedHotel, String location) {
        System.out.println("Please select a category in " + selectedHotel);
        HashSet<String> distinctCategory = new HashSet<>();
        for (Hotel hotel : hotels) {
            if (hotel.getLocation().equals(location) && hotel.getHotelName().equals(selectedHotel)) {
                for (Room room : hotel.getRooms()) {
                    distinctCategory.add(room.getCategory());
                }

                int i = 1;
                ArrayList<String> distinctCategoryList = new ArrayList<>(distinctCategory);
                for (String category : distinctCategoryList) {
                    System.out.println(i + ". " + category);
                    i++;
                }

                int exit = i;
                System.out.println(exit + ". " + "back");

                int categoryChoice = select_category(distinctCategoryList, exit, location);
                String selectedCategory = select_rooms_in_this_category(categoryChoice, distinctCategoryList, hotel);
                available_rooms(hotel, selectedCategory);
                book_room(hotel, selectedCategory);

            }
        }

    }

    public static int select_category(ArrayList<String> distinctCategoryList, int exit, String location) {
        int categoryChoice = -1;

        while (true) {
            try {
                System.out.println("Please enter your category choice:");
                categoryChoice = scanner.nextInt();

                if (categoryChoice == exit) {
                    view_hotels_based_on_location(location);
                } else if (categoryChoice > 0 && categoryChoice <=  (distinctCategoryList.size()+1)) {
                    break;
                } else {
                    System.out.println("Category choice should be between 1 and " + (distinctCategoryList.size()+1));


                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number between 1 and " +  (distinctCategoryList.size()+1));
                scanner.next(); // Clear the invalid input
            }
        }
        return categoryChoice;

    }

    public static String select_rooms_in_this_category(int categoryChoice, ArrayList<String> distinctCategoryList, Hotel hotel) {
        String selectedCategory = distinctCategoryList.get(categoryChoice - 1);
        System.out.println(distinctCategoryList.get(categoryChoice - 1));
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            if (room.getCategory().equals(selectedCategory) && room.isRoomAvailable()) {
                availableRooms.add(room);
            }
        }

        return selectedCategory;
    }


    public static void available_rooms(Hotel hotel, String selectedCategory) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            if (room.getCategory().equals(selectedCategory) && room.isRoomAvailable()) {
                availableRooms.add(room);
            }
        }

        if (!availableRooms.isEmpty()) {
            System.out.println("Rooms available: " + availableRooms.size());
            System.out.println("Price is " + availableRooms.get(0).getRoomPricePerDay());
        } else {
            System.out.println("sorry no rooms are available");
            mainMenu();
            System.exit(0);
        }
    }

    public static void book_room(Hotel hotel, String selectedCategory) {
        Scanner sc = new Scanner(System.in);
        String bookRoom = "";

        while (true) {
            System.out.println("Book Room? y/n");
            bookRoom = sc.nextLine().trim().toLowerCase();

            if (bookRoom.equals("y") || bookRoom.equals("n")) {
                break;
            } else {
                System.out.println("Invalid input, please enter 'y' for yes or 'n' for no:");
            }
        }
        System.out.println(bookRoom);
        if (bookRoom.equals("y")) {

            for (Room room : hotel.getRooms()) {
                if (room.getCategory().equals(selectedCategory) && room.isRoomAvailable()) {
                    System.out.println("Enter Payment");
                    double amount=0;
                    while (true) {
                    	 try {
                    		 amount = sc.nextDouble();
                    		 if(amount>=0) {
                    			 break;
                    		 }
						} catch (Exception e) {
							System.out.println("payment shoule be in numbers");
							sc.next();
						}
                    	
						
					}
                    boolean paymentDone = makePayment(amount, room.getRoomPricePerDay());
                    while (!paymentDone) {
                        System.out.println("Enter Payment");
                        amount = sc.nextDouble();
                        paymentDone = makePayment(amount, room.getRoomPricePerDay());
                    }
                    System.out.println("Room booked successfully...your room number is "+room.getRoomId());

                    room.setHotel(hotel);
                    myBookedRooms.add(room);
                    User.user.setMyBookedRooms(myBookedRooms);
                    room.setRoomAvailable(false);
                    Scanner sc1=new Scanner(System.in);
                    System.out.println("Want to book another room ? y/n");
                    String bookAnotherRoom = sc1.nextLine();
                    if(bookAnotherRoom.equals("y")){
                        mainMenu();
                    }else {
                        my_bookings();
                    }


                    break;
                }
            }


        } else {
            mainMenu();
        }

    }

    public static boolean makePayment(double amount, double roomPrice) {

        if (amount == roomPrice) {
            return true;
        } else {
            System.out.println("Please do correct payment");
            return false;
        }
    }

    private static void my_bookings() {

        if (User.user == null) {
            System.out.println("Seems like you are not registered.Please create a new Account ");
            User.registerUser();

        }
        if(User.user.getMyBookedRooms()==null || User.user.getMyBookedRooms().isEmpty()){
            System.out.println("You have not booked any rooms yet !!");
        }else {
                for(Room room: myBookedRooms){
                    System.out.println();
                    System.out.println("These are your room bookings");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("Location===> "+ room.getHotel().getLocation());
                    System.out.println("HotelName==> "+room.getHotel().getHotelName());
                    System.out.println("Room category===> "+room.getCategory());
                    System.out.println("Room Number===> "+room.getRoomId());
                    System.out.println("==========");
                }
            }

        mainMenu();

    }


}

