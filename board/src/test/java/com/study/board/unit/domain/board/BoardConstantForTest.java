package com.study.board.unit.domain.board;

import com.study.global.util.SHA512;

public class BoardConstantForTest {

	public static final String BASE_URL = "/api/boards";
	public static final String SUBJECT = "test";
	public static final String CONTENTS = "test contents";
	public static final String WRITER = "tester";
	public static final Long BOARD_ID = 1L;
	public static final String PASSWORD = SHA512.decryption("");

}
