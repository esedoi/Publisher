package com.paul.publisher

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paul.publisher.data.Articles
import com.paul.publisher.databinding.ItemHomeBinding
import java.sql.Date

class HomeAdapter: ListAdapter<Articles, RecyclerView.ViewHolder>(FriendListCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticlesHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        if(holder is ArticlesHolder) {
            holder.bind(item, position)
        }

    }


    class ArticlesHolder(private var binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SimpleDateFormat")
        fun bind(item: Articles, position:Int) {
            binding.articleTitle.text = item.title
            binding.authorName.text = item.author.name
            binding.articleContent.text = item.content
            binding.category.text = item.category
            binding.createdTime.text = item.createdTime.toString()
            val sdf = SimpleDateFormat("MM/dd HH:mm")
//            binding.createdTime.text = item.createdTime.toString()
            binding.createdTime.text = sdf.format(item.createdTime?.let { Date(it) })


        }
        companion object {
            fun from(parent: ViewGroup): ArticlesHolder {
                val friend = ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return ArticlesHolder(friend)
            }
        }
    }

}

class FriendListCallback: DiffUtil.ItemCallback<Articles>(){
    override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }


}

