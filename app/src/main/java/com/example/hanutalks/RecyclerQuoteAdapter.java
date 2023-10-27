package com.example.hanutalks;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerQuoteAdapter extends RecyclerView.Adapter<RecyclerQuoteAdapter.ViewHolder> {

   Context context;
   ArrayList<QuoteModel> arrQuote;
   RecyclerQuoteAdapter(Context context, ArrayList<QuoteModel> arrQuote)
   {
       this.context = context;
       this.arrQuote = arrQuote;
   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.quote, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       String quoteId = "Quote "+ arrQuote.get(position).id;
        holder.id.setText(quoteId);
        holder.quote.setText(arrQuote.get(position).quote);
        setColor(position , holder);
    }

    public  void setColor(int position , ViewHolder holder)
    {
        switch (position % 5)
        {
            case 0:     holder.quote.setBackgroundColor(context.getResources().getColor(R.color.cardBg1));
                        break;
            case 1:     holder.quote.setBackgroundColor(context.getResources().getColor(R.color.cardBg2));
                break;
            case 2:     holder.quote.setBackgroundColor(context.getResources().getColor(R.color.cardBg3));
                break;
            case 3:     holder.quote.setBackgroundColor(context.getResources().getColor(R.color.cardBg4));
                break;
            case 4:     holder.quote.setBackgroundColor(context.getResources().getColor(R.color.cardBg5));
                break;
        }
    }
    @Override
    public int getItemCount() {
        return arrQuote.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
       TextView id, quote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.card_title);
            quote = itemView.findViewById(R.id.card_text);
        }
    }
}
