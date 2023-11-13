package com.tb.domain.phrase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.base.Request;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhraseController {
	private final Scanner sc;
	private int lastId;
	private List<Phrase> list;
	private String filePath;
	private ObjectMapper objectMapper = new ObjectMapper();

	public PhraseController(Scanner _sc) {
		this.sc = _sc;
		this.lastId = 0;
		this.list = new ArrayList<>();
		this.filePath = "src/main/resources/data.json";
	}

	public void addPhrase() {
		Phrase ph = new Phrase();
		System.out.print("명언: ");
		ph.setContent(sc.nextLine());
		System.out.print("작가: ");
		ph.setAuthor(sc.nextLine());
		ph.setId(++lastId);
		list.add(ph);
		System.out.println(ph.getId() + "번 명언이 등록되었습니다.");
	}

	public void getList() {
		System.out.println("번호 / 작가 / 명언\n" + "----------------------");
		for (Phrase phrase : list)
			System.out.println(phrase.getId() + " / " + phrase.getAuthor() + " / " + phrase.getContent());
	}

	public void getListFromFile() {
		// data.json 파일 읽어서 list에 추가하기
		try {
			list = objectMapper.readValue(new File(filePath), new TypeReference<List<Phrase>>(){});
			lastId = list.stream().mapToInt(Phrase::getId).max().orElse(0);
		} catch (IOException e) {
		}
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

	public void buildData(Request rq) {
		// list에 있는 데이터를 data.json에 새로 쓰기.(기존 파일 존재하면 덮어쓰기. 추가X)
		try {
			objectMapper.writeValue(new File(filePath), list);
			System.out.println("data.json 파일의 내용이 갱신되었습니다.");
		} catch (Exception e) {
		}
	}
}
