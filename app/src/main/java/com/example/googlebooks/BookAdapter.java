package com.example.googlebooks;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.googlebooks.entity.BookList;
import com.example.googlebooks.entity.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BooksViewHolder> {

    BookList myBookList;

    public BookAdapter(BookList myBookList) {
        this.myBookList = myBookList;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_item, viewGroup, false);

        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder booksViewHolder, int position) {
        List<Item> myItemList = myBookList.getItems();

        String imageUrl = "";
        if (myItemList.get(position).getVolumeInfo().getImageLinks() != null) {
            imageUrl = myItemList.get(position).getVolumeInfo().getImageLinks().getThumbnail();
        }
        String title = myItemList.get(position).getVolumeInfo().getTitle();
        String publishedDate = myItemList.get(position).getVolumeInfo().getPublishedDate();

        booksViewHolder.titleTV.setText(title);
        booksViewHolder.publishedDateTV.setText(publishedDate);

        Picasso.get()
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(booksViewHolder.thumbnailIV);

    }

    @Override
    public int getItemCount() {
        return myBookList.getItems().size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail_iv)
        ImageView thumbnailIV;

        @BindView(R.id.book_title_tv)
        TextView titleTV;

        @BindView(R.id.book_publish_date_tv)
        TextView publishedDateTV;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
