package uz.gita.newcontactapp.presentation.ui.screen.fragment.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newcontactapp.R
import uz.gita.newcontactapp.databinding.ScreenMainBinding
import uz.gita.newcontactapp.databinding.ScreenSplashBinding
import uz.gita.newcontactapp.presentation.ui.viewmodel.SplashScreenViewModel
import uz.gita.newcontactapp.presentation.ui.viewmodel.impl.SplashScreenViewModelImpl

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {

    private val binding: ScreenSplashBinding by viewBinding(ScreenSplashBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.openLoginScreenLiveData.observe(this) {
            navController.navigate(R.id.action_splashScreen_to_loginScreen)
        }
        viewModel.openMainScreenLiveData.observe(this) {
            navController.navigate(R.id.action_splashScreen_to_mainScreen)
        }
    }

}