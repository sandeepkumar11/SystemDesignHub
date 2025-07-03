package repository.impl;

import model.Room;
import repository.RoomRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RoomRepositoryImpl implements RoomRepository {

    Map<String, Room> roomMap;

    public RoomRepositoryImpl() {
        this.roomMap = new ConcurrentHashMap<>();
    }

    @Override
    public Room addRoom(Room room) {
        roomMap.put(room.getName().toUpperCase(), room);
        return room;
    }

    @Override
    public void removeRoom(String name) {
        roomMap.remove(name.toUpperCase());
    }

    @Override
    public Room updateRoom(Room room) {
        return roomMap.put(room.getName().toUpperCase(), room);
    }

    @Override
    public Room getRoomByName(String name) {
        return roomMap.getOrDefault(name.toUpperCase(),null);
    }

    @Override
    public int getRoomCount() {
        return roomMap.size();
    }

    @Override
    public List<Room> getAllRooms() {
        return List.copyOf(roomMap.values());
    }
}
