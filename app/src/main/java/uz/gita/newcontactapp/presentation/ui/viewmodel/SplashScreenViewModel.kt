package uz.gita.newcontactapp.presentation.ui.viewmodel

import androidx.lifecycle.LiveData


interface SplashScreenViewModel {

    val openMainScreenLiveData: LiveData<Unit>
    val openLoginScreenLiveData: LiveData<Unit>
    val loadingLiveData: LiveData<Boolean>


}