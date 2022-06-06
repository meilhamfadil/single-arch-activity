package id.kudzoza.core.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import id.kudzoza.core.util.showSnackBar

/**
 * Created by Kudzoza
 * on 02/08/2021
 **/

abstract class BaseFragment<VB : ViewBinding>(
    private val viewBinding: (LayoutInflater) -> VB,
) : Fragment() {

    val binding by lazy { viewBinding.invoke(LayoutInflater.from(requireContext())) }
    val rootView by lazy { binding.root }
    var isViewed = false
    lateinit var parentVM: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d("BACK-STACK", findNavController().backQueue.size.toString())
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentVM = registerViewModel()

        setUpBaseEvent()
        registerObserver()
        arguments?.let { onReceivedData(it) }
        onViewReady()
        isViewed = true
    }

    private fun setUpBaseEvent() {
        parentVM.apply {
            eventShowMessage.observe(viewLifecycleOwner) {
                binding.root.showSnackBar(it)
            }

            eventShowMessageRes.observe(viewLifecycleOwner) {
                binding.root.showSnackBar(it)
            }
        }
    }

    /**
     * onReceivedData\
     * this method will be launch if there is any arguments on the fragment
     */
    open fun onReceivedData(bundle: Bundle) {}

    /**
     * getActivityContract
     * if there is any chance that you need something from Main Activity
     * you can use this method for get the Activity Contract Object
     */
    fun getActivityContract() = requireActivity() as ActivityContract

    /**
     * registerViewModel
     * to be overridden on the main fragment class
     * this method used to register viewModel to parentVM
     * so this base fragment can access the BaseViewModel object
     */
    abstract fun registerViewModel(): BaseViewModel

    /**
     * onViewReady
     * to be overridden on the main fragment class
     * this method will be launch after
     * - registerViewModel has been called
     * - setupBaseEvent has been called
     * - registerObserver has been called
     * - onReceivedData has been called, if there is any args
     *
     * this method will contain all the main logic for fragment
     */
    abstract fun onViewReady()

    /**
     * registerObserver
     * to be overridden on the main fragment class
     * this method will be launch after
     * - setupBaseEvent has been called
     *
     * this method will register all the main event inside fragment
     */
    abstract fun registerObserver()

}