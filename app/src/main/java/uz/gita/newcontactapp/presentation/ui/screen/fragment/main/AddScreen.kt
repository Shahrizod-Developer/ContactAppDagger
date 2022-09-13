package uz.gita.newcontactapp.presentation.ui.screen.fragment.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newcontactapp.R
import uz.gita.newcontactapp.data.local.entity.ContactEntity
import uz.gita.newcontactapp.databinding.ScreenAddBinding
import uz.gita.newcontactapp.presentation.ui.viewmodel.AddScreenViewModel
import uz.gita.newcontactapp.presentation.ui.viewmodel.impl.AddScreenViewModelImpl

@AndroidEntryPoint
class AddScreen : Fragment(R.layout.screen_add) {

    private val binding: ScreenAddBinding by viewBinding(ScreenAddBinding::bind)
    private val viewModel: AddScreenViewModel by viewModels<AddScreenViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.errorLiveData.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.successLiveData.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.add.setOnClickListener {
            viewModel.add(
                ContactEntity(
                    0,
                    binding.name.text.toString(),
                    binding.number.text.toString(),
                    0,
                    0,
                    0
                )
            )
        }
        viewModel.openScreenLiveData.observe(this) {
            findNavController().popBackStack()
        }
    }
}