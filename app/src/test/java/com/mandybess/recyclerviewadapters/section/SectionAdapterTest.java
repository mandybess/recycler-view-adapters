package com.mandybess.recyclerviewadapters.section;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.LinearLayout;
import com.mandybess.recyclerviewadapters.BuildConfig;
import com.mandybess.recyclerviewadapters.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.mandybess.recyclerviewadapters.section.SectionAdapter.VIEW_TYPE_HEADER;
import static com.mandybess.recyclerviewadapters.section.SectionAdapter.VIEW_TYPE_QTY;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SectionAdapterTest {

  private Context context;
  private FakeSectionA sectionA;
  private FakeSectionB sectionB;
  private LinearLayout viewGroup;
  private View view;

  @Before
  public void setUp() throws Exception {
    Application application = RuntimeEnvironment.application;
    assertNotNull(application);

    context = application;
    viewGroup = new LinearLayout(context);
    view = new View(context);

    sectionA = new FakeSectionA(R.layout.list_item_header, R.layout.list_item_footer,
        R.layout.list_item_book);
    sectionB = new FakeSectionB(R.layout.list_item_header, R.layout.list_item_footer,
        R.layout.list_item_book);
  }

  @Test
  public void testGetItemViewType() {
    SectionAdapter adapter = new SectionAdapter();
    adapter.addSection("sectionA", sectionA);
    adapter.addSection("sectionB", sectionB);
    adapter.addSection("sectionC", sectionB);

    //position = 0 is the header for sectionA
    //sectionA was the first section added, so the viewType = VIEW_TYPE_HEADER + (VIEW_TYPE_QTY * 0)
    assertEquals(VIEW_TYPE_HEADER, adapter.getItemViewType(0));

    //position = 33 is the header for sectionB
    //sectionB was the second section added, so the viewType = VIEW_TYPE_HEADER + (VIEW_TYPE_QTY * 1)
    assertEquals(VIEW_TYPE_HEADER + VIEW_TYPE_QTY, adapter.getItemViewType(32));

    //position = 64 is the header for sectionC
    //sectionC was the third section added, so the viewType = VIEW_TYPE_HEADER + (VIEW_TYPE_QTY * 2)
    assertEquals(VIEW_TYPE_HEADER + (VIEW_TYPE_QTY * 2), adapter.getItemViewType(64));

    //remove first section
    adapter.removeSection("sectionA");

    //after removing sectionA, position 0 now equals the header of sectionB
    assertEquals(VIEW_TYPE_HEADER + VIEW_TYPE_QTY, adapter.getItemViewType(0));
  }

  @Test
  public void testOnCreateViewHolder() {
    SectionAdapter adapter = new SectionAdapter();
    adapter.addSection("sectionA", sectionA);
    adapter.addSection("sectionB", sectionB);
    adapter.addSection("sectionC", sectionB);

    //viewType for sectionA header == 0
    assertThat(adapter.onCreateViewHolder(viewGroup, 0), instanceOf(FakeHeaderViewHolderA.class));

    //viewType for sectionA footer == 1
    assertThat(adapter.onCreateViewHolder(viewGroup, 1), instanceOf(FakeFooterViewHolderA.class));

    //viewType for sectionA item == 2
    assertThat(adapter.onCreateViewHolder(viewGroup, 2), instanceOf(FakeItemViewHolderA.class));

    //viewType for sectionB header == 3
    assertThat(adapter.onCreateViewHolder(viewGroup, 3), instanceOf(FakeHeaderViewHolderB.class));

    //viewType for sectionB footer == 4
    assertThat(adapter.onCreateViewHolder(viewGroup, 4), instanceOf(FakeFooterViewHolderB.class));

    //viewType for sectionB item == 5
    assertThat(adapter.onCreateViewHolder(viewGroup, 5), instanceOf(FakeItemViewHolderB.class));

    //viewType for sectionC header == 6
    assertThat(adapter.onCreateViewHolder(viewGroup, 6), instanceOf(FakeHeaderViewHolderB.class));

    //viewType for sectionB footer == 7
    assertThat(adapter.onCreateViewHolder(viewGroup, 7), instanceOf(FakeFooterViewHolderB.class));

    //viewType for sectionB item == 8
    assertThat(adapter.onCreateViewHolder(viewGroup, 8), instanceOf(FakeItemViewHolderB.class));
  }

  @Test
  public void testOnBindViewHolder_onBindHeader() {
    SectionAdapter adapter = new SectionAdapter();
    adapter.addSection("sectionA", sectionA);
    adapter.addSection("sectionB", sectionB);

    //position = 0 is the header for sectionA
    adapter.onBindViewHolder(null, 0);
    assertTrue(sectionA.onBindHeaderCalled);
    assertFalse(sectionA.onBindItemCalled);
    assertFalse(sectionA.onBindFooterCalled);

    //ensure none of sectionB onBind methods were called
    assertFalse(sectionB.onBindHeaderCalled);
    assertFalse(sectionB.onBindItemCalled);
    assertFalse(sectionB.onBindFooterCalled);
  }

  @Test
  public void testOnBindViewHolder_onBindItem() {
    SectionAdapter adapter = new SectionAdapter();
    adapter.addSection("sectionA", sectionA);
    adapter.addSection("sectionB", sectionB);

    //position = 1 is the item for sectionA
    adapter.onBindViewHolder(null, 1);
    assertFalse(sectionA.onBindHeaderCalled);
    assertTrue(sectionA.onBindItemCalled);
    assertFalse(sectionA.onBindFooterCalled);

    //ensure none of sectionB onBind methods were called
    assertFalse(sectionB.onBindHeaderCalled);
    assertFalse(sectionB.onBindItemCalled);
    assertFalse(sectionB.onBindFooterCalled);
  }

  @Test
  public void testOnBindViewHolder_onBindFooter() {
    SectionAdapter adapter = new SectionAdapter();
    adapter.addSection("sectionA", sectionA);
    adapter.addSection("sectionB", sectionB);

    //position = 31 is the footer for sectionA
    adapter.onBindViewHolder(null, 31);
    assertFalse(sectionA.onBindHeaderCalled);
    assertFalse(sectionA.onBindItemCalled);
    assertTrue(sectionA.onBindFooterCalled);

    //ensure none of sectionB onBind methods were called
    assertFalse(sectionB.onBindHeaderCalled);
    assertFalse(sectionB.onBindItemCalled);
    assertFalse(sectionB.onBindFooterCalled);
  }

  @Test
  public void testGetItemCount_withNoSection() {
    SectionAdapter adapter = new SectionAdapter();

    assertEquals(0, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_withHeadersAndFooters() {
    SectionAdapter adapter = new SectionAdapter();
    adapter.addSection("sectionA", sectionA);

    assertEquals(32, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_afterRemovingSection() {
    SectionAdapter adapter = new SectionAdapter();
    adapter.addSection("sectionA", sectionA);

    //initial
    assertEquals(32, adapter.getItemCount());

    //remove section
    adapter.removeSection("sectionA");
    assertEquals(0, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_withHeadersAndNoFooters() {
    SectionAdapter adapter = new SectionAdapter();
    Section sectionC =
        new FakeSectionA(R.layout.list_item_header, null, R.layout.list_item_book);
    adapter.addSection("sectionC", sectionC);

    assertEquals(31, adapter.getItemCount());
  }

  @Test
  public void testGetItemCount_withFooterAndNoHeaders() {
    SectionAdapter adapter = new SectionAdapter();
    Section sectionC =
        new FakeSectionA(null, R.layout.list_item_footer, R.layout.list_item_book);
    adapter.addSection("sectionC", sectionC);

    assertEquals(31, adapter.getItemCount());
  }

  @Test
  public void testGetSectionItemViewType() {
    SectionAdapter adapter = new SectionAdapter();
    adapter.addSection("sectionA", sectionA);
    adapter.addSection("sectionB", sectionB);
    adapter.addSection("sectionC", sectionB);

    //position = 0 is the header for sectionA
    assertEquals(VIEW_TYPE_HEADER, adapter.getSectionItemViewType(0));

    //position = 33 is the header for sectionB
    assertEquals(VIEW_TYPE_HEADER, adapter.getSectionItemViewType(32));

    //position = 64 is the header for sectionC
    assertEquals(VIEW_TYPE_HEADER, adapter.getSectionItemViewType(64));
  }

  @Test
  public void testGetSectionForPosition() {
    SectionAdapter adapter = new SectionAdapter();
    adapter.addSection("sectionA", sectionA);
    adapter.addSection("sectionB", sectionB);

    //sectionA positions [0,32]
    for (int i = 0; i < 32; i++) {
      assertEquals(sectionA, adapter.getSectionForPosition(i));
    }

    //sectionB positions [32,64]
    for (int i = 32; i < 64; i++) {
      assertEquals(sectionB, adapter.getSectionForPosition(i));
    }
  }

  @Test
  public void testGetItemIndexForSection() {
    SectionAdapter adapter = new SectionAdapter();
    adapter.addSection("sectionA", sectionA);
    adapter.addSection("sectionB", sectionB);

    //header position for sectionA
    assertEquals(-1, adapter.getItemIndexForSection(0));

    //header position for sectionB
    assertEquals(-1, adapter.getItemIndexForSection(32));

    //item positions for sectionA
    for (int i = 0; i < 31; i++) {
      assertEquals(i - 1, adapter.getItemIndexForSection(i));
    }

    //item positions for sectionB
    int offsetFromSectionA = 32;
    for (int i = 32; i < 64; i++) {
      assertEquals(i - 1 - offsetFromSectionA, adapter.getItemIndexForSection(i));
    }

    //footer position for sectionA
    assertEquals(30, adapter.getItemIndexForSection(31));

    //footer position for sectionB
    assertEquals(30, adapter.getItemIndexForSection(63));
  }

  private class FakeSectionA extends Section {
    public boolean onBindHeaderCalled;
    public boolean onBindFooterCalled;
    public boolean onBindItemCalled;

    public FakeSectionA(@Nullable Integer headerResourceId, @Nullable Integer footerResourceId,
        @NonNull int itemResourceId) {
      super(headerResourceId, footerResourceId, itemResourceId);
    }

    @Override
    public int getContentItemsTotal() {
      return 30;
    }

    @Override
    public ViewHolder onCreateHeaderViewHolder(View view) {
      return new FakeHeaderViewHolderA(view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
      onBindHeaderCalled = true;
    }

    @Override
    public ViewHolder onCreateFooterViewHolder(View view) {
      return new FakeFooterViewHolderA(view);
    }

    @Override
    public void onBindFooterViewHolder(ViewHolder holder) {
      onBindFooterCalled = true;
    }

    @Override
    public ViewHolder onCreateItemViewHolder(View view) {
      return new FakeItemViewHolderA(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
      onBindItemCalled = true;
    }
  }

  private class FakeHeaderViewHolderA extends RecyclerView.ViewHolder {
    public FakeHeaderViewHolderA(View itemView) {
      super(itemView);
    }
  }

  private class FakeItemViewHolderA extends RecyclerView.ViewHolder {
    public FakeItemViewHolderA(View itemView) {
      super(itemView);
    }
  }

  private class FakeFooterViewHolderA extends RecyclerView.ViewHolder {
    public FakeFooterViewHolderA(View itemView) {
      super(itemView);
    }
  }

  private class FakeSectionB extends Section {
    public boolean onBindHeaderCalled;
    public boolean onBindFooterCalled;
    public boolean onBindItemCalled;

    public FakeSectionB(@Nullable Integer headerResourceId, @Nullable Integer footerResourceId,
        @NonNull int itemResourceId) {
      super(headerResourceId, footerResourceId, itemResourceId);
    }

    @Override
    public int getContentItemsTotal() {
      return 30;
    }

    @Override
    public ViewHolder onCreateHeaderViewHolder(View view) {
      return new FakeHeaderViewHolderB(view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
      onBindHeaderCalled = true;

    }

    @Override
    public ViewHolder onCreateFooterViewHolder(View view) {
      return new FakeFooterViewHolderB(view);
    }

    @Override
    public void onBindFooterViewHolder(ViewHolder holder) {
      onBindFooterCalled = true;

    }

    @Override
    public ViewHolder onCreateItemViewHolder(View view) {
      return new FakeItemViewHolderB(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
      onBindItemCalled = true;

    }
  }

  private class FakeHeaderViewHolderB extends RecyclerView.ViewHolder {
    public FakeHeaderViewHolderB(View itemView) {
      super(itemView);
    }
  }

  private class FakeItemViewHolderB extends RecyclerView.ViewHolder {
    public FakeItemViewHolderB(View itemView) {
      super(itemView);
    }
  }

  private class FakeFooterViewHolderB extends RecyclerView.ViewHolder {
    public FakeFooterViewHolderB(View itemView) {
      super(itemView);
    }
  }

}

