package id.kudzoza.example.global.screen.notfound

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.core.base.BaseFragment
import id.kudzoza.core.util.areVisible
import id.kudzoza.core.util.gone
import id.kudzoza.example.global.databinding.FragmentNotfoundBinding
import id.kudzoza.example.global.screen.GlobalVM

/**
 * Created by Kudzoza
 * on 06/06/2022
 **/

@AndroidEntryPoint
class NotFoundFragment : BaseFragment<FragmentNotfoundBinding>(
    FragmentNotfoundBinding::inflate
) {

    private val vm: GlobalVM by viewModels()

    override fun registerViewModel() = vm

    override fun onViewReady() = with(binding) {
        message.text = arguments?.getString("message") ?: "Page Not Found"
        arguments?.let {
            keyword.text = it.getString("keyword").orEmpty()
            keyword.visibility = it.getString("keyword").isNullOrEmpty().not().areVisible()
        } ?: keyword.gone()
    }

    override fun registerObserver() = with(vm) {
        eventBack.observe(viewLifecycleOwner) { findNavController().popBackStack() }
    }

}
