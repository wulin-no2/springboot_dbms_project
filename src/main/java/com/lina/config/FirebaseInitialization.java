package com.lina.config;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitialization {
//    @Value("${firebase.bucketLocation}")
    @Autowired
    private FirebaseProperties firebaseProperties;
    @PostConstruct
    public void initialize() {

//        String bucketLocation = "springboot-crud-lina.appspot.com";
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/springboot-crud-lina-firebase-adminsdk-1r4p4-2438143b06.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(firebaseProperties.getBucketLocation())
                    .build();

            if (FirebaseApp.getApps().isEmpty()) { //<--- check with this line
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
