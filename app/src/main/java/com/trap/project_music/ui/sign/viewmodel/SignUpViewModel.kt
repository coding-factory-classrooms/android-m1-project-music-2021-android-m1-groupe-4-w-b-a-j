package com.trap.project_music.ui.sign.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.model.Account
import com.trap.project_music.model.GenderType
import com.trap.project_music.model.NewAccount
import com.trap.project_music.server.service.APIAccount
import com.trap.project_music.ui.sign.viewmodel.service.ISignUpViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed class SignUpViewModelFirstState{
    object Success : SignUpViewModelFirstState()
    data class Error(val nameIsValid: Boolean, val emailIsValid: Boolean): SignUpViewModelFirstState()
    object Initial : SignUpViewModelFirstState()
}
sealed class SignUpViewModelSecondState{
    object Success : SignUpViewModelSecondState()
    data class Error(val isBirthdateValid: Boolean, val isGendervalid: Boolean): SignUpViewModelSecondState()
    object Initial : SignUpViewModelSecondState()
}
sealed class SignUpViewModelThirdState{
    object Success : SignUpViewModelThirdState()
    data class Error(val isPasswordValid: Boolean, val isConfirmValid: Boolean): SignUpViewModelThirdState()
    object Initial : SignUpViewModelThirdState()
}

class SignUpViewModel(private val apiAccount: APIAccount, application: Application) : AndroidViewModel(
    application
),ISignUpViewModel {

    private val firstState = MutableLiveData<SignUpViewModelFirstState>()
    fun getFirstState(): LiveData<SignUpViewModelFirstState> = firstState

    private val secondState = MutableLiveData<SignUpViewModelSecondState>()
    fun getSecondState(): LiveData<SignUpViewModelSecondState> = secondState

    private val thirdState = MutableLiveData<SignUpViewModelThirdState>()
    fun getThirdState(): LiveData<SignUpViewModelThirdState> = thirdState

    private val accountCreated = MutableLiveData<Boolean>()
    fun isAccountCreated(): LiveData<Boolean> = accountCreated

    init {
        firstState.value = SignUpViewModelFirstState.Initial
        secondState.value = SignUpViewModelSecondState.Initial
        thirdState.value = SignUpViewModelThirdState.Initial
    }

    private var name: String= ""
    private var email: String = ""
    private var gender: GenderType? = null

    @RequiresApi(Build.VERSION_CODES.O)
    private var birthdate: LocalDate = LocalDate.now()
    private var isNameValid : Boolean = true
    private var isEmailValid : Boolean = true

    private var nameRequest: Call<Boolean>? = null
    private var emailRequest: Call<Boolean>? = null


     var password: String? = null
         set(value) {
             field = value
             checkThirdState()
         }

    var confirmPassword: String? = null
        set(value) {
            field = value
            checkThirdState()
        }



    fun isNameUse(){
        if(name.isEmpty()){
            isNameValid = false
            return
        }
        nameRequest?.cancel()
        nameRequest = apiAccount.isNameAlreadyUse(name)
        nameRequest!!.enqueue(object : Callback<Boolean>{
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.v("test","error nameIsUse : $t")
            }
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                isNameValid = response.code() == 404
                checkFirstState()
            }
        })
    }
    fun isEmailUse(){
        if(email.isEmpty()){
            isEmailValid = false
            return
        }

        val test = apiAccount.isEmailAlreadyUse(email)
        test.enqueue(object : Callback<Boolean>{
            override fun onFailure(call: Call<Boolean>, t: Throwable) {

            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                isEmailValid = response.code() == 404
                checkFirstState()
            }

        })

    }

    fun changeName(name: String){
        this.name = name
        isNameUse()
    }
    fun changeEmail(email: String){
        this.email = email
        isEmailUse()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeGender(gender: GenderType){
        this.gender = gender
        checkSecondState()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setBirthdate(birthdate: LocalDate){
        this.birthdate = birthdate
        checkSecondState()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun createAccount() {

        apiAccount.signup(NewAccount.Request(name,password,email,gender, birthdate?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))).enqueue(object : Callback<NewAccount.Response>{
            override fun onFailure(call: Call<NewAccount.Response>, t: Throwable) {
                Log.v("test","error : ${t}")
            }

            override fun onResponse(call: Call<NewAccount.Response>, response: Response<NewAccount.Response>) {
                Log.v("test","response : ${response.body()} code : ${response.code()}")
                if(response.code() == 201){
                    getApplication<Application>().getSharedPreferences("ACCOUNT", Context.MODE_PRIVATE).edit().putString("authToken",
                        response.body()?.jwtResponse?.token
                    ).apply()
                    Log.v("test","create")
                    accountCreated.postValue(true)
                }else{
                    accountCreated.postValue(false)
                }
            }

        })
    }

    override fun checkFirstState() {
        if(isNameValid && isEmailValid){
            firstState.value = SignUpViewModelFirstState.Success
        }else{
            firstState.value = SignUpViewModelFirstState.Error(isNameValid,isEmailValid)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun checkSecondState() {
        val birthDateValid = birthdate.isBefore(LocalDate.now().minusYears(13))
        if(birthDateValid){
            secondState.value = SignUpViewModelSecondState.Success
        }else{
            secondState.value = SignUpViewModelSecondState.Error(birthDateValid,true)
        }

    }

    override fun checkThirdState() {
        val passwordValid = password?.length!! >= 6
        val confirmValid = password.equals(confirmPassword)
        if(passwordValid && confirmValid){
            thirdState.value = SignUpViewModelThirdState.Success
        }else{
            thirdState.value = SignUpViewModelThirdState.Error(passwordValid,confirmValid)
        }
    }

}
