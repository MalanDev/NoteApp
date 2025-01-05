package lk.malanadev.noteapp.domain.repository

interface NotificationRepository {
   suspend fun getToken(result:(String)-> Unit)

   suspend fun subscribeTopic(topic:String)
}