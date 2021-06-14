package com.instawork.orbitz.pages;

import com.instawork.orbitz.CommonTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FlightPage extends CommonTest {

    By flightButton = By.cssSelector("a[href*='flight-pwa']");
    By roundTripButton = By.cssSelector("[href*='roundtrip']");
    By returningDatePicker= By.id("d2-btn");
    By leavingFromButton= By.cssSelector("[aria-label='Leaving from']");
    By leavingFromInput= By.id("location-field-leg1-origin");
    By goingToButton = By.cssSelector("[aria-label='Going to']");
    By goingToInput= By.id("location-field-leg1-destination");
    By departureButton = By.id("d1-btn");
    By returningButton = By.id("d2-btn");
    By searchButton= By.cssSelector("[data-testid='submit-button']");
    By monthPicker = By.xpath("(//*[@class='uitk-date-picker-month'])[1]//h2");
    By doneDate= By.cssSelector("[data-stid='apply-date-picker']");
    By nextMonthButton= By.xpath("(//button[@data-stid='date-picker-paging'])[2]");
    By flyingFromText= By.cssSelector("[aria-label*='Flying from'] , [id='departure-airport-1']");
    By flyingToText= By.cssSelector("[aria-label*='Flying to'], [id='arrival-airport-1']");
    By departingText= By.cssSelector("[id='start-date-ROUND_TRIP-0-btn'], [id='departure-date-1']");
    By returningText= By.cssSelector(" [id='end-date-ROUND_TRIP-0-btn'], [id='return-date-1']");
    By nonStopCheckbox= By.cssSelector("[data-test-id='stops-0-label'], [data-test-id='stopFilter_stops-0']");
    By sortByDropdown= By.cssSelector("[data-test-id='sortDropdown']");
    By continueButton= By.cssSelector("[data-test-id='select-button']");
    By flightChargeText= By.cssSelector("[data-test-id='details-and-fares-footer'] span[class='uitk-lockup-price'],ul[id='flightModuleList'] li:nth-child(1) span[data-test-id='listing-price-dollars']");
    By flightDetailsText= By.cssSelector("[data-test-id='details-and-fares']");
    By headerAccountMenuId= By.id("header-account-menu");
    By tripTotal= By.xpath("//h3[text()='Trip total']//ancestor::td//following-sibling::td[1]/span");
    By noThanksButton = By.id("forcedChoiceNoThanks");
    By selectThisFairButton= By.xpath("(//span[text()='Select this fare']/ancestor::button[@data-test-id='select-button-1'])[1]");
    By cheapestFlightText= By.xpath("//span[text()='Cheapest flight']");
    String leavingCitiesList= "//li[@data-stid='location-field-leg1-origin-result-item']";
    String goingtoCitiesList= "//li[@data-stid='location-field-leg1-destination-result-item']";

    public void selectFlightTab()
    {
        click(flightButton);
        Assert.assertTrue(isElementPresent(roundTripButton), "User is not navigated to flight booking page");
    }

    public void clickOnRoundTripTab()
    {
        if(!getAttributeValue(roundTripButton,"aria-selected").equalsIgnoreCase("true"))
        {
            click(roundTripButton);
        }
        Assert.assertTrue(isElementPresent(returningDatePicker), "User is not on the roundTrip page");
    }

    public void enterLeavingFromCity(String leavingCity)
    {
        click(leavingFromButton);
        sendKeys(leavingFromInput, leavingCity);
        By cities= By.xpath( leavingCitiesList+"//strong[contains(text(),'"+leavingCity+"')]");
        waitForElementToBeVisible(cities,30);
        click(cities);
    }

    public void enterGoingToCity(String goingCity)
    {
        click(goingToButton);
        sendKeys(goingToInput, goingCity);
        By cities= By.xpath( goingtoCitiesList+"//strong[contains(text(),'"+goingCity+"')]");
        waitForElementToBeVisible(cities,30);
        click(cities);
    }

    public void selectDepartureDate(Date date)
    {
        click(departureButton);
        selectDateFromDatePicker(date);
    }

    public void selectReturningDate(Date date)
    {
        click(returningButton);
        selectDateFromDatePicker(date);
    }

    public void clickOnSearchButton()
    {
        click(searchButton);
        Assert.assertTrue(getPageURL().contains("Flights-Search"), "user should navigate to flight search page");
        waitForElementToBeVisible(cheapestFlightText,40);
    }

    public void verifyFlightDetails(String flyingFrom, String flyingTo, Date departureDate, Date returningDate)
    {
        if (isElementPresent(headerAccountMenuId)) {
            SimpleDateFormat month_date = new SimpleDateFormat("M/d/yyyy", Locale.ENGLISH);
            Assert.assertTrue(getValue(flyingFromText).contains(flyingFrom), flyingFrom + " should be visible on page");
            Assert.assertTrue(getValue(flyingToText).contains(flyingTo), flyingFrom + " should be visible on page");
            Assert.assertTrue(getValue(departingText).contains(month_date.format(departureDate)), month_date.format(departureDate) + " should be visible on page");
            Assert.assertTrue(getValue(returningText).contains(month_date.format(returningDate)), month_date.format(returningDate) + " should be visible on page");
        }
        else
        {
            SimpleDateFormat month_date = new SimpleDateFormat( "MMM d", Locale.ENGLISH);
            Assert.assertTrue(getText(flyingFromText).contains(flyingFrom), flyingFrom + " should be visible on page");
            Assert.assertTrue(getText(flyingToText).contains(flyingTo), flyingFrom + " should be visible on page");
            Assert.assertTrue(getText(departingText).contains(month_date.format(departureDate)), month_date.format(departureDate) + " should be visible on page");
            Assert.assertTrue(getText(returningText).contains(month_date.format(returningDate)), month_date.format(returningDate) + " should be visible on page");
        }
    }

    public void sortBy(String filterName)
    {
        selectByText(sortByDropdown, filterName);
    }

    public void clickOnNonStopCheckbox()
    {
        click(nonStopCheckbox);
    }

    public void selectFlightFromList()
    {
        selectFlight();
        sortBy("Price (Highest)");
        clickOnNonStopCheckbox();
        selectFlight();
        if(isElementPresent(noThanksButton))
            click(noThanksButton);
    }
    public void selectFlight()
    {
        if(isElementPresent(headerAccountMenuId))
        {
            showFlightDetails(1);
            waitForElementToBeVisible(flightChargeText, 30);
            StoreEnvVariable("Flight Price", getText(flightChargeText));
            selectFromList(1);
            click(selectThisFairButton);
        }
        else
        {
            selectFromList(1);
            waitForElementToBeVisible(flightDetailsText,30);
            Assert.assertTrue(isElementPresent(flightDetailsText), "Flight details area should visible on page");
            StoreEnvVariable("Flight Price", getText(flightChargeText));
            click(continueButton);
        }
        sleep(10);
    }

    public void verifyFlightInformation()
    {
        switchWindow();
        Assert.assertTrue(getPageURL().contains("Flight-Information"));
        Assert.assertTrue(getText(tripTotal).contains(getEnvVariable("Flight Price")));
    }

    public void selectFromList(int n)
    {
        By listEle = By.cssSelector("[data-test-id='listings'] li[data-test-id='offer-listing']:nth-child("+n+"), [id='flight-listing-container'] li[data-test-id='offer-listing']:nth-child("+n+") button[data-test-id='select-button']");
        click(listEle);
    }

    public void showFlightDetails(int n)
    {
        By listItem= By.cssSelector("[id='flight-listing-container'] li[data-test-id='offer-listing']:nth-child(1) span[class='show-flight-details']");
        click(listItem);
    }

    public void selectDateFromDatePicker(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("d-MMMM-yyyy");
        String formattedDate = formatter.format(date);
        String splitter[] = formattedDate.split("-");
        String month = splitter[1];
        String day = splitter[0];
        String year = splitter[2];

        while(!(getText(monthPicker).contains(month) && getText(monthPicker).contains(year))) {
            click(nextMonthButton);
        }
        int row=1;
        outerloop:
        for(; row<= 5;row++)
        {
            int column=1;
            By datePickerColumn= By.xpath("(//*[@class='uitk-date-picker-month'])[1]//tbody/tr["+row+"]/td["+column+" and not(@colspan)]");
            for(int i=0; i<finds(datePickerColumn).size(); i++)
            {
                WebElement ele= finds(datePickerColumn).get(i).findElement(By.xpath("button"));
                if(ele.getAttribute("data-day").equalsIgnoreCase(day))
                {
                    click(ele);
                    click(doneDate);
                    break outerloop;
                }
                column++;
            }
        }
    }
}
