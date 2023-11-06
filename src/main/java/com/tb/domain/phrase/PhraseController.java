package com.tb.domain.phrase;

import com.tb.base.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhraseController {
	private final Scanner sc;
	private int lastId;
	private List<Phrase> list;

	public PhraseController(Scanner _sc) {
		this.sc = _sc;
		this.lastId = 0;
		this.list = new ArrayList<>();
	}

	public void addPhrase() {
		Phrase ph = new Phrase();
		System.out.print("명언: ");
		ph.setContent(sc.nextLine());
		System.out.print("작가: ");
		ph.setAuthor(sc.nextLine());
		ph.setId(++lastId);
		list.add(ph);
		System.out.println(ph.getId() + "번 명령이 등록되었습니다.");
	}

	public void getList() {
		System.out.println("번호 / 작가 / 명언\n" + "----------------------");
		for (Phrase o: list)
			System.out.println(o.getId() + " / " + o.getAuthor() + " / " + o.getContent());
	}

	public void removePhrase(Request rq) {
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

	public void modifyPhrase(Request rq) {
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
