package uz.gita.newcontactapp.presentation.ui.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.newcontactapp.data.local.shp.MySharedPreference
import uz.gita.newcontactapp.data.model.AuthStateEnum
import uz.gita.newcontactapp.domain.usecase.AuthUseCase
import uz.gita.newcontactapp.presentation.ui.viewmodel.SplashScreenViewModel
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(authUseCase: AuthUseCase) :
    SplashScreenViewModel, ViewModel() {
    override val openMainScreenLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val openLoginScreenLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val sharedPreference = MySharedPreference.getInstance()

    init {
        viewModelScope.launch {
            loadingLiveData.value = true

            delay(2000)
            loadingLiveData.value = false

            if (sharedPreference.token != "") {
                openMainScreenLiveData.value = Unit
            } else {
                openLoginScreenLiveData.value = Unit
            }
        }
    }
}