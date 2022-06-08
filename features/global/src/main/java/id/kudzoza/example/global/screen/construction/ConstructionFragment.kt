package id.kudzoza.example.global.screen.construction

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id.kudzoza.core.base.BaseFragment
import id.kudzoza.example.global.databinding.FragmentConstructionBinding
import id.kudzoza.example.global.screen.GlobalEvent
import id.kudzoza.example.global.screen.GlobalVM

class ConstructionFragment : BaseFragment<FragmentConstructionBinding>(
    FragmentConstructionBinding::inflate
) {

    private val vm: GlobalVM by viewModels()

    override fun registerViewModel() = vm

    override fun onViewReady() = with(binding) {
        actionBack.setOnClickListener {
            vm.callEvent(GlobalEvent.BackPressed)
        }
    }

    override fun registerObserver() = with(vm) {
        eventBack.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

}