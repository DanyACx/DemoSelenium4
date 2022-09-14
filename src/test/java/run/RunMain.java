package run;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class RunMain {

    private ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Map<String, Object> dm = new HashMap<String, Object>();

        dm.put("width", 270);
        dm.put("height", 550);
        dm.put("deviceScaleFactor", 100);
        dm.put("mobile", true);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", dm);


        driver.get("https://certi.lp.lamark-it.com/main/#/pincode1/hlm/ae/etisalat");
    }

    @Test
    public void test1() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement inputMsisdn = driver.findElement(By.name("phoneNumber"));
        WebElement botonSuscribir = driver.findElement(By.xpath("/html/body/app-root/app-hlm-operator/app-background-content/div/div/div/app-request-page/div/div[3]/button[1]"));


        inputMsisdn.clear();

        inputMsisdn.sendKeys("2022013201");
        System.out.println("si ingreso texto");
        botonSuscribir.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement textoPinCode = driver.findElement(By.xpath("/html/body/app-root/app-hlm-operator/app-background-content/div/div/div/app-verify-page/div/label[1]"));
        System.out.println(textoPinCode.getText());
        assertEquals("Please enter the Pin you received to activate your subscription:", textoPinCode.getText());
    }

    @After
    public void tearDown(){
        //driver.quit();
    }

}
