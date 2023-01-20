package com.news.app.feature.news.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.news.app.R
import com.news.app.core.data.remote.news.model.Article
import com.news.app.databinding.FragmentNewsListingBinding
import com.news.app.feature.news.ui.adapters.NewsAdapter
import com.news.app.feature.news.ui.adapters.NewsArticleLoadStateAdapter
import com.news.app.feature.news.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListingFragment :
    Fragment(R.layout.fragment_news_listing),
    NewsAdapter.ArticleItemClickListener,
    MenuProvider {
    private val viewModel by viewModels<NewsViewModel>()
    private var _binding: FragmentNewsListingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_news, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_Canada -> {
                viewModel.searchNewsByCountry(NewsViewModel.Country.Canada)
                binding.rvNews.scrollToPosition(0)
                return true
            }
            R.id.action_USA -> {
                viewModel.searchNewsByCountry(NewsViewModel.Country.USA)
                binding.rvNews.scrollToPosition(0)
                return true
            }
            R.id.action_news_settings -> {
                val action =
                    NewsListingFragmentDirections.actionNewsListingFragmentToSettingsActivity()
                findNavController().navigate(action)
                return true
            }
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewsListingBinding.bind(view)
        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        val newsAdapter = NewsAdapter(this)

        binding.apply {
            rvNews.apply {
                setHasFixedSize(true)
                itemAnimator = null
                adapter = newsAdapter.withLoadStateHeaderAndFooter(
                    header = NewsArticleLoadStateAdapter { newsAdapter.retry() },
                    footer = NewsArticleLoadStateAdapter { newsAdapter.retry() }
                )
            }
            btnRetry.setOnClickListener {
                newsAdapter.retry()
            }
            viewModel.articles.observe(viewLifecycleOwner) {
                newsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }

        newsAdapter.addLoadStateListener { loadState ->
            binding.apply {
                pbNewsListing.isVisible = loadState.source.refresh is LoadState.Loading
                rvNews.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                tvCantLoad.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    newsAdapter.itemCount < 1
                ) {
                    rvNews.isVisible = false
                    tvResultsNotFound.isVisible = true
                } else {
                    tvResultsNotFound.isVisible = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickOnItem(article: Article, itemView: View) {
        val action =
            NewsListingFragmentDirections.actionNewsListingFragmentToNewsDetailFragment(
                article,
                article.title ?: "News Article"
            )
        findNavController().navigate(action)
    }

    companion object {
        const val VIEW_KEY = "news_articles_key"
    }
}
