package demo.itextpoc.utility;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfBorderDictionary;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;

public class TextFieldChunk extends PdfPageEventHelper {
	@Override
	public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
		rect.rectangle(10, 20);
		TextField field = new TextField(writer, rect, text);
		field.setBorderStyle(PdfBorderDictionary.STYLE_UNDERLINE);
		try {
			// field.setFont(FontUtility.getNormalBody(8).getBaseFont());
			writer.addAnnotation(field.getTextField());
		} catch (IOException ex) {
			throw new ExceptionConverter(ex);
		} catch (DocumentException ex) {
			throw new ExceptionConverter(ex);
		}
	}
}
