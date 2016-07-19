package com.mandybess.recyclerviewadapters.header;

import android.app.Application;
import android.content.Context;
import android.view.View;
import com.mandybess.recyclerviewadapters.BuildConfig;
import com.mandybess.recyclerviewadapters.header.HeaderAdapter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.mandybess.recyclerviewadapters.header.HeaderAdapter.TYPE_HEADER;
import static com.mandybess.recyclerviewadapters.header.HeaderAdapter.TYPE_ITEM;
import static com.mandybess.recyclerviewadapters.testUtils.TestDataFactory.makeList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public abstract class BaseHeaderAdapterTest<T, A extends HeaderAdapter> {

  public static final int LIST_LENGTH = 50;
  public static final int HEADER_COUNT = 1;

  protected Context context;
  private List<T> emptyList;
  private List<T> fullList;

  @Before
  public void setUp() throws Exception {
    Application application = RuntimeEnvironment.application;
    assertNotNull(application);

    context = application;
    emptyList = new ArrayList<>();
    fullList = makeList(makePojo(), LIST_LENGTH);
  }

  @Test
  public void testGetItemCount_withItems_noHeader() {
    A adapter = makeAdapter();
    adapter.setItems(fullList);

    assertEquals(LIST_LENGTH, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_withOutItems_noHeader() {
    A adapter = makeAdapter();

    assertEquals(0, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_withZeroItems_noHeader() {
    A adapter = makeAdapter();
    adapter.setItems(emptyList);

    assertEquals(0, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_withItems_withHeader() {
    A adapter = makeAdapter();
    adapter.setItems(fullList);
    adapter.setHeader(new View(context));

    assertEquals(LIST_LENGTH + HEADER_COUNT, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_withOutItems_withHeader() {
    A adapter = makeAdapter();
    adapter.setHeader(new View(context));

    assertEquals(HEADER_COUNT, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_withZeroItems_withHeader() {
    A adapter = makeAdapter();
    adapter.setItems(emptyList);
    adapter.setHeader(new View(context));

    assertEquals(HEADER_COUNT, adapter.getItemCount());
  }

  @Test
  public void testSetItems_onceWithValidList_noHeader() {
    A adapter = makeAdapter();
    adapter.setItems(fullList);

    //confirm setItems via the count
    assertEquals(LIST_LENGTH, adapter.getItemCount());
  }

  @Test
  public void testSetItems_onceWithValidList_withHeader() {
    A adapter = makeAdapter();
    adapter.setItems(fullList);
    adapter.setHeader(new View(context));

    //confirm setItems via the count
    assertEquals(LIST_LENGTH + HEADER_COUNT, adapter.getItemCount());
  }

  @Test
  public void testSetItems_twiceWithSameList_noHeader() {
    A adapter = makeAdapter();
    List<T> list = fullList;
    adapter.setItems(list);

    //add item to list && confirm list size has increased
    list.add(makePojo());
    assertEquals(LIST_LENGTH + 1, list.size());

    //confirm setItems correctly updates backing list
    adapter.setItems(list);
    assertEquals(LIST_LENGTH + 1, adapter.getItemCount());
  }

  @Test
  public void testSetItems_twiceWithSameList_withHeader() {
    A adapter = makeAdapter();
    List<T> list = fullList;
    adapter.setItems(list);
    adapter.setHeader(new View(context));

    //add item to list && confirm list size has increased
    list.add(makePojo());
    assertEquals(LIST_LENGTH + 1, list.size());

    //confirm setItems correctly updates backing list
    adapter.setItems(list);
    assertEquals(LIST_LENGTH + HEADER_COUNT + 1, adapter.getItemCount());
  }

  @Test
  public void testSetItems_twiceWithDifferentList_withHeader() {
    A adapter = makeAdapter();
    List<T> list = fullList;
    adapter.setItems(list);
    adapter.setHeader(new View(context));

    //confirm initial setItems correctly updates backing list
    assertEquals(LIST_LENGTH + HEADER_COUNT, adapter.getItemCount());

    List<T> newList = fullList;
    newList.add(makePojo());

    //confirm new list size
    assertEquals(LIST_LENGTH + 1, newList.size());

    //confirm setItems correctly updates backing list to newList
    adapter.setItems(newList);
    assertEquals(LIST_LENGTH + HEADER_COUNT + 1, adapter.getItemCount());
  }

  @Test
  public void testSetItems_twiceWithDifferentList_noHeader() {
    A adapter = makeAdapter();
    List<T> list = fullList;
    adapter.setItems(list);

    //confirm initial setItems correctly updates backing list
    assertEquals(LIST_LENGTH, adapter.getItemCount());

    List<T> newList = fullList;
    newList.add(makePojo());

    //confirm new list size
    assertEquals(LIST_LENGTH + 1, newList.size());

    //confirm setItems correctly updates backing list to newList
    adapter.setItems(newList);
    assertEquals(LIST_LENGTH + 1, adapter.getItemCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetItems_onceWithNullList_noHeader() {
    A adapter = makeAdapter();
    List<T> list = fullList;
    adapter.setItems(list);

    //set list to null & confirm null list was ignored and original data remains
    list = null;
    adapter.setItems(list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetItems_onceWithNullList_withHeader() {
    A adapter = makeAdapter();
    List<T> list = fullList;
    adapter.setItems(list);
    adapter.setHeader(new View(context));

    //set list to null & confirm null list was ignored and original data remains
    list = null;
    adapter.setItems(list);
  }

  @Test
  public void testOnCreateViewHolder_headerType() {
    A adapter = makeAdapter();
    adapter.setHeader(new View(context));
    assertNotNull(adapter.onCreateViewHolder(null, TYPE_HEADER));
  }

  @Test
  public void testOnCreateViewHolder_itemType() {
    A adapter = makeAdapter();
    assertNotNull(adapter.onCreateViewHolder(null, TYPE_ITEM));
  }

  public abstract A makeAdapter();

  public abstract T makePojo();

}
