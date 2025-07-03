package com.example.retrofitgraphql.presentation.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitgraphql.data.api.RetrofitClient
import com.example.retrofitgraphql.base.GraphQLQuery
import com.example.retrofitgraphql.domain.model.LogoutGraphQlResponse
import com.example.retrofitgraphql.preference.UserSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.jvm.java

class MainViewModel : ViewModel() {

    private val _logoutSuccess = MutableLiveData<Boolean>()
    val logoutSuccess: LiveData<Boolean> = _logoutSuccess

    fun logout(context: Context) {
        val query = "mutation {\n" +
                "   logout\n" +
                "       }"

        val request = GraphQLQuery(query)

        RetrofitClient.getApi(context).logoutUser(request)
            .enqueue(object : Callback<LogoutGraphQlResponse> {
                override fun onResponse(
                    call: Call<LogoutGraphQlResponse>,
                    response: Response<LogoutGraphQlResponse>
                ) {
                    val logoutResult = response.body()?.data?.logout
                    Log.d("Logout", "Logout result = $logoutResult")

                    if (response.isSuccessful && logoutResult == true) {
                        UserSession(context).clearToken()
                        _logoutSuccess.value = true
                    } else {
                        Log.e("Logout", "Failed or null: ${response.errorBody()?.string()}")
                        _logoutSuccess.value = false
                    }
                }

                override fun onFailure(call: Call<LogoutGraphQlResponse>, t: Throwable) {
                    Log.e("Logout", "API call failed: ${t.message}")
                    _logoutSuccess.value = false
                }
            })
    }
}
