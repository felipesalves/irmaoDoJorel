package br.com.irmaodojoreliesb.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.com.irmaodojoreliesb.R
import br.com.irmaodojoreliesb.databinding.FragmentLoginBinding
import br.com.irmaodojoreliesb.domain.login.LoginData
import br.com.irmaodojoreliesb.domain.login.LoginResultado
import br.com.irmaodojoreliesb.view.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btLogin.setOnClickListener { login() }
        tvCadastrar.setOnClickListener { registrarConta() }
        tvForgoteSenha.setOnClickListener { esquecerSenha() }

        //escuta se ouve alteração na variavel resultadoParaTela
        viewModel.retornoLogin.observe(viewLifecycleOwner) { resultado ->
            resultadoLogin(resultado)
        }
    }

    fun resultadoLogin(res: LoginResultado) {
        if(res.error != null) {
            Toast.makeText(context, res.error, Toast.LENGTH_LONG).show()
            return
        }

        findNavController().navigate(R.id.action_login2_to_listagem)
    }

    fun login(){

        val email = etEmail.text.toString()
        val senha = etSenha.text.toString()

        val data = LoginData(email, senha, "")

        viewModel.login(data)

    }

    fun esquecerSenha() {
        findNavController().navigate(R.id.action_login2_to_recuperarSenha)
    }

    fun registrarConta() {
        findNavController().navigate(R.id.action_login2_to_criarConta)
    }


}