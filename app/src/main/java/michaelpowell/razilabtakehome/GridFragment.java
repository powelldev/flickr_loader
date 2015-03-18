package michaelpowell.razilabtakehome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GridFragment extends Fragment {

  private RecyclerView mRecyclerView;

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_grid, container, false);
    mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mRecyclerView.setLayoutManager(layoutManager);
    return rootView;
  }

  public void setData(List<String> data) {
    if (data.size() > 0) {
      mRecyclerView.setAdapter(new ImageAdapter(data));
    }
  }

  class ImageAdapter extends RecyclerView.Adapter<ImageHolder> {

    final List<String> images;

    public ImageAdapter(List<String> images) {
      this.images = images;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
      View listItemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_image, viewGroup, false);
      return new ImageHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(ImageHolder imageHolder, int position) {
      Picasso.with(getActivity())
          .load(images.get(position))
          .into(imageHolder.imageView);
    }

    @Override
    public int getItemCount() {
      return images.size();
    }
  }

  static class ImageHolder extends RecyclerView.ViewHolder {

    protected final ImageView imageView;

    public ImageHolder(View view) {
      super(view);
      imageView = (ImageView) view.findViewById(R.id.image);
    }
  }
}
