package com.mandybess.recyclerviewadapters.header;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import com.mandybess.recyclerviewadapters.R;
import com.mandybess.recyclerviewadapters.header.HeaderAdapterActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HeaderAdapterTest {
  private RecyclerView recyclerView;
  @Rule
  public ActivityTestRule<HeaderAdapterActivity> activityRule =
      new ActivityTestRule<>(HeaderAdapterActivity.class);

  @Before
  public void setUp() {
    recyclerView =
        (RecyclerView) activityRule.getActivity().findViewById(R.id.recycler_view);
  }

  @Test
  public void testClickHeader() {
    onView(withId(R.id.recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
  }

  @Test
  public void testClickItem() {
    onView(withId(R.id.recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
  }
}
