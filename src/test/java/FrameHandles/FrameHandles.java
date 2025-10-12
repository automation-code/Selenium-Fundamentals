package FrameHandles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameHandles {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ui.vision/demo/webtest/frames/");

        WebElement frame1 = driver.findElement(By.cssSelector("frame[src='frame_1.html']"));
        WebElement frame2 = driver.findElement(By.cssSelector("frame[src='frame_2.html']"));
        WebElement frame3 = driver.findElement(By.cssSelector("frame[src='frame_3.html']"));
        WebElement frame4 = driver.findElement(By.cssSelector("frame[src='frame_4.html']"));
        WebElement frame5 = driver.findElement(By.cssSelector("frame[src='frame_5.html']"));

        driver.switchTo().frame(frame2);
        driver.findElement(By.xpath("//input[@name='mytext2']")).sendKeys("textBox2");
        String frame2Text = driver.findElement(By.cssSelector("form[id='id2'] div[align='center']")).getText();
        System.out.println("Move to: " + frame2Text);
        driver.switchTo().defaultContent();

        driver.switchTo().frame(frame3);
        driver.findElement(By.xpath("//input[@name='mytext3']")).sendKeys("textBox3");
        String frame3Text = driver.findElement(By.cssSelector("form[id='id3'] div[align='center']")).getText();
        System.out.println("Move to: " + frame3Text);
        driver.switchTo().defaultContent();

        driver.switchTo().frame(frame1);
        driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("textBox1");
        String frame1Text = driver.findElement(By.cssSelector("form[id='id1'] div[align='center']")).getText();
        System.out.println("Move to: " + frame1Text);
        driver.switchTo().defaultContent();

        driver.switchTo().frame(frame5);
        driver.findElement(By.xpath("//input[@name='mytext5']")).sendKeys("textBox5");
        String frame5Text = driver.findElement(By.cssSelector("form[id='id5'] div[align='center']")).getText();
        System.out.println("Move to: " + frame5Text);
        driver.switchTo().defaultContent();

        driver.switchTo().frame(frame4);
        driver.findElement(By.xpath("//input[@name='mytext4']")).sendKeys("textBox4");
        String frame4Text = driver.findElement(By.cssSelector("form[id='id4'] div[align='center']")).getText();
        System.out.println("Move to: " + frame4Text);
        driver.switchTo().defaultContent();

        Thread.sleep(5000);

        driver.quit();

    }
}
