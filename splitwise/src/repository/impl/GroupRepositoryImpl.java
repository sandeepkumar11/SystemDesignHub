package repository.impl;

import entity.Group;
import repository.GroupRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class GroupRepositoryImpl implements GroupRepository {
    private final Map<String, Group> groupMap;

    public GroupRepositoryImpl() {
        this.groupMap = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Group group) {
        groupMap.put(group.getId(), group);
    }

    @Override
    public Optional<Group> findById(String id) {
        return Optional.ofNullable(groupMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return groupMap.containsKey(id);
    }

    @Override
    public void delete(String id) {
        groupMap.remove(id);
    }
}
