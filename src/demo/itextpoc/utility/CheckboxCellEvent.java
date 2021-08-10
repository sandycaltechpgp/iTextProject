package demo.itextpoc.utility;

import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RadioCheckField;

public class CheckboxCellEvent implements PdfPCellEvent {
	// The name of the check box field
	protected String name;

	// We create a cell event
	public CheckboxCellEvent(String name) {
		this.name = name;
	}

	// We create and add the check box field
	@Override
	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
		PdfWriter writer = canvases[0].getPdfWriter();
		// define the coordinates of the middle
		float x = (position.getLeft() + position.getRight()) / 2;
		float y = (position.getTop() + position.getBottom()) / 2;
		// define the position of a check box that measures 20 by 20
		Rectangle rect = new Rectangle(x - 10, y - 10, x + 10, y + 10);
		// define the check box
		RadioCheckField checkbox = new RadioCheckField(writer, rect, name, "Yes");
		// add the check box as a field
		try {
			writer.addAnnotation(checkbox.getCheckField());
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}

}
