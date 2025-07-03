package service;

import model.Room;

import java.time.LocalDateTime;

public interface RoomService {
    Room addRoom(String name, int capacity);

    void removeRoom(String name);

    void updateRoom(String name, int capacity);

    String getRoomDetails(String name);

    int getRoomCount();

}
