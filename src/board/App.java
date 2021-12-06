package board;

import java.util.Scanner;

import board.controller.ArticleController;
import board.controller.Controller;
import board.controller.ExportController;
import board.controller.MemberController;

public class App {

	public void start() {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		ExportController exportController = new ExportController(sc);

		articleController.makeTestData();
		memberController.makeTestData();
		exportController.makeTestData();

		while (true) {
			System.out.print("명령어 : ");
			String command = sc.nextLine();

			command.trim();

			if (command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}

			if (command.length() == 0) {
				continue;
			}

			String[] commandBits = command.split(" ");

			if (commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			// article detail
			String controllerName = commandBits[0]; // article
			String actionMethodName = commandBits[1]; // detail

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("export")) {
				controller = exportController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}

			String actionName = controllerName + "/" + actionMethodName;

			switch (actionName) {
			case "article/write":
			case "article/delete":
			case "article/modify":
			case "member/logout":
				if (Controller.isLogined() == false) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}
				break;
			}

			switch (actionName) {
			case "member/login":
			case "member/join":
				if (Controller.isLogined()) {
					System.out.println("로그아웃 후 이용해주세요");
					continue;
				}
				break;
			}

			controller.doAction(command, actionMethodName);

		}
		sc.close();

		System.out.println("==프로그램 끝==");
	}

}
