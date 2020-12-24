package br.com.irmaodojoreliesb.repository

import br.com.irmaodojoreliesb.domain.personagem.Personagem
import br.com.irmaodojoreliesb.repository.network.connector.Conector
import br.com.irmaodojoreliesb.repository.network.service.PersonagemService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonagemRepository{

    private val conector: Conector = Conector()
    private val criarConexao = conector.criarConexao()

    suspend fun listaPersonagem(): List<Personagem>{
        val service = criarConexao.create(PersonagemService::class.java)
        return withContext(Dispatchers.IO) {
            val resultado = service.ListaPersonagemAsync()

            val resposta = resultado.map {
                Personagem(
                    id = it.id,
                    nome = it.nome,
                    imagemUrl = it.imagemUrl,
                    categoria = it.categoria,
                    idade = it.idade,
                    descricao = it.descricao,
                    frase = it.frase,
                    biografia = it.biografia,
                    aparencia = it.aparencia,
                    personalidade = it.personalidade,
                    vozes = it.vozes,
                    episodioPersonagems = it.episodioPersonagems
                )
            }
            resposta
        }
    }


    suspend fun detalhePersonagem(id: Int): Personagem {
        val service = criarConexao.create(PersonagemService::class.java)

        return withContext(Dispatchers.IO) {
            val resultado = service.DetalhePersoangemAsync(id)

            val resposta =
                Personagem(
                    id = resultado.id,
                    nome = resultado.nome,
                    imagemUrl = resultado.imagemUrl,
                    categoria = resultado.categoria,
                    idade = resultado.idade,
                    descricao = resultado.descricao,
                    frase = resultado.frase,
                    biografia = resultado.biografia,
                    aparencia = resultado.aparencia,
                    personalidade = resultado.personalidade,
                    vozes = resultado.vozes,
                    episodioPersonagems = resultado.episodioPersonagems
                )

            resposta
        }
    }


}