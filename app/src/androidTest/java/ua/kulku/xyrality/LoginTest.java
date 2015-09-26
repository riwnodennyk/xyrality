package ua.kulku.xyrality;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ua.kulku.xyrality.ui.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
public class LoginTest {
    @Rule
    public final ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void flow() {
        onView(withId(R.id.email)).perform(typeText("android.test@xyrality.com"));
        onView(withId(R.id.password)).perform(typeText("password"));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        onView(withText("Australia 7 (recommended)")).check(matches(isDisplayed())); //todo mock the response
    }
}