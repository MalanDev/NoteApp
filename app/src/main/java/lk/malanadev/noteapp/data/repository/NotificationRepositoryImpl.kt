package lk.malanadev.noteapp.data.repository

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import lk.malanadev.noteapp.domain.repository.NotificationRepository

class NotificationRepositoryImpl():NotificationRepository {
    override suspend fun getToken(result: (String) -> Unit) {
      FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
       if(task.isSuccessful){
           result(task.result)
       }else{
           Log.e("FCM","Failed to get token",task.exception)
       }
      }
    }

    override suspend fun subscribeTopic(topic: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d("FCM", "Subscribe to Topic: $topic")
                }else{
                    Log.e("FCM","Fail;ed to subscribe to topic",task.exception)
                }
            }
    }
}