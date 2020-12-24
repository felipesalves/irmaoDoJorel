package br.com.irmaodojoreliesb.interactor

import br.com.irmaodojoreliesb.domain.login.LoginData
import br.com.irmaodojoreliesb.domain.login.LoginResultado
import br.com.irmaodojoreliesb.repository.LoginRepository

class LoginInteractor{

    val repo: LoginRepository = LoginRepository()

    suspend fun login(data: LoginData): LoginResultado{

        val resultado = LoginResultado()

        if (data.email.isBlank()){
            resultado.error  = "ERROR_EMAIL_VAZIO"
            return resultado
        }

        if (data.senha.isBlank()){
            resultado.error  = "ERROR_SENHA_VAZIO"
            return resultado
        }

        if (data.senha.length < 6){
            resultado.error  = "ERROR_SENHA_MENOR_QUE_6"
            return resultado
        }

        return repo.login(data)
    }

    suspend fun cadastrarLogin(data: LoginData): LoginResultado{
        val resultado = LoginResultado()

        if (data.email.isBlank()){
            resultado.error  = "ERROR_EMAIL_VAZIO"
            return resultado
        }

        if (data.senha.isBlank()){
            resultado.error  = "ERROR_SENHA_VAZIO"
            return resultado
        }

        if (data.senha.length < 6){
            resultado.error  = "ERROR_SENHA_MENOR_QUE_6"
            return resultado
        }

        if (data.confirmarSenha.isBlank()){
            resultado.error  = "ERROR_CONFIRMAR_SENHA_VAZIO"
            return resultado
        }

        if (data.confirmarSenha.length < 6){
            resultado.error  = "ERROR_CONFIRMAR_SENHA_MENOR_QUE_6"
            return resultado
        }

        if(data.senha != data.confirmarSenha){
            resultado.error  = "ERROR_SENHA_NAO_CONFERE"
            return resultado
        }

        return repo.criarConta(data)
    }

    suspend fun esqueceuSenha(data: LoginData): LoginResultado{
        val resultado = LoginResultado()

        if (data.email.isBlank()){
            resultado.error  = "ERROR_EMAIL_VAZIO"
            return resultado
        }
        return repo.esqueceuSenha(data)
    }
}