package com.example.rf1.myapplication2;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CategoryAdapter extends SelectableAdapter<CategoryAdapter.CardViewHolder> {

    List<Category> categories;
    private CardViewHolder.ClickListener clickListener;
    private Context context;
    private int lastPosition = -1;


    public CategoryAdapter(List<Category> categories, Context context, CardViewHolder.ClickListener clickListener) {

        this.categories = categories;
        this.clickListener = clickListener;
        this.context = context;

    }

    public CategoryAdapter(List<Category> categories) {

        this.categories = categories;

    }


    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

        return new CardViewHolder(itemView, clickListener);
    }


    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.catname.setText(category.categoryname);
        holder.catselected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
        //   setAnimation(holder.cardView, position);

    }

    public void removeItem(int position) {
        categories.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItems(List<Integer> positions) {
        // Reverse-sort the list
        Collections.sort(positions, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });


        while (!positions.isEmpty()) {
            if (positions.size() == 1) {
                removeItem(positions.get(0));
                positions.remove(0);
            } else {
                int count = 1;
                while (positions.size() > count && positions.get(count).equals(positions.get(count - 1) - 1)) {
                    ++count;
                }

                if (count == 1) {
                    removeItem(positions.get(0));
                } else {
                    removeRange(positions.get(count - 1), count);
                }

                for (int i = 0; i < count; ++i) {
                    positions.remove(0);
                }
            }
        }
    }

    private void removeRange(int positionStart, int itemCount) {
        for (int i = 0; i < itemCount; ++i) {
            categories.remove(positionStart);
        }

        notifyItemRangeRemoved(positionStart, itemCount);

    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        protected TextView catname;
        protected View catselected;
        protected CardView cardView;
        private ClickListener clickListener;

        public CardViewHolder(View itemView, ClickListener clickListener) {
            super(itemView);
            catname = (TextView) itemView.findViewById(R.id.catname);
            catselected = itemView.findViewById(R.id.catselected);
            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            cardView = (CardView) itemView.findViewById(R.id.catview);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClicked(getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (clickListener != null) {
                return clickListener.onItemLongClicked(getPosition());
            }
            return false;
        }

        public interface ClickListener {
            void onItemClicked(int position);

            boolean onItemLongClicked(int position);
        }
    }

}
