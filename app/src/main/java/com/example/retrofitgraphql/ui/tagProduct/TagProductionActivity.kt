package com.example.retrofitgraphql.ui.tagProduct

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitgraphql.databinding.ActivityTagProductionBinding

class TagProductionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTagProductionBinding
private lateinit var viewModel: TagProductionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTagProductionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TagProductionViewModel::class.java]

        requestProductionTag()
        handleResponse()
    }

    private fun requestProductionTag(){
        viewModel.getProductionTag(this)
    }

    private fun handleResponse(){
        viewModel.tagProduction.observe(this) { productList ->
            val names = productList.joinToString("\n") { it.tagName }
            binding.tvProductionName.text = names
        }

    }
}