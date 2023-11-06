package com.tb;

import java.util.Scanner;

public class App {
	private final Scanner sc;

	public App() {
		this.sc = new Scanner(System.in);
	}

	public void run() {
		Phrase ph = new Phrase();
		System.out.println("== 명언 앱 ==");
		while (true) {
			System.out.print("명령) ");
			String cmd = sc.nextLine();
			if (cmd.equals("종료")) {
				sc.close();
				System.exit(0);
			} else if (cmd.equals("등록")) {
				System.out.print("명언: ");
				ph.setContent(sc.nextLine());
				System.out.print("작가: ");
				ph.setAuthor(sc.nextLine());
			}
		}
	}
}
