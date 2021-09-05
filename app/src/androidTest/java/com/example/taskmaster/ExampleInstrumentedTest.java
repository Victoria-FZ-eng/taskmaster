package com.example.taskmaster;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.util.EnumSet.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.taskmaster", appContext.getPackageName());
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void checkAllDisplayed() {
        onView(withId(R.id.tskUser)).check((matches(isDisplayed())));
        onView(withId(R.id.btn1)).check((matches(isDisplayed())));
        onView(withId(R.id.textView8)).check((matches(isDisplayed())));
        onView(withId(R.id.recViewTask)).check((matches(isDisplayed())));
        onView(withId(R.id.settings)).check((matches(isDisplayed())));
    }
    //withText
    @Test
    public void settingsButton() {
        onView(withId(R.id.settings)).check((matches(isDisplayed()))).perform(click());
        onView(withId(R.id.saveName)).check((matches(isDisplayed()))).perform(click());
    }
    @Test
    public void addTaskButton() {
        onView(withId(R.id.btn1)).check((matches(isDisplayed()))).perform(click());
        onView(withId(R.id.btn3)).check((matches(isDisplayed()))).perform(click());
    }
    @Test
    public void fragTitleButton() {
        onData(withId(R.id.recViewTask)).perform(click());
    }


//    @Rule
//    public ActivityScenarioRule<addTask> activityAddTaskRule =
//            new ActivityScenarioRule<>(addTask.class);
//
//    @Test
//    public void testAddingTask(){
//        onView(withId(R.id.btn3)).check((matches(isDisplayed()))).perform(click());
//    }




}

