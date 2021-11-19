package board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import board.controller.ArticleController;
import board.controller.MemberController;
import board.dto.Article;
import board.dto.Member;
import board.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;

	App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);

		articleController.makeTestData();

		while (true) {
			System.out.print("명령어 : ");
			String command = sc.nextLine();

			command.trim();

			if (command.length() == 0) {
				continue;
			}
			if (command.equals("member join")) {
				memberController.doJoin();
			} else if (command.startsWith("article list")) {
				articleController.showList(command);
			} else if (command.equals("article write")) {
				articleController.doWrite();
			} else if (command.startsWith("article detail ")) {
				articleController.showDetail(command);
			} else if (command.startsWith("article delete ")) {
				articleController.doDelte(command);
			} else if (command.startsWith("article modify ")) {
				articleController.doModify(command);
			} else if (command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.printf("%s는 존재하지 않는 명령어입니다.\n", command);
			}
		}
	}

}
