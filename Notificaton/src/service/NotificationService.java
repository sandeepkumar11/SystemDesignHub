package service;

import dto.request.NotificationRequest;

import java.util.List;

public interface NotificationService {
    void notify(NotificationRequest request);

    void notifyBulk(List<NotificationRequest> requests);
}
