package id.kudzoza.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.example.databinding.SplashActivityBinding

/**
 * Created by Kudzoza
 * on 06/06/2022
 **/

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val binding by lazy { SplashActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}