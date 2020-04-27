package com.demoaut.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.demoaut.base.TestBase;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class TestUtil extends TestBase {

	public static long PAGELOADTIME = 20;
	public static long IMPLICITWAITTIME = 20;
	static String currentDir = System.getProperty(prop.getProperty("dir"));
	public static String TESTDATA_SHEET_PATH = currentDir + "\\database\\MercuryTours_TestData.xls";
	static Workbook book;
	static Sheet sheet;
	static String date = date();
	static String directory = currentDir + "/reports/" + date;
	static ExtentReports extent;
	
	//Method to fetch the data from the excel sheet - Data driven approach
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		System.out.println(sheet.getLastRowNum());
		System.out.println(sheet.getRow(0).getLastCellNum());
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				// System.out.println(data[i][j]);
			}
		}
		return data;
	}

	//Explicit wait - wait until the passed webElement to be Clickable
	public static boolean webdriverWait(WebElement element, WebDriver driver, int secs) {
		boolean elementPresent = false;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null)
			;
		elementPresent = true;
		return elementPresent;
	}

	//Used to scroll down the page completely
	public static void scrollDown() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// This will scroll the page till the element is found
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	//Generate Screenshot and save it in screenshot folder 
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	// mouseHover is a method used to move the mouse from one point to other
	public static void mouseHover(WebDriver driver, WebElement locator) {
		Actions action = new Actions(driver);
		action.moveToElement(locator);
		action.click().build().perform();
	}

	//Initializing to generate Extent report 4.0
	public static ExtentReports createInstance() {
		String reportName = getReportName();
		new File(directory).mkdirs();
		String path = directory + reportName;
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Mindtree");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);
		return extent;
	}

	//Generating the report name 
	public static String getReportName() {
		String FileName = "/" + ProjectName() + "_" + date + ".html";
		return FileName;
	}

	//Generating the name of the project
	public static String ProjectName() {
		String projectName = currentDir.substring(currentDir.lastIndexOf("\\") + 1, currentDir.length());
		return projectName;
	}

	//Generating time stamp for report name
	public static String date() {
		Date d = new Date();
		return d.toString().replace(":", "_").replace(" ", "_");
	}

	//Convert HTML to PDF
	public void createPdf(String file) throws DocumentException, IOException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				new FileInputStream(System.getProperty("user.dir")+"/test-output/emailable-report.html"));
		document.close();
	}

	public void pdf() throws IOException, DocumentException {
		File file = new File(TestUtil.directory + "/" + TestUtil.date() + ".pdf");
		file.getParentFile().mkdirs();
		createPdf(TestUtil.directory + "/" + date + ".pdf");
	}
}
