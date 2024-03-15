package Test;

import Base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class FillFormAdvanceTest {

    public class FillFormBasicTest extends BaseTest {
        private final String browserName = "chrome";
        private final boolean headlessBrowser = false; //przełaczamy na true poza developerka
        private WebDriver driver;

        private Logger log = LoggerFactory.getLogger(FillFormBasicTest.class);


        @Test
        void fillFormBasicScenario() {

            // inicjalizacja driver

            //Zaczynamy klikać
//Krok 1 klik "contact us"
            WebElement contactUsLink = driver.findElement(By.cssSelector("#contact-link a"));
            contactUsLink.click();
            log.info("Step 1: Contact us kliknięty.");

            //Krok 2 wybierz
            WebElement dropdownList = driver.findElement(By.cssSelector("#id_contact"));
            Select select = new Select(dropdownList);
            select.selectByVisibleText("Webmaster");
            log.info("Step 2: subject został wybrany.");

//Krok 3 wpisz email (krok 2 skipujemy)
            WebElement emailInput = driver.findElement(By.cssSelector("#email"));
            emailInput.sendKeys("mail@op.pl");
            log.info("Step 3: email wpisany.");

//Krok 4 wpisz Oreder refference
//WebElement orderRef = driver.findElement(By.cssSelector("#id_order"));
            WebElement orderRef = driver.findElement(By.id("id_order"));
            orderRef.sendKeys("123567");
            log.info("Step 4: order ref wpisany.");

            //Krok 5 wpisz wiadomość
            WebElement messageInput = driver.findElement(By.cssSelector("#message"));
            messageInput.sendKeys("""
                    To jest pierwsza linijka tekstu,
                    to jest druga linijka
                    to jest ostatnia linijka...
                    """);
            log.info("Step 5: Wiadomość wpisana.");

            //Krok 6 dodaj plik
            WebElement fileUpload = driver.findElement(By.id("fileUpload"));
            fileUpload.sendKeys("C:\\log.txt");
            log.info("Step 6: Załadowano załącznik.");

// krok 7 wysłanie form
            WebElement submitMessage = driver.findElement(By.cssSelector("#submitMessage"));
            submitMessage.click();
            log.info("Step 7: wysłanie formularza.");

            //Krok 8 Weryfikacja Succes message
            WebElement succesMessage = driver.findElement(By.className("alert-success"));
            String expectedMassage = "Your message has been successfully sent to our team.";
            String actualMassage = succesMessage.getText();
            assertThat(actualMassage).isEqualTo(expectedMassage);
            log.info("Step 8: Weryfikacja Succes message.");


            //   System.out.println("test stop");
            log.info("stop test");
            log.debug("Wiadomosc z debugera");





    }

}
}

