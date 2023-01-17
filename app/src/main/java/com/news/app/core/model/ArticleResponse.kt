import com.news.app.core.model.Article

data class ArticleResponse(
    val status: String,
    val
    totalResults: Int,
    val
    articles: List<Article>
)
