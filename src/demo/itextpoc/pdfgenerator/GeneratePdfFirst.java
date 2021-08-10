package demo.itextpoc.pdfgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import demo.itextpoc.utility.FontUtility;
import demo.itextpoc.utility.GetCellInputName;
import demo.itextpoc.utility.SelectCellEvent;
import demo.itextpoc.utility.TextFieldForPdfCell;
import demo.itextpoc.utility.TextUtility;

public class GeneratePdfFirst {

	public static void main(String[] args) {
		System.out.println("Pdf creation ............................START");
		try {

			GetCellInputName inpName = new GetCellInputName();

			String filePath = "./target/firstpdf.pdf";
			File file = new File(filePath);
			file.getParentFile().mkdirs();

			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

			Font boldFont = FontUtility.getBoldBody(8);
			Font regFont = FontUtility.getNormalBody(6);

			document.open();

			Image img = Image.getInstance("./src/resources/icons/firstheader.png");

			img.scaleToFit(600, 600);
			img.setAlignment(img.ALIGN_LEFT);

			Paragraph pImages = new Paragraph();
			pImages.add(img);

			PdfPTable headerPdfPTable = new PdfPTable(1);
			headerPdfPTable.setWidthPercentage(100);

			headerPdfPTable.addCell(img);

			headerPdfPTable.setHorizontalAlignment(img.ALIGN_LEFT);

			document.add(headerPdfPTable);

			PdfPTable mainPdfPTable = new PdfPTable(2);

			mainPdfPTable.setWidthPercentage(100);
			mainPdfPTable.setWidths(new int[] { 40, 60 });

			PdfPCell cell1 = new PdfPCell();
			cell1.setBorderColor(BaseColor.BLUE);

			PdfPCell celll = new PdfPCell();
			celll.setBorder(0);
			celll.setBorderColor(BaseColor.WHITE);

			PdfPTable cell2 = new PdfPTable(2);
			cell2.setWidths(new int[] { 40, 60 });

			Paragraph p1 = new Paragraph();
			p1.setFont(boldFont);
			p1.add("Date of Notice:");

			Paragraph p2 = new Paragraph();
			p2.setFont(boldFont);
			p2.add("Caseload Code:");

			Paragraph p3 = new Paragraph();
			p3.setFont(boldFont);
			p3.add("Child Care Case #:");

			PdfPCell cell21 = new PdfPCell(p1);
			cell21.setBorderColor(BaseColor.ORANGE);

			PdfPCell cell22 = new PdfPCell();
			cell22.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			cell22.setBorderColor(BaseColor.ORANGE);

			PdfPCell cell31 = new PdfPCell(p2);
			cell31.setBorderColor(BaseColor.ORANGE);

			PdfPCell cell32 = new PdfPCell();
			cell32.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			cell32.setBorderColor(BaseColor.ORANGE);

			PdfPCell cell41 = new PdfPCell(p3);
			cell41.setBorderColor(BaseColor.ORANGE);

			PdfPCell cell42 = new PdfPCell();
			cell42.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			cell42.setBorderColor(BaseColor.ORANGE);

			cell2.addCell(cell21);
			cell2.addCell(cell22);

			cell2.addCell(cell31);
			cell2.addCell(cell32);

			cell2.addCell(cell41);
			cell2.addCell(cell42);

			celll.addElement(cell2);

			mainPdfPTable.addCell(cell1);
			mainPdfPTable.addCell(cell2);

			document.add(mainPdfPTable);

			// ------------------------

			PdfPTable seccPdfPTable = new PdfPTable(2);
			seccPdfPTable.setWidthPercentage(100);
			seccPdfPTable.setWidths(new int[] { 40, 60 });

			PdfPCell ccell = new PdfPCell();
			ccell.setBorderColor(BaseColor.BLUE);
			seccPdfPTable.addCell(ccell);

			PdfPCell cellSelectProvdTitle = new PdfPCell();
			Phrase provdTitlePhrase = TextUtility.getMandatoryStar();
			provdTitlePhrase.add(new Phrase("Select Provider:", boldFont));
			cellSelectProvdTitle.addElement(provdTitlePhrase);
			cellSelectProvdTitle.setBorderColor(BaseColor.ORANGE);
			seccPdfPTable.addCell(cellSelectProvdTitle);

			document.add(seccPdfPTable);

			PdfPTable secyPdfPTable = new PdfPTable(2);
			secyPdfPTable.setWidthPercentage(100);
			secyPdfPTable.setWidths(new int[] { 40, 60 });

			PdfPCell cxcell = new PdfPCell(new Phrase(" "));
			cxcell.setBorderColor(BaseColor.BLUE);
			secyPdfPTable.addCell(cxcell);

			PdfFormField selectGroup = PdfFormField.createEmpty(writer);
			selectGroup.setFieldName("provdGroup1");
			String[] options = { "Choose Option", "Provider 1", "Provider 2", "Provider 3" };
			String[] exports = { "N/A", "option1", "option2", "option3" };
			PdfPCell cellSelectProvdInput = new PdfPCell();
			cellSelectProvdInput.setCellEvent(new SelectCellEvent(selectGroup, "provdInp1", exports, options));
			cellSelectProvdInput.setBorderColor(BaseColor.ORANGE);
			secyPdfPTable.addCell(cellSelectProvdInput);

			document.add(secyPdfPTable);

			writer.addAnnotation(selectGroup);

			PdfPTable reasonTable = new PdfPTable(2);
			reasonTable.setWidthPercentage(100);
			reasonTable.setWidths(new int[] { 20, 60 });

			Phrase ppr = new Phrase();
			ppr.setFont(boldFont);
			ppr.add("Reason For ChildCare:");

			PdfPCell rcell = new PdfPCell(ppr);
			rcell.setBorderColor(BaseColor.ORANGE);
			reasonTable.addCell(rcell);

			PdfPCell rrcell = new PdfPCell();
			rrcell.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			rrcell.setBorderColor(BaseColor.ORANGE);
			reasonTable.addCell(rrcell);

			document.add(reasonTable);

			PdfPTable parentTable = new PdfPTable(3);
			parentTable.setWidthPercentage(100);
			parentTable.setWidths(new int[] { 49, 2, 49 });

			Phrase papr = new Phrase();
			papr.setFont(boldFont);
			papr.add("Parent/Gaurdian:");

			PdfPTable fTable = new PdfPTable(1);
			fTable.setWidthPercentage(100);
			fTable.setHorizontalAlignment(TabStop.Alignment.LEFT.ordinal());

			PdfPCell oprcell = new PdfPCell();
			oprcell.setBorderColor(BaseColor.ORANGE);

			PdfPCell prcell = new PdfPCell(papr);
			prcell.setBorderColor(BaseColor.ORANGE);

			PdfPCell prrcell = new PdfPCell();
			prrcell.setBorderColor(BaseColor.ORANGE);
			prrcell.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			prrcell.setFixedHeight(35);

			fTable.addCell(prcell);
			fTable.addCell(prrcell);

			oprcell.addElement(fTable);

			parentTable.addCell(oprcell);

			PdfPCell mrcell = new PdfPCell();
			mrcell.setBorderColor(BaseColor.ORANGE);

			parentTable.addCell(mrcell);

			Phrase ppapr = new Phrase();
			ppapr.setFont(boldFont);
			ppapr.add("Provider Address:");

			PdfPCell opprcell = new PdfPCell();
			opprcell.setBorderColor(BaseColor.ORANGE);

			PdfPTable fTpable = new PdfPTable(1);
			fTpable.setWidthPercentage(100);

			PdfPCell pprcell = new PdfPCell(ppapr);
			pprcell.setBorderColor(BaseColor.ORANGE);

			PdfPCell pprrcell = new PdfPCell();
			pprrcell.setBorderColor(BaseColor.ORANGE);
			pprrcell.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			pprrcell.setFixedHeight(35);

			fTpable.addCell(pprcell);
			fTpable.addCell(pprrcell);

			opprcell.addElement(fTpable);

			parentTable.addCell(opprcell);

			document.add(parentTable);

			PdfPTable table1 = new PdfPTable(5);
			table1.setWidthPercentage(100);
			table1.setWidths(new int[] { 43, 10, 8, 10, 31 });

			Phrase pt1 = new Phrase();
			pt1.setFont(regFont);
			pt1.add("Your eligibility for child care has been approved beginning");

			PdfPCell ct1 = new PdfPCell(pt1);
			ct1.setBorderColor(BaseColor.BLUE);

			PdfPCell ct2 = new PdfPCell();
			ct2.setBorderColor(BaseColor.ORANGE);
			ct2.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			Phrase pt3 = new Phrase();
			pt3.setFont(regFont);
			pt3.add("through");

			PdfPCell ct3 = new PdfPCell(pt3);
			ct3.setBorderColor(BaseColor.BLUE);

			PdfPCell ct4 = new PdfPCell();
			ct4.setBorderColor(BaseColor.ORANGE);
			ct4.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			Phrase pt5 = new Phrase();
			pt5.setFont(regFont);
			pt5.add("or until there is change in eligibility,");

			PdfPCell ct5 = new PdfPCell(pt5);
			ct5.setBorderColor(BaseColor.BLUE);

			table1.addCell(ct1);
			table1.addCell(ct2);
			table1.addCell(ct3);
			table1.addCell(ct4);
			table1.addCell(ct5);

			document.add(table1);

			PdfPTable table2 = new PdfPTable(1);
			table2.setWidthPercentage(100);
			Phrase pt6 = new Phrase();
			pt6.setFont(regFont);
			pt6.add("whichever is sooner. We cannot pay for child care provide before the date you were approved. "
					+ "Eligibility will be redetermined near the end of your eligibilty period, but mat be"
					+ "redetermined sooner if there is a change in income, family size or at the "
					+ "Department's discretion.");

			PdfPCell ct6 = new PdfPCell(pt6);
			ct6.setBorderColor(BaseColor.BLUE);

			table2.addCell(ct6);

			document.add(table2);

			PdfPTable table3 = new PdfPTable(3);
			table3.setWidthPercentage(100);
			table3.setWidths(new int[] { 50, 35, 15 });

			Phrase rp = new Phrase();
			rp.setFont(boldFont);
			rp.add("REPORTING CHANGES:");
			rp.setFont(regFont);
			rp.add("You are responsible for notifying");

			PdfPCell ct7 = new PdfPCell(rp);
			ct7.setBorderColor(BaseColor.BLUE);

			PdfPCell ct8 = new PdfPCell();
			ct8.setBorderColor(BaseColor.ORANGE);
			ct8.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			Phrase rp1 = new Phrase();
			rp1.setFont(regFont);
			rp1.add("within 10 days");

			PdfPCell ct9 = new PdfPCell(rp1);
			ct9.setBorderColor(BaseColor.BLUE);

			table3.addCell(ct7);
			table3.addCell(ct8);
			table3.addCell(ct9);

			document.add(table3);

			Paragraph prg = new Paragraph();
			prg.setFont(regFont);
			prg.add("if you Change Providers, Stop Working or Change Jobs, Stop Attending School or Training"
					+ ", Change Family Size, Change Income, Change Address, Stong Receiving TANF,"
					+ "Have Maternity/Medical Leave, or Have any Other Changes that may affect your eligibility.");
			Chunk chunkPara02_02 = new Chunk("Failure to do so in a timely manner may result in "
					+ "payback of over payments and/or loss of child care benefits. Also, report these changes to your DHS local office "
					+ "caseworker if you are receiving other benefits from DHS.");
			chunkPara02_02.setUnderline(0.5f, -1);
			prg.add(chunkPara02_02);

			PdfPTable table4 = new PdfPTable(1);
			table4.setWidthPercentage(100);

			PdfPCell cellt4 = new PdfPCell(prg);

			table4.addCell(cellt4);
			document.add(table4);

			PdfPTable table5 = new PdfPTable(1);
			table5.setWidthPercentage(100);

			Paragraph para03Text = new Paragraph();
			para03Text.setLeading(10);
			para03Text.setSpacingBefore(10);
			para03Text.setSpacingAfter(5);
			para03Text.setFont(boldFont);
			para03Text.add("PARENT CO-PAYMENT: ");
			para03Text.setFont(FontUtility.getNormalBody(6));
			para03Text.add(
					"The parent/guardian is required to help pay for the cost Of child care. This is called the parent co-payment. "
							+ "The parent co-payment is based on gross monthly income, family size, and number Of children receiving care. "
							+ "The provider is responsible for collecting the parent co-payment. The state will deduct the parent co-payment "
							+ "from the total charges up to the maximum child care rate. If the co-payment is more than the total charges, "
							+ "the parent pays the lesser amount to the provider and no payment is made by the state.");

			PdfPCell cellt5 = new PdfPCell(para03Text);

			table5.addCell(cellt5);

			document.add(table5);

			PdfPTable pdfTableParentCoPay = new PdfPTable(4);
			pdfTableParentCoPay.setWidthPercentage(100);
			pdfTableParentCoPay.setWidths(new int[] { 1, 1, 1, 1 });
			pdfTableParentCoPay.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell cellParentCoPay_01 = new PdfPCell(new Phrase("Parent Co-Payment", regFont));
			pdfTableParentCoPay.addCell(cellParentCoPay_01);

			PdfPCell cellParentCoPay_02 = new PdfPCell(new Phrase(" ", regFont));
			pdfTableParentCoPay.addCell(cellParentCoPay_02);

			PdfPCell cellParentCoPay_03 = new PdfPCell(new Phrase("Monthly Amount", regFont));
			pdfTableParentCoPay.addCell(cellParentCoPay_03);

			PdfPCell cellParentCoPay_04 = new PdfPCell(new Phrase(" ", regFont));
			pdfTableParentCoPay.addCell(cellParentCoPay_04);

			PdfPCell cellParentCoPay_05 = new PdfPCell(new Phrase(" ", regFont));
			cellParentCoPay_05.setColspan(2);
			cellParentCoPay_05.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			pdfTableParentCoPay.addCell(cellParentCoPay_05);

			Paragraph paraAmt = new Paragraph("$", regFont);
			PdfPCell cellParentCoPay_06 = new PdfPCell(paraAmt);
			cellParentCoPay_06.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfTableParentCoPay.addCell(cellParentCoPay_06);

			PdfPCell cellParentCoPay_07 = new PdfPCell(new Phrase(" ", regFont));
			cellParentCoPay_07.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			pdfTableParentCoPay.addCell(cellParentCoPay_07);

			document.add(pdfTableParentCoPay);

			Paragraph paraFamilySize = new Paragraph("FAMILY SIZE", FontUtility.getBoldHeader(10));
			paraFamilySize.setSpacingAfter(10);
			document.add(paraFamilySize);

			/************** Family Detail **************/
			PdfPTable pdfTableFamilyDtl = new PdfPTable(6);
			pdfTableFamilyDtl.setWidthPercentage(100);
			pdfTableFamilyDtl.setWidths(new int[] { 1, 1, 1, 1, 1, 1 });
			pdfTableFamilyDtl.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell cellFamilyDtl_01_02 = new PdfPCell(new Phrase("Eligible Children", boldFont));
			cellFamilyDtl_01_02.setColspan(2);
			cellFamilyDtl_01_02.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfTableFamilyDtl.addCell(cellFamilyDtl_01_02);

			PdfPCell cellFamilyDtl_03 = new PdfPCell(new Phrase("Date of Birth", boldFont));
			cellFamilyDtl_03.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfTableFamilyDtl.addCell(cellFamilyDtl_03);

			PdfPCell cellFamilyDtl_04 = new PdfPCell(new Phrase("Rate", boldFont));
			cellFamilyDtl_04.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfTableFamilyDtl.addCell(cellFamilyDtl_04);

			PdfPCell cellFamilyDtl_05 = new PdfPCell(new Phrase("Full/Part/SchAge", boldFont));
			cellFamilyDtl_05.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfTableFamilyDtl.addCell(cellFamilyDtl_05);

			PdfPCell cellFamilyDtl_06_011 = new PdfPCell(new Phrase("Weekly Eligible Days", boldFont));
			cellFamilyDtl_06_011.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfTableFamilyDtl.addCell(cellFamilyDtl_06_011);

			for (int i = 0; i < 5; i++) {
				PdfPCell cellFamilyDtlFirstName = new PdfPCell(new Phrase("First Name", regFont));
				cellFamilyDtlFirstName.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfTableFamilyDtl.addCell(cellFamilyDtlFirstName);

				PdfPCell cellFamilyDtlLastName = new PdfPCell(new Phrase("Last Name", regFont));
				cellFamilyDtlLastName.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfTableFamilyDtl.addCell(cellFamilyDtlLastName);

				PdfPCell cellFamilyDtlMergedCells = new PdfPCell(new Phrase("", boldFont));
				cellFamilyDtlMergedCells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellFamilyDtlMergedCells.setColspan(4);
				pdfTableFamilyDtl.addCell(cellFamilyDtlMergedCells);

				PdfPCell cellFamilyDtlInputCell1 = new PdfPCell(new Phrase("  ", boldFont));
				cellFamilyDtlInputCell1.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
				cellFamilyDtlInputCell1.setBorder(10);
				cellFamilyDtlInputCell1.setBorderColor(BaseColor.ORANGE);
				pdfTableFamilyDtl.addCell(cellFamilyDtlInputCell1);

				PdfPCell cellFamilyDtlInputCell2 = new PdfPCell();
				cellFamilyDtlInputCell2.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
				cellFamilyDtlInputCell2.setBorder(10);
				cellFamilyDtlInputCell2.setBorderColor(BaseColor.ORANGE);
				pdfTableFamilyDtl.addCell(cellFamilyDtlInputCell2);

				PdfPCell cellFamilyDtlInputCell3 = new PdfPCell();
				cellFamilyDtlInputCell3.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
				cellFamilyDtlInputCell3.setBorder(10);
				cellFamilyDtlInputCell3.setBorderColor(BaseColor.ORANGE);
				pdfTableFamilyDtl.addCell(cellFamilyDtlInputCell3);

				PdfPCell cellFamilyDtlInputCell4 = new PdfPCell();
				cellFamilyDtlInputCell4.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
				cellFamilyDtlInputCell4.setBorder(10);
				cellFamilyDtlInputCell4.setBorderColor(BaseColor.ORANGE);
				pdfTableFamilyDtl.addCell(cellFamilyDtlInputCell4);

				PdfPCell cellFamilyDtlInputCell5 = new PdfPCell();
				cellFamilyDtlInputCell5.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
				cellFamilyDtlInputCell5.setBorder(10);
				cellFamilyDtlInputCell5.setBorderColor(BaseColor.ORANGE);
				pdfTableFamilyDtl.addCell(cellFamilyDtlInputCell5);

				PdfPCell cellFamilyDtlInputCell6 = new PdfPCell();
				cellFamilyDtlInputCell6.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
				cellFamilyDtlInputCell6.setBorder(10);
				cellFamilyDtlInputCell6.setBorderColor(BaseColor.ORANGE);
				pdfTableFamilyDtl.addCell(cellFamilyDtlInputCell6);
			}

			document.add(pdfTableFamilyDtl);

			/** comment table **/
			PdfPTable commentTable = new PdfPTable(2);
			commentTable.setWidthPercentage(100);
			commentTable.setWidths(new int[] { 1, 7 });
			commentTable.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell cellCommentTitle = new PdfPCell(new Phrase("COMMENTS", boldFont));
			cellCommentTitle.setBorderColor(BaseColor.ORANGE);
			commentTable.addCell(cellCommentTitle);

			PdfPCell cellCommentInput = new PdfPCell();
			cellCommentInput.setCellEvent(new TextFieldForPdfCell("cellCommentInput"));
			cellCommentInput.setBorderColor(BaseColor.ORANGE);
			cellCommentInput.setFixedHeight(80);
			commentTable.addCell(cellCommentInput);

			document.add(commentTable);

			/** add bottom box with content **/
			PdfPTable bottomBoxTable = new PdfPTable(1);
			bottomBoxTable.setWidthPercentage(100);

			PdfPCell cellParent = new PdfPCell();
			cellParent.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPTable innerBoxTable = new PdfPTable(1);
			innerBoxTable.setWidthPercentage(100);

			PdfPCell cellChild_01 = new PdfPCell();
			Paragraph paraBottomBox_01 = new Paragraph(
					"Please keep this notice. We will need information from this form to help you if you call us.",
					regFont);
			paraBottomBox_01.setAlignment(Element.ALIGN_CENTER);
			cellChild_01.addElement(paraBottomBox_01);
			innerBoxTable.addCell(cellChild_01);

			PdfPCell cellChild_02 = new PdfPCell();
			Paragraph paraBottomBox_02 = new Paragraph("YOU HAVE THE RIGHT TO APPEAL THIS DECISION", boldFont);
			paraBottomBox_02.setAlignment(Element.ALIGN_CENTER);
			cellChild_02.addElement(paraBottomBox_02);
			innerBoxTable.addCell(cellChild_02);

			PdfPCell cellChild_03 = new PdfPCell();
			Paragraph paraBottomBox_03 = new Paragraph(
					"At any time within 60 days following the date of this notice you have the right to "
							+ "appeal this decision and be given a fair hearing. Such an appeal must be filed with the Department in writing "
							+ "or by calling (toll free) 1-800-435-0774. You may represent yourself at this hearing or you may be represented "
							+ "by anyone else, such as a lawyer, relative or friend. Your local office will provide you with an appeal from and will "
							+ "help you fill it out if you wish.",
					regFont);
			cellChild_03.addElement(paraBottomBox_03);
			innerBoxTable.addCell(cellChild_03);

			cellParent.addElement(innerBoxTable);

			bottomBoxTable.addCell(cellParent);
			document.add(bottomBoxTable);

			PdfPTable bfootPdfPTable = new PdfPTable(1);
			bfootPdfPTable.setWidthPercentage(100);

			Paragraph empty = new Paragraph("\n \n \n \n \n \n \n");

			bfootPdfPTable.addCell(empty);

			document.add(bfootPdfPTable);

			Image imgf = Image.getInstance("./src/resources/icons/footer.png");

			imgf.scaleToFit(600, 600);
			imgf.setAlignment(img.ALIGN_LEFT);

			Paragraph pImagesf = new Paragraph();
			pImages.add(imgf);

			PdfPTable footPdfPTable = new PdfPTable(1);
			footPdfPTable.setWidthPercentage(100);

			footPdfPTable.addCell(imgf);

			footPdfPTable.setHorizontalAlignment(img.ALIGN_LEFT);

			document.add(footPdfPTable);

			document.close();

			System.out.println("Pdf creation ............................END:" + new Date());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
