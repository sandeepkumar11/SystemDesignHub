import model.BookingResponse;
import model.Room;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.impl.BookingRepositoryImpl;
import repository.impl.RoomRepositoryImpl;
import service.BookingService;
import service.RoomService;
import service.impl.BookingServiceImpl;
import service.impl.RoomServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

public class BookingManager {
    public void demo(){
        System.out.println("Conference Room Booking System");

        // Initialize the repositories and services
        RoomRepository roomRepository = new RoomRepositoryImpl();
        BookingRepository bookingRepository = new BookingRepositoryImpl();
        RoomService roomService = new RoomServiceImpl(roomRepository);
        BookingService bookingService = new BookingServiceImpl(bookingRepository,roomRepository);

        // Add rooms
        Room room1 = roomService.addRoom("Conference-A", 5);
        Room room2 = roomService.addRoom("Conference-B", 10);
        Room room3 = roomService.addRoom("Conference-C", 15);
        Room room4 = roomService.addRoom("Conference-D", 20);

        // Add bookings
        bookingService.addBooking("booking1", "Conference-A",
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 12, 0));
        bookingService.addBooking("booking2", "Conference-A",
                LocalDateTime.of(2025, 10, 1, 13, 0),
                LocalDateTime.of(2025, 10, 1, 15, 0));

        boolean isAvailable = bookingService.isRoomAvailable("Conference-A",
                LocalDateTime.of(2025, 10, 1, 12, 0),
                LocalDateTime.of(2025, 10, 1, 14, 0));

        System.out.println("Is Conference-A available from 12:00 to 14:00? " + isAvailable);

        bookingService.cancelBooking("booking1");

        List<BookingResponse> allBookings = bookingService.getAllBookingsForRoom("Conference-A");
        System.out.println("All bookings for Conference-A:" + allBookings);
    }
}
