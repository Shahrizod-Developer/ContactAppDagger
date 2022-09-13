package uz.gita.newcontactapp.presentation.ui.screen.fragment.main

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newcontactapp.R
import uz.gita.newcontactapp.databinding.ScreenMainBinding
import uz.gita.newcontactapp.databinding.ScreenUpdateBinding

@AndroidEntryPoint
class UpdateScreen : Fragment(R.layout.screen_update) {

    private val binding: ScreenUpdateBinding by viewBinding(ScreenUpdateBinding::bind)

}