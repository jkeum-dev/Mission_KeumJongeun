package com.tb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Phrase {
	@Getter
	private int id;
	@Getter
	@Setter
	private String content;
	@Getter
	@Setter
	private String author;
}
