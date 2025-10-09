package service;

import dto.request.GroupRequest;
import dto.response.GroupResponse;

import java.util.List;

public interface GroupService {

    GroupResponse getGroupById(String groupId);

    GroupResponse createGroup(GroupRequest request);

    void addMemberToGroup(String groupId, String memberId);

    void removeMemberFromGroup(String groupId, String memberId);

    List<String> listGroupMembers(String groupId);
}
