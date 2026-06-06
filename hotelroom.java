import java.util.Scanner;

interface Service {
    double calculateBill(int days, double rate);
}

class Room {
    private int roomNumber;
    private String roomType;
    private double pricePerDay;
    private boolean isAvailable;

    Room(int roomNumber, String roomType, double pricePerDay, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerDay = pricePerDay;
        this.isAvailable = isAvailable;
    }

    int getRoomNumber() {
        return roomNumber;
    }

    String getRoomType() {
        return roomType;
    }

    double getPricePerDay() {
        return pricePerDay;
    }

    boolean isAvailable() {
        return isAvailable;
    }

    void bookRoom() {
        isAvailable = false;
    }

    void cancelBooking() {
        isAvailable = true;
    }

    void displayDetails() {
        String status = isAvailable ? "Available" : "Booked";
        System.out.println("Room Number: " + roomNumber
                + ", Type: " + roomType
                + ", Price Per Day: " + pricePerDay
                + ", Status: " + status);
    }
}

class Hotel implements Service {
    private Room[][] rooms;

    Hotel(int floors, int roomsPerFloor) {
        rooms = new Room[floors][roomsPerFloor];
        initializeRooms();
    }

    private void initializeRooms() {
        for (int floor = 0; floor < rooms.length; floor++) {
            for (int roomIndex = 0; roomIndex < rooms[floor].length; roomIndex++) {
                int roomNumber = (floor + 1) * 100 + (roomIndex + 1);
                String roomType;
                double pricePerDay;

                if (roomIndex == 0) {
                    roomType = "Single";
                    pricePerDay = 1500;
                } else if (roomIndex == 1) {
                    roomType = "Double";
                    pricePerDay = 2500;
                } else {
                    roomType = "Deluxe";
                    pricePerDay = 4000;
                }

                rooms[floor][roomIndex] = new Room(roomNumber, roomType, pricePerDay, true);
            }
        }
    }

    void bookRoom(int floor, int roomIndex) {
        if (!isValidRoom(floor, roomIndex)) {
            System.out.println("Invalid room selection.");
            return;
        }

        Room room = rooms[floor][roomIndex];
        if (room.isAvailable()) {
            room.bookRoom();
            System.out.println("Room " + room.getRoomNumber() + " booked successfully.");
        } else {
            System.out.println("Room " + room.getRoomNumber() + " is already booked.");
        }
    }

    void cancelBooking(int floor, int roomIndex) {
        if (!isValidRoom(floor, roomIndex)) {
            System.out.println("Invalid room selection.");
            return;
        }

        Room room = rooms[floor][roomIndex];
        room.cancelBooking();
        System.out.println("Booking cancelled for room " + room.getRoomNumber() + ".");
    }

    public double calculateBill(int days, double rate) {
        double total = days * rate;
        double tax = total * 0.05;
        double serviceFee = 200;
        return total + tax + serviceFee;
    }

    void displayRoomStatus() {
        for (int floor = 0; floor < rooms.length; floor++) {
            System.out.println("Floor " + (floor + 1) + ":");
            for (int roomIndex = 0; roomIndex < rooms[floor].length; roomIndex++) {
                rooms[floor][roomIndex].displayDetails();
            }
            System.out.println();
        }
    }

    Room getRoom(int floor, int roomIndex) {
        if (!isValidRoom(floor, roomIndex)) {
            return null;
        }
        return rooms[floor][roomIndex];
    }

    private boolean isValidRoom(int floor, int roomIndex) {
        return floor >= 0
                && floor < rooms.length
                && roomIndex >= 0
                && roomIndex < rooms[floor].length;
    }
}

public class hotelroom {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(3, 3);
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nHotel Room Booking System");
            System.out.println("1. Display room status");
            System.out.println("2. Book room");
            System.out.println("3. Cancel booking");
            System.out.println("4. Calculate bill");
            System.out.println("5. Exit");

            int choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    hotel.displayRoomStatus();
                    break;

                case 2:
                    int bookFloor = readInt(sc, "Enter floor number (1-3): ") - 1;
                    int bookRoomIndex = readInt(sc, "Enter room number on floor (1-3): ") - 1;
                    hotel.bookRoom(bookFloor, bookRoomIndex);
                    break;

                case 3:
                    int cancelFloor = readInt(sc, "Enter floor number (1-3): ") - 1;
                    int cancelRoomIndex = readInt(sc, "Enter room number on floor (1-3): ") - 1;
                    hotel.cancelBooking(cancelFloor, cancelRoomIndex);
                    break;

                case 4:
                    int billFloor = readInt(sc, "Enter floor number (1-3): ") - 1;
                    int billRoomIndex = readInt(sc, "Enter room number on floor (1-3): ") - 1;
                    int days = readInt(sc, "Enter number of days: ");
                    Room selectedRoom = hotel.getRoom(billFloor, billRoomIndex);

                    if (selectedRoom == null) {
                        System.out.println("Invalid room selection.");
                    } else if (days <= 0) {
                        System.out.println("Days must be greater than 0.");
                    } else {
                        double bill = hotel.calculateBill(days, selectedRoom.getPricePerDay());
                        System.out.println("Room Number: " + selectedRoom.getRoomNumber());
                        System.out.println("Room Type: " + selectedRoom.getRoomType());
                        System.out.println("Price Per Day: " + selectedRoom.getPricePerDay());
                        System.out.printf("Total Bill: %.2f%n", bill);
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Thank you for using the hotel booking system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    private static int readInt(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
            System.out.print(message);
        }
        return sc.nextInt();
    }
}
