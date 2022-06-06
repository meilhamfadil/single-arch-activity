package id.kudzoza.example.example.screen.movie

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.core.base.BaseFragment
import id.kudzoza.core.data.model.*
import id.kudzoza.core.util.catchToNull
import id.kudzoza.core.util.gone
import id.kudzoza.core.util.visible
import id.kudzoza.example.data.model.MovieModel
import id.kudzoza.example.example.databinding.FragmentMovieBinding

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {

    private val vm: MovieVM by activityViewModels()

    override fun registerViewModel() = vm

    override fun onResume() {
        super.onResume()
        if (isViewed)
            vm.callEvent(MovieEvent.MoviesRequest)
    }

    override fun onViewReady() = with(binding) {
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = MovieAdapter {
            vm.callEvent(MovieEvent.MovieClicked(it))
        }

        refresh.setOnRefreshListener {
            vm.callEvent(MovieEvent.MoviesRequest)
        }

        action.setOnClickListener {
            vm.callEvent(MovieEvent.MovieRefresh)
        }
    }

    override fun registerObserver() = with(vm) {

        movieList.observe(viewLifecycleOwner, ::eventMovieList)

        eventMovieClicked.observe(viewLifecycleOwner) {
            val action = MovieFragmentDirections.actionMovieFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }

    }

    private fun eventMovieList(movies: DataState<List<MovieModel>>) = with(binding) {
        movies.resource {
            loading {
                action.gone()
                refresh.isRefreshing = true
            }
            success {
                movieAdapter?.replaceAll(it.orEmpty())
            }
            error {
                vm.eventShowMessage.value = it.message
            }
            finish {
                action.visible()
                refresh.isRefreshing = false
            }
        }
    }

    private val movieAdapter: MovieAdapter?
        get() = catchToNull { binding.recycler.adapter as MovieAdapter }

}