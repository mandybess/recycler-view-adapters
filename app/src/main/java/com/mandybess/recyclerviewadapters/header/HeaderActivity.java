package com.mandybess.recyclerviewadapters.header;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import com.mandybess.recyclerviewadapters.Book;
import com.mandybess.recyclerviewadapters.R;

public class HeaderActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private LinearLayoutManager layoutManager;
  private BookHeaderAdapter headerAdapter;
  private View header;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layout);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(getClass().getSimpleName());

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    layoutManager = new LinearLayoutManager(this);
    headerAdapter = new BookHeaderAdapter(this);

    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(headerAdapter);
    headerAdapter.setItems(Book.makeBooks(30));

    //If you want to set the recyclerView as the parent ViewGroup for the header, you must inflate
    //the header *after* the RecyclerView has been inflated *AND* it's LayoutManager has been set
    header = LayoutInflater.from(this).inflate(R.layout.list_item_header, recyclerView, false);
    headerAdapter.setHeader(header);
  }

}
