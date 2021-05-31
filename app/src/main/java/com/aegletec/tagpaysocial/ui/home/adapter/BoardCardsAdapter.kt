package com.aegletec.tagpaysocial.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.data.localdb.db_model.BoardCards
import com.aegletec.tagpaysocial.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.boardcardlist.view.*


class BoardCardsAdapter(val viewModel: HomeViewModel, val boardcardList: ArrayList<BoardCards>,
                        val context: Context)
    : RecyclerView.Adapter<BoardCardsAdapter.BoardCardHolder>(){

    var onItemClick: ((BoardCards) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardCardHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.boardcardlist, parent, false)
        return BoardCardHolder(root)
    }

    override fun onBindViewHolder(holder: BoardCardHolder, position: Int) {
        val cardoption= boardcardList.get(position)

        holder.binding.card_name.text=cardoption.card_name
        holder.binding.card_thumbnail.setImageResource(cardoption.card_thumbnail)

    }


    override fun getItemCount(): Int {
        if(boardcardList.size==0){
            Toast.makeText(context, "Empty", Toast.LENGTH_LONG).show()
        }
        return boardcardList.size
    }


    inner class BoardCardHolder(val binding: View) :RecyclerView.ViewHolder(binding){

        init {
            binding.setOnClickListener {
                onItemClick?.invoke(boardcardList[adapterPosition])
            }
        }


    }

}