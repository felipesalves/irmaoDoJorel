package br.com.irmaodojoreliesb.view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.irmaodojoreliesb.R
import br.com.irmaodojoreliesb.domain.login.LoginData
import br.com.irmaodojoreliesb.domain.login.LoginResultado
import br.com.irmaodojoreliesb.interactor.LoginInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(val app: Application) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    val interactor = LoginInteractor()

    val retornoLogin = MutableLiveData<LoginResultado>()

    fun login(data: LoginData) {

        launch {
            val resultado = interactor.login(data)
            if (resultado.error != null) {
                when (resultado.error) {
                    "ERROR_EMAIL_VAZIO" -> {
                        resultado.error = app.getString(R.string.email_obrigatorio)
                    }
                    "ERROR_SENHA_VAZIO" -> {
                        resultado.error = app.getString(R.string.senha_obrigatorio)
                    }
                    "ERROR_SENHA_MENOR_QUE_6" -> {
                        resultado.error = app.getString(R.string.senha_6_menor)
                    }
                    "LOGIN_FIREBASE_ERROR" -> {
                        resultado.error = app.getString(R.string.login_error)
                    }
                }
            } else {
                //Enviar sucesso tela
                resultado.error = null
                resultado.resultado = "login com sucesso"
            }

            retornoLogin.value = resultado
        }
    }

    fun cadastrarLogin(dataCadastro: LoginData){

        launch {
            val resultadoCriarConta = interactor.cadastrarLogin(dataCadastro)
            if (resultadoCriarConta.error != null) {
                when (resultadoCriarConta.error) {
                    "ERROR_EMAIL_VAZIO" -> {
                        resultadoCriarConta.error = app.getString(R.string.email_obrigatorio)
                    }
                    "ERROR_SENHA_VAZIO" -> {
                        resultadoCriarConta.error = app.getString(R.string.senha_obrigatorio)
                    }
                    "ERROR_SENHA_MENOR_QUE_6" -> {
                        resultadoCriarConta.error = app.getString(R.string.senha_6_menor)
                    }
                    "LOGIN_FIREBASE_ERROR" -> {
                        resultadoCriarConta.error = app.getString(R.string.login_error)
                    }
                    "ERROR_SENHA_NAO_CONFERE" -> {
                        resultadoCriarConta.error = app.getString(R.string.senha_Confirmar_nao_confere)
                    }
                    "ERROR_CONFIRMAR_SENHA_VAZIO" -> {
                        resultadoCriarConta.error = app.getString(R.string.senha_confirmar_obrigatorio)
                    }
                    "ERROR_CONFIRMAR_SENHA_MENOR_QUE_6" -> {
                        resultadoCriarConta.error = app.getString(R.string.senha_Confirmar_nao_confere)
                    }
                }
            } else {
                //Enviar sucesso tela
                resultadoCriarConta.error = null
                resultadoCriarConta.resultado = "login com sucesso"
            }
            retornoLogin.value = resultadoCriarConta
        }
    }

    fun esqueceuSenha(dataEsqueceuSenha: LoginData){
        launch {
            val resultadoEsqueceuSenha = interactor.esqueceuSenha(dataEsqueceuSenha)
            if (resultadoEsqueceuSenha.error != null) {
                when (resultadoEsqueceuSenha.error) {
                    "ERROR_EMAIL_VAZIO" -> {
                        resultadoEsqueceuSenha.error = app.getString(R.string.email_obrigatorio)
                    }
                    "EMAIL_NAO_CONFERE" -> {
                        resultadoEsqueceuSenha.error = app.getString(R.string.emial_nao_confere)
                    }
                }
            } else {
                //Enviar sucesso tela
                resultadoEsqueceuSenha.error = null
                resultadoEsqueceuSenha.resultado = app.getString(R.string.esqueceu_senha)
            }
            retornoLogin.value = resultadoEsqueceuSenha
        }
    }
}