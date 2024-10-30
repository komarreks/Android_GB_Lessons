package com.example.examplerest.viewmodels


import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.examplerest.domain.Name
import com.example.examplerest.domain.Picture
import com.example.examplerest.domain.User
import com.example.examplerest.retrofit.RetrofitService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random


class ViewModel: ViewModel() {

    private val _user = MutableStateFlow<User>(emptyUser())
    val user = _user.asStateFlow()

    private fun updateRandomUser(c: Context, v: ImageView){
        viewModelScope.launch {
            try{
                _user.value = RetrofitService.randomUserApi.getRandomUsers().users.first()
                Glide.with(c).load(user.value.picture.medium).into(v)
            }catch (e: Exception){
                _user.value = emptyUser()
            }
        }
    }

    fun emptyUser(): User{
        return User("",
            Name("", "",""),
            "",
            "",
            Picture("", "", ""))
    }

    fun update(c: Context, v:ImageView){
        updateRandomUser(c, v)
    }

}