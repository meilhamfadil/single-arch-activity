package id.kudzoza.example.example.screen.movie

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.core.base.BaseFragment
import id.kudzoza.core.data.model.Resource
import id.kudzoza.core.helper.EventHelper
import id.kudzoza.core.util.catchToNull
import id.kudzoza.core.util.json
import id.kudzoza.example.example.R
import id.kudzoza.example.example.databinding.FragmentMovieBinding

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {

    private val vm: MovieVM by viewModels()

    private val movieAdapter: MovieAdapter?
        get() = catchToNull { binding.recycler.adapter as MovieAdapter }

    override fun onResume() {
        super.onResume()
        vm.getMovies()
    }

    override fun onViewReady() = with(binding) {
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = MovieAdapter {
            navigateTo(
                R.id.action_movieFragment_to_detailFragment,
                bundleOf(
                    "MOVIE" to json(it)
                )
            )

        }

        refresh.setOnRefreshListener { vm.getMovies() }
        action.setOnClickListener { vm.getMovies() }
    }

    override fun registerObserver() = with(vm) {

        movieList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.action.setLoading(true)
                    binding.refresh.isRefreshing = true
                }
                is Resource.Finish -> {
                    binding.action.setLoading(false)
                    binding.refresh.isRefreshing = false
                }
                is Resource.Success -> {
                    movieAdapter?.replaceAll(it.data.orEmpty())
                }
                is Resource.Error -> {
                    EventHelper.message.value = it.message
                }
            }
        }

    }

}