package br.com.irmaodojoreliesb.domain.personagem

data class Personagem(
    val id: Int,
    val nome: String? = null,
    val imagemUrl: String? = null,
    val categoria: String? = null,
    var idade: String? = null,
    val descricao: String? = null,
    val frase: String? = null,
    val biografia: String? = null,
    val aparencia: String? = null,
    val personalidade: String? = null,
    val vozes: List<VozesPersonagem>,
    val episodioPersonagems: String? = null

)