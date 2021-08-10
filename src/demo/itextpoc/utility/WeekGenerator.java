package demo.itextpoc.utility;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class WeekGenerator {

	public WeekGenerator() {
		// TODO Auto-generated constructor stub
	}

	public void generateWeekTable(Document doc, String name) throws Exception {

		GetCellInputCB cb = new GetCellInputCB(name);

		PdfPTable weekTable = new PdfPTable(8);
		weekTable.setWidthPercentage(100);
		weekTable.setWidths(new float[] { 12.5f, 12.5f, 12.5f, 12.5f, 12.5f, 12.5f, 12.5f, 12.5f });

		Paragraph mon = new Paragraph();
		mon.setFont(FontUtility.getBoldBody(10));
		mon.add("MON");

		Paragraph tue = new Paragraph();
		tue.setFont(FontUtility.getBoldBody(10));
		tue.add("TUE");

		Paragraph wed = new Paragraph();
		wed.setFont(FontUtility.getBoldBody(10));
		wed.add("WED");

		Paragraph thu = new Paragraph();
		thu.setFont(FontUtility.getBoldBody(10));
		thu.add("THU");

		Paragraph fri = new Paragraph();
		fri.setFont(FontUtility.getBoldBody(10));
		fri.add("FRI");

		Paragraph sat = new Paragraph();
		sat.setFont(FontUtility.getBoldBody(10));
		sat.add("SAT");

		Paragraph sun = new Paragraph();
		sun.setFont(FontUtility.getBoldBody(10));
		sun.add("SUN");

		PdfPCell cell1 = new PdfPCell();
		cell1.addElement(new Paragraph(""));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setVerticalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell2 = new PdfPCell(mon);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setVerticalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell3 = new PdfPCell(tue);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setVerticalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell4 = new PdfPCell(wed);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setVerticalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell5 = new PdfPCell(thu);
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell5.setVerticalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell6 = new PdfPCell(fri);
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell6.setVerticalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell7 = new PdfPCell(sat);
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setVerticalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell8 = new PdfPCell(sun);
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell8.setVerticalAlignment(Element.ALIGN_CENTER);

		weekTable.addCell(cell1);
		weekTable.addCell(cell2);
		weekTable.addCell(cell3);
		weekTable.addCell(cell4);

		weekTable.addCell(cell5);
		weekTable.addCell(cell6);
		weekTable.addCell(cell7);
		weekTable.addCell(cell8);

		PdfPTable tt = new PdfPTable(1);

		PdfPCell fromcell = new PdfPCell(new Paragraph("FROM", FontUtility.getBoldBody(10)));
		fromcell.setMinimumHeight(20);

		PdfPCell tocell = new PdfPCell(new Paragraph("TO", FontUtility.getBoldBody(10)));
		tocell.setMinimumHeight(20);

		tt.addCell(fromcell);
		tt.addCell(tocell);

		weekTable.addCell(tt);

		weekTable.addCell(getAMPMTable(cb.getCellInputName()));
		weekTable.addCell(getAMPMTable(cb.getCellInputName()));
		weekTable.addCell(getAMPMTable(cb.getCellInputName()));
		weekTable.addCell(getAMPMTable(cb.getCellInputName()));
		weekTable.addCell(getAMPMTable(cb.getCellInputName()));
		weekTable.addCell(getAMPMTable(cb.getCellInputName()));
		weekTable.addCell(getAMPMTable(cb.getCellInputName()));

		doc.add(weekTable);

	}

	public PdfPTable getAMPMTable(String name) throws Exception {

		PdfPTable main = new PdfPTable(2);
		main.setWidths(new int[] { 50, 50 });

		PdfPTable left = new PdfPTable(1);

		PdfPCell mtcell = new PdfPCell(new Paragraph(""));
		mtcell.setBorderColor(BaseColor.ORANGE);
		mtcell.setCellEvent(new TextFieldForPdfCell(name + "ce"));

		left.addCell(mtcell);

		PdfPTable right = new PdfPTable(2);
		right.setWidths(new int[] { 40, 60 });

		PdfPCell lcell1 = new PdfPCell();
		lcell1.setCellEvent(new CheckboxCellEvent(name + "1"));
		lcell1.setMinimumHeight(20);
		lcell1.setBorderColor(BaseColor.ORANGE);

		PdfPCell lcell2 = new PdfPCell();
		lcell2.setCellEvent(new CheckboxCellEvent(name + "2"));
		lcell2.setMinimumHeight(20);
		lcell2.setBorderColor(BaseColor.ORANGE);

		right.addCell(lcell1);

		PdfPCell rcell1 = new PdfPCell(new Paragraph(String.valueOf("AM"), FontUtility.getBoldBody(8)));
		rcell1.setMinimumHeight(20);
		rcell1.setBorderColor(BaseColor.ORANGE);

		right.addCell(rcell1);

		right.addCell(lcell2);

		PdfPCell rcell2 = new PdfPCell(new Paragraph(String.valueOf("PM"), FontUtility.getBoldBody(8)));
		rcell2.setMinimumHeight(20);
		rcell2.setBorderColor(BaseColor.ORANGE);

		right.addCell(rcell2);

		main.addCell(left);
		main.addCell(right);
		return main;
	}

}
