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

WebUI.delay(1)

WebUI.click(findTestObject('Frame 3/Sender To Information TGR'))

WebUI.delay(1)

WebUI.click(findTestObject('Frame 3/Sender To Information Filter'))

WebUI.setText(findTestObject('Frame 3/Sender To Information Filter'), 'Account')

WebUI.delay(1)

WebUI.click(findTestObject('Frame 3/Sender To Information U1'))

WebUI.delay(2)

def driver = DriverFactory.getWebDriver()

def driverExt = new WebDriverWrapper(driver)

driver = driverExt.WaituntilFrameLoads(By.xpath('//iframe[contains(@class, \'x-component\')]'))

def inputHelper = new InputFillerHelper(driver)

WebUI.delay(2)

inputHelper.GetInputFromLabel('Nama PJK').ShouldBe().Textbox().SendText('Pejeka')

inputHelper.GetInputFromLabel('Kode PJK').ShouldBe().Textbox().SendText('1122')

inputHelper.GetInputFromLabel('Kode Swift').ShouldBe().Textbox().SendText('062')

inputHelper.GetInputFromLabel('Kantor Pembukaan Rekening').ShouldBe().Textbox().SendText('Jakarta')

inputHelper.GetInputFromLabel('No Rekening').ShouldBe().Textbox().SendText('11135490')

inputHelper.GetInputFromLabel('Tanggal Pembukaan Rekening').ShouldBe().Date().SendText('01-01-2012')

WebUI.delay(2)

//inputHelper.GetInputFromLabel('Tanggal Penutupan Rekening').ShouldBe().Date().SendText('01-01-2021')
//inputHelper.GetInputFromLabel('Tanggal Saldo').ShouldBe().Date().SendText('02-03-2021')
inputHelper.ClickButtonWithText('Tambah Signatory')

WebUI.delay(5)

inputHelper.GetInputFromLabel('Gelar:').ShouldBe().Textbox().SendText('MR.')

inputHelper.GetInputFromLabel('Nama Lengkap:').ShouldBe().Textbox().SendText('Anggi Firmansah')

inputHelper.GetInputFromLabel('Tanggal Lahir:').ShouldBe().Date().SendText('11-11-1991')

inputHelper.GetInputFromLabel('Tempat Lahir:').ShouldBe().Textbox().SendText('Jakarta')

inputHelper.GetInputFromLabel('Nama Ibu Kandung:').ShouldBe().Textbox().SendText('Bubu')

inputHelper.GetInputFromLabel('Nama Alias:').ShouldBe().Textbox().SendText('Ann')

WebUI.delay(2)

inputHelper.GetInputFromLabel('NIK:').ShouldBe().Textbox().SendText('121337469921')

inputHelper.GetInputFromLabel('No. Paspor:').ShouldBe().Textbox().SendText('21337469921')

inputHelper.GetInputFromLabel('No. Identitas Lain:').ShouldBe().Textbox().SendText('37469921')

inputHelper.GetInputFromLabel('Email:').ShouldBe().Textbox().SendText('anggi@gmail.com')

inputHelper.GetInputFromLabel('Email2:').ShouldBe().Textbox().SendText('anggi2@gmail.com')

inputHelper.GetInputFromLabel('Email3:').ShouldBe().Textbox().SendText('anggi3@gmail.com')

inputHelper.GetInputFromLabel('Email4:').ShouldBe().Textbox().SendText('anggi4@gmail.com')

inputHelper.GetInputFromLabel('Email5:').ShouldBe().Textbox().SendText('anggi5@gmail.com')

inputHelper.GetInputFromLabel('Pekerjaan:').ShouldBe().Textbox().SendText('IT')

inputHelper.GetInputFromLabel('Tempat Bekerja:').ShouldBe().Textbox().SendText('Tangerang')

inputHelper.GetInputFromLabel('NPWP:').ShouldBe().Textbox().SendText('121337469921762')

inputHelper.GetInputFromLabel('Sumber Dana:').ShouldBe().Textbox().SendText('Maling')

inputHelper.GetInputFromLabel('Catatan:').ShouldBe().Textbox().SendText('Tidak Ada')

driver = driver.switchTo().defaultContent()

WebUI.click(findTestObject('Frame Director/Peran'))

WebUI.delay(2)

WebUI.click(findTestObject('Frame Director/Peran 2'))

WebUI.click(findTestObject('Frame Director/Add Phone'))

WebUI.delay(2)

WebUI.delay(2)

WebUI.click(findTestObject('Frame Director/Kategori Kontak'))

WebUI.delay(2)

WebUI.click(findTestObject('Frame Director/Kategori Kontak 2'))

WebUI.click(findTestObject('Frame Director/Alat Komunikasi'))

WebUI.delay(2)

WebUI.click(findTestObject('Frame Director/Alat Komunikasi 2'))

WebUI.setText(findTestObject('Frame Director/Nomor Telepon'), '089652767639')

WebUI.click(findTestObject('Frame Director/save phone'), FailureHandling.STOP_ON_FAILURE)

