package board.dto;

public class Article extends Dto {

	public String title; // 게시물 번호
	public String body; // 게시물 번호
	public int hit; // 게시물 조회수

	public Article(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
	}

	public Article(int id, String regDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void increaseHit() {
		hit++;
	}

}
