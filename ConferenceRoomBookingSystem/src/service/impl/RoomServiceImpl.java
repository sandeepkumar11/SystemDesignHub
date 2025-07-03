package service.impl;

import exception.InvalidNameException;
import exception.RoomNotFoundException;
import model.Room;
import repository.RoomRepository;
import service.RoomService;

import java.time.LocalDateTime;

public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room addRoom(String name, int capacity) {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Room name cannot be null or empty");
        }
        return roomRepository.addRoom(new Room(name,capacity));
    }

    @Override
    public void removeRoom(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Room name cannot be null or empty");
        }
        roomRepository.removeRoom(name);
    }

    @Override
    public void updateRoom(String name, int capacity) {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Room name cannot be null or empty");
        }
        Room room = roomRepository.getRoomByName(name);
        if (room == null) {
            throw new RoomNotFoundException("Room does not exist");
        }
        room.setCapacity(capacity);
        roomRepository.updateRoom(room);
    }

    @Override
    public String getRoomDetails(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Room name cannot be null or empty");
        }
        Room room = roomRepository.getRoomByName(name);
        if (room == null) {
            throw new RoomNotFoundException("Room does not exist");
        }
        return room.toString();
    }

    @Override
    public int getRoomCount() {
        return roomRepository.getRoomCount();
    }

}
