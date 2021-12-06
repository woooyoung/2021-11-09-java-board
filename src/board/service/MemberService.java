package board.service;

import board.container.Container;
import board.dao.MemberDao;
import board.dto.Member;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	public int getMemberIndexByLoginId(String loginId) {
		return memberDao.getMemberIndexByLoginId(loginId);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public int getNewId() {
		return memberDao.getNewId();
	}

	public void join(Member member) {
		memberDao.add(member);
	}

	public String getMemberNameById(int memberId) {
		return memberDao.getMemberNameById(memberId);
	}

}
