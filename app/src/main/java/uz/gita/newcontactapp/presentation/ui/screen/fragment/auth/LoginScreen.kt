package uz.gita.newcontactapp.presentation.ui.screen.fragment.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newcontactapp.R
import uz.gita.newcontactapp.data.model.AuthData
import uz.gita.newcontactapp.databinding.ScreenLoginBinding
import uz.gita.newcontactapp.databinding.ScreenMainBinding
import uz.gita.newcontactapp.presentation.ui.viewmodel.LoginScreenViewModel
import uz.gita.newcontactapp.presentation.ui.viewmodel.impl.LoginScreenViewModelImpl

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    private val binding: ScreenLoginBinding by viewBinding(ScreenLoginBinding::bind)
    private val viewModel: LoginScreenViewModel by viewModels<LoginScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnLogin.setOnClickListener {
            viewModel.login(
                AuthData(
                    binding.login.text.toString(),
                    binding.password.text.toString()
                )
            )
        }

        viewModel.successLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_loginScreen_to_mainScreen)
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginScreen_to_registerScreen)
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }
        viewModel.messageLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
