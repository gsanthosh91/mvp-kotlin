package com.gsanthosh91.mvpkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gsanthosh91.mvpkotlin.R
import com.gsanthosh91.mvpkotlin.data.TodoItem

class TodoAdapter(private val list: List<TodoItem>, private val clickListener: ClickListener) :
    RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_todo, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener.click(item)
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var title: TextView? = null

        init {
            title = itemView.findViewById(R.id.title)
        }

        fun bind(item: TodoItem) {
            title?.text = item.title
        }

    }

    override fun getItemCount(): Int = list.size

    interface ClickListener {
        fun click(item: TodoItem)
    }
}