package com.example.googlebooks;

import com.example.googlebooks.entity.BookList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BooksService {

    @GET("volumes?q=android&startIndex=0&maxResults=40")
    Call<BookList> getBooks();
}
