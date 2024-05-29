package org.reusable;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UtilityClass {

	public static WebDriver driver;
	public static Robot r;

	public static void launchBrowser(String browserName) {

		switch (browserName) {
		case "Chrome":

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "Edge":

			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;

		case "Firefox":

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		}
	}

	public static void launchUrl(String url) {

		driver.get(url);
	}

	public static void implitWait() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public static void passTextToTextBox(WebElement element, String testData) {

		element.sendKeys(testData);
	}

	public static void clickWebElement(WebElement element) {

		element.click();
	}

	public static void pressEnter() throws AWTException {

		r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void switchToAnotherWindow(int windowNumber) {

		Set<String> allId = driver.getWindowHandles();
		List<String> l = new LinkedList<>();
		l.addAll(allId);

		driver.switchTo().window(l.get(windowNumber));

	}

	public static void pageDown() throws AWTException {

		r = new Robot();
		r.keyPress(KeyEvent.VK_PAGE_DOWN);
		r.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	public static void passTextUsingJs(WebElement element, String testData) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + testData + "')", element);
	}

	public static void selectState(WebElement element, String text) {

		Select s = new Select(element);
		s.selectByVisibleText(text);
	}

	public static String readFromExcel(String text, int rowNo, int cellNo) throws IOException {

		File f = new File(
				"C:\\Users\\prade\\Documents\\Project\\Test\\excel\\trackdefect.xlsx");

		FileInputStream fi = new FileInputStream(f);

		Workbook book = new XSSFWorkbook(fi);

		Sheet sheet = book.getSheet(text);

		Row row = sheet.getRow(rowNo);

		Cell c = row.getCell(cellNo);

		int type = c.getCellType();
		String value = "";
		if (type == 1) {
			value = c.getStringCellValue();

		}

		else if (DateUtil.isCellDateFormatted(c)) {

			Date dateCellValue = c.getDateCellValue();
			SimpleDateFormat s = new SimpleDateFormat("DD MM yyyy");
			value = s.format(dateCellValue);
		}

		else {
			double date = c.getNumericCellValue();
			long l = (long) date;
			value = String.valueOf(l);
		}

		return value;

	}

	public static void closeBrowser() {

		driver.quit();
	}
}
