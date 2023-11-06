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
			if (cmd.equals("종료")) {
				sc.close();
				System.exit(0);
			} else if (cmd.equals("등록")) {
				addPhrase();
			} else if (cmd.equals("목록")) {
				getList();
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
}
