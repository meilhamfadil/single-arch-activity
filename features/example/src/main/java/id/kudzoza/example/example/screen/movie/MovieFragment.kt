package id.kudzoza.example.example.screen.movie

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.core.base.BaseFragment
import id.kudzoza.core.data.model.Resource
import id.kudzoza.core.util.catchToNull
import id.kudzoza.core.util.json
import id.kudzoza.example.domain.model.MovieModel
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
            MovieFragmentDirections.actionMovieFragmentToDetailFragment(json(it))
        }
    }

    private fun eventMovieList(movies: Resource<List<MovieModel>>) = with(binding) {
        when (movies) {
            is Resource.Loading -> {
                action.setLoading(true)
                refresh.isRefreshing = true
            }
            is Resource.Success -> {
                movieAdapter?.replaceAll(movies.data.orEmpty())
            }
            is Resource.Error -> {
                vm.eventShowMessage.value = movies.message
            }
            is Resource.Finish -> {
                action.setLoading(false)
                refresh.isRefreshing = false
            }
        }
    }

    private val movieAdapter: MovieAdapter?
        get() = catchToNull { binding.recycler.adapter as MovieAdapter }

}