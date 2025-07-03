package service.impl;

import enums.BookingStatus;
import exception.BookingConflictException;
import exception.BookingNotFoundException;
import exception.InvalidNameException;
import exception.RoomNotFoundException;
import model.Booking;
import model.BookingRequest;
import model.BookingResponse;
import model.Room;
import repository.BookingRepository;
import repository.RoomRepository;
import service.BookingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final Map<String, ReentrantLock> roomLocks = new ConcurrentHashMap<>();

    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void addBooking(String bookingId, String roomName, LocalDateTime startTime, LocalDateTime endTime) {
        ReentrantLock lock = getRoomLock(roomName);
        lock.lock();
        try {
            if (isRoomAvailable(roomName, startTime, endTime)) {
                bookingRepository.addBooking(new Booking(bookingId, roomName, startTime, endTime,
                        BookingStatus.CONFIRMED.toString()));
                System.out.println("Booking added successfully for room: " + roomName);
            } else {
                throw new BookingConflictException("Room is not available for the given time slot");
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = getBookingById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        Booking updatedBooking = new Booking(booking.bookingId(), booking.roomName(),
                booking.startTime(), booking.endTime(), BookingStatus.CANCELLED.toString());
        bookingRepository.updateBooking(bookingId, updatedBooking);
    }

    @Override
    public BookingResponse updateBooking(String bookingId, BookingRequest bookingRequest) {
        ReentrantLock lock = getRoomLock(bookingRequest.roomName());
        lock.lock();
        try {
            Booking existingBooking = getBookingById(bookingId)
                    .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

            if (!isRoomAvailable(existingBooking.roomName(), bookingRequest.startTime(), bookingRequest.endTime())) {
                throw new BookingConflictException("Room is not available for the updated time slot");
            }

            Booking updatedBooking = new Booking(bookingId, existingBooking.roomName(),
                    bookingRequest.startTime(), bookingRequest.endTime(), BookingStatus.CONFIRMED.toString());
            Booking booking = bookingRepository.updateBooking(bookingId, updatedBooking);
            return new BookingResponse(bookingId, booking.roomName(),
                    booking.startTime().toString(), booking.endTime().toString(),
                    booking.status());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public BookingResponse getBookingDetails(String bookingId) {
        Booking booking = getBookingById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        return new BookingResponse(bookingId, booking.roomName(),
                booking.startTime().toString(),
                booking.endTime().toString(),
                booking.status());
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.getAllBookings()
                .stream()
                .map(booking -> new BookingResponse(booking.bookingId(), booking.roomName(),
                        booking.startTime().toString(), booking.endTime().toString(), booking.status()))
                .toList();
    }

    @Override
    public boolean isRoomAvailable(String name, LocalDateTime startTime, LocalDateTime endTime) {
        ReentrantLock lock = getRoomLock(name);
        lock.lock();
        try {
            if (name == null || name.isEmpty()) {
                throw new InvalidNameException("Room name cannot be null or empty");
            }

            getRoomByName(name);

            List<BookingResponse> bookings = getAllBookingsForRoom(name);
            for (BookingResponse booking : bookings) {
                LocalDateTime bookingStart = LocalDateTime.parse(booking.startTime());
                LocalDateTime bookingEnd = LocalDateTime.parse(booking.endTime());
                if ((startTime.isBefore(bookingEnd) && endTime.isAfter(bookingStart))) {
                    return false; // Room is not available
                }
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<BookingResponse> getAllBookingsForRoom(String roomName) {
        if (roomName == null || roomName.isEmpty()) {
            throw new InvalidNameException("Room name cannot be null or empty");
        }
        Room room = getRoomByName(roomName);
        List<Booking> bookings = bookingRepository.getAllBookingsForRoom(roomName);
        return bookings.stream()
                .map(booking -> new BookingResponse(booking.bookingId(), booking.roomName(),
                        booking.startTime().toString(), booking.endTime().toString(), booking.status()))
                .toList();
    }


    @Override
    public void clearAllBookings() {
        bookingRepository.clearAllBookings();
    }

    private Room getRoomByName(String name) {
        Room room = roomRepository.getRoomByName(name);
        if (room == null) {
            throw new RoomNotFoundException("Room does not exist");
        }
        return room;
    }

    private Optional<Booking> getBookingById(String bookingId) {
        return bookingRepository.getBookingDetails(bookingId);
    }

    private ReentrantLock getRoomLock(String roomName) {
        return roomLocks.computeIfAbsent(roomName, k -> new ReentrantLock());
    }
}
