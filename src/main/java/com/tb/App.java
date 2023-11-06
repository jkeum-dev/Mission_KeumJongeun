package com.tb;

import java.util.Scanner;

public class App {
	private final Scanner sc;

	public App() {
		this.sc = new Scanner(System.in);
	}

	public void run() {
		System.out.println("== 명언 앱 ==");
		String cmd = sc.nextLine();
		if (cmd.equals("종료")) {
			sc.close();
			System.exit(0);
		}
	}
}
