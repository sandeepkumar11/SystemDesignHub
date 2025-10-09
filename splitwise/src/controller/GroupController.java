package controller;

import dto.request.GroupRequest;
import dto.response.GroupResponse;
import repository.GroupRepository;
import service.GroupService;
import service.impl.GroupServiceImpl;

public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupRepository groupRepository) {
        this.groupService = new GroupServiceImpl(groupRepository);
    }

    public GroupResponse create(GroupRequest request){
        return groupService.createGroup(request);
    }

    public String addMember(String groupId, String memberId){
        groupService.addMemberToGroup(groupId, memberId);
        return "Member with ID " + memberId + " added to group with ID " + groupId;
    }

    public void getGroup(String groupId){
        GroupResponse response = groupService.getGroupById(groupId);
        System.out.println("Group ID: " + response.getId());
        System.out.println("Group Name: " + response.getName());
        System.out.println("Group Description: " + response.getDescription());
        System.out.println("Group Members: " + response.getMembers());
    }
}
