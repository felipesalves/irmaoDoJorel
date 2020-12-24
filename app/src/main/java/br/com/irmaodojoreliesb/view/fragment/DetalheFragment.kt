package br.com.irmaodojoreliesb.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.irmaodojoreliesb.R
import br.com.irmaodojoreliesb.databinding.FragmentDetalheBinding
import br.com.irmaodojoreliesb.databinding.FragmentListagemBinding
import br.com.irmaodojoreliesb.view.adapter.PersonagemAdapter
import br.com.irmaodojoreliesb.view.viewModel.PersonagemViewModel
import com.squareup.picasso.Picasso


class DetalheFragment : Fragment() {

    private lateinit var binding: FragmentDetalheBinding
    private val viewModel: PersonagemViewModel by lazy {
        ViewModelProvider(this).get(PersonagemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetalheBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perosnagemDetalhe()
    }

    private fun perosnagemDetalhe(){
        val idCharacter = arguments?.getInt("id")
        val urlImagem = arguments?.getString("imagemAvatar")

        Picasso.get().load(urlImagem).into(binding.imgDetPersonagem)

        idCharacter?.let { viewModel.detalhePersoangem(it) }
    }


}