package com.strelkovax.catsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.strelkovax.catsapp.R
import com.strelkovax.catsapp.domain.entity.CatItem

class CatListAdapter: RecyclerView.Adapter<CatListAdapter.CatListViewHolder>() {

    var catList = listOf<CatItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return CatListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatListViewHolder, position: Int) {
        val cat = catList[position]
        Picasso.get().load(cat.url).placeholder(R.drawable.img_placeholder).resize(225, 225).into(holder.imgCat)
    }

    override fun getItemCount() = catList.size

    inner class CatListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgCat: ImageView = itemView.findViewById(R.id.img_cat)
    }
}