package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

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

	@Test
	@Order(5)
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {
		WebElement keywordInput = webDriver.findElement(By.id("form"));
		WebElement searchButton = webDriver.findElement(By.cssSelector("input[value='検索']"));
		String keyword = "セルフ";

		keywordInput.clear();
		keywordInput.sendKeys(keyword);
		searchButton.click();

		getEvidence(new Object() {
		});

		List<WebElement> searchResults = webDriver.findElements(By.cssSelector("tr dt.mb10 span:not(.mr10)"));

		for (WebElement searchResultElement : searchResults) {
			assertThat(searchResultElement.getText(), is(containsString(keyword)));

		}
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		WebElement clearButton = webDriver.findElement(By.cssSelector("input[value='クリア']"));
		clearButton.click();
		WebElement keywordCheck = webDriver.findElement(By.id("form"));
		getEvidence(new Object() {
		});
		assertEquals("", keywordCheck.getAttribute("value"));
	}

}
