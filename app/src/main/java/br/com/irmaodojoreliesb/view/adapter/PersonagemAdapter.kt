package br.com.irmaodojoreliesb.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.irmaodojoreliesb.R
import br.com.irmaodojoreliesb.databinding.ItemPersonagemBinding
import br.com.irmaodojoreliesb.domain.personagem.Personagem
import com.squareup.picasso.Picasso

class PersonagemAdapter (
    private val listaPersonagens: List<Personagem>,
    private val itemClick: ((Personagem) -> Unit)
) : RecyclerView.Adapter<PersonagemAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_personagem, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.personagem = listaPersonagens[position]
        binding?.executePendingBindings()

        if (binding != null) {
            Picasso.get().load(binding?.personagem?.imagemUrl).into(binding.imgPersonagem)
        }
    }


    override fun getItemCount(): Int = listaPersonagens.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemPersonagemBinding? = ItemPersonagemBinding.bind(view)

        init {
            view.setOnClickListener{
                itemClick.invoke(listaPersonagens[adapterPosition])
            }
        }
    }

}