package com.example.newsapp.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_article.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder> () {

    inner class NewsViewHolder(view: View): RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<Article>(){
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(article_image)
            article_image.clipToOutline = true
            article_title.text = article.title
            article_desc.text = article.author
            article_date.text = article.publishedAt

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }
    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


}