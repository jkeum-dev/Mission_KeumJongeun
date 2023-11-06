package com.tb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	private final Scanner sc;
	private int lastId;
	private List<Phrase> list;

	public App() {
		this.sc = new Scanner(System.in);
		this.lastId = 0;
		this.list = new ArrayList<>();
	}

	public void run() {
		System.out.println("== 명언 앱 ==");
		while (true) {
			System.out.print("명령) ");
			String cmd = sc.nextLine();
			Request rq = new Request(cmd);

			switch (rq.getAction()) {
				case "종료": sc.close(); return;
				case "등록": addPhrase(); break;
				case "목록": getList(); break;
				case "삭제": removePhrase(rq); break;
				case "수정": modifyPhrase(rq); break;
			}
		}
	}

	private void addPhrase() {
		Phrase ph = new Phrase();
		System.out.print("명언: ");
		ph.setContent(sc.nextLine());
		System.out.print("작가: ");
		ph.setAuthor(sc.nextLine());
		ph.setId(++lastId);
		list.add(ph);
		System.out.println(ph.getId() + "번 명령이 등록되었습니다.");
	}

	private void getList() {
		System.out.println("번호 / 작가 / 명언\n" + "----------------------");
		for (Phrase o: list)
			System.out.println(o.getId() + " / " + o.getAuthor() + " / " + o.getContent());
	}

	private void removePhrase(Request rq) {
		int id = rq.getParamAsInt("id", 0);
		if (id == 0) {
			System.out.println("id 값을 정확히 입력해주세요.");
			return;
		}
		for (Phrase o: list) {
			if (o.getId() == id) {
				list.remove(o);
				System.out.println(id + "번 명언이 삭제되었습니다.");
				return;
			}
		}
		System.out.println(id + "번 명언은 존재하지 않습니다.");
	}

	private void modifyPhrase(Request rq) {
		int id = rq.getParamAsInt("id", 0);
		if (id == 0) {
			System.out.println("id 값을 정확히 입력해주세요.");
			return;
		}
		for (Phrase o: list) {
			if (o.getId() == id) {
				System.out.println("명언(기존): " + o.getContent());
				System.out.print("명언: ");
				o.setContent(sc.nextLine());
				System.out.println("작가(기존): " + o.getAuthor());
				System.out.print("작가: ");
				o.setAuthor(sc.nextLine());
				return;
			}
		}
		System.out.println(id + "번 명언은 존재하지 않습니다.");
	}
}
