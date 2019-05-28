package com.example.googlebooks;


import android.content.Intent;
import android.os.Bundle;

import com.example.googlebooks.entity.BookList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.googlebooks.BookAdapter.EXTRA_BOOK_LIST;
import static com.example.googlebooks.BookAdapter.EXTRA_SELECTED_POSITION;


public class BookDetailsActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    BookViewPagerAdapter bookViewPagerAdapter;
    BookList bookList;
    int selectedBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        ButterKnife.bind(this);
        receiveIntent();
        displayDetails();
    }

    private void displayDetails() {
        bookViewPagerAdapter = new BookViewPagerAdapter(bookList, this);
        viewPager.setAdapter(bookViewPagerAdapter);
        viewPager.setCurrentItem(selectedBook);
    }

    private void receiveIntent() {
        Intent intent = getIntent();
        bookList = (BookList) intent.getSerializableExtra(EXTRA_BOOK_LIST);
        selectedBook = (int) intent.getSerializableExtra(EXTRA_SELECTED_POSITION);
    }
}
