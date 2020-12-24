package br.com.irmaodojoreliesb.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.com.irmaodojoreliesb.R
import br.com.irmaodojoreliesb.databinding.FragmentLoginBinding
import br.com.irmaodojoreliesb.databinding.FragmentRecuperarSenhaBinding
import br.com.irmaodojoreliesb.domain.login.LoginData
import br.com.irmaodojoreliesb.domain.login.LoginResultado
import br.com.irmaodojoreliesb.view.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_recuperar_senha.*


class RecuperarSenhaFragment : Fragment() {

    private lateinit var binding: FragmentRecuperarSenhaBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecuperarSenhaBinding.inflate(inflater, container, false)
        //binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btEsqueceuSenha.setOnClickListener { esqueceuLogin() }
        btVoltarLogin.setOnClickListener{ voltarLogin() }

        //escuta se ouve alteração na variavel resultadoParaTela
        viewModel.retornoLogin.observe(viewLifecycleOwner) { resultado ->
            resulesqueceuSenha(resultado)
        }
    }

    fun esqueceuLogin(){

        val email = etEmailEsqueceuSenha.text.toString()
        val data = LoginData(email, "", "")

        viewModel.esqueceuSenha(data)
    }

    fun resulesqueceuSenha(res: LoginResultado) {
        if(res.error != null) {
            Toast.makeText(context, res.error, Toast.LENGTH_LONG).show()
            return
        }

        Toast.makeText(context, res.resultado, Toast.LENGTH_LONG).show()
        return
        //sucesso
        //val intentListagem = Intent(this, HomeActivity::class.java)
        //startActivity(intentHome)
        //finish()
    }

    fun voltarLogin(){
        findNavController().navigate(R.id.action_recuperarSenha_to_login2)
    }


}