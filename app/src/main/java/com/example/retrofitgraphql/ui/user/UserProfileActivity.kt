package com.example.retrofitgraphql.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitgraphql.databinding.ActivityUserBinding


class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var viewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[UserProfileViewModel::class.java]

        handleResponse()
        requestGetProfile()

    }

    private fun requestGetProfile() {
        viewModel.getUserProfile(this)
    }

    private fun handleResponse() {
        viewModel.userProfile.observe(this) { profile ->
            binding.tvFullName.text = profile.fullName
            binding.tvPhone.text = profile.phoneNumber
        }
    }
}
