package com.tb.domain.phrase;

import com.tb.base.Request;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhraseController {
	private final Scanner sc;
	private int lastId;
	private List<Phrase> list;
	private String filePath; // 파일 경로 지정

	public PhraseController(Scanner _sc) {
		this.sc = _sc;
		this.lastId = 0;
		this.list = new ArrayList<>();
		this.filePath = "src/main/resources/phrase.txt";
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
		writePhraseToFile(ph);
	}

	private void writePhraseToFile(Phrase phrase) {
		// BufferedWriter와 FileWriter를 사용하여 파일에 쓰기
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			writer.write(phrase.getId() + "," + phrase.getContent() + "," + phrase.getAuthor());
			writer.newLine(); // 다음 입력을 위한 줄바꿈
		} catch (IOException e) {
			System.err.println("파일 쓰기 중 오류 발생: " + e.getMessage());
		}
	}

	public void getList() {
		System.out.println("번호 / 작가 / 명언\n" + "----------------------");
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length >= 3) {
					int id = Integer.parseInt(parts[0].trim());
					String content = parts[1].trim();
					String author = parts[2].trim();
					System.out.println(id + " / " + author + " / " + content);
				}
			}
		} catch (IOException e) {
			System.err.println("파일 읽기 중 오류 발생: " + e.getMessage());
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
}
