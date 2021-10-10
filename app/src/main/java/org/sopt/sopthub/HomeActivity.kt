package org.sopt.sopthub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sopthub.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}