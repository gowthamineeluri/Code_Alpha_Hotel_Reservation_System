import java.util.List;

public class Hotel {

    private static int hId=10;

    private String hotelId;

    private String hotelName;

    private String location;

    private List<Room> rooms;

    public Hotel() {
    }

    public Hotel(String hotelName, String location, List<Room> rooms) {
        this.hotelId = "H"+hId++;
        this.hotelName = hotelName;
        this.location = location;
        this.rooms=rooms;
    }

    public static int gethId() {
        return hId;
    }

    public static void sethId(int hId) {
        Hotel.hId = hId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId='" + hotelId + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", location='" + location + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
