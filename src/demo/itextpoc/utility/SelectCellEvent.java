package demo.itextpoc.utility;

import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;

public class SelectCellEvent implements PdfPCellEvent {

	protected PdfFormField selectGroup;
	protected String name;
	protected String[] exports;
	protected String[] options;
	protected BaseFont font;

	public SelectCellEvent(PdfFormField selectGroup, String name, String[] exports, String[] options)
			throws DocumentException, IOException {
		this.selectGroup = selectGroup;
		this.name = name;
		this.exports = exports;
		this.options = options;
		font = BaseFont.createFont(BaseFont.COURIER_BOLD, BaseFont.CP1250, BaseFont.EMBEDDED);
		font.setSubset(false);
	}

	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
		PdfWriter writer = canvases[0].getPdfWriter();
		TextField tf = new TextField(writer, position, name);
		tf.setFont(font);
		tf.setFontSize(8);
		tf.setBorderStyle(0);
		tf.setChoiceExports(exports);
		tf.setChoices(options);
		tf.setAlignment(Element.ALIGN_LEFT);
		try {
			selectGroup.addKid(tf.getComboField());
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}

}
