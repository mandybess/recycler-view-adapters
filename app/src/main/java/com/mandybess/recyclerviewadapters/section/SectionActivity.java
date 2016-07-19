package com.mandybess.recyclerviewadapters.section;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.mandybess.recyclerviewadapters.Book;
import com.mandybess.recyclerviewadapters.R;

public class SectionActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private LinearLayoutManager layoutManager;
  private SectionAdapter sectionAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layout);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(getClass().getSimpleName());

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    layoutManager = new LinearLayoutManager(this);
    sectionAdapter = new SectionAdapter();

    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(sectionAdapter);

    sectionAdapter.addSection("Book Section", new BookSection(this, Book.makeBooks(20)));
  }

}
