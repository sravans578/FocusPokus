package com.example.focupokus;

import android.widget.ArrayAdapter;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ScoreAdapter extends ArrayAdapter<UserScoreBean>
{

    private ArrayList<UserScoreBean> scoreList;

    public ScoreAdapter(Context context, int textViewResourceId, ArrayList<UserScoreBean> scoreList)
    {
        super(context,textViewResourceId,scoreList);
        this.scoreList=scoreList;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if (v==null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.score_list,null);
        }
        UserScoreBean i= scoreList.get(position);

        if(i!=null)
        {
            TextView userName = v.findViewById(R.id.lsUserName);
            TextView UserScore = v.findViewById(R.id.lsUserScore);
            userName.setText(i.getName());
            UserScore.setText(String.valueOf(i.getScore()));
        }
        return v;
    }
}
