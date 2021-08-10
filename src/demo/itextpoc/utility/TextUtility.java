package demo.itextpoc.utility;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;

public class TextUtility {

	private TextUtility() {

	}

	public static Phrase getMandatoryStar() throws IOException {
		Font font = new Font(FontFamily.COURIER, 9, Font.NORMAL, BaseColor.RED);
		Phrase mandateStar = new Phrase("*", font);
		return mandateStar;
	}
}
