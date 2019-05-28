package com.example.googlebooks;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.googlebooks.BookAdapter.EXTRA_BOOK_ID;

public class BookDetailsActivity extends AppCompatActivity {

    @BindView(R.id.book_id_tv)
    TextView tvBookId;

    String bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        ButterKnife.bind(this);
        receiveIntent();
        displayDetails();
    }

    private void displayDetails() {

    }

    private void receiveIntent() {
        Intent intent = getIntent();
        bookId = intent.getStringExtra(EXTRA_BOOK_ID);
    }
}
