package com.example.rf1.myapplication2;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class TransactionsFragmentTest extends ActivityInstrumentationTestCase2<MainActivity_> {

    public TransactionsFragmentTest() {
        super(MainActivity_.class);
    }

    @Before
    public void setUp() {
        getActivity();
    }

    public void testAddButton() {
        onView(withId(R.id.fab)).perform(click());
        onView(withText(R.string.sum)).check(matches(isDisplayed()));
    }
}