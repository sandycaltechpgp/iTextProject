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
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import demo.itextpoc.utility.FontUtility;
import demo.itextpoc.utility.PdfUtil;
import demo.itextpoc.utility.SelectCellEvent;
import demo.itextpoc.utility.TextFieldChunk;
import demo.itextpoc.utility.TextFieldForPdfCell;
import demo.itextpoc.utility.TextUtility;

public class GeneratePdf {

	public static void main(String[] args) {
		System.out.println("Pdf creation ............................START");
		try {
			String filePath = "./target/sandeeptemplate.pdf";
			File file = new File(filePath);
			file.getParentFile().mkdirs();

			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();

			// add logo
			String imageLogoFile = "./src/resources/icons/logo.png";
			Image logoImg = Image.getInstance(imageLogoFile);
			logoImg.scaleToFit(25, 25);

			Paragraph pImages = new Paragraph();
			pImages.add(logoImg);

			PdfPTable headerPdfPTable = new PdfPTable(3);
			headerPdfPTable.setWidthPercentage(100);
			headerPdfPTable.setWidths(new int[] { 1, 1, 10 });

			PdfPCell logoCell = new PdfPCell(logoImg);
			logoCell.setBorder(0);
			headerPdfPTable.addCell(logoCell);

			PdfPCell headerBlankCell = new PdfPCell();
			headerBlankCell.setBorder(0);
			headerPdfPTable.addCell(headerBlankCell);

			Paragraph pHeader = new Paragraph(
					"State of Illinois \nDepartment of Human Services - Bureau of Child Care and Development",
					FontUtility.getNormalHeader(9));
			PdfPCell headerTextCell = new PdfPCell(pHeader);
			headerTextCell.setBorder(0);
			headerPdfPTable.addCell(headerTextCell);

			PdfPCell emptyCell = PdfUtil.getBlankCell();
			emptyCell.setColspan(2);
			emptyCell.setBorder(0);
			headerPdfPTable.addCell(emptyCell);

			Paragraph pHeaderCaps = new Paragraph("APPROVAL OF REQUEST FOR CHILD CARE PAYMENT.",
					FontUtility.getBoldHeader(9));
			PdfPCell capsHeaderTextCell = new PdfPCell(pHeaderCaps);
			capsHeaderTextCell.setBorder(0);
			headerPdfPTable.addCell(capsHeaderTextCell);

			document.add(headerPdfPTable);

			/************** add date and other info to right **************/
			PdfPTable pdfTableContentRight = new PdfPTable(2);
			pdfTableContentRight.setWidthPercentage(40);
			pdfTableContentRight.setWidths(new int[] { 1, 1 });
			pdfTableContentRight.setHorizontalAlignment(Element.ALIGN_RIGHT);

			// add empty row
			emptyCell.addElement(new Phrase(" "));
			emptyCell.setBorder(0);
			pdfTableContentRight.addCell(emptyCell);

			PdfPCell cellDateTitle = new PdfPCell(new Phrase("Date of notice:", FontUtility.getNormalHeader(8)));
			cellDateTitle.setBorder(0);
			pdfTableContentRight.addCell(cellDateTitle);
			PdfPCell cellDateInput = new PdfPCell();
			cellDateInput.setBorder(0);
			cellDateInput.setCellEvent(new TextFieldForPdfCell("cellDateInput"));
			pdfTableContentRight.addCell(cellDateInput);

			PdfPCell cellCascadeTitle = new PdfPCell(new Phrase("Caseload  Code:", FontUtility.getNormalHeader(8)));
			cellCascadeTitle.setBorder(0);
			pdfTableContentRight.addCell(cellCascadeTitle);
			PdfPCell cellCascadeInput = new PdfPCell();
			cellCascadeInput.setBorder(0);
			cellCascadeInput.setCellEvent(new TextFieldForPdfCell("cellCascadeInput"));
			pdfTableContentRight.addCell(cellCascadeInput);

			PdfPCell cellCaseNumTitle = new PdfPCell(new Phrase("Child Care Case #:", FontUtility.getNormalHeader(8)));
			cellCaseNumTitle.setBorder(0);
			pdfTableContentRight.addCell(cellCaseNumTitle);
			PdfPCell cellCaseNumInput = new PdfPCell();
			cellCaseNumInput.setBorder(0);
			cellCaseNumInput.setCellEvent(new TextFieldForPdfCell("cellCaseNumInput"));
			pdfTableContentRight.addCell(cellCaseNumInput);

			PdfPCell cellIvrCaseNumTitle = new PdfPCell(new Phrase("IVR Case #:", FontUtility.getNormalHeader(8)));
			cellIvrCaseNumTitle.setBorder(0);
			pdfTableContentRight.addCell(cellIvrCaseNumTitle);
			PdfPCell cellIvrCaseNumInput = new PdfPCell();
			cellIvrCaseNumInput.setBorder(0);
			cellIvrCaseNumInput.setCellEvent(new TextFieldForPdfCell("cellIvrCaseNumInput"));
			pdfTableContentRight.addCell(cellIvrCaseNumInput);

			PdfPCell cellSelectProvdTitle = new PdfPCell();
			Phrase provdTitlePhrase = TextUtility.getMandatoryStar();
			provdTitlePhrase.add(new Phrase("Select Provider:", FontUtility.getNormalHeader(8)));
			cellSelectProvdTitle.addElement(provdTitlePhrase);
			cellSelectProvdTitle.setBorder(0);
			pdfTableContentRight.addCell(cellSelectProvdTitle);

			PdfFormField selectGroup = PdfFormField.createEmpty(writer);
			selectGroup.setFieldName("provdGroup");
			String[] options = { "", "Choose option 1", "Choose option 2", "Choose option 3" };
			String[] exports = { "N/A", "option1", "option2", "option3" };
			PdfPCell cellSelectProvdInput = new PdfPCell();
			cellSelectProvdInput.setBorder(0);
			cellSelectProvdInput.setCellEvent(new SelectCellEvent(selectGroup, "provdInput", exports, options));
			pdfTableContentRight.addCell(cellSelectProvdInput);

			document.add(pdfTableContentRight);
			writer.addAnnotation(selectGroup);

			/******** Table before body of the mail document ***********/
			PdfPTable tableContentSubject = new PdfPTable(4);
			tableContentSubject.setWidthPercentage(100);
			tableContentSubject.setWidths(new int[] { 1, 1, 1, 1 });

			PdfPCell cellChildCareReasonTitle = new PdfPCell(
					new Phrase("Reason for Child Care:", FontUtility.getNormalHeader(8)));
			cellChildCareReasonTitle.setBorder(0);
			tableContentSubject.addCell(cellChildCareReasonTitle);
			PdfPCell cellChildCareReasonInput = new PdfPCell();
			cellChildCareReasonInput.setBorder(0);
			cellChildCareReasonInput.setCellEvent(new TextFieldForPdfCell("cellChildCareReasonInput"));
			tableContentSubject.addCell(cellChildCareReasonInput);

			PdfPCell blankTitle = new PdfPCell(new Phrase(" "));
			blankTitle.setColspan(2);
			blankTitle.setBorder(0);
			tableContentSubject.addCell(blankTitle);

			PdfPCell cellGuardianTitle = new PdfPCell(
					new Phrase("Reason for Child Care:", FontUtility.getNormalHeader(8)));
			cellGuardianTitle.setBorder(0);
			tableContentSubject.addCell(cellGuardianTitle);
			PdfPCell cellGuardianInput = new PdfPCell();
			cellGuardianInput.setBorder(0);
			cellGuardianInput.setCellEvent(new TextFieldForPdfCell("cellGuardianInput"));
			tableContentSubject.addCell(cellGuardianInput);

			PdfPCell cellPrvdAddrTitle = new PdfPCell(new Phrase("Provider Address:", FontUtility.getNormalHeader(8)));
			cellPrvdAddrTitle.setBorder(0);
			tableContentSubject.addCell(cellPrvdAddrTitle);
			PdfPCell cellPrvdAddrInput = new PdfPCell();
			cellPrvdAddrInput.setBorder(0);
			cellPrvdAddrInput.setCellEvent(new TextFieldForPdfCell("cellPrvdAddrInput"));
			tableContentSubject.addCell(cellPrvdAddrInput);

			emptyCell = new PdfPCell(new Phrase(" "));
			emptyCell.setColspan(4);
			emptyCell.setBorder(0);
			tableContentSubject.addCell(emptyCell);

			document.add(tableContentSubject);

			/********* Add table body **********/
			Paragraph para01Text = new Paragraph("Your eligibility for child care has been approved beginning ",
					FontUtility.getNormalBody(8));
			// para01Text.setLeading(10);
			writer.setPageEvent(new TextFieldChunk());
			Chunk beginDate = new Chunk("     ");
			beginDate.setGenericTag("beginDate");
			para01Text.add(beginDate);

			para01Text.add(" through ");

			Chunk endDate = new Chunk("     ");
			endDate.setGenericTag("endDate");
			para01Text.add(endDate);

			para01Text.add(
					" or until there is a change in eligibility, whichever is sooner. We cannot pay for child care provided before the date "
							+ "you were approved. Eligibility will be redetermined near the end of your eligibility period, but "
							+ "may be redetermined sooner if there is a change in income, family size, or at the Department's direction");
			document.add(para01Text);

			Paragraph para02Text = new Paragraph();
			para02Text.setLeading(10);
			para02Text.setSpacingBefore(10);
			para02Text.setSpacingAfter(5);

			para02Text.setFont(FontUtility.getBoldBody(8));
			para02Text.add("REPORTING CHANGES:");
			para02Text.setFont(FontUtility.getNormalBody(8));
			para02Text.add("our are responsible for notifying");
			// writer.setPageEvent(new TextFieldChunk());
			Chunk notifyTo = new Chunk("     									");
			notifyTo.setGenericTag("notifyTo");
			para02Text.add(notifyTo);
			Chunk chunkPara02_01 = new Chunk("within 10 days ");
			chunkPara02_01.setUnderline(0.5f, -1);
			para02Text.add(chunkPara02_01);

			para02Text.add("if you Change Providers, Stop Working or Change Jobs, Stop Attending School or "
					+ "Training, Change Family Size, Change Income, Change Address, Stop Receiving TANF, Have Maternity/Medical Leave, "
					+ "or Have Any Other Changes that may affect your eligibility. ");

			Chunk chunkPara02_02 = new Chunk("Failure to do so in a timely manner may result in "
					+ "payback of over payments and/or loss of child care benefits. Also, report these changes to your DHS local office "
					+ "caseworker if you are receiving other benefits from DHS.");
			chunkPara02_02.setUnderline(0.5f, -1);
			para02Text.add(chunkPara02_02);
			document.add(para02Text);

			Paragraph para03Text = new Paragraph();
			para03Text.setLeading(10);
			para03Text.setSpacingBefore(10);
			para03Text.setSpacingAfter(5);
			para03Text.setFont(FontUtility.getBoldBody(8));
			para03Text.add("PARENT CO-PAYMENT:");
			para03Text.setFont(FontUtility.getNormalBody(8));
			para03Text.add(
					"The parent/guardian is required to help pay for the cost Of child care. This is called the parent co-payment. "
							+ "The parent co-payment is based on gross monthly income, family size, and number Of children receiving care. "
							+ "The provider is responsible for collecting the parent co-payment. The state will deduct the parent co-payment "
							+ "from the total charges up to the maximum child care rate. If the co-payment is more than the total charges, "
							+ "the parent pays the lesser amount to the provider and no payment is made by the state.");

			document.add(para03Text);

			/************** add parent **************/
			PdfPTable pdfTableParentCoPay = new PdfPTable(4);
			pdfTableParentCoPay.setWidthPercentage(80);
			pdfTableParentCoPay.setWidths(new int[] { 1, 1, 1, 1 });
			pdfTableParentCoPay.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell cellParentCoPay_01 = new PdfPCell(new Phrase("Parent Co-Payment", FontUtility.getNormalHeader(8)));
			cellParentCoPay_01.setBorder(0);
			pdfTableParentCoPay.addCell(cellParentCoPay_01);

			PdfPCell cellParentCoPay_02 = new PdfPCell(new Phrase(" ", FontUtility.getNormalHeader(8)));
			cellParentCoPay_02.setBorder(0);
			pdfTableParentCoPay.addCell(cellParentCoPay_02);

			PdfPCell cellParentCoPay_03 = new PdfPCell(new Phrase("Monthly Amount", FontUtility.getNormalHeader(8)));
			cellParentCoPay_03.setBorder(0);
			pdfTableParentCoPay.addCell(cellParentCoPay_03);

			PdfPCell cellParentCoPay_04 = new PdfPCell(new Phrase(" ", FontUtility.getNormalHeader(8)));
			cellParentCoPay_04.setBorder(0);
			pdfTableParentCoPay.addCell(cellParentCoPay_04);

			PdfPCell cellParentCoPay_05 = new PdfPCell(new Phrase(" ", FontUtility.getNormalHeader(8)));
			cellParentCoPay_05.setBorder(0);
			cellParentCoPay_05.setColspan(2);
			pdfTableParentCoPay.addCell(cellParentCoPay_05);

			Paragraph paraAmt = new Paragraph("$", FontUtility.getNormalHeader(8));
			PdfPCell cellParentCoPay_06 = new PdfPCell(paraAmt);
			cellParentCoPay_06.setBorder(0);
			cellParentCoPay_06.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfTableParentCoPay.addCell(cellParentCoPay_06);

			PdfPCell cellParentCoPay_07 = new PdfPCell(new Phrase(" ", FontUtility.getNormalHeader(8)));
			cellParentCoPay_07.setBorder(0);
			pdfTableParentCoPay.addCell(cellParentCoPay_07);

			document.add(pdfTableParentCoPay);

			Paragraph paraFamilySize = new Paragraph("FAMILY SIZE", FontUtility.getBoldHeader(10));
			paraFamilySize.setSpacingAfter(10);
			document.add(paraFamilySize);

			/************** Family Detail **************/
			PdfPTable pdfTableFamilyDtl = new PdfPTable(11);
			pdfTableFamilyDtl.setWidthPercentage(100);
			pdfTableFamilyDtl.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 });
			pdfTableFamilyDtl.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell cellFamilyDtl_01_02 = new PdfPCell(new Phrase("Eligible Children", FontUtility.getBoldHeader(8)));
			cellFamilyDtl_01_02.setColspan(2);
			cellFamilyDtl_01_02.setBorder(0);
			cellFamilyDtl_01_02.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfTableFamilyDtl.addCell(cellFamilyDtl_01_02);

			PdfPCell cellFamilyDtl_03 = new PdfPCell(new Phrase("Date of Birth", FontUtility.getBoldHeader(8)));
			cellFamilyDtl_03.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellFamilyDtl_03.setBorder(0);
			pdfTableFamilyDtl.addCell(cellFamilyDtl_03);

			PdfPCell cellFamilyDtl_04 = new PdfPCell(new Phrase("Rate", FontUtility.getBoldHeader(8)));
			cellFamilyDtl_04.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellFamilyDtl_04.setBorder(0);
			pdfTableFamilyDtl.addCell(cellFamilyDtl_04);

			PdfPCell cellFamilyDtl_05 = new PdfPCell(new Phrase("Full/Part/SchAge", FontUtility.getBoldHeader(8)));
			cellFamilyDtl_05.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellFamilyDtl_05.setBorder(0);
			pdfTableFamilyDtl.addCell(cellFamilyDtl_05);

			PdfPCell cellFamilyDtl_06_011 = new PdfPCell(
					new Phrase("Weekly Eligible Days", FontUtility.getBoldHeader(8)));
			cellFamilyDtl_06_011.setBorder(0);
			cellFamilyDtl_06_011.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellFamilyDtl_06_011.setColspan(6);
			pdfTableFamilyDtl.addCell(cellFamilyDtl_06_011);

			for (int i = 0; i < 8; i++) {
				if (i % 2 == 0) {
					PdfPCell cellFamilyDtlFirstName = new PdfPCell(
							new Phrase("First Name", FontUtility.getBoldHeader(8)));
					cellFamilyDtlFirstName.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellFamilyDtlFirstName.setBorder(0);
					pdfTableFamilyDtl.addCell(cellFamilyDtlFirstName);

					PdfPCell cellFamilyDtlLastName = new PdfPCell(
							new Phrase("Last Name", FontUtility.getBoldHeader(8)));
					cellFamilyDtlLastName.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellFamilyDtlLastName.setBorder(0);
					pdfTableFamilyDtl.addCell(cellFamilyDtlLastName);

					PdfPCell cellFamilyDtlMergedCells = new PdfPCell(new Phrase("", FontUtility.getBoldHeader(8)));
					cellFamilyDtlMergedCells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellFamilyDtlMergedCells.setColspan(9);
					cellFamilyDtlMergedCells.setBorder(0);
					pdfTableFamilyDtl.addCell(cellFamilyDtlMergedCells);
				} else {
					for (int j = 0; j <= 10; j++) {

						Font font = new Font(Font.getFamily("COURIER"), 8, Font.NORMAL | Font.UNDERLINE);
						Paragraph p = new Paragraph("        ", font);
						PdfPCell cellFamilyDtlInputCell = new PdfPCell(p);
						cellFamilyDtlInputCell.setPaddingLeft(5);
						cellFamilyDtlInputCell.setBorderWidthTop(1f);
						cellFamilyDtlInputCell.setBorderColorTop(BaseColor.WHITE);
						cellFamilyDtlInputCell.setBorderColorRight(BaseColor.WHITE);
						cellFamilyDtlInputCell.setBorderColorBottom(BaseColor.BLACK);
						cellFamilyDtlInputCell.setBorderColorLeft(BaseColor.WHITE);
						pdfTableFamilyDtl.addCell(cellFamilyDtlInputCell);
					}
				}
			}
			document.add(pdfTableFamilyDtl);

			/** comment table **/
			PdfPTable commentTable = new PdfPTable(2);
			commentTable.setWidthPercentage(100);
			commentTable.setSpacingBefore(15);
			commentTable.setSpacingAfter(15);
			commentTable.setWidths(new int[] { 1, 7 });
			commentTable.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell cellCommentTitle = new PdfPCell(new Phrase("COMMENTS", FontUtility.getBoldHeader(8)));
			cellCommentTitle.setBorder(0);
			commentTable.addCell(cellCommentTitle);

			PdfPCell cellCommentInput = new PdfPCell();
			cellCommentInput.setBorder(0);
			cellCommentInput.setCellEvent(new TextFieldForPdfCell("cellCommentInput"));
			cellCommentInput.setFixedHeight(20);
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
			Phrase paraBottomBox_01 = new Phrase(
					"Please keep this notice. We will need information from this form to help you if you call us.",
					FontUtility.getNormalBody(8));
			cellChild_01.setBorder(0);
			cellChild_01.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellChild_01.addElement(paraBottomBox_01);
			innerBoxTable.addCell(cellChild_01);

			PdfPCell cellChild_02 = new PdfPCell();
			Phrase paraBottomBox_02 = new Phrase("YOU HAVE THE RIGHT TO APPEAL THIS DECISION",
					FontUtility.getBoldBody(8));
			cellChild_02.setBorder(0);
			cellChild_02.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellChild_02.addElement(paraBottomBox_02);
			innerBoxTable.addCell(cellChild_02);

			PdfPCell cellChild_03 = new PdfPCell();
			Paragraph paraBottomBox_03 = new Paragraph(
					"At any time within 60 days following the date of this notice you have the right to "
							+ "appeal this decision and be given a fair hearing. Such an appeal must be filed with the Department in writing "
							+ "or by calling (toll free) 1-800-435-0774. You may represent yourself at this hearing or you may be represented "
							+ "by anyone else, such as a lawyer, relative or friend. Your local office will provide you with an appeal from and will "
							+ "help you fill it out if you wish.",
					FontUtility.getNormalBody(8));
			cellChild_03.setBorder(0);
			cellChild_03.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellChild_03.addElement(paraBottomBox_03);
			innerBoxTable.addCell(cellChild_03);

			cellParent.addElement(innerBoxTable);

			bottomBoxTable.addCell(cellParent);
			document.add(bottomBoxTable);

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
