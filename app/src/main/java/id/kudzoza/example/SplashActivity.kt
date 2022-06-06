package id.kudzoza.example

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.kudzoza.example.databinding.SplashActivityBinding
import id.kudzoza.example.landing.screen.splash.SplashContract
import id.kudzoza.example.landing.screen.splash.SplashFragment

/**
 * Created by Kudzoza
 * on 06/06/2022
 **/

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity(), SplashContract {

    private val binding by lazy { SplashActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().apply {
            replace(
                binding.mainFragment.id,
                SplashFragment(),
                SplashFragment::class.java.name
            )
        }.commit()
    }

    override fun startMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

}