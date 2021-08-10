package demo.itextpoc.pdfgenerator;

import com.itextpdf.text.pdf.PdfPTable;

import demo.itextpoc.utility.GetCellInputName;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		GetCellInputName inp = new GetCellInputName();

		PdfPTable headerPdfPTable = new PdfPTable(1);
		headerPdfPTable.setWidthPercentage(100);
	}
}
