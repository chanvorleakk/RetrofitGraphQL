package com.example.retrofitgraphql.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitgraphql.databinding.ActivityMainBinding
import com.example.retrofitgraphql.presentation.ui.login.LoginActivity
import com.example.retrofitgraphql.presentation.ui.tagProduct.TagProductionActivity
import com.example.retrofitgraphql.presentation.ui.user.UserProfileActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.logoutSuccess.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Logout failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGetUser.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }

        binding.btnGetProductionName.setOnClickListener {
            val intent = Intent(this, TagProductionActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout(this)
        }
    }
}
