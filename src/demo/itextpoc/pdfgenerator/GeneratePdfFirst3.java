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

import demo.itextpoc.utility.CheckboxCellEvent;
import demo.itextpoc.utility.FontUtility;
import demo.itextpoc.utility.GetCellInputName;
import demo.itextpoc.utility.TextFieldForPdfCell;
import demo.itextpoc.utility.WeekGenerator;

public class GeneratePdfFirst3 {

	public static void main(String[] args) throws Exception {
		System.out.println("Pdf creation ............................START");
		try {

			GetCellInputName inpName = new GetCellInputName();

			String filePath = "./target/thirdpdf.pdf";
			File file = new File(filePath);
			file.getParentFile().mkdirs();

			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

			Font boldFont = FontUtility.getBoldBody(8);
			Font regFont = FontUtility.getNormalBody(6);

			document.open();

			PdfPCell mtCell = new PdfPCell(new Paragraph("\n"));
			mtCell.setBorder(0);

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

			PdfPTable mainPdfPTable = new PdfPTable(3);

			mainPdfPTable.setWidthPercentage(100);
			mainPdfPTable.setWidths(new int[] { 45, 5, 50 });

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

			Paragraph pp1 = new Paragraph();
			pp1.setFont(boldFont);
			pp1.add("Return your completed Redetermination to :");

			PdfPCell cell2p = new PdfPCell(pp1);
			cell2p.setBorder(0);
			cell2p.setColspan(2);

			cell1.addCell(mtCell);
			cell1.addCell(mtCell);
			cell1.addCell(mtCell);

			cell2.addCell(cell21);
			cell2.addCell(mtCell);
			cell2.addCell(mtCell);

			celll.addCell(cell31);
			celll.addCell(cell32);
			celll.addCell(cell2p);

			cell3.addCell(mtCell);
			cell3.addCell(celll);

			mainPdfPTable.addCell(cell1);
			mainPdfPTable.addCell(mtCell);
			mainPdfPTable.addCell(cell2);

			document.add(mainPdfPTable);
			document.add(cell3);

			PdfPTable clientTable = new PdfPTable(3);
			clientTable.setWidths(new int[] { 45, 5, 50 });
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

			clientTable.addCell(ccel);
			clientTable.addCell(mtCell);
			clientTable.addCell(mCell);

			document.add(clientTable);

			PdfPTable bclientTable = new PdfPTable(3);
			bclientTable.setWidths(new int[] { 45, 5, 50 });
			bclientTable.setWidthPercentage(100);

			PdfPTable rfc = new PdfPTable(2);
			rfc.setWidths(new int[] { 50, 50 });

			bclientTable.addCell(mCell1);
			bclientTable.addCell(mtCell);

			Paragraph rfcp = new Paragraph();
			rfcp.setFont(boldFont);
			rfcp.add("Reason for child care:");

			PdfPCell rfcpc = new PdfPCell(rfcp);

			rfc.addCell(rfcpc);
			rfc.addCell(mCell2);

			bclientTable.addCell(rfc);

			Paragraph rfcpp = new Paragraph();
			rfcpp.setFont(boldFont);
			rfcpp.add("Provider(s):");

			PdfPCell rfcpcp = new PdfPCell(rfcpp);
			rfcpcp.setColspan(2);

			bclientTable.addCell(mCell5);
			bclientTable.addCell(mtCell);

			bclientTable.addCell(rfcpcp);

			document.add(bclientTable);

			PdfPTable bpt = new PdfPTable(3);
			bpt.setWidths(new int[] { 45, 5, 50 });
			bpt.setWidthPercentage(100);

			PdfPTable ccn = new PdfPTable(2);
			ccn.setWidths(new int[] { 50, 50 });
			ccn.setWidthPercentage(100);

			Paragraph ccnt = new Paragraph();
			ccnt.setFont(boldFont);
			ccnt.add("Child Care Case Number:");

			PdfPCell ccnc = new PdfPCell(ccnt);

			PdfPCell mCell6 = new PdfPCell(new Paragraph("\n"));
			mCell6.setBorderColor(BaseColor.ORANGE);
			mCell6.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell mCell7 = new PdfPCell(new Paragraph("\n"));
			mCell7.setBorderColor(BaseColor.ORANGE);
			mCell7.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			ccn.addCell(ccnc);
			ccn.addCell(mCell6);

			bpt.addCell(ccn);
			bpt.addCell(mtCell);
			bpt.addCell(mCell7);

			document.add(bpt);

			PdfPTable bpt1 = new PdfPTable(3);
			bpt1.setWidths(new int[] { 45, 5, 50 });
			bpt1.setWidthPercentage(100);

			PdfPTable ccn1 = new PdfPTable(2);
			ccn1.setWidths(new int[] { 50, 50 });
			ccn1.setWidthPercentage(100);

			Paragraph ccnt1 = new Paragraph();
			ccnt1.setFont(boldFont);
			ccnt1.add("Caseload Code:");

			PdfPCell ccnc1 = new PdfPCell(ccnt1);

			PdfPCell mCell8 = new PdfPCell(new Paragraph("\n"));
			mCell8.setBorderColor(BaseColor.ORANGE);
			mCell8.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell mCell9 = new PdfPCell(new Paragraph("\n"));
			mCell9.setBorderColor(BaseColor.ORANGE);
			mCell9.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			ccn1.addCell(ccnc1);
			ccn1.addCell(mCell8);

			bpt1.addCell(ccn1);
			bpt1.addCell(mtCell);
			bpt1.addCell(mCell9);

			document.add(bpt1);

			PdfPTable keepTable = new PdfPTable(1);
			keepTable.setWidthPercentage(100);

			Paragraph kp = new Paragraph();
			kp.setFont(regFont);
			kp.add("Your eligibility for CHILD CARE needs to be Redetermined at this time. Please complete and return this form to us at the address listed above. If we do not receive this information within 10 business days, your child care will be CANCELED. If you are having problems filling out this form, please contact us.\r\n"
					+ "IF YOU'RE EMPLOYED, ATTACH COPIES OF YOUR 2 MOST RECENT PAYSTUBS.\r\n"
					+ "IF YOU'RE ATTENDING A TANF REQUIRED ACTIVITY (such as education or training), ATTACH A COPY OF YOUR CURRENT RESPONSIBILITY AND\r\n"
					+ "SERVICE PLAN (RSP).\r\n"
					+ "IF YOU'RE ATTENDING SCHOOL BUT NOT ON TANF, ATTACH A COPY OF YOUR SCHOOL SCHEDULE AND MOST RECENT REPORT CARD.\r\n"
					+ "IF YOU'RE A TEEN PARENT ATTENDING HIGH SCHOOUGED, ONLY A COPY OF YOUR SCHOOL SCHEDULE IS NEEDED.\r\n"
					+ "PLEASE PRINT CLEARLY IN BLUE OR BLACK INK.\r\n"
					+ "PLEASE READ THE ATTACHED INSTRUCTIONS BEFORE COMPLETING THIS FORM (P. 1).");

			PdfPCell kpCell = new PdfPCell(kp);
			kpCell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

			keepTable.addCell(kpCell);

			document.add(keepTable);

			PdfPTable sectable = new PdfPTable(1);
			sectable.setWidthPercentage(100);

			Paragraph kps = new Paragraph();
			kps.setFont(FontUtility.getBoldBody(12));
			kps.add("SECTION 1 - PARENT/GUARDIAN INFORMATION");

			PdfPCell secCell = new PdfPCell(kps);
			secCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			secCell.setHorizontalAlignment(Element.ALIGN_CENTER);

			sectable.addCell(secCell);
			document.add(sectable);

			PdfPTable workinfo = new PdfPTable(2);
			workinfo.setWidthPercentage(100);
			workinfo.setWidths(new int[] { 75, 25 });

			Paragraph wi = new Paragraph();
			wi.setFont(boldFont);
			wi.add("WORK INFORMATION ");
			wi.setFont(regFont);
			wi.add("-  if you are working more than one job, you must tell us about all your jobs even if you dont need"
					+ "child care for that job. Photocopy this page and complete a seperate work information and work schedule section for "
					+ "each job you have.");

			PdfPCell wic = new PdfPCell(wi);

			PdfPTable jobs = new PdfPTable(1);
			jobs.setWidthPercentage(100);

			Paragraph wij = new Paragraph();
			wij.setFont(regFont);
			wij.add("Number of jobs currently working.");

			PdfPCell jcell = new PdfPCell(wij);
			jcell.setBorder(0);

			jobs.addCell(jcell);

			PdfPCell mCell10 = new PdfPCell(new Paragraph("\n"));
			mCell10.setBorderColor(BaseColor.ORANGE);
			mCell10.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			jobs.addCell(mCell10);

			workinfo.addCell(wic);
			workinfo.addCell(jobs);

			document.add(workinfo);

			PdfPTable dp = new PdfPTable(3);
			dp.setWidthPercentage(100);
			dp.setWidths(new int[] { 60, 20, 20 });

			Paragraph dpp = new Paragraph();
			dpp.setFont(regFont);
			dpp.add("List a phone number where we can reach you during the day:");

			PdfPCell dpcell = new PdfPCell(dpp);

			PdfPCell mCell11 = new PdfPCell(new Paragraph("\n"));
			mCell11.setBorderColor(BaseColor.ORANGE);
			mCell11.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell mCell12 = new PdfPCell(new Paragraph("\n"));
			mCell12.setBorderColor(BaseColor.ORANGE);
			mCell12.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			dp.addCell(dpcell);
			dp.addCell(mCell11);
			dp.addCell(mCell12);

			document.add(dp);

			PdfPTable curremp = new PdfPTable(2);
			curremp.setWidthPercentage(100);
			curremp.setWidths(new int[] { 60, 40 });

			Paragraph cpp = new Paragraph();
			cpp.setFont(regFont);
			cpp.add("Current Employer/Company Name");

			PdfPCell cppcell = new PdfPCell(cpp);

			Paragraph cpp1 = new Paragraph();
			cpp1.setFont(regFont);
			cpp1.add("Job Title");

			PdfPCell cppcell1 = new PdfPCell(cpp1);

			PdfPCell mCell13 = new PdfPCell(new Paragraph("\n"));
			mCell13.setBorderColor(BaseColor.ORANGE);
			mCell13.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell mCell14 = new PdfPCell(new Paragraph("\n"));
			mCell14.setBorderColor(BaseColor.ORANGE);
			mCell14.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			curremp.addCell(cppcell);
			curremp.addCell(cppcell1);
			curremp.addCell(mCell13);
			curremp.addCell(mCell14);

			document.add(curremp);

			PdfPTable addr = new PdfPTable(4);
			addr.setWidthPercentage(100);
			addr.setWidths(new int[] { 40, 30, 15, 15 });

			Paragraph ap1 = new Paragraph("");
			ap1.setFont(regFont);
			ap1.add("Address");

			Paragraph ap2 = new Paragraph("");
			ap2.setFont(regFont);
			ap2.add("City");

			Paragraph ap3 = new Paragraph("");
			ap3.setFont(regFont);
			ap3.add("State");

			Paragraph ap4 = new Paragraph("");
			ap4.setFont(regFont);
			ap4.add("ZipCode");

			PdfPCell addrc1 = new PdfPCell(ap1);
			addrc1.setBorderColor(BaseColor.ORANGE);

			PdfPCell addrc2 = new PdfPCell(ap2);
			addrc2.setBorderColor(BaseColor.ORANGE);

			PdfPCell addrc3 = new PdfPCell(ap3);
			addrc3.setBorderColor(BaseColor.ORANGE);

			PdfPCell addrc4 = new PdfPCell(ap4);
			addrc4.setBorderColor(BaseColor.ORANGE);

			addr.addCell(addrc1);
			addr.addCell(addrc2);
			addr.addCell(addrc3);
			addr.addCell(addrc4);

			PdfPCell addmt1 = new PdfPCell(new Paragraph(" "));
			addmt1.setBorderColor(BaseColor.ORANGE);
			addmt1.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell addmt2 = new PdfPCell(new Paragraph(" "));
			addmt2.setBorderColor(BaseColor.ORANGE);
			addmt2.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell addmt3 = new PdfPCell(new Paragraph(" "));
			addmt3.setBorderColor(BaseColor.ORANGE);
			addmt3.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell addmt4 = new PdfPCell(new Paragraph(" "));
			addmt4.setBorderColor(BaseColor.ORANGE);
			addmt4.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			addr.addCell(addmt1);
			addr.addCell(addmt2);
			addr.addCell(addmt3);
			addr.addCell(addmt4);

			document.add(addr);

			PdfPTable tele = new PdfPTable(4);
			tele.setWidthPercentage(100);
			tele.setWidths(new int[] { 30, 10, 20, 40 });

			Paragraph tp1 = new Paragraph("");
			tp1.setFont(regFont);
			tp1.add("Work Telephone Number");

			Paragraph tp2 = new Paragraph("");
			tp2.setFont(regFont);
			tp2.add("Ext.");

			Paragraph tp3 = new Paragraph("");
			tp3.setFont(regFont);
			tp3.add("Date you started this job.");

			Paragraph tp4 = new Paragraph("");

			PdfPCell telerc1 = new PdfPCell(tp1);
			telerc1.setBorderColor(BaseColor.ORANGE);

			PdfPCell telerc2 = new PdfPCell(tp2);
			telerc2.setBorderColor(BaseColor.ORANGE);

			PdfPCell telerc3 = new PdfPCell(tp3);
			telerc3.setBorderColor(BaseColor.ORANGE);

			PdfPCell telerc4 = new PdfPCell(ap4);
			telerc4.setBorderColor(BaseColor.ORANGE);

			tele.addCell(telerc1);
			tele.addCell(telerc2);
			tele.addCell(telerc3);
			tele.addCell(telerc4);

			PdfPCell telmt1 = new PdfPCell(new Paragraph(" "));
			telmt1.setBorderColor(BaseColor.ORANGE);
			telmt1.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell telmt2 = new PdfPCell(new Paragraph(" "));
			telmt2.setBorderColor(BaseColor.ORANGE);
			telmt2.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell telmt3 = new PdfPCell(new Paragraph(" "));
			telmt3.setBorderColor(BaseColor.ORANGE);
			telmt3.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			PdfPCell telmt4 = new PdfPCell(new Paragraph(" "));
			telmt4.setBorderColor(BaseColor.ORANGE);
			telmt4.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			tele.addCell(telmt1);
			tele.addCell(telmt2);
			tele.addCell(telmt3);
			tele.addCell(telmt4);

			document.add(tele);

			PdfPTable ftab = new PdfPTable(5);
			ftab.setWidthPercentage(100);
			ftab.setWidths(new int[] { 35, 15, 15, 15, 20 });

			Paragraph ftp1 = new Paragraph("");
			ftp1.setFont(regFont);
			ftp1.add("I earn before deduction (complete one) :");

			PdfPCell ftpc1 = new PdfPCell(ftp1);

			PdfPTable ftabb = new PdfPTable(2);
			ftabb.setWidthPercentage(100);
			ftabb.setWidths(new int[] { 20, 80 });

			PdfPCell lc1 = new PdfPCell();
			lc1.setCellEvent(new CheckboxCellEvent("lc1"));
			lc1.setMinimumHeight(8);
			lc1.setBorderColor(BaseColor.ORANGE);

			ftabb.addCell(lc1);

			Paragraph ftp2 = new Paragraph("");
			ftp2.setFont(regFont);
			ftp2.add("Per Hour");

			PdfPCell ftpc2 = new PdfPCell(ftp2);

			ftabb.addCell(ftp2);

			PdfPTable ftabb1 = new PdfPTable(2);
			ftabb1.setWidthPercentage(100);
			ftabb1.setWidths(new int[] { 20, 80 });

			PdfPCell lc2 = new PdfPCell();
			lc2.setCellEvent(new CheckboxCellEvent("lc2"));
			lc2.setMinimumHeight(8);
			lc2.setBorderColor(BaseColor.ORANGE);

			ftabb1.addCell(lc2);

			Paragraph ftp3 = new Paragraph("");
			ftp3.setFont(regFont);
			ftp3.add("Per Month");

			PdfPCell ftpc3 = new PdfPCell(ftp3);

			ftabb1.addCell(ftpc3);

			PdfPTable ftabb2 = new PdfPTable(2);
			ftabb2.setWidthPercentage(100);
			ftabb2.setWidths(new int[] { 20, 80 });

			PdfPCell lc3 = new PdfPCell();
			lc3.setCellEvent(new CheckboxCellEvent("lc3"));
			lc3.setMinimumHeight(8);
			lc3.setBorderColor(BaseColor.ORANGE);

			ftabb2.addCell(lc3);

			Paragraph ftp4 = new Paragraph("");
			ftp4.setFont(regFont);
			ftp4.add("Per Month");

			PdfPCell ftpc4 = new PdfPCell(ftp4);

			ftabb2.addCell(ftpc4);

			PdfPTable ftabb3 = new PdfPTable(2);
			ftabb3.setWidthPercentage(100);
			ftabb3.setWidths(new int[] { 50, 50 });

			Paragraph ftp5 = new Paragraph("");
			ftp5.setFont(regFont);
			ftp5.add("Amount $");

			PdfPCell ftpc5 = new PdfPCell(ftp5);

			ftabb3.addCell(ftpc5);

			PdfPCell lc4 = new PdfPCell(new Paragraph(" "));
			lc4.setBorderColor(BaseColor.ORANGE);
			lc4.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			ftabb3.addCell(lc4);

			ftab.addCell(ftpc1);
			ftab.addCell(ftabb);
			ftab.addCell(ftabb1);
			ftab.addCell(ftabb2);
			ftab.addCell(ftabb3);

			document.add(ftab);

			PdfPTable ltab = new PdfPTable(3);
			ltab.setWidthPercentage(100);
			ltab.setWidths(new int[] { 60, 20, 20 });

			PdfPTable ftabbb = new PdfPTable(1);

			PdfPTable table1 = new PdfPTable(3);
			PdfPTable table2 = new PdfPTable(3);
			PdfPTable table3 = new PdfPTable(3);

			Paragraph lpp1 = new Paragraph();
			lpp1.setFont(regFont);
			lpp1.add("I get paid (check one): ");

			PdfPCell fcell1 = new PdfPCell(lpp1);

			table1.addCell(fcell1);

			PdfPTable intable = new PdfPTable(2);
			intable.setWidths(new int[] { 15, 85 });

			PdfPCell inlc1 = new PdfPCell();
			inlc1.setCellEvent(new CheckboxCellEvent("lc11"));
			inlc1.setMinimumHeight(8);
			inlc1.setBorderColor(BaseColor.ORANGE);

			intable.addCell(inlc1);

			Paragraph lpp3 = new Paragraph();
			lpp3.setFont(regFont);
			lpp3.add("every day");

			PdfPCell fcell3 = new PdfPCell(lpp3);

			intable.addCell(fcell3);

			PdfPTable intable1 = new PdfPTable(2);
			intable1.setWidths(new int[] { 15, 85 });

			PdfPCell inlc2 = new PdfPCell();
			inlc2.setCellEvent(new CheckboxCellEvent("lc12"));
			inlc2.setMinimumHeight(8);
			inlc2.setBorderColor(BaseColor.ORANGE);

			intable1.addCell(inlc2);

			Paragraph lpp4 = new Paragraph();
			lpp4.setFont(regFont);
			lpp4.add("every week");

			PdfPCell fcell2 = new PdfPCell(lpp4);

			intable1.addCell(fcell2);

			PdfPTable intable2 = new PdfPTable(2);
			intable2.setWidths(new int[] { 15, 85 });

			PdfPCell inlc23 = new PdfPCell();
			inlc23.setCellEvent(new CheckboxCellEvent("lc121"));
			inlc23.setMinimumHeight(8);
			inlc23.setBorderColor(BaseColor.ORANGE);

			intable2.addCell(inlc23);

			Paragraph lpp41 = new Paragraph();
			lpp41.setFont(regFont);
			lpp41.add("every two weeks");

			PdfPCell fcell2p = new PdfPCell(lpp41);

			intable2.addCell(fcell2p);

			PdfPTable intable3 = new PdfPTable(2);
			intable3.setWidths(new int[] { 15, 85 });

			PdfPCell inlc233 = new PdfPCell();
			inlc233.setCellEvent(new CheckboxCellEvent("lc1213"));
			inlc233.setMinimumHeight(8);
			inlc233.setBorderColor(BaseColor.ORANGE);

			intable3.addCell(inlc233);

			Paragraph lpp413 = new Paragraph();
			lpp413.setFont(regFont);
			lpp413.add("twice per month");

			PdfPCell fcell2p3 = new PdfPCell(lpp413);

			intable3.addCell(fcell2p3);

			PdfPTable intable4 = new PdfPTable(2);
			intable4.setWidths(new int[] { 15, 85 });

			PdfPCell inlc234 = new PdfPCell();
			inlc234.setCellEvent(new CheckboxCellEvent("lc1213"));
			inlc234.setMinimumHeight(8);
			inlc234.setBorderColor(BaseColor.ORANGE);

			intable4.addCell(inlc233);

			Paragraph lpp414 = new Paragraph();
			lpp414.setFont(regFont);
			lpp414.add("once per month");

			PdfPCell fcell2p4 = new PdfPCell(lpp414);

			intable4.addCell(fcell2p4);

			PdfPTable intable5 = new PdfPTable(2);
			intable5.setWidths(new int[] { 15, 85 });

			PdfPCell inlc235 = new PdfPCell();
			inlc235.setCellEvent(new CheckboxCellEvent("lc1213"));
			inlc235.setMinimumHeight(8);
			inlc235.setBorderColor(BaseColor.ORANGE);

			intable5.addCell(inlc235);

			Paragraph lpp415 = new Paragraph();
			lpp415.setFont(regFont);
			lpp415.add("other (please explain)");

			PdfPCell fcell2p5 = new PdfPCell(lpp415);

			intable5.addCell(fcell2p5);

			table1.addCell(intable);
			table1.addCell(intable1);
			table1.addCell(intable2);

			table1.addCell(intable3);
			table1.addCell(new Paragraph(""));
			table1.addCell(intable4);
			table1.addCell(intable5);
			table1.addCell(new Paragraph(""));

			ftabbb.addCell(table1);
			ftabbb.addCell(table2);
			ftabbb.addCell(table3);

			ltab.addCell(ftabbb);

			PdfPTable lpart1 = new PdfPTable(1);

			Paragraph lpar1 = new Paragraph();
			lpar1.setFont(regFont);
			lpar1.add("Number of hours usually worked at this job each week");

			Paragraph lpar2 = new Paragraph();
			lpar2.setFont(regFont);
			lpar2.add("Number of days usually worked at this job each week");

			PdfPCell ltabc1 = new PdfPCell(new Paragraph(" "));
			ltabc1.setBorderColor(BaseColor.ORANGE);
			ltabc1.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			lpart1.addCell(lpar1);
			lpart1.addCell(ltabc1);
			ltab.addCell(lpart1);

			PdfPTable lpart2 = new PdfPTable(1);

			PdfPCell ltabc2 = new PdfPCell(new Paragraph(" "));
			ltabc2.setBorderColor(BaseColor.ORANGE);
			ltabc2.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));

			lpart2.addCell(lpar2);
			lpart2.addCell(ltabc2);
			ltab.addCell(lpart2);

			document.add(ltab);

			PdfPTable tt = new PdfPTable(10);
			tt.setWidthPercentage(100);
			tt.setWidths(new int[] { 40, 6, 4, 7, 4, 29, 5, 5, 5, 5 });

			Paragraph ttp1 = new Paragraph();
			ttp1.setFont(regFont);
			ttp1.add("Travel time from the child care provider to work:");

			PdfPCell ttpc1 = new PdfPCell(ttp1);
			tt.addCell(ttpc1);

			Paragraph ttp2 = new Paragraph();
			ttp2.setFont(regFont);
			ttp2.add("(Hrs.)");

			PdfPCell ttpc2 = new PdfPCell(ttp2);
			tt.addCell(ttpc2);

			/*
			 * Paragraph ttp3 = new Paragraph(); ttp3.setFont(regFont); ttp3.add("00");
			 * 
			 * PdfFormField selectGroup = PdfFormField.createEmpty(writer);
			 * selectGroup.setFieldName("provdgrp1");
			 * 
			 * PdfFormField selectGroup1 = PdfFormField.createEmpty(writer);
			 * selectGroup1.setFieldName("provdgrp2");
			 * 
			 * ArrayList hoursStrArr = new ArrayList<String>(); ArrayList minsStrArr = new
			 * ArrayList<String>();
			 * 
			 * for (int i = 0; i <= 9; i++) { hoursStrArr.add("0" + i); minsStrArr.add("0" +
			 * i); } for (int i = 10; i <= 60; i++) { hoursStrArr.add(i + "");
			 * minsStrArr.add(i + ""); }
			 * 
			 * Object[] harr = hoursStrArr.toArray(); String[] options1 = new
			 * String[harr.length]; for (int j = 0; j < hoursStrArr.size(); j++) {
			 * options1[j] = (String) harr[j]; }
			 * 
			 * Object[] marr = minsStrArr.toArray(); String[] options2 = new
			 * String[marr.length]; for (int j = 0; j < minsStrArr.size(); j++) {
			 * options2[j] = (String) marr[j]; }
			 * 
			 * System.out.println(harr.length);
			 * 
			 * PdfPCell cellSelectProvdInput = new PdfPCell();
			 * cellSelectProvdInput.setCellEvent(new SelectCellEvent(selectGroup,
			 * "provdInp111", options1, options1));
			 * cellSelectProvdInput.setBorderColor(BaseColor.ORANGE);
			 * 
			 * // PdfPCell ttpc3 = new PdfPCell(ttp3); tt.addCell(cellSelectProvdInput);
			 * writer.addAnnotation(selectGroup);
			 * 
			 */

			PdfPCell ttpc3 = new PdfPCell(new Paragraph(" "));
			ttpc3.setBorderColor(BaseColor.ORANGE);
			ttpc3.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			tt.addCell(ttpc3);

			Paragraph ttp4 = new Paragraph();
			ttp4.setFont(regFont);
			ttp4.add("(Mins.)");

			PdfPCell ttpc4 = new PdfPCell(ttp4);
			tt.addCell(ttpc4);

			/*
			 * PdfPCell cellSelectProvdInput1 = new PdfPCell();
			 * cellSelectProvdInput1.setCellEvent(new SelectCellEvent(selectGroup1,
			 * "provdInp211", options2, options2));
			 * cellSelectProvdInput1.setBorderColor(BaseColor.ORANGE);
			 * 
			 * tt.addCell(cellSelectProvdInput1); writer.addAnnotation(selectGroup1);
			 * 
			 * // Paragraph ttp41 = new Paragraph(); // ttp41.setFont(regFont); //
			 * ttp41.add("00");
			 * 
			 * // PdfPCell ttpc5 = new PdfPCell(ttp41); // tt.addCell(ttpc5);
			 */

			PdfPCell ttpc5 = new PdfPCell(new Paragraph(" "));
			ttpc5.setBorderColor(BaseColor.ORANGE);
			ttpc5.setCellEvent(new TextFieldForPdfCell(inpName.getCellInputName()));
			tt.addCell(ttpc5);

			Paragraph ttp5 = new Paragraph();
			ttp5.setFont(regFont);
			ttp5.add("Do you use public transportation?");

			PdfPCell ttpc6 = new PdfPCell(ttp5);
			tt.addCell(ttpc6);

			Paragraph ttp6 = new Paragraph();
			ttp6.setFont(regFont);
			ttp6.add("00");

			Paragraph ttp7 = new Paragraph();
			ttp7.setFont(regFont);
			ttp7.add("YES");

			Paragraph ttp8 = new Paragraph();
			ttp8.setFont(regFont);
			ttp8.add("00");

			Paragraph ttp9 = new Paragraph();
			ttp9.setFont(regFont);
			ttp9.add("NO");

			// PdfPCell ttpc7 = new PdfPCell(ttp6);
			PdfPCell ttpc7 = new PdfPCell();
			ttpc7.setCellEvent(new CheckboxCellEvent("ttpc7"));
			ttpc7.setMinimumHeight(8);
			ttpc7.setBorderColor(BaseColor.ORANGE);

			tt.addCell(ttpc7);

			PdfPCell ttpc8 = new PdfPCell(ttp7);
			tt.addCell(ttpc8);

			// PdfPCell ttpc9 = new PdfPCell(ttp8);

			PdfPCell ttpc9 = new PdfPCell();
			ttpc9.setCellEvent(new CheckboxCellEvent("ttpc8"));
			ttpc9.setMinimumHeight(8);
			ttpc9.setBorderColor(BaseColor.ORANGE);
			tt.addCell(ttpc9);

			PdfPCell ttpc10 = new PdfPCell(ttp9);
			tt.addCell(ttpc10);

			document.add(tt);

			PdfPTable ws = new PdfPTable(1);
			ws.setWidthPercentage(100);

			Paragraph wsp = new Paragraph();
			wsp.setFont(FontUtility.getBoldBody(10));
			wsp.add("WORK SCHEDULE : ");
			wsp.setFont(FontUtility.getNormalBody(10));
			wsp.add("If you schedule varies, provide an example of your schedule.");

			PdfPCell wsc = new PdfPCell(wsp);
			wsc.setHorizontalAlignment(Element.ALIGN_CENTER);

			ws.addCell(wsc);

			document.add(ws);

			WeekGenerator weekGenerator = new WeekGenerator();
			weekGenerator.generateWeekTable(document, "wk1");

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
