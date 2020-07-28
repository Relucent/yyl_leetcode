package yyl.leetcode.util;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 断言工具类
 */
public class Assert {

	/**
	 * 断言表达式为真，如果不为真则抛出异常
	 * @param expression 表达式
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}

	/**
	 * 断言表达式为真，如果不为真则抛出异常
	 * @param expression 表达式
	 * @param message 异常信息内容
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			fail(message);
		}
	}

	/**
	 * 断言两个值相等，如果不相等则抛出异常
	 * @param expected 预期值
	 * @param actual 实际值
	 */
	public static <T> void assertEquals(T expected, T actual) {
		assertEquals(expected, actual, "[Assertion failed] - Actual is not the equal as expected");
	}

	/**
	 * 断言两个值相等，如果不相等则抛出异常
	 * @param expected 预期值
	 * @param actual 实际值
	 * @param message 异常信息内容
	 */
	public static <T> void assertEquals(T expected, T actual, String message) {
		if (isEquals(expected, actual)) {
			return;
		}
		fail(format(message, expected, actual));
	}

	private static String format(String message, Object expected, Object actual) {
		String formatted = "";
		if (message != null && !message.equals("")) {
			formatted = message + " ";
		}
		String expectedString = String.valueOf(expected);
		String actualString = String.valueOf(actual);
		if (expectedString.equals(actualString)) {
			return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: "
					+ formatClassAndValue(actual, actualString);
		} else {
			return formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
		}
	}

	private static String formatClassAndValue(Object value, String valueString) {
		String className = value == null ? "null" : value.getClass().getName();
		return className + "<" + valueString + ">";
	}

	private static void fail(String message) {
		if (message == null) {
			throw new AssertionError();
		}
		throw new AssertionError(message);
	}

	private static <T> boolean isEquals(T expecteds, T actuals) {
		if (expecteds == actuals) {
			return true;
		}
		if (expecteds == null && actuals != null) {
			return false;
		}
		if (expecteds.equals(actuals)) {
			return true;
		}
		if (!isArray(expecteds) || !isArray(actuals)) {
			return false;
		}
		if (Arrays.deepEquals(new Object[] { expecteds }, new Object[] { actuals })) {
			return true;
		}
		int actualsLength = Array.getLength(actuals);
		int expectedsLength = Array.getLength(expecteds);
		if (actualsLength != expectedsLength) {
			return false;
		}
		for (int i = 0; i < expectedsLength; i++) {
			Object expected = Array.get(expecteds, i);
			Object actual = Array.get(actuals, i);
			if (!isEquals(expected, actual)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isArray(Object expected) {
		return expected != null && expected.getClass().isArray();
	}
}
