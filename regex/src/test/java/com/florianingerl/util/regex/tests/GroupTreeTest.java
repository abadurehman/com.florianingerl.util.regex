package com.florianingerl.util.regex.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.florianingerl.util.regex.GroupTree;
import com.florianingerl.util.regex.Matcher;
import com.florianingerl.util.regex.Pattern;

public class GroupTreeTest {

	@Test
	public void test() {
		Pattern p = Pattern.compile("(?<sum>(?<number>\\d+)\\+(?:(?'sum')|(?'number')))");
		Matcher s = p.matcher("5+8");
		assertTrue(s.matches());

		GroupTree gt = s.groupTree();
		assertEquals("0\n\tsum\n\t\tnumber\n\t\tnumber\n", gt.print());

		s = p.matcher("5+6+7");
		assertTrue(s.matches());

		gt = s.groupTree();
		System.out.println(gt.print());
		assertEquals("0\n\tsum\n\t\tnumber\n\t\tsum\n\t\t\tnumber\n\t\t\tnumber\n", gt.print());
	}

	@Test
	public void test2() {
		Pattern p = Pattern.compile(
				"(?(DEFINE)(?<term>(?'number')|(?'sum')|(?'product'))(?<sum>(?'summand')(?:\\+(?'summand'))+)(?<product>(?'factor')(?:\\*(?'factor'))+)(?<summand>\\((?'term')\\)|(?'number')|(?'product'))(?<factor>(?'number')|\\((?'term')\\))(?<number>\\d+))(?'term')");
		Matcher m = p.matcher("5+6*9");
		assertTrue(m.matches());

		GroupTree gt = m.groupTree();
		System.out.println(gt.print());

	}

}
