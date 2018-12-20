package exp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ReplaceUtilsTest {
	@ParameterizedTest
	@CsvSource({
			"1이요 2이요 3이요, [0-9], 숫자, 숫자이요 숫자이요 숫자이요",
			"1이요 2이요 3이요, 1, 숫자, 숫자이요 2이요 3이요",
	})
	public void replaceAll(String text, String regEx, String replacement, String expected) {
		Pattern pattern = Pattern.compile(regEx);

		String actual0 = ReplaceUtils.replaceAll0(text, regEx, replacement);
		assertThat(actual0).isEqualTo(expected);

		String actual1 = ReplaceUtils.replaceAll1(pattern, text, replacement);
		assertThat(actual1).isEqualTo(expected);

		String actual2 = ReplaceUtils.replaceAll2(pattern, text, replacement);
		assertThat(actual2).isEqualTo(expected);

		StringBuffer actual3 = ReplaceUtils.replaceAll3(pattern, new StringBuffer(text), replacement);
		assertThat(actual3.toString()).isEqualTo(expected);

		StringBuilder actual4 = ReplaceUtils.replaceAll4(pattern, new StringBuilder(text), replacement);
		assertThat(actual4.toString()).isEqualTo(expected);

		StringBuilder actual5 = ReplaceUtils.replaceAll4(pattern, new StringBuilder(text), replacement);
		assertThat(actual5.toString()).isEqualTo(expected);
	}
}