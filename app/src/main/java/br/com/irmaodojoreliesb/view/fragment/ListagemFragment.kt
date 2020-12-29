package br.com.irmaodojoreliesb.view.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.irmaodojoreliesb.R
import br.com.irmaodojoreliesb.databinding.FragmentListagemBinding
import br.com.irmaodojoreliesb.view.adapter.PersonagemAdapter
import br.com.irmaodojoreliesb.view.viewModel.PersonagemViewModel


class ListagemFragment : Fragment() {

    private lateinit var binding: FragmentListagemBinding
    private val viewModel: PersonagemViewModel by lazy {
        ViewModelProvider(this).get(PersonagemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListagemBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visibility = View.VISIBLE

        recycleView()
    }


    private fun recycleView() {
       val recycleListView = binding.listagem
        recycleListView.layoutManager = LinearLayoutManager(context)

        viewModel.personagensListagem.observe(viewLifecycleOwner) { list ->

            binding.progressbar.visibility = View.GONE

            recycleListView.adapter = PersonagemAdapter(list) {

                var bundle = bundleOf(
                    "id" to it.id,
                    "imagemAvatar" to it.imagemUrl
                )
                findNavController().navigate(R.id.action_listagem_to_detalheFragment, bundle)
            }
        }

        viewModel.listPersonagem()
    }

}