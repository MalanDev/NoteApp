package lk.malanadev.noteapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lk.malanadev.noteapp.domain.repository.NotificationRepository
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val repository: NotificationRepository
):ViewModel() {

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> get() = _token

    fun fetchToken(){
       viewModelScope.launch {
           withContext(Dispatchers.IO) {
               repository.getToken {
                   _token.postValue(it)
               }
           }
       }
    }

    fun subscribeTopic(topic:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.subscribeTopic(topic)
            }
        }
    }
}