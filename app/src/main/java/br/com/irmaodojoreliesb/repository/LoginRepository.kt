package br.com.irmaodojoreliesb.repository

import br.com.irmaodojoreliesb.domain.login.LoginData
import br.com.irmaodojoreliesb.domain.login.LoginResultado
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepository{

    suspend fun login(data: LoginData): LoginResultado = suspendCoroutine { retorno ->

        val auth = FirebaseAuth.getInstance()
        val operacao = auth.signInWithEmailAndPassword(data.email, data.senha)

        operacao.addOnCompleteListener{ retornoLogin ->
            val respostaLogin = LoginResultado()
            if(retornoLogin.isSuccessful){
                respostaLogin.resultado = "LOGIN_FIREBASE_SUCESSO"
            } else {
                respostaLogin.error = "LOGIN_FIREBASE_ERROR"
            }
            retorno.resume(respostaLogin)
        }
    }

    suspend fun criarConta(dataCriarConta: LoginData): LoginResultado = suspendCoroutine { retorno ->
        val auth = FirebaseAuth.getInstance()
        val operacao = auth.createUserWithEmailAndPassword(dataCriarConta.email, dataCriarConta.senha)

        operacao.addOnCompleteListener{ retornoLogin ->
            val respostaLogin = LoginResultado()
            if(retornoLogin.isSuccessful){
                respostaLogin.resultado = "LOGIN_CONTA_FIREBASE_SUCESSO"
            } else {
                respostaLogin.error = "LOGIN_FIREBASE_ERROR"
            }
            retorno.resume(respostaLogin)
        }
    }

    suspend fun esqueceuSenha(dataValidarEmail: LoginData):  LoginResultado = suspendCoroutine { retorno ->
        val auth = FirebaseAuth.getInstance()
        val operacao = auth.sendPasswordResetEmail(dataValidarEmail.email)

        operacao.addOnCompleteListener{ retornoLogin ->
            val respostaLogin = LoginResultado()
            if(retornoLogin.isSuccessful){
                respostaLogin.resultado = "LOGIN_CONTA_FIREBASE_SUCESSO"
            } else {
                respostaLogin.error = "EMAIL_NAO_CONFERE"
            }
            retorno.resume(respostaLogin)
        }

    }



}