package swat.swatcodeutilities;

import java.util.List;

public class SwatPrintUtils {

	public static <T> void print(List<T> list, String msg) {
		print(list, msg, ", ");
	}

	public static <T> void print(List<T> list, String msg, String delim) {
		System.out.println("");
		System.out.print(msg + " : \n");
		for (T i : list) {
			System.out.print(i + delim);
		}
		System.out.println("");
	}
}
