package com.example.googlebooks;


import android.os.Bundle;
import android.widget.Toast;

import com.example.googlebooks.entity.BookList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.books_rv)
    RecyclerView booksRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        booksRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                if (recyclerView.canScrollVertically(1)) {
//                    Toast.makeText(MainActivity.this, "Call with new Index", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        loadBooks();
    }

    public void loadBooks() {
        BooksService service = RetrofitClient.getRetrofitInstance().create(BooksService.class);
        Call<BookList> call = service.getBooks();
        call.enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                displayBooks(response.body());
            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayBooks(BookList data) {
        booksRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        booksRecyclerView.setAdapter(new BookAdapter(data));
    }
}
