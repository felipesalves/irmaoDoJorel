package br.com.irmaodojoreliesb.repository.network.service

import br.com.irmaodojoreliesb.repository.network.dto.PersonagemDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PersonagemService {

    @GET("Personagem")
    @Headers("Content-Type: application/json")
    suspend fun ListaPersonagemAsync(): List<PersonagemDTO>

    @GET("Personagem/{id}")
    @Headers("Content-Type: application/json")
    suspend fun DetalhePersoangemAsync(@Path("id") int: Int): PersonagemDTO
}