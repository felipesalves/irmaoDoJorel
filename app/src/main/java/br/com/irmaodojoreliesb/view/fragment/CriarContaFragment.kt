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
import br.com.irmaodojoreliesb.databinding.FragmentCriarContaBinding
import br.com.irmaodojoreliesb.domain.login.LoginData
import br.com.irmaodojoreliesb.domain.login.LoginResultado
import br.com.irmaodojoreliesb.view.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_criar_conta.*

class CriarContaFragment : Fragment() {

    private lateinit var binding: FragmentCriarContaBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCriarContaBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btCadastrarConta.setOnClickListener { cadastrarLogin() }
        btVoltarLoginRegsitro.setOnClickListener { voltarLogin() }

        viewModel.retornoLogin.observe(viewLifecycleOwner) { retorno ->
            resultadoCriarLogin(retorno)
        }
    }

    fun cadastrarLogin(){
        val email = etEmailRegister.text.toString()
        val senha = etSenhaRegister.text.toString()
        val confirmar = etConfirmarSenhaRegister.text.toString()

        val dataCadastro = LoginData(email, senha, confirmar)

        viewModel.cadastrarLogin(dataCadastro)
    }

    fun resultadoCriarLogin(res: LoginResultado) {
        if(res.error != null) {
            Toast.makeText(context, res.error, Toast.LENGTH_LONG).show()
            return
        }

        findNavController().navigate(R.id.action_criarConta_to_listagem)
    }

    fun voltarLogin(){
        findNavController().navigate(R.id.action_criarConta_to_login2)
    }

}