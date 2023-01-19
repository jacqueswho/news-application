package com.news.app.feature.news.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.news.app.core.api.NewsApi
import com.news.app.feature.news.model.Article
import java.io.IOException
import retrofit2.HttpException

private const val STARTING_PAGE_NO = 1

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val country: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: STARTING_PAGE_NO
        return try {
            val newsResponse =
                newsApi.topNewsForCountry(country = country, position, params.loadSize)
            var articles: List<Article> = emptyList()
            if (newsResponse.isSuccessful) {
                newsResponse.body()?.let { resultResponse ->
                    articles = resultResponse.articles
                }
            }

            LoadResult.Page(
                data = articles,
                prevKey = if (position == STARTING_PAGE_NO) null else position - 1,
                if (articles.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }
}