package service.impl;

import dto.request.GroupRequest;
import dto.response.GroupResponse;
import entity.Group;
import exception.ItemExists;
import exception.ItemNotFound;
import helper.GroupValidation;
import helper.UserValidation;
import repository.GroupRepository;
import service.GroupService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final UserValidation userValidation;
    private final GroupValidation groupValidation;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
        this.userValidation = new UserValidation();
        this.groupValidation = new GroupValidation();
    }

    @Override
    public GroupResponse getGroupById(String groupId) {
        Group group = getGroupByIdInternal(groupId);
        String members = String.join(", ", group.getMembers());
        return new GroupResponse(group.getId(), group.getName(), group.getDescription(), members);
    }

    @Override
    public void createGroup(GroupRequest request) {
        String groupId = generateGroupId();
        if (groupRepository.existsById(groupId)) {
            throw new ItemExists("Generated group ID already exists. Try again.");
        }
        // Validate members
        if (!userValidation.usersExist(request.getMembers())) {
            throw new ItemNotFound("One or more members do not exist in the system.");
        }
        Set<String> members = new HashSet<>(request.getMembers());
        Group group = new Group(groupId, request.getName(), request.getDescription(), members);
        groupRepository.save(group);
    }

    @Override
    public void addMemberToGroup(String groupId, String memberId) {
        Group group = getGroupByIdInternal(groupId);
        if (groupValidation.isUserInGroup(groupId, memberId)) {
            System.out.println("Member with ID " + memberId + " is already in the group.");
            return;
        }
        if (!userValidation.userExists(memberId)) {
            throw new ItemNotFound("User with ID " + memberId + " does not exist.");
        }
        Set<String> updatedMembers = new HashSet<>(group.getMembers());
        updatedMembers.add(memberId);
        Group updatedGroup = new Group(group.getId(), group.getName(), group.getDescription(), updatedMembers);
        groupRepository.save(updatedGroup);
    }

    @Override
    public void removeMemberFromGroup(String groupId, String memberId) {
        Group group = getGroupByIdInternal(groupId);
        if (!groupValidation.isUserInGroup(groupId, memberId)) {
            System.out.println("Member with ID " + memberId + " is not in the group.");
            return;
        }
        Set<String> updatedMembers = new HashSet<>(group.getMembers());
        updatedMembers.remove(memberId);
        Group updatedGroup = new Group(group.getId(), group.getName(), group.getDescription(), updatedMembers);
        groupRepository.save(updatedGroup);
    }

    @Override
    public List<String> listGroupMembers(String groupId) {
        return List.copyOf(getGroupByIdInternal(groupId).getMembers());
    }

    private String generateGroupId() {
        return "group-" + UUID.randomUUID().toString();
    }

    private Group getGroupByIdInternal(String groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new ItemNotFound("Group with ID " + groupId + " does not exist."));
    }
}
