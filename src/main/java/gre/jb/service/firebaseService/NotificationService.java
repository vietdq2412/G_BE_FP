package gre.jb.service.firebaseService;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(String title, String body, String topic) throws Exception {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setTopic(topic)
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        System.out.println(response);
    }
}

