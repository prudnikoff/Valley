package com.example.prudnikoff.valley;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventListAdapter extends ArrayAdapter<EventListElement> {

    private Context context;
    private int resources;
    private EventListElement[] events;

    public EventListAdapter(Context context, int resources, EventListElement[] events) {
        super(context, resources, events);
        this.context = context;
        this.resources = resources;
        this.events = events;
    }

    @Override
    public EventListElement getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        EventHolder eventHolder;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(resources, parent, false);
            eventHolder = new EventHolder();
            eventHolder.nameTextView = (TextView)row.findViewById(R.id.event_name_textView);
            eventHolder.tagsTextView = (TextView)row.findViewById(R.id.event_tags_textView);
            eventHolder.dateTextView = (TextView)row.findViewById(R.id.date_textView);
            eventHolder.locationTextView = (TextView)row.findViewById(R.id.location_textView);
            eventHolder.admissionFeeTextView = (TextView)row.findViewById(R.id.admission_fee_textView);
            row.setTag(eventHolder);
        } else {
            eventHolder = (EventHolder)row.getTag();
        }
        EventListElement element = events[position];
        eventHolder.nameTextView.setText(element.name);
        eventHolder.tagsTextView.setText(element.tags);
        eventHolder.dateTextView.setText(element.date);
        eventHolder.locationTextView.setText(element.location);
        eventHolder.admissionFeeTextView.setText(element.admissionFee);
        return row;
    }

    private static class EventHolder {
        TextView nameTextView;
        TextView tagsTextView;
        TextView dateTextView;
        TextView locationTextView;
        TextView admissionFeeTextView;
    }
}
