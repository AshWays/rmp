package com.example.emapp.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emapp.R

class BdzhilsAdapter: RecyclerView.Adapter<BdzhilsAdapter.BdzhilsViewHolder>() {

    private var bdzhilsList: ArrayList<User> = ArrayList()
    private var onClickItem: ((User) -> Unit)? = null
    private var onClickShowItem: ((User) -> Unit)? = null

    fun addItem(item: User){
        this.bdzhilsList.add(item)
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (User) -> Unit){
        this.onClickItem = callback
    }

    fun setOnClickShowItem(callback: (User) -> Unit){
        this.onClickShowItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BdzhilsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.bdzhils_item_view, parent, false)
    )

    override fun onBindViewHolder(holder: BdzhilsViewHolder, position: Int) {
        val bdzhil = bdzhilsList[position]
        holder.bindView(bdzhil)
        holder.itemView.setOnClickListener { onClickItem?.invoke(bdzhil) }
        holder.btnShow.setOnClickListener { onClickShowItem?.invoke(bdzhil) }
    }

    override fun getItemCount(): Int {
        return bdzhilsList.size
    }

    class BdzhilsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private  var userx = view.findViewById<TextView>(R.id.cardUser)
        private  var namex = view.findViewById<TextView>(R.id.cardName)
        private  var commentx = view.findViewById<TextView>(R.id.cardComment)
        var btnShow = view.findViewById<Button>(R.id.btnShow)


        fun bindView(user: User){
            userx.text = "User: " + user.user
            namex.text = user.name
            commentx.text = user.comment

        }
    }
}