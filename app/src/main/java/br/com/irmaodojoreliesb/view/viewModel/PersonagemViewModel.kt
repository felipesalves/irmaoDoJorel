package br.com.irmaodojoreliesb.view.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.irmaodojoreliesb.R
import br.com.irmaodojoreliesb.domain.login.LoginData
import br.com.irmaodojoreliesb.domain.login.LoginResultado
import br.com.irmaodojoreliesb.domain.personagem.Personagem
import br.com.irmaodojoreliesb.interactor.LoginInteractor
import br.com.irmaodojoreliesb.interactor.PersonagemInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PersonagemViewModel(val app: Application) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val interactor = PersonagemInteractor()

    val personagensListagem = MutableLiveData<List<Personagem>>()
    val detalhePersonagem = MutableLiveData<Personagem>()

    fun listPersonagem() {

        launch {
            try {
                val listaPersonagem = interactor.listaPersonagem()
                personagensListagem.value = listaPersonagem
            }catch (t: Throwable){
                Log.d("Irmao_do_jorel", "Error on Coroutine listPersonagem: ${t.localizedMessage}")
            }
        }
    }


    fun detalhePersoangem(id: Int) {

        launch {
            try {
                val personagemDetalhe = interactor.detalhePersonagem(id)
                personagemDetalhe.idade = if(personagemDetalhe.idade == null) app.getString(R.string.idade_vazia) else personagemDetalhe.idade
                detalhePersonagem.value = personagemDetalhe

            }catch (t: Throwable){
                Log.d("Irmao_do_jorel", "Error on Coroutine detalhePersonagem: ${t.localizedMessage}")
            }
        }
    }


}