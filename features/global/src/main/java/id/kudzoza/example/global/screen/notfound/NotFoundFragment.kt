package id.kudzoza.example.global.screen.notfound

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.core.base.BaseFragment
import id.kudzoza.core.util.areVisible
import id.kudzoza.example.global.databinding.FragmentNotfoundBinding
import id.kudzoza.example.global.screen.GlobalEvent
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
        actionBack.setOnClickListener {
            vm.callEvent(GlobalEvent.BackPressed)
        }

        val argMessage = arguments?.getString("message") ?: "Page not found"
        val argKeyword = arguments?.getString("keyword") ?: ""
        renderNotFound(argMessage, argKeyword)
    }

    private fun renderNotFound(argMessage: String, argKeyword: String) = with(binding) {
        message.text = argMessage
        keyword.text = argKeyword
        keyword.visibility = (argKeyword.isEmpty() || argKeyword.isBlank()).areVisible()
    }

    override fun registerObserver() = with(vm) {
        eventBack.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

}
