package com.external.NavigationDrawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imfree.imfree.R;

import java.util.List;

/**
 * Created by 종열 on 2015-05-31.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_MENUITEM = 1;
    private static final int TYPE_DEVIDER = 2;

    private List<NavigationDrawerItem> _menuItem;
    private NavigationDrawerCallback _drawerCallbacks;
    private int mSelectedPosition;
    private int mTouchedPosition = -1;

    private String _name;
    private int _profile;
    private String _email;

    public NavigationDrawerAdapter(List<NavigationDrawerItem> menuItem) {
        _name = "TEST";
        _email = "test@test";
        _profile = R.drawable.ic_aka;

        _menuItem = menuItem;
    }

    public void setNavigationDrawerCallbacks(NavigationDrawerCallback drawerCallbacks) {
        _drawerCallbacks = drawerCallbacks;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int _holderid;

        TextView _textView;
        View _devider;
        ImageView _profile;
        TextView _name;
        TextView _email;

        public ViewHolder(View itemView,int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created
            if(ViewType == TYPE_MENUITEM) {
                _textView = (TextView) itemView.findViewById(R.id.navigationDrawerItem); // Creating TextView object with the id of textView from item_row.xml
                _holderid = TYPE_MENUITEM;                                               // setting holder id as 1 as the object being populated are of type item row
            }
            else if (ViewType == TYPE_DEVIDER)
            {
                _devider = (View) itemView.findViewById(R.id.navigationDrawerDevider);
                _holderid = TYPE_DEVIDER;
            }
            else{
                _name = (TextView) itemView.findViewById(R.id.name);         // Creating Text View object from header.xml for name
                _email = (TextView) itemView.findViewById(R.id.email);       // Creating Text View object from header.xml for email
                _profile = (ImageView) itemView.findViewById(R.id.circleView);// Creating Image view object from header.xml for profile pic
                _holderid = TYPE_HEADER;                                                // Setting holder id = 0 as the object being populated are of type header view
            }
        }
    }

    @Override
    public NavigationDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_MENUITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigationdrawer_item,parent,false); //Inflating the layout
            ViewHolder vhItem = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view
            return vhItem; // Returning the created object
            //inflate your layout and pass it to view holder
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigationdrawer_header,parent,false); //Inflating the layout
            ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view
            return vhHeader; //returning the object created
        } else if (viewType == TYPE_DEVIDER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigationdrawer_devider,parent,false); //Inflating the layout
            ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view
            return vhHeader; //returning the object created
        }
        return null;
    }

    @Override
    public void onBindViewHolder(NavigationDrawerAdapter.ViewHolder viewHolder, final int i) {
        if(viewHolder._holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
            viewHolder._textView.setText(_menuItem.get(i - 1).getText());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (_drawerCallbacks != null)
                        _drawerCallbacks.onNavigationDrawerSelected(i);
                }
            });

            if (mSelectedPosition == i || mTouchedPosition == i) {
                viewHolder._textView.setTextColor(viewHolder.itemView.getContext().getResources().getColor(R.color.NavigationDrawerSelection));
            } else {
                viewHolder._textView.setTextColor(viewHolder.itemView.getContext().getResources().getColor(R.color.NavigationDrawerUnSelection));
            }
        }
        else if (viewHolder._holderid == 2){

        } else{
            viewHolder._profile.setImageResource(_profile);           // Similarly we set the resources for header view
            viewHolder._name.setText(_name);
            viewHolder._email.setText(_email);
        }
    }

    public void selectPosition(int position) {
        int lastPosition = mSelectedPosition;
        mSelectedPosition = position;
        notifyItemChanged(lastPosition);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return _menuItem.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if ( position == 0 ) {
            return TYPE_HEADER;
        }

        if ( position == 3)
            return TYPE_DEVIDER;

        if ( _menuItem.get(position - 1).equals("-")) {
            return TYPE_DEVIDER;
        }

        return TYPE_MENUITEM;
    }
}
