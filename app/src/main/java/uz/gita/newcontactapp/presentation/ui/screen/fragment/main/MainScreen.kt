package uz.gita.newcontactapp.presentation.ui.screen.fragment.main

import android.annotation.SuppressLint
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
import uz.gita.newcontactapp.databinding.ScreenMainBinding
import uz.gita.newcontactapp.presentation.adapter.ContactAdapter
import uz.gita.newcontactapp.presentation.ui.viewmodel.MainScreenViewModel
import uz.gita.newcontactapp.presentation.ui.viewmodel.impl.MainScreenViewModelImpl
import java.util.*

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val binding: ScreenMainBinding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    private val adapter: ContactAdapter by lazy { ContactAdapter() }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logout.setOnClickListener {
            viewModel.logout()
        }
        viewModel.getAllContact()

        viewModel.onClickDeleteAccountLiveData.observe(this) {
            findNavController().navigate(R.id.action_mainScreen_to_loginScreen)
        }
        viewModel.onClickLogoutLiveData.observe(this) {
            findNavController().navigate(R.id.action_mainScreen_to_loginScreen)
        }
        viewModel.messageLiveData.observe(this) {
            Toast.makeText(
                requireContext(),
                it.getMessageString(requireContext()),
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.refresh.setOnClickListener {

            viewModel.sync()
        }

        viewModel.listLiveData.observe(this) {
            if (it != null) {
                binding.text.visibility = View.GONE
            } else {
                binding.text.visibility = View.VISIBLE
            }
            adapter.submitList(it)
        }
        binding.rv.adapter = adapter

        adapter.submitEdit {
            val bundle = Bundle()
            bundle.putSerializable("contact", it)
            findNavController().navigate(R.id.action_mainScreen_to_updateScreen, bundle)
        }

        adapter.submitDelete {
            Toast.makeText(requireContext(), it.id.toString(), Toast.LENGTH_SHORT).show()
            viewModel.delete(it)
        }
        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_addScreen)
        }
    }
}