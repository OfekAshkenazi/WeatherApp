package Adapters;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.myapplication.MainActivity;
import com.example.android.myapplication.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.Date;

import DarkSkyService.Entities.DayDetails;
import DarkSkyService.Entities.DetailedWeatherInfo;
import DarkSkyService.WeatherInfo;

/**
 * Created by Ofek on 10-Dec-17.
 */

public class DaysListAdapter extends RecyclerView.Adapter<DaysListAdapter.DayVH> {
    ArrayList<DayDetails> data;
    ArrayList<HoursListAdapter> hoursListAdapters;
    private FoldingCell lastCell;
    public DaysListAdapter(ArrayList<DayDetails> data, AppCompatActivity context) {
        this.data = data;
        hoursListAdapters=new ArrayList<>();
        for (DayDetails day : data) {
            hoursListAdapters.add(new HoursListAdapter(day.getHoursInfo()));
        }
    }

    @Override
    public DayVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.days_list_item,parent,false);
        return new DayVH(view);
    }

    @Override
    public void onBindViewHolder(DayVH holder, int position) {
        DayDetails currentDay=data.get(position);
        Date date=currentDay.getDate();
        holder.dateTV_headline.setText(date.toString());
        holder.dateTV_content.setText(date.toString());
        holder.timezoneTV_headline.setText(MainActivity.timezone);
        holder.timezoneTV_content.setText(MainActivity.timezone);
        holder.tempTV_headline.setText(String.valueOf((int)currentDay.getTemperatureHigh()) + (char) 0x00B0);
        holder.tempTV_content.setText(String.valueOf((int)currentDay.getTemperatureHigh()) + (char) 0x00B0);
        int iconId=getResultIcon(currentDay.getIcon());
        holder.iconIV_headline.setImageResource(iconId);
        holder.iconIV_content.setImageResource(iconId);
        int rainPrecent= (int) (currentDay.getRainPercentage()*100);
        holder.rainTV_headline.setText(String.valueOf(rainPrecent));
        holder.rainTV_content.setText(String.valueOf(rainPrecent));
        holder.humidityTV_headline.setText(String.valueOf((int) (currentDay.getHumidity()*100)));
        holder.humidityTV_content.setText(String.valueOf((int) (currentDay.getHumidity()*100)));
        holder.summaryTV_headline.setText(currentDay.getSummary());
        holder.summaryTV_content.setText(currentDay.getSummary());
        holder.hoursList.setAdapter(hoursListAdapters.get(position));
    }

    private int getResultIcon(String icon) {
        switch (icon){
            case "clear-day":
                return R.drawable.ic_005_day_clear;
            case "clear-night":
                return R.drawable.ic_004_night_clear;
            case "rain":
                return R.drawable.ic_003_rain;
            case "snow":
                return R.drawable.ic_002_snowing;
            case "sleet":
                return R.drawable.ic_009_sleet;
            case "wind":
                return R.drawable.ic_001_wind;
            case "fog":
                return R.drawable.ic_008_fog;
            case "cloudy":
                return R.drawable.ic_clouds;
            case "partly-cloudy-day":
                return R.drawable.ic_007_cloudy_day;
            case "partly-cloudy-night":
                return R.drawable.ic_006_cloudy_night;
            default:
                return R.drawable.ic_007_cloudy_day;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DayVH extends RecyclerView.ViewHolder{
        FoldingCell cell;
        TextView dateTV_headline,dateTV_content,timezoneTV_headline,timezoneTV_content,tempTV_headline,tempTV_content;
        TextView humidityTV_headline,humidityTV_content,rainTV_headline,rainTV_content;
        ImageView iconIV_headline,iconIV_content;
        TextView summaryTV_headline,summaryTV_content;
        RecyclerView hoursList;
        LinearLayout hFragContainer;
        public DayVH(View itemView) {
            super(itemView);
            cell= itemView.findViewById(R.id.cell);
            cell.initialize(1000, R.color.colorPrimary, 5);
            setViews();
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (lastCell!=null&&lastCell!=cell){
                        lastCell.fold(true);
                    }
                    if (getAdapterPosition()==data.size()-1)
                        cell.toggle(true);
                    else
                        cell.toggle(false);
                    lastCell= (FoldingCell) view;
                    RecyclerView recyclerView= (RecyclerView) view.getParent();
                    recyclerView.scrollToPosition(getAdapterPosition());
                }
            });
        }
        private void setViews() {
            dateTV_content=cell.findViewById(R.id.dateTV_content);
            dateTV_headline=cell.findViewById(R.id.dateTV_headline);
            timezoneTV_headline=cell.findViewById(R.id.timezoneTV_headline);
            timezoneTV_content=cell.findViewById(R.id.timezoneTV_content);
            tempTV_content=cell.findViewById(R.id.tempTV_content);
            tempTV_headline=cell.findViewById(R.id.tempTV_headline);
            humidityTV_headline=cell.findViewById(R.id.humidityTV_headline);
            humidityTV_content=cell.findViewById(R.id.humidityTV_content);
            rainTV_headline=cell.findViewById(R.id.rainTV_headline);
            rainTV_content=cell.findViewById(R.id.rainTV_content);
            iconIV_headline=cell.findViewById(R.id.iconIV_headline);
            iconIV_content=cell.findViewById(R.id.iconIV_content);
            hoursList=cell.findViewById(R.id.hourlyList);
            summaryTV_content=cell.findViewById(R.id.summaryTV_content);
            summaryTV_headline=cell.findViewById(R.id.summaryTV_headline);
            hoursList.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
