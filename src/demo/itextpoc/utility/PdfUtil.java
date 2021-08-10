package demo.itextpoc.utility;

import com.itextpdf.text.pdf.PdfPCell;

public class PdfUtil {

	private PdfUtil() {
		// TODO Auto-generated constructor stub
	}

	public static PdfPCell getBlankCell() {
		PdfPCell blankCell = new PdfPCell();
		return blankCell;
	}

}
