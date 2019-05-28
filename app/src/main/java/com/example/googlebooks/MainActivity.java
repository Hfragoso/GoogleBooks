package com.example.googlebooks;


import android.os.Bundle;
import android.widget.Toast;

import com.example.googlebooks.entity.BookList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_RESULTS = 40;

    @BindView(R.id.books_rv)
    RecyclerView booksRecyclerView;

    BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        booksRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (!recyclerView.canScrollVertically(1)) {
//                    Toast.makeText(MainActivity.this, "Call with new Index", Toast.LENGTH_SHORT).show();
//                }
//            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    int index = bookAdapter.indexCounter;
                    loadBooks(index);
                }
            }
        });
        loadBooks(0);
    }

    public void loadBooks(final int index) {
        BooksService service = RetrofitClient.getRetrofitInstance().create(BooksService.class);
        Call<BookList> call = service.getBooks(index, MAX_RESULTS);
        call.enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                if (index > 0) {
                    refreshData(response.body());
                } else {
                    displayBooks(response.body());
                }
            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayBooks(BookList data) {
        bookAdapter = new BookAdapter(data);
        booksRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        booksRecyclerView.setAdapter(bookAdapter);
    }


    private void refreshData(BookList data) {
        bookAdapter.updateBookList(data);
    }
}
