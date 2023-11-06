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
			} else if (cmd.startsWith("삭제?")) {
				removePhrase(cmd);
			} else if (cmd.startsWith("수정?")) {
				modifyPhrase(cmd);
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

	private void removePhrase(String cmd) {
		int id = getParamId(cmd);
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

	private void modifyPhrase(String cmd) {
		int id = getParamId(cmd);
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

	private int getParamId(String str) {
		// 삭제?id=2&dd=ss&ll=aa
		String[] queryStr = str.split("\\?", 2); // cmdQuery: 삭제 | id=2&dd=ss&ll=aa
		String cmd = queryStr[0]; // cmd: 삭제
		String[] params = queryStr[1].split("&"); // params: id=2 | dd=ss | ll=aa
		for (String param: params) {
			String[] pair = param.split("=", 2);
			if (pair[0].equals("id")) {
				try {
					return Integer.parseInt(pair[1]);
				} catch (NumberFormatException e) {
					return 0;
				}
			}
		}
		return 0;
	}
}
