package com.instawork.orbitz.tests;

import com.instawork.orbitz.BaseTest;
import org.testng.annotations.Test;

import java.util.Date;

public class FlightBookingTestCase extends BaseTest {
    @Test
    public void flightBooking()
    {
        Date date1,date2;
        cPage.openUrl(url);
        fPage.selectFlightTab();
        fPage.clickOnRoundTripTab();
        fPage.enterLeavingFromCity(flying_from);
        fPage.enterGoingToCity(flying_to);
        date1= cPage.calculateDateAfterWeek(2);
        fPage.selectDepartureDate(date1);
        date2= cPage.calculateDateAfterWeek(3);
        fPage.selectReturningDate(date2);
        fPage.clickOnSearchButton();
        fPage.verifyFlightDetails(flying_from, flying_to, date1, date2);
        fPage.clickOnNonStopCheckbox();
        fPage.sortBy("Price (Highest)");
        fPage.selectFlightFromList();
        fPage.verifyFlightInformation();
    }
}
