package binay.bemoapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import binay.bemoapplication.R;
import binay.bemoapplication.ui.model.FriendlyMessage;

/**
 * Created by Binay on 15/06/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomView> {

    private Context mContext;
    private List<FriendlyMessage> mFriendlyMessageList;

    public MessageAdapter(Context mContext, List<FriendlyMessage> friendlyMessageList) {
        this.mContext = mContext;
        this.mFriendlyMessageList = friendlyMessageList;
    }

    @Override
    public CustomView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomView(LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomView holder, int position) {
        FriendlyMessage friendlyMessage = mFriendlyMessageList.get(position);
        holder.mMessageTextView.setText("" + friendlyMessage.getText());
        holder.mNameTextView.setText("" + friendlyMessage.getName());
    }

    @Override
    public int getItemCount() {
        return mFriendlyMessageList.size();
    }

    public class CustomView extends RecyclerView.ViewHolder {
        TextView mMessageTextView, mNameTextView;

        public CustomView(View itemView) {
            super(itemView);
            mMessageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
            mNameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }
    }
}
