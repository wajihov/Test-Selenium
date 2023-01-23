package car;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;

public class SaleCar {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\kafka\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Integer> timeOUts = new HashMap<>();
        timeOUts.put("implicit", 1000);
        chromeOptions.setCapability("timeouts", timeOUts);

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://www.paruvendu.fr/");
        js.executeScript("cmp_pv.cookie.saveConsent(false);");
        driver.navigate().refresh();
        driver.findElement(By.partialLinkText("Déposer une annonce gratuite")).click();
        driver.findElement(By.xpath("//div[@data-type=1]")).click();
        driver.findElement(By.xpath("//li[contains(text(),'AUTO-MOTO-BATEAU')]")).click();
        driver.findElement(By.xpath("(//li[normalize-space()=\"Voiture d'occasion\"])[1]")).click();

        Select select = new Select(driver.findElement(By.name("choixFamille")));
        select.selectByVisibleText("Location");

        Select selectVoiture = new Select(driver.findElement(By.name("categorie")));
        selectVoiture.selectByVisibleText("Voiture");

        driver.findElement(By.name("descBien")).sendKeys("Voiture à louer et en bonne Etat ....");

        Select selectDuration = new Select(driver.findElement(By.name("nbrSemainesPublication")));
        selectDuration.selectByVisibleText("Pendant 6 mois");

        driver.findElement(By.id("prix")).sendKeys("25000");

        Select selectPays = new Select(driver.findElement(By.id("codePays")));
        selectPays.selectByVisibleText("Tunisie");

        driver.findElement(By.id("communeLibre")).sendKeys("Korba");
        //driver.findElement(By.id("particulier_boutonpublifb")).click();
        driver.findElement(By.id("codePromoEtape1")).sendKeys("75013");
        driver.findElement(By.xpath("//a[@id='suivant1']//span[contains(text(),'Suivant')]")).click();

        String str = driver.getPageSource();
        String strAttendue = "Vos coordinate de contact";
        if (str.contains(strAttendue)) {
            System.out.println(" C'est OK ");
        } else {
            System.out.println("Ce C'est pas OK");
        }


    }
}
