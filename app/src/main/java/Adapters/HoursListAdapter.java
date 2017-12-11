package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.android.myapplication.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import DarkSkyService.Entities.DetailedWeatherInfo;

/**
 * Created by Ofek on 10-Dec-17.
 */

public class HoursListAdapter extends RecyclerView.Adapter<HoursListAdapter.HourVH>{

    ArrayList<DetailedWeatherInfo> data;

    public HoursListAdapter(ArrayList<DetailedWeatherInfo> data) {
        this.data = data;
    }

    @Override
    public HourVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.hours_list_item,parent,false);
        return new HourVH(view);
    }

    @Override
    public void onBindViewHolder(HourVH holder, int position) {
        DetailedWeatherInfo hour=data.get(position);
        Date date=hour.getDate();


        String dateMin,dateHours;
        if (date.getHours()<10)
            dateHours='0'+ String.valueOf(date.getHours());
        else
            dateHours= String.valueOf(date.getHours());
        if (date.getMinutes()<10){
            dateMin='0' + String.valueOf(date.getMinutes());
        }
        else {
            dateMin= String.valueOf(date.getMinutes());
        }
        holder.timeTV.setText(dateHours+':'+dateMin);
        holder.icon.setImageResource(getResultIcon(hour.getIcon()));
        holder.tempTV.setText(String.valueOf((int)hour.getTemperature())+ (char) 0x00B0);
        holder.summaryTV.setText(hour.getSummary());
        holder.rainTV.setText(""+(int) hour.getRainPercentage());
        holder.humidityTV.setText(""+(int)(hour.getHumidity()*100));
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

    public class HourVH extends RecyclerView.ViewHolder {
        TextView tempTV,summaryTV,rainTV, humidityTV;
        TextView timeTV;
        ImageView icon;
        public HourVH(View itemView) {
            super(itemView);
            timeTV=itemView.findViewById(R.id.timeTV_hourly);
            tempTV=itemView.findViewById(R.id.tempTV_hourly);
            summaryTV=itemView.findViewById(R.id.sumTV_hourly);
            rainTV=itemView.findViewById(R.id.rainTV_hourly);
            humidityTV=itemView.findViewById(R.id.hummidityTV_hourly);
            icon=itemView.findViewById(R.id.iconIV_hourly);
        }
    }
}
