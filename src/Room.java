public class Room {

    private static int id=100;

    private String roomId;

    private String category;

    private boolean isRoomAvailable;

    private double roomPricePerDay;

    private Hotel hotel;


    public Room() {
    }

    public Room(String category, boolean isRoomAvailable, double roomPricePerDay) {
        this.category = category;
        this.isRoomAvailable = isRoomAvailable;
        this.roomId = "R"+id++;
        this.roomPricePerDay = roomPricePerDay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Room.id = id;
    }

    public boolean isRoomAvailable() {
        return isRoomAvailable;
    }

    public void setRoomAvailable(boolean roomAvailable) {
        isRoomAvailable = roomAvailable;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public double getRoomPricePerDay() {
        return roomPricePerDay;
    }

    public void setRoomPricePerDay(double roomPricePerDay) {
        this.roomPricePerDay = roomPricePerDay;
    }

    @Override
    public String toString() {
        return "Room{" +
                "category='" + category + '\'' +
                ", roomId='" + roomId + '\'' +
                ", isRoomAvailable=" + isRoomAvailable +
                ", roomPricePerDay=" + roomPricePerDay +
//                ", hotel=" + hotel +
                '}';
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
