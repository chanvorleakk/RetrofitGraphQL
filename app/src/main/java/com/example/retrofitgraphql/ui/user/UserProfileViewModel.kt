package com.example.retrofitgraphql.ui.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitgraphql.api.RetrofitClient
import com.example.retrofitgraphql.base.GraphQLQuery
import com.example.retrofitgraphql.model.UserGraphQLResponse
import com.example.retrofitgraphql.model.UserProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileViewModel : ViewModel() {

    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile

    fun getUserProfile(context: android.content.Context) {
        val strQuery = " query {\n" +
                "              getProfile {\n" +
                "                id,\n" +
                "                fullName,\n" +
                "                phoneNumber,\n" +
                "                username\n" +
                "              }\n" +
                "            }"

        val request = GraphQLQuery(strQuery)

        RetrofitClient.getApi(context).fetchUserProfile(request)
            .enqueue(object : Callback<UserGraphQLResponse> {
                override fun onResponse(
                    call: Call<UserGraphQLResponse>,
                    response: Response<UserGraphQLResponse>
                ) {
                    if (response.isSuccessful) {
                        _userProfile.value = response.body()?.data?.getProfile
                    } else {
                        Log.e("UserProfileVM", "Error: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(
                    call: Call<UserGraphQLResponse>,
                    t: Throwable
                ) {
                    Log.e("UserProfileVM", "Failure: ${t.message}")
                }
            })
    }
}