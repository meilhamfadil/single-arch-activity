package id.kudzoza.example.global.screen.maintenance

import androidx.fragment.app.viewModels
import id.kudzoza.core.base.BaseFragment
import id.kudzoza.example.global.databinding.FragmentMaintenanceBinding
import id.kudzoza.example.global.screen.GlobalVM

/**
 * Created by Kudzoza
 * on 06/06/2022
 **/

class MaintenanceFragment : BaseFragment<FragmentMaintenanceBinding>(
    FragmentMaintenanceBinding::inflate
) {

    private val vm: GlobalVM by viewModels()

    override fun registerViewModel() = vm

    override fun registerObserver() {

    }

    override fun onViewReady() {

    }

}