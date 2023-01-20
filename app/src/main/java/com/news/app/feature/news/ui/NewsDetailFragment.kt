package com.news.app.feature.news.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.news.app.R
import com.news.app.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {
    private val args by navArgs<NewsDetailFragmentArgs>()

    @Inject
    lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewsDetailBinding.bind(view)
        val article = args.article

        binding.apply {
            glide.load(article.urlToImage)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pbNewsDetail.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pbNewsDetail.isVisible = false
                        return false
                    }
                })
                .into(ivArticleImage)
            tvArticleAuthor.text = article.author
            tvArticleContent.text = article.content
            tvArticleTitle.text = article.title
            tvArticlePublishDate.text = article.publishedAt.toGMTString()
        }
    }

    companion object {
        const val VIEW_KEY = "news_article_key"
    }
}