package demo.itextpoc.pdfgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import demo.itextpoc.utility.FontUtility;
import demo.itextpoc.utility.GetCellInputName;
import demo.itextpoc.utility.TextFieldForPdfCell;

public class GeneratePdfFirst2 {

	public static void main(String[] args) {
		System.out.println("Pdf creation ............................START");
		try {

			GetCellInputName inpName = new GetCellInputName();

			String filePath = "./target/secpdf.pdf";
			File file = new File(filePath);
			file.getParentFile().mkdirs();

			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

			Font boldFont = FontUtility.getBoldBody(8);
			Font regFont = FontUtility.getNormalBody(6);

			document.open();

			Image img = Image.getInstance("./src/resources/icons/secheader.png");

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
			mainPdfPTable.setWidths(new int[] { 50, 50 });

			PdfPTable cell1 = new PdfPTable(1);

			PdfPTable cell2 = new PdfPTable(2);
			cell2.setWidths(new int[] { 60, 40 });

			Paragraph p1 = new Paragraph();
			p1.setFont(boldFont);
			p1.add("Parent/Gaurdian Name:");

			Paragraph p2 = new Paragraph();
			p2.setFont(boldFont);
			p2.add("DATE OF NOTICE:");

			PdfPCell cell21 = new PdfPCell(p1);
			cell21.setBorder(0);

			PdfPTable cell3 = new PdfPTable(2);
			cell3.setWidths(new int[] { 50, 50 });
			cell3.setWidthPercentage(100);

			PdfPTable celll = new PdfPTable(2);
			celll.setWidths(new int[] { 40, 60 });

			PdfPCell cell31 = new PdfPCell(p2);
			cell31.setBorderColor(BaseColor.ORANGE);

			PdfPCell cell32 = new PdfPCell();
			cell32.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			cell32.setBorderColor(BaseColor.ORANGE);

			PdfPCell mtCell = new PdfPCell(new Paragraph("\n"));
			mtCell.setBorder(0);

			cell1.addCell(mtCell);
			cell1.addCell(mtCell);
			cell1.addCell(mtCell);
			cell1.addCell(mtCell);

			cell2.addCell(cell21);
			cell2.addCell(mtCell);
			cell2.addCell(mtCell);
			cell2.addCell(mtCell);

			celll.addCell(cell31);
			celll.addCell(cell32);

			cell3.addCell(mtCell);
			cell3.addCell(celll);

			mainPdfPTable.addCell(cell1);
			mainPdfPTable.addCell(cell2);

			document.add(mainPdfPTable);
			document.add(cell3);

			PdfPTable clientTable = new PdfPTable(2);
			clientTable.setWidths(new int[] { 50, 50 });
			clientTable.setWidthPercentage(100);

			Paragraph pp = new Paragraph();
			pp.setFont(boldFont);
			pp.add("CLIENT :");

			PdfPCell ccel = new PdfPCell(pp);
			ccel.setBorderColor(BaseColor.ORANGE);

			PdfPCell mCell = new PdfPCell(new Paragraph("\n"));
			mCell.setBorderColor(BaseColor.ORANGE);
			mCell.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell mCell1 = new PdfPCell(new Paragraph("\n"));
			mCell1.setBorderColor(BaseColor.ORANGE);
			mCell1.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell mCell2 = new PdfPCell(new Paragraph("\n"));
			mCell2.setBorderColor(BaseColor.ORANGE);
			mCell2.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell mCell3 = new PdfPCell(new Paragraph("\n"));
			mCell3.setBorderColor(BaseColor.ORANGE);
			mCell3.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell mCell4 = new PdfPCell(new Paragraph("\n"));
			mCell4.setBorderColor(BaseColor.ORANGE);
			mCell4.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell mCell5 = new PdfPCell(new Paragraph("\n"));
			mCell5.setBorderColor(BaseColor.ORANGE);
			mCell5.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell mCell6 = new PdfPCell(new Paragraph("\n"));
			mCell6.setBorderColor(BaseColor.ORANGE);
			mCell6.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			clientTable.addCell(ccel);
			clientTable.addCell(mCell);

			document.add(clientTable);

			PdfPTable bclientTable = new PdfPTable(2);
			bclientTable.setWidths(new int[] { 50, 50 });
			bclientTable.setWidthPercentage(100);

			bclientTable.addCell(mCell1);
			bclientTable.addCell(mCell2);
			bclientTable.addCell(mCell3);
			bclientTable.addCell(mCell4);
			bclientTable.addCell(mCell5);
			bclientTable.addCell(mCell6);

			document.add(bclientTable);

			PdfPTable keepTable = new PdfPTable(1);
			keepTable.setWidthPercentage(100);

			Paragraph kp = new Paragraph();
			kp.setFont(FontUtility.getBoldBody(15));
			kp.add("KEEP FOR YOUR RECORDS");

			PdfPCell kpCell = new PdfPCell(kp);
			kpCell.setHorizontalAlignment(Element.ALIGN_CENTER);

			keepTable.addCell(kpCell);

			document.add(keepTable);

			/*
			 * The State of Illinois helps income eligible families pay for their child care
			 * services while they work or go to school, training and other work- related
			 * activities. To apply please read the following pages carefully and then
			 * submit your completed Redetermination to your local Child Care Resource and
			 * Referral (CCR&R) or child care center/home if they have a contract with IDHS
			 * to provide child care assistance. If you have any questions about your
			 * eligibility or if you need help completi9ng this form, call your local CCR&R.
			 * To find your local CCR&R go to
			 * http://www.inccrra.org/find-your-local-ccrr-other call 1-877-202-4453 (toll
			 * -free).
			 */

			PdfPTable tTable = new PdfPTable(1);
			tTable.setWidthPercentage(100);

			String ttext = "The State of Illinois helps income eligible families pay for their child care services while they work or go to school, training and other work-\r\n"
					+ "related activities. To apply please read the following pages carefully and then submit your completed Redetermination to your local Child Care\r\n"
					+ "Resource and Referral (CCR&R) or child care center/home if they have a contract with IDHS to provide child care assistance. If you have any \r\n"
					+ "questions about your eligibility or if you need help  completi9ng  this form, call your local CCR&R. To find your local CCR&R go to \r\n"
					+ "http://www.inccrra.org/find-your-local-ccrr-other call 1-877-202-4453 (toll -free).";

			Paragraph kpt = new Paragraph();
			kpt.setFont(regFont);
			kpt.add(ttext);

			PdfPCell kptCell = new PdfPCell(kpt);
			kptCell.setHorizontalAlignment(Element.ALIGN_LEFT);

			tTable.addCell(kptCell);

			document.add(tTable);

			/*
			 * 
			 * Please be sure that all of the information is complete before sending in your
			 * Redetermination:
			 */

			PdfPTable tTable1 = new PdfPTable(1);
			tTable1.setWidthPercentage(100);

			String ttext1 = "Please be sure that all of the information is complete before sending in your Redetermination:";

			Paragraph kpt1 = new Paragraph();
			kpt1.setFont(regFont);
			kpt1.add(ttext1);

			PdfPCell kptCell1 = new PdfPCell(kpt1);
			kptCell1.setHorizontalAlignment(Element.ALIGN_LEFT);

			tTable1.addCell(kptCell1);

			document.add(tTable1);

			/*
			 * 
			 * The Redetermination is filled out clearly in blue or black ink. All questions
			 * on the Redetermination are complete. If the section or question does not
			 * apply, write "n/a" in the box to show that the question was not missed. This
			 * information is for your current job/education activity. You will inform the
			 * CCR&R or Site provider if any information changes in the future. ' The
			 * parent/guardian's name is listed at the top of each page of the
			 * Redetermination. Both you and the other parent/adult have signed the
			 * Redetermination (page 12). All social security numbers are listed clearly or
			 * "n/a" is listed in the box. Social security numbers are not required for
			 * parents or children but they are used to gather information to help determine
			 * your eligibility for child care assistance. All information is confidential
			 * and will not be shared with anyone else. All Family Information is complete
			 * in Section 3 (page 7) including information about your children's immigration
			 * status. Children can get assistance regardless of their immigration status,
			 * but IDHS is required to ask for this information. This information will not
			 * be shared with anyone. Your child's alien registration number must be listed
			 * if they have one. All persons living in your household are listed in Section
			 * 3 (page 7). If working, at least one of the following is attached to verify
			 * your employment and the employment of everyone listed in your family size
			 * that is 19 years of age or older:
			 * 
			 */

			PdfPTable tTable2 = new PdfPTable(1);
			tTable2.setWidthPercentage(100);

			String ttext2 = "* The Redetermination is filled out clearly in blue or black ink.\r\n"
					+ "* All questions on the Redetermination are complete. If the section or question does not apply, write \"n/a\" in the box to show that the question was not missed.\r\n"
					+ "* This information is for your current job/education activity. You will inform the CCR&R or Site provider if any information changes in the future.\r\n"
					+ "* The parent/guardian's name is listed at the top of each page of the Redetermination.\r\n"
					+ "* Both you and the other parent/adult have signed the Redetermination (page 12).\r\n"
					+ "* All social security numbers are listed clearly or \"n/a\" is listed in the box. Social security numbers are not required for parents or children but they are used to gather information to help determine your eligibility for child care assistance. All information is confidential and will not be shared with anyone else.\r\n"
					+ "* All Family Information is complete in Section 3 (page 7) including information about your children's immigration status. Children can get\r\n"
					+ "assistance regardless of their immigration status, but IDHS is required to ask for this information. This information will not be shared with anyone. Your child's alien registration number must be listed if they have one.\r\n"
					+ "* All persons living in your household are listed in Section 3 (page 7).\r\n"
					+ "* If working, at least one of the following is attached to verify your employment and the employment of everyone listed in your family size that\r\n"
					+ "is 19 years of age or older:";

			Paragraph kpt2 = new Paragraph();
			kpt2.setFont(regFont);
			kpt2.add(ttext2);

			PdfPCell kptCell2 = new PdfPCell(kpt2);
			kptCell2.setHorizontalAlignment(Element.ALIGN_LEFT);

			tTable2.addCell(kptCell2);

			document.add(tTable2);

			/*
			 ** Copies ofyour last (2) paycheck stubs, or if you have not been working long
			 * enough to get two paychecks: -- A letter from your employer or an employment
			 * verification form listing the following: • The date you started working. •
			 * The amount of money you are paid. • Your typical work schedule, including the
			 * total number of hours you work per week. • Your employer's address and phone
			 * number. • Your employer's signature, or Verification of your self-employment.
			 * This can include: — A copy of your most recent Federal income tax return (IRS
			 * 1040) and all schedules and attachments. — A copy of your quarterly estimated
			 * taxes. — A listing of all business income and expenses for the last 30 days.
			 * This can be reported on your own form or on a
			 * 
			 */

			PdfPTable tTable3 = new PdfPTable(1);
			tTable3.setWidthPercentage(100);

			String ttext3 = "** Copies ofyour last (3) paycheck stubs, or if you have not been working long enough to get two paychecks:\r\n"
					+ "-- A letter from your employer or an employment verification form listing the following:\r\n"
					+ "• The date you started working.\r\n" + "• The amount of money you are paid.\r\n"
					+ "• Your typical work schedule, including the total number of hours you work per week.\r\n"
					+ "• Your employer's address and phone number.\r\n" + "• Your employer's signature, or\r\n"
					+ "** Verification of your self-employment. This can include:\r\n"
					+ "— A copy of your most recent Federal income tax return (IRS 1040) and all schedules and attachments.\r\n"
					+ "— A copy of your quarterly estimated taxes.\r\n"
					+ "— A listing of all business income and expenses for the last 30 days. This can be reported on your own form or on a";

			Paragraph kpt3 = new Paragraph();
			kpt3.setFont(regFont);
			kpt3.add(ttext3);

			PdfPCell kptCell3 = new PdfPCell(kpt3);
			kptCell3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

			tTable3.addCell(kptCell3);

			document.add(tTable3);

			PdfPTable tTable4 = new PdfPTable(1);
			tTable4.setWidthPercentage(100);

			String ttext4 = "on a Self -Employment form which can be downloaded at"
					+ "  http://www.dhs.state.il.us/OneNetLibrary/27897/documents/Forms/IL444-2790.pdf";

			Paragraph kpt4 = new Paragraph();
			kpt4.setFont(regFont);
			kpt4.add(ttext4);

			PdfPCell kptCell4 = new PdfPCell(kpt4);
			kptCell4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

			tTable4.addCell(kptCell4);

			document.add(tTable4);

			PdfPTable tTable5 = new PdfPTable(1);
			tTable5.setWidthPercentage(100);

			String ttext5 = "or requested from your local CCR&R. When reporting income and expenses, receipts, invoices, or other documentation must be attached to verify\r\n"
					+ "all information.";

			Paragraph kpt5 = new Paragraph();
			kpt5.setFont(regFont);
			kpt5.add(ttext5);

			PdfPCell kptCell5 = new PdfPCell(kpt5);
			kptCell5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

			tTable5.addCell(kptCell5);

			document.add(tTable5);

			PdfPTable tTable6 = new PdfPTable(1);
			tTable6.setWidthPercentage(100);

			String ttext6 = "* If in school \r\n" + "ALL of the following are attached:\r\n"
					+ "** Copies of your official school schedule.\r\n"
					+ "** Copies of your most recent report card showing your cumulative grade point average (GPA).\r\n"
					+ "* You have made a copy of your Redetermination for your records. You understand if you send original check stubs or other documents that they\r\n"
					+ "will not be returned.\r\n"
					+ "* All jobs and income information for BOTH parents have been reported on pages 3 through 6 and documentation is attached.\r\n"
					+ "* You understand that if any questions are left blank or if any attachments are missing, your redetermination form will be returned to you as\r\n"
					+ "incomplete. This may cause a delay in approval for Child Care Assistance Program payments.\r\n"
					+ "* You also understand that all of the information you submit will be verified using State and/or local databases and the intemet. If any\r\n"
					+ "inconsistencies are discovered, your redetermination may be delayed or your participation in the Child Care\r\n"
					+ "Assistance Program\r\n" + "may be cancelled.";

			Paragraph kpt6 = new Paragraph();
			kpt6.setFont(regFont);
			kpt6.add(ttext6);

			PdfPCell kptCell6 = new PdfPCell(kpt6);
			kptCell6.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

			tTable6.addCell(kptCell6);

			document.add(tTable6);

			PdfPTable bfootPdfPTable = new PdfPTable(1);
			bfootPdfPTable.setWidthPercentage(100);

			Paragraph empty = new Paragraph("\n \n \n \n \n \n \n");

			bfootPdfPTable.addCell(empty);

			document.add(bfootPdfPTable);

			Image imgf = Image.getInstance("./src/resources/icons/secfooter.png");

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
