package com.mandybess.recyclerviewadapters.multitype;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.mandybess.recyclerviewadapters.BuildConfig;
import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for MultiTypeAdapter
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MultiTypeAdapterTest {

  @Mock AdapterItem adapterItemA;
  @Mock AdapterItem adapterItemB;

  @Mock ViewHolder viewHolderA;
  @Mock ViewHolder viewHolderB;

  @Mock Comparator<AdapterItem> comparator;

  public static final int LIST_LENGTH = 50;
  private Context context;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    Application application = RuntimeEnvironment.application;
    assertNotNull(application);

    context = application;
  }

  @Test
  public void testGetItemCount_withNoAdapters() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();

    int expected = 0;
    assertEquals(expected, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_withOneAdapter() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    adapter.addItem(adapterItemA);

    //mock count to be LIST_LENGTH
    when(adapterItemA.getItemCount()).thenReturn(LIST_LENGTH);

    int expected = LIST_LENGTH;
    assertEquals(expected, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_withMultipleAdapters() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    adapter.addItem(adapterItemA);
    adapter.addItem(adapterItemB);

    when(adapterItemA.getItemCount()).thenReturn(LIST_LENGTH);
    when(adapterItemB.getItemCount()).thenReturn(LIST_LENGTH);

    int expected = LIST_LENGTH * 2;
    assertEquals(expected, adapter.getItemCount());
  }

  @Test
  public void testGetItemViewType() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    adapter.addItem(adapterItemA);
    adapter.addItem(adapterItemB);

    when(adapterItemA.getItemCount()).thenReturn(LIST_LENGTH);
    when(adapterItemB.getItemCount()).thenReturn(LIST_LENGTH);

    int expected = adapterItemA.getItemViewType();
    assertEquals(expected, adapter.getItemViewType(0));

    int expected1 = adapterItemB.getItemViewType();
    assertEquals(expected1, adapter.getItemViewType(LIST_LENGTH));
  }

  @Test
  public void testOnCreateViewHolder() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    adapter.addItem(adapterItemA);
    adapter.addItem(adapterItemB);

    ViewGroup group = new LinearLayout(context);
    int adapterAViewType = 1;
    int adapterBViewType = 2;

    //mock view types
    when(adapterItemA.getItemViewType()).thenReturn(adapterAViewType);
    when(adapterItemB.getItemViewType()).thenReturn(adapterBViewType);

    adapter.onCreateViewHolder(group, adapterAViewType);
    verify(adapterItemA).onCreateViewHolder(group);

    adapter.onCreateViewHolder(group, adapterBViewType);
    verify(adapterItemB).onCreateViewHolder(group);
  }

  @Test
  public void testOnBindViewHolder() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    adapter.addItem(adapterItemA);
    adapter.addItem(adapterItemB);

    //onBind makes internal calls to getItemCount and getItemViewType, so we need to mock them
    when(adapterItemA.getItemCount()).thenReturn(LIST_LENGTH);
    when(adapterItemB.getItemCount()).thenReturn(LIST_LENGTH);
    when(adapterItemA.getItemViewType()).thenReturn(1);
    when(adapterItemB.getItemViewType()).thenReturn(2);

    //position 1 == first item in adapterItemA
    adapter.onBindViewHolder(viewHolderA, 1);
    verify(adapterItemA).onBindViewHolder(viewHolderA, 1);

    //position 51 == first item in adapterItemB
    adapter.onBindViewHolder(viewHolderB, LIST_LENGTH + 1);
    verify(adapterItemB).onBindViewHolder(viewHolderB, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetItems_nullItems() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    adapter.addItems(null);
  }

  @Test
  public void testComparator() {
    //order: B, A
    when(comparator.compare(adapterItemB, adapterItemA)).thenReturn(-1);

    //stub list sizes
    when(adapterItemA.getItemCount()).thenReturn(LIST_LENGTH);
    when(adapterItemB.getItemCount()).thenReturn(LIST_LENGTH);

    MultiTypeAdapter adapter = new MultiTypeAdapter(comparator);
    adapter.addItem(adapterItemA);
    adapter.addItem(adapterItemB);

    assertEquals(adapter.getAdapterItems().get(0), adapterItemB);
    assertEquals(adapter.getAdapterItems().get(1), adapterItemA);
  }
}


