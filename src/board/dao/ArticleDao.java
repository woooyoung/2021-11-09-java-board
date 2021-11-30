package board.dao;

import java.util.ArrayList;
import java.util.List;

import board.dto.Article;

public class ArticleDao {
	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}
}
