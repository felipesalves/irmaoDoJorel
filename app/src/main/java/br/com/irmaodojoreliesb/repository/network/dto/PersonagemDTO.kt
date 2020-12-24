package br.com.irmaodojoreliesb.repository.network.dto

import br.com.irmaodojoreliesb.domain.personagem.VozesPersonagem


data class PersonagemDTO(
    val id: Int,
    val nome: String,
    val imagemUrl: String,
    val categoria: String,
    val idade: String,
    val descricao: String,
    val frase: String,
    val biografia: String,
    val aparencia: String,
    val personalidade: String,
    val vozes: List<VozesPersonagem>,
    val episodioPersonagems: String
)