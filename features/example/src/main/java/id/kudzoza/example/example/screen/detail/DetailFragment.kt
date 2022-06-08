package id.kudzoza.example.example.screen.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.core.base.BaseFragment
import id.kudzoza.core.data.model.*
import id.kudzoza.core.helper.NavigationHelper.openConstructionPage
import id.kudzoza.core.helper.NavigationHelper.openNotFoundPage
import id.kudzoza.core.util.hideProgress
import id.kudzoza.core.util.showProgress
import id.kudzoza.example.data.model.MovieModel
import id.kudzoza.example.example.R
import id.kudzoza.example.example.databinding.FragmentDetailBinding

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {

    private val vm: DetailVM by viewModels()

    override fun registerViewModel() = vm

    override fun onViewReady() = with(binding) {
        findNavController().openConstructionPage(
            navOptions {
                popUpTo(R.id.movieFragment)
            }
        )

        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun registerObserver() = with(vm) {
        movie.observe(viewLifecycleOwner, ::eventMovie)
    }

    private fun eventMovie(movie: DataState<MovieModel>) = with(binding) {
        movie.state {
            loading { requireActivity().showProgress() }
            success { it?.let { m -> renderMovie(m) } }
            error {
                findNavController().openNotFoundPage(
                    "Can\'t find what the movie you\'re looking for",
                    "Movie ID = 0",
                    navOptions {
                        popUpTo(R.id.movieFragment)
                    }
                )
            }
            finish { requireActivity().hideProgress() }
        }
    }

    private fun renderMovie(movie: MovieModel) = with(binding) {
        toolbar.title = movie.title
        synopsis.text = movie.synopsis
        Picasso.get()
            .load(movie.poster)
            .into(poster)
    }

}