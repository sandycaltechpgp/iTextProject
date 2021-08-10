package demo.itextpoc.utility;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

public class FontUtility {

	private FontUtility() {

	}

	public static Font getNormalHeader(int size) throws IOException {
		Font font = new Font(FontFamily.COURIER, size, Font.NORMAL, BaseColor.BLACK);
		return font;
	}

	public static Font getBoldHeader(int size) throws IOException {
		Font font = new Font(FontFamily.COURIER, size, Font.BOLD, BaseColor.BLACK);
		return font;
	}

	public static Font getNormalBody(int size) throws IOException {
		Font font = new Font(FontFamily.COURIER, size, Font.NORMAL, BaseColor.BLACK);
		return font;
	}

	public static Font getBoldBody(int size) throws IOException {
		Font font = new Font(FontFamily.COURIER, size, Font.BOLD, BaseColor.BLACK);
		return font;
	}

	public static Font getBoldBodyItalic(int size) throws IOException {
		Font font = new Font(FontFamily.COURIER, size, Font.ITALIC, BaseColor.BLACK);
		return font;
	}

}
