package com.example.googlebooks;

import com.example.googlebooks.entity.BookList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksService {

    @GET("volumes?q=android&")
    Call<BookList> getBooks(@Query("startIndex") int index, @Query("maxResults") int maxResults);

}
