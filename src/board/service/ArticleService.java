package board.service;

import java.util.List;

import board.container.Container;
import board.dto.Article;

public class ArticleService {

	public List<Article> getForPrintArticles(String searchKeyword) {

		return Container.articleDao.getArticles(searchKeyword);
	}

	public int getArticleIndexById(int id) {

		return Container.articleDao.getArticleIndexById(id);
	}

}
