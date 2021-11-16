package board.dto;

public class Article {

	public int id; // 게시물 번호
	public String title; // 게시물 번호
	public String body; // 게시물 번호
	public String regDate; // 게시물 등록 날짜
	public int hit; // 게시물 조회수

	public Article(int id, String title, String body, String regDate) {
		this(id, title, body, regDate, 0);
	}

	public Article(int id, String title, String body, String regDate, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
	}

	public void increaseHit() {
		hit++;
	}

}
