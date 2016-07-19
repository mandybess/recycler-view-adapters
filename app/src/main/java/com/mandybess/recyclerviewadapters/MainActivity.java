package com.mandybess.recyclerviewadapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.mandybess.recyclerviewadapters.header.HeaderActivity;
import com.mandybess.recyclerviewadapters.multitype.MultiTypeActivity;
import com.mandybess.recyclerviewadapters.section.SectionActivity;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button header = (Button) findViewById(R.id.header_button);
    header.setOnClickListener(navigateTo(HeaderActivity.class));

    Button multiSelect = (Button) findViewById(R.id.multitype_button);
    multiSelect.setOnClickListener(navigateTo(MultiTypeActivity.class));

    Button section = (Button) findViewById(R.id.section_button);
    section.setOnClickListener(navigateTo(SectionActivity.class));

  }

  public OnClickListener navigateTo(final Class<?> clazz) {
    return new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
      }
    };
  }
}

