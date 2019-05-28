package com.example.googlebooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.googlebooks.entity.BookList;
import com.example.googlebooks.entity.VolumeInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookViewPagerAdapter extends PagerAdapter {

    LayoutInflater layoutInflater;
    Context context;
    BookList bookList;

    public BookViewPagerAdapter(BookList bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bookList.getItems().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @BindView(R.id.book_cover)
    ImageView bookCover;

    @BindView(R.id.book_published_date)
    TextView bookPublishedDate;

    @BindView(R.id.authors)
    TextView authors;

    @BindView(R.id.description)
    TextView description;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.book_detail_page, container, false);
        ButterKnife.bind(this, view);
        VolumeInfo volumeInfo = bookList.getItems().get(position).getVolumeInfo();
        String imageUrl = "";
        if (bookList.getItems().get(position).getVolumeInfo().getImageLinks() != null) {
            imageUrl = bookList.getItems().get(position).getVolumeInfo().getImageLinks().getThumbnail();
        }
        Picasso.get()
                .load(imageUrl != "" ? imageUrl : "emptypath")
                .placeholder(R.mipmap.ic_launcher)
                .into(bookCover);

        bookPublishedDate.setText(volumeInfo.getPublishedDate());
        authors.setText(formatAuthors(volumeInfo.getAuthors()));
        description.setText(volumeInfo.getDescription());

        container.addView(view);

        return view;
    }

    private String formatAuthors(List<String> authors) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String author : authors) {
            stringBuilder.append(author);
            stringBuilder.append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
