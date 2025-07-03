package com.example.retrofitgraphql.presentation.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitgraphql.data.api.RetrofitClient
import com.example.retrofitgraphql.base.GraphQLQuery
import com.example.retrofitgraphql.domain.model.LoginGraphQlResponse
import com.example.retrofitgraphql.domain.model.LoginRequestModel
import com.example.retrofitgraphql.domain.model.LoginResponse
import com.example.retrofitgraphql.preference.UserSession
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginSuccess = MutableLiveData<LoginResponse>()
    val loginSuccess: LiveData<LoginResponse> = _loginSuccess

    fun getLoginRequest(context: Context, requestModel: LoginRequestModel) {
        val query = "mutation {\n" +
                "  login(\n" +
                "    input: {\n" +
                "      phoneNumber: \"${requestModel.phoneNumber}\", \n" +
                "      password: \"${requestModel.password}\", \n" +
                "      firebaseToken: \"${requestModel.firebaseToken}\", \n" +
                "      userType: [PARTNER, NON_PARTNER]\n" +
                "    }\n" +
                "  ) {\n" +
                "    token,\n" +
                "    user {\n" +
                "      id,\n" +
                "      username,\n" +
                "      fullName,\n" +
                "      phoneNumber\n" +
                "    }\n" +
                "  }\n" +
                "}"

        val request = GraphQLQuery(query)
        val api = RetrofitClient.getApi(context)

        api.postData(request)
            .enqueue(object : Callback<LoginGraphQlResponse> {
                override fun onResponse(
                    call: retrofit2.Call<LoginGraphQlResponse>,
                    response: Response<LoginGraphQlResponse>
                ) {
                    if (response.isSuccessful) {
                        val loginData = response.body()?.data!!
                        if (true) {
                            _loginSuccess.value = loginData
                            Log.e("LoginViewModel", "Response body: ${response.body()}")
                            loginData.login.token.let {
                                UserSession(context).saveToken(it)
                            }
                        } else {
                            Log.e("LoginViewModel", "Login failed: data is null")
                        }
                    }
                }

                override fun onFailure(
                    call: retrofit2.Call<LoginGraphQlResponse>,
                    t: Throwable
                ) {
                    Log.e("LoginViewModel", "Login failure: ${t.message}")
                }
            })
    }
}
