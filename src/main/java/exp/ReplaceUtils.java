package exp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceUtils {
	public static String replaceAll0(String text, String regex, String replacement) {
		return text.replaceAll(regex, replacement);
	}

	public static String replaceAll1(Pattern pattern, String text, String replacement) {
		return pattern.matcher(text).replaceAll(replacement);
	}
	public static String replaceAll2(Pattern pattern, String text, String replacement) {
		Matcher matcher = pattern.matcher(text);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			matcher.appendReplacement(sb, replacement);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static StringBuffer replaceAll3(Pattern pattern, StringBuffer text, String replacement) {
		Matcher matcher = pattern.matcher(text);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, replacement);
		}
		matcher.appendTail(sb);
		return sb;
	}

	public static StringBuilder replaceAll4(Pattern pattern, StringBuilder text, String replacement) {
		Matcher matcher = pattern.matcher(text);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			matcher.appendReplacement(sb, replacement);
		}
		matcher.appendTail(sb);
		return sb;
	}

	public static StringBuilder replaceAll5(Pattern pattern, StringBuilder text, String replacement) {
		Matcher matcher = pattern.matcher(text);
		StringBuilder sb = new StringBuilder(text.length());
		while (matcher.find()) {
			matcher.appendReplacement(sb, replacement);
		}
		matcher.appendTail(sb);
		return sb;
	}
}
