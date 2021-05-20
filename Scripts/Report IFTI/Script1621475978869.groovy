import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.SimpleDateFormat as SimpleDateFormat
import java.time.format.DateTimeFormatter as DateTimeFormatter
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.builtin.ScrollToElementKeyword as ScrollToElementKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.nawadata.nfUnitTestLibrary.WebDriverWrapper as WebDriverWrapper
import com.nawadata.nfUnitTestLibrary.formInputHelper.WebElementExtended as WebElementExtended
import com.nawadata.nfUnitTestLibrary.formInputHelper.FillDateClass as FillDateClass
import com.nawadata.nfUnitTestLibrary.formInputHelper.InputFillerHelper as InputFillerHelper
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import java.time.LocalDate as LocalDate
import org.openqa.selenium.ElementNotInteractableException as ElementNotInteractableException
import org.openqa.selenium.ElementNotVisibleException as ElementNotVisibleException
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.NoSuchElementException as NoSuchElementException
import org.openqa.selenium.TimeoutException as TimeoutException
import org.openqa.selenium.interactions.Actions as Actions
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait

WebUI.openBrowser('http://goaml.southeastasia.cloudapp.azure.com/goaml/Login.aspx?ReturnUrl=%2fgoaml%2fDefault.aspx')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Frame 1/Username'), 'anggi1')

WebUI.setEncryptedText(findTestObject('Frame 1/Password'), 'iFGeFYmXIrUhQZHvW7P22w==')

WebUI.sendKeys(findTestObject('Frame 1/Password'), Keys.chord(Keys.ENTER))

WebUI.delay(2)

WebUI.setText(findTestObject('Frame 1/Filter'), 'ifti')

WebUI.delay(2)

WebUI.click(findTestObject('Frame 1/View IFTI'))

WebUI.click(findTestObject('Frame 1/add new record'))

WebUI.delay(8)

WebUI.click(findTestObject('Frame 2/Jenis Laporan TGR'))

if (JenisLaporan == 'Incoming Bank') {
    WebUI.click(findTestObject('Frame 2/TKLIB'))
} else if (JenisLaporan == 'Outgoing Bank') {
    WebUI.click(findTestObject('Frame 2/TKLOB'))
}

WebUI.delay(2)

def driver = DriverFactory.getWebDriver()

def driverExt = new WebDriverWrapper(driver)

driver = driverExt.WaituntilFrameLoads(By.xpath('//iframe[contains(@class, \'x-component\')]'))

def inputHelper = new InputFillerHelper(driver)

WebUI.delay(2)

inputHelper.GetInputFromLabel('Transaksi Unik').ShouldBe().Dropdown().SelectElementFromText(TransaksiUnik)

inputHelper.GetInputFromLabel('Value Transaksi Unik').ShouldBe().Textbox().SendText('Transaksi IFTI')

inputHelper.GetInputFromLabel('Alasan').ShouldBe().Textbox().SendText('Tidak Beralasan')

inputHelper.GetInputFromLabel('Tindakan Pelapor').ShouldBe().Textbox().SendText('Pelapor Tidak Bertindak')

inputHelper.ClickButtonWithText('Tambah Transaksi')

WebUI.delay(5)

inputHelper.GetInputFromLabel('Nomor Transaksi').ShouldBe().Textbox().SendText('12133746')

inputHelper.GetInputFromLabel('No Ref Transaksi').ShouldBe().Textbox().SendText('118811')

inputHelper.GetInputFromLabel('Lokasi Transaksi').ShouldBe().Textbox().SendText('Jakarta')

inputHelper.GetInputFromLabel('Keterangan Transaksi').ShouldBe().Textbox().SendText('Tanpa Keterangan')

inputHelper.GetInputFromLabel('Tanggal Transaksi').ShouldBe().Date().SendText('01-01-2021')

inputHelper.GetInputFromLabel('Nama Teller / Petugas Front Office').ShouldBe().Textbox().SendText('Mr Teller')

inputHelper.GetInputFromLabel('Nama Pejabat Pengotorisasi Transaksi').ShouldBe().Textbox().SendText('Mr Bro')

inputHelper.GetInputFromLabel('Tanggal Pembukuan').ShouldBe().Date().SendText('01-02-2021')

inputHelper.GetInputFromLabel('Cara Transaksi Dilakukan').ShouldBe().Dropdown().SelectElementFromText(CaraTransaksiDilakukan)

driver = driver.switchTo().defaultContent()

WebUI.scrollToElement(findTestObject('Frame 3/Kurs'), 3)

WebUI.setText(findTestObject('Frame 3/Nilai Transaksi'), NilaiTransaksi)

if (FromMyClient == 'yes') {
    WebUI.check(findTestObject('Frame 3/FromMyClient'))
} else if (FromMyClient == 'no'){
    WebUI.uncheck(findTestObject('Frame 3/FromMyClient'))
}

WebUI.setText(findTestObject('Frame 3/Instrumen Transaksi Asal'), InstrumenTransaksiAsal)

WebUI.delay(1)

WebUI.sendKeys(findTestObject('Frame 3/Instrumen Transaksi Asal'), Keys.chord(Keys.ENTER))

WebUI.delay(1)

WebUI.click(findTestObject('Frame 3/Negara Transaksi Asal TGR'), FailureHandling.STOP_ON_FAILURE)

WebUI.delay(1)

WebUI.click(findTestObject('Frame 3/Negara Transaksi Asal Filter'), FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Frame 3/Negara Transaksi Asal Filter'), 'ID')

WebUI.delay(2)

WebUI.click(findTestObject('Frame 3/Negara Transaksi Asal U1'))

WebUI.delay(2)

if (InformasiPengirim == 'Account') {
    WebUI.callTestCase(findTestCase('Account'), [:], FailureHandling.STOP_ON_FAILURE)
}

