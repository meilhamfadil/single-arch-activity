package id.kudzoza.example.example.screen.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.core.base.BaseFragment
import id.kudzoza.example.domain.model.MovieModel
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

    override fun onViewReady() {

    }

    override fun registerObserver() = with(vm) {
        movie.observe(viewLifecycleOwner, ::eventMovie)
    }

    private fun eventMovie(movie: MovieModel?) = with(binding) {
        if (movie != null) {
            toolbar.title = movie.title
            synopsis.text = movie.synopsis
            Picasso.get()
                .load(movie.poster)
                .into(poster)

            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}