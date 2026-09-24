package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		goTo("http://localhost:8080/lms");
		WebElement login = webDriver.findElement(By.tagName("h2"));
		assertEquals("ログイン", login.getText());
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		WebElement loginIdInput = webDriver.findElement(By.id("loginId"));
		loginIdInput.clear();
		loginIdInput.sendKeys("StudentAA05");

		WebElement loginPassInput = webDriver.findElement(By.id("password"));
		loginPassInput.clear();
		loginPassInput.sendKeys("StudentAA05");

		WebElement loginButton = webDriver.findElement(By.className("btn-primary"));
		loginButton.click();

		WebElement courseDetail = webDriver.findElement(By.className("active"));
		assertEquals("コース詳細", courseDetail.getText());
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		WebElement menu = webDriver.findElement(By.linkText("機能"));
		menu.click();

		WebElement helpLink = webDriver.findElement(By.linkText("ヘルプ"));
		helpLink.click();

		WebElement helpCheck = webDriver.findElement(By.tagName("h2"));
		assertEquals("ヘルプ", helpCheck.getText());
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		WebElement faqLink = webDriver.findElement(By.linkText("よくある質問"));
		faqLink.click();

		for (String window : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(window);
		}

		WebElement faqLinkCheck = webDriver.findElement(By.tagName("h2"));
		assertEquals("よくある質問", faqLinkCheck.getText());
		getEvidence(new Object() {
		});
	}

}
