package de.fh_zwickau.ciapp.common;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.fh_zwickau.ciapp.R;
import de.fh_zwickau.ciapp.models.CI;

public class CIsAdapter extends RecyclerView.Adapter<CIsAdapter.ViewHolder> {
    private final List<CI> cIList;
    private OnFavoriteBtnClickedListener favoritesBtnListener;
    private OnDetailsBtnClickedListener detailsBtnListener;
    private boolean isDataListEmpty;


    public CIsAdapter(List<CI> cIList) {
        this.cIList = cIList;
        isDataListEmpty = cIList.isEmpty();
    }

    public void setOnFavoriteBtnListener(OnFavoriteBtnClickedListener listener) {
        this.favoritesBtnListener = listener;
    }

    public void setOnDetailsBtnClickedListener(OnDetailsBtnClickedListener listener) {
        this.detailsBtnListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == 0) {
            View emptyView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.empty_view, viewGroup, false);
            return new ViewHolder(emptyView);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ci_card, viewGroup, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (isDataListEmpty) {
            // Do nothing
        } else {
            CI ci = cIList.get(position);
            ImageView favoriteBtn =  viewHolder.getIsFavoriteImageView();

            viewHolder.getStoryTitleTextView().setText(ci.getTitle());
            viewHolder.getStoryContentTextView().setText(ci.getStory());

            if (ci.getIsFavorite()) {
                favoriteBtn.setImageResource(R.drawable.baseline_favorite);
            } else {
                favoriteBtn.setImageResource(R.drawable.baseline_favorite_border);
            }

            viewHolder.getDetailsBtn().setOnClickListener(view -> {
                if (detailsBtnListener != null) detailsBtnListener.onClicked(ci.getId());
            });

            favoriteBtn.setOnClickListener(view -> {
                if (favoritesBtnListener != null) favoritesBtnListener.onClicked(ci, position);
            });

        }
    }

    @Override
    public int getItemCount() {
        if (isDataListEmpty) {
            // Return 1 to display the empty view
            return 1;
        } else {
            return cIList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isDataListEmpty) {
            // Return a different view type for the empty view
            return 0;
        } else {
            return 1;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateDataList(List<CI> newList) {
        cIList.clear();
        cIList.addAll(newList);
        isDataListEmpty = cIList.isEmpty();
        notifyDataSetChanged();
    }

    public void removeItemFromDataList(int position) {
        cIList.remove(position);
        isDataListEmpty = cIList.isEmpty();
        notifyItemRemoved(position);
    }

    public interface OnFavoriteBtnClickedListener {
        void onClicked(CI ci, int position);
    }

    public interface OnDetailsBtnClickedListener {
        void onClicked(int id);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView storyTitle;
        private final ImageView isFavorite;
        private final TextView storyContent;
        private final Button detailsBtn;

        public ViewHolder(View view) {
            super(view);
            storyTitle = (TextView) view.findViewById(R.id.story_title);
            isFavorite = (ImageView) view.findViewById(R.id.is_favorite);
            storyContent = (TextView) view.findViewById(R.id.story_overview);
            detailsBtn = (Button) view.findViewById(R.id.show_details_button);
        }

        public TextView getStoryTitleTextView() {
            return storyTitle;
        }

        public TextView getStoryContentTextView() {
            return storyContent;
        }

        public ImageView getIsFavoriteImageView() {
            return isFavorite;
        }

        public Button getDetailsBtn() {
            return detailsBtn;
        }
    }
}