package com.tb.base;

import com.tb.domain.phrase.PhraseController;

import java.util.Scanner;

public class App {
	private final Scanner sc;

	public App() {
		this.sc = new Scanner(System.in);
	}

	public void run() {
		PhraseController phraseController = new PhraseController(sc);
		System.out.println("== 명언 앱 ==");
		while (true) {
			System.out.print("명령) ");
			String cmd = sc.nextLine();
			Request rq = new Request(cmd);

			switch (rq.getAction()) {
				case "종료": sc.close(); return;
				case "등록": phraseController.addPhrase(); break;
				case "목록": phraseController.getList(); break;
				case "삭제": phraseController.removePhrase(rq); break;
				case "수정": phraseController.modifyPhrase(rq); break;
				case "빌드": phraseController.buildData(rq); break;
			}
		}
	}
}
