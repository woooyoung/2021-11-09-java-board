package board.controller;

import java.util.Scanner;

import board.container.Container;
import board.dto.Member;
import board.service.MemberService;
import board.util.Util;

public class MemberController extends Controller {

	private Scanner sc;
	private String command;
	private String actionMethodName;
	private MemberService memberService;

	public MemberController(Scanner sc) {
		this.sc = sc;
		memberService = Container.memberService;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "whoami":
			showWhoAmI();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	private void doLogout() {

		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}

	private void showWhoAmI() {

		if (loginedMember == null) {
			System.out.println("로그아웃 상태입니다.");
			return;
		}

		System.out.println("== 로그인 된 회원의 정보 ==");
		System.out.printf("로그인 아이디 : %s\n", loginedMember.loginId);
		System.out.printf("이름 : %s\n", loginedMember.name);
	}

	private void doLogin() {

		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다.");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		}

		loginedMember = member;
		System.out.printf("로그인 되었습니다. %s님 환영합니다.\n", loginedMember.name);
	}

	private void doJoin() {
		int id = memberService.getNewId();

		String loginId = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s (은)는 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}

			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();

			System.out.printf("로그인 비밀번호 확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}

			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		String regDate = Util.getCurrentDate();

		Member member = new Member(id, regDate, loginId, loginPw, name);

		memberService.join(member);

		System.out.printf("%d번 회원 가입이 완료되었습니다.\n", id);
	}

	private boolean isJoinableLoginId(String loginId) {

		int index = memberService.getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");

		Container.memberDao
				.add(new Member(Container.memberDao.getNewId(), Util.getCurrentDate(), "admin", "admin", "관리자"));
		Container.memberDao
				.add(new Member(Container.memberDao.getNewId(), Util.getCurrentDate(), "test1", "test1", "김철수"));
		Container.memberDao
				.add(new Member(Container.memberDao.getNewId(), Util.getCurrentDate(), "test2", "test2", "박영수"));
	}

}
