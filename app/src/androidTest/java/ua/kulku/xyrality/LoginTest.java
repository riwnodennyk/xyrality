package ua.kulku.xyrality;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ua.kulku.xyrality.ui.LoginActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
public class LoginTest {
    @Rule
    public final ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void flow() {
        onView(withHint(R.string.prompt_email)).perform(typeText("android.test@xyrality.com"));
        onView(withHint(R.string.prompt_password)).perform(typeText("password"));
        onView(withText(R.string.action_sign_in)).perform(click());
        onData(withText("Deutsch 15 (empfohlen)")).check(matches(isDisplayed()));
    }
}