package demo.itextpoc.utility;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;

public class TextFieldForPdfCell implements PdfPCellEvent {
	protected String fieldname;

	public TextFieldForPdfCell(String fieldname) {
		this.fieldname = fieldname;
	}

	public void cellLayout(PdfPCell cell, Rectangle rectangle, PdfContentByte[] canvases) {
		final PdfWriter writer = canvases[0].getPdfWriter();
		final TextField textField = new TextField(writer, rectangle, fieldname);
		textField.setBorderColor(BaseColor.BLACK);
		textField.setBorderWidth(0);
		textField.setBackgroundColor(BaseColor.WHITE);
		try {
			textField.setFont(FontUtility.getNormalBody(6).getBaseFont());
			final PdfFormField field = textField.getTextField();
			writer.addAnnotation(field);
		} catch (final IOException ioe) {
			throw new ExceptionConverter(ioe);
		} catch (final DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}
}