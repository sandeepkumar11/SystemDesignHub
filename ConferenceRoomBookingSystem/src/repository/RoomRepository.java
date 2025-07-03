package repository;

import model.Room;

import java.util.List;

public interface RoomRepository {
    Room addRoom(Room room);

    void removeRoom(String name);

    Room updateRoom(Room room);

    Room getRoomByName(String name);

    int getRoomCount();

    List<Room> getAllRooms();
}
