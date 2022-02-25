package id.kudzoza.core.base

import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import id.kudzoza.core.AppNavigator
import id.kudzoza.core.helper.EventHelper
import id.kudzoza.core.util.hideProgress
import id.kudzoza.core.util.showProgress
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d("BACK-STACK", findNavController().backQueue.size.toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpHelperEvent()
        registerObserver()

        arguments?.let { onReceivedData(it) }
        onViewReady()
    }

    open fun onViewReady() {}

    open fun registerObserver() {}

    open fun onReceivedData(bundle: Bundle) {}

    fun navigateTo(target: Int, bundle: Bundle = bundleOf()) {
        findNavController().navigate(target, bundle)
    }

    fun navigateTo(uri: String) {
        try {
            val target = NavDeepLinkRequest.Builder
                .fromUri(uri.toUri())
                .build()
            findNavController().navigate(target)
        } catch (e: Exception) {
            EventHelper.message.value = e.message
        }
    }

    private fun setUpHelperEvent() {
        EventHelper.apply {
            busy.observe(this@BaseFragment, Observer {
                if (it) requireActivity().showProgress()
                else requireActivity().hideProgress()
            })

            message.observe(this@BaseFragment, Observer {
                binding.root.showSnackBar(it)
            })

            messageResource.observe(this@BaseFragment, Observer {
                binding.root.showSnackBar(it)
            })

            finish.observe(this@BaseFragment, Observer {
                requireActivity().finish()
            })
        }
    }

    fun requireApplication() = requireContext()

}