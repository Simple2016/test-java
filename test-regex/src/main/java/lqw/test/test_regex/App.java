package lqw.test.test_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 匹配双引号之间的内容
 *
 */
class Main {
	public static void main(String[] args) {
		String txt = "String a=\"ad\\\"f\"\"";

		String re1 = ".*?"; // Non-greedy match on filler
		String re2 = "(\".*?\")"; // Double Quote String 1
		String re3 = "((?:[a-z][a-z0-9_]*))"; // Variable Name 1
		String re4 = "(.)"; // Any Single Character 1

		Pattern p = Pattern.compile(re1 + re2 + re3 + re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(txt);
		if (m.find()) {
			String string1 = m.group(1);
			String var1 = m.group(2);
			String c1 = m.group(3);
			System.out.print(
					"(" + string1.toString() + ")" + "(" + var1.toString() + ")" + "(" + c1.toString() + ")" + "\n");
		}
	}
}