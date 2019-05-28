package com.example.googlebooks;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.googlebooks.entity.BookList;
import com.example.googlebooks.entity.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BooksViewHolder> {

    public static final String EXTRA_BOOK_ID = "com.example.googlebooks.EXTRA_BOOK_ID";

    BookList myBookList;

    int indexCounter = 1;

    public BookAdapter(BookList myBookList) {
        this.myBookList = myBookList;
    }

    public void updateBookList(BookList newCallData) {
        List<Item> mergeList = myBookList.getItems();
        mergeList.addAll(newCallData.getItems());
        myBookList.setItems(mergeList);
        indexCounter++;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_item, viewGroup, false);

        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder booksViewHolder, final int position) {
        List<Item> myItemList = myBookList.getItems();

        String imageUrl = "";
        if (myItemList.get(position).getVolumeInfo().getImageLinks() != null) {
            imageUrl = myItemList.get(position).getVolumeInfo().getImageLinks().getThumbnail();
        }
        String title = myItemList.get(position).getVolumeInfo().getTitle();
        String publishedDate = myItemList.get(position).getVolumeInfo().getPublishedDate();

        booksViewHolder.titleTV.setText(title);
        booksViewHolder.publishedDateTV.setText(publishedDate);
        booksViewHolder.bookLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookDetailsActivity.class);
                intent.putExtra(EXTRA_BOOK_ID, myBookList.getItems().get(position).getId());
                view.getContext().startActivity(intent);
            }
        });

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

        @BindView(R.id.book_layout)
        LinearLayout bookLinearLayout;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
