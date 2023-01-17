package com.news.app.feature.news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.news.app.R
import com.news.app.core.model.Article
import com.news.app.databinding.NewsArticleCardItemBinding

class NewsAdapter(private val articleItemClickListener: ArticleItemClickListener) :
    PagingDataAdapter<Article, NewsAdapter.NewsArticleViewHolder>(ARTICLE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val binding = NewsArticleCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsArticleViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    inner class NewsArticleViewHolder(
        val articleBinding: NewsArticleCardItemBinding
    ) : RecyclerView.ViewHolder(articleBinding.root) {

        init {
            articleBinding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val article = getItem(position)
                    if (article != null) {
                        articleItemClickListener.clickOnItem(article, itemView)
                    }
                }
            }
        }

        fun bind(articleItem: Article) {
            articleBinding.apply {
                Glide.with(itemView)
                    .load(articleItem.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivArticleImage)
                article = articleItem
            }
        }
    }

    companion object {
        private val ARTICLE_COMPARATOR by lazy {
            object : DiffUtil.ItemCallback<Article>() {
                override fun areItemsTheSame(oldItem: Article, newItem: Article) =
                    oldItem.url == newItem.url

                override fun areContentsTheSame(oldItem: Article, newItem: Article) =
                    oldItem == newItem
            }
        }
    }

    interface ArticleItemClickListener {
        fun clickOnItem(data: Article, itemView: View)
    }
}