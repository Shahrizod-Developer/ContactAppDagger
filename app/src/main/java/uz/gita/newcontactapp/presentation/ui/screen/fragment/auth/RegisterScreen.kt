package uz.gita.newcontactapp.presentation.ui.screen.fragment.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newcontactapp.R
import uz.gita.newcontactapp.data.model.AuthData
import uz.gita.newcontactapp.databinding.ScreenMainBinding
import uz.gita.newcontactapp.databinding.ScreenRegisterBinding
import uz.gita.newcontactapp.presentation.ui.viewmodel.RegisterScreenViewModel
import uz.gita.newcontactapp.presentation.ui.viewmodel.impl.RegisterScreenViewModelImpl

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_register) {

    private val binding: ScreenRegisterBinding by viewBinding(ScreenRegisterBinding::bind)
    private val viewModel: RegisterScreenViewModel by viewModels<RegisterScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnLogin.setOnClickListener {
            viewModel.createAccount(
                AuthData(
                    binding.login.text.toString(),
                    binding.password.text.toString()
                )
            )
        }

        viewModel.successLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_registerScreen_to_mainScreen)
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