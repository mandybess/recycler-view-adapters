package com.mandybess.recyclerviewadapters.multitype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.mandybess.recyclerviewadapters.Book;
import com.mandybess.recyclerviewadapters.Movie;
import com.mandybess.recyclerviewadapters.R;

public class MultiTypeActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private LinearLayoutManager layoutManager;
  private MultiTypeAdapter multiTypeAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layout);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(getClass().getSimpleName());

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    layoutManager = new LinearLayoutManager(this);
    multiTypeAdapter = new MultiTypeAdapter();

    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(multiTypeAdapter);

    //AdapterItems will be ordered in the order they were added, if you want to manually ensure
    //a particular order pass a Comparator into the adapters constructor
    multiTypeAdapter.addItem(new BookAdapterItem(this, Book.makeBooks(5)));
    multiTypeAdapter.addItem(new MovieAdapterItem(this, Movie.makeMovies(5)));
  }
}
