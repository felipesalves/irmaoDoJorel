package br.com.irmaodojoreliesb.interactor

import br.com.irmaodojoreliesb.domain.login.LoginData
import br.com.irmaodojoreliesb.domain.login.LoginResultado
import br.com.irmaodojoreliesb.domain.personagem.Personagem
import br.com.irmaodojoreliesb.repository.LoginRepository
import br.com.irmaodojoreliesb.repository.PersonagemRepository

class PersonagemInteractor{

    private val repo = PersonagemRepository()

    suspend fun listaPersonagem(): List<Personagem>{
        return repo.listaPersonagem()
    }

    suspend fun detalhePersonagem(id: Int): Personagem{
        return repo.detalhePersonagem(id)
    }

}