package id.kudzoza.example.landing.screen.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.core.AppNavigator
import id.kudzoza.core.base.BaseFragment
import id.kudzoza.core.helper.NavigationHelper.navigateModule
import id.kudzoza.example.landing.databinding.FragmentSplashBinding

/**
 * Created by Kudzoza
 * on 04/09/2021
 **/

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(
    FragmentSplashBinding::inflate
) {
    private val vm: SplashVM by viewModels()

    override fun registerViewModel() = vm

    override fun onViewReady() {}

    override fun registerObserver() = with(vm) {
        moveToMain.observe(requireActivity()) {
            findNavController().popBackStack()
            findNavController().navigateModule(AppNavigator.featMovies)
        }
    }

}