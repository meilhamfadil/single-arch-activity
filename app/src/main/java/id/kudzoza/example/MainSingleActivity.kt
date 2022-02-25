package id.kudzoza.example

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.core.base.ActivityContract
import id.kudzoza.example.databinding.SingleActivityBinding
import android.os.Looper
import android.util.Log
import androidx.navigation.fragment.findNavController
import id.kudzoza.core.util.json
import id.kudzoza.core.util.showSnackBar


/**
 * Created by Kudzoza
 * on 08/12/2021
 **/

@AndroidEntryPoint
class MainSingleActivity : AppCompatActivity(), ActivityContract {

    private val binding by lazy { SingleActivityBinding.inflate(layoutInflater) }
    private var backExit = false

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.main_frame)
                as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        when {
            navController.backQueue.size > 3 -> super.onBackPressed()
            backExit -> finish()
            else -> {
                backExit = true
                binding.root.showSnackBar(R.string.app_double_back)
                Handler(Looper.getMainLooper()).postDelayed(
                    { backExit = false },
                    1500
                )
            }
        }
    }

}