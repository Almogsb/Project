package com.example.michael.designpattrenproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.michael.designpattrenproject.Activities.ProsecutionFormActivity;
import com.example.michael.designpattrenproject.Classes.Logger.FileHelper;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.ApartmentInsurance;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.CarInsurance;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.Insurance;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.LifeInsurance;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.LostOfWorkAbilityInsurance;
import com.example.michael.designpattrenproject.R;

import java.util.ArrayList;

/**
 * Created by Michael on 13/01/2018.
 */

public class MyInsuranceAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Insurance> my_insurances;
    private ArrayList<String> insurance_id;

    public MyInsuranceAdapter(@NonNull Context context, int resource, ArrayList<Insurance> my_insurances) {
        super(context, resource , my_insurances);
        this.context=context;
        this.my_insurances = my_insurances;
        this.insurance_id = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable  View convertView, @NonNull ViewGroup parent) {


        ViewHolder holder=null; // to reference the child views for later actions
        if (convertView == null) {
            LayoutInflater vi =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.my_insurance_row, null);

            // cache view fields into the holder
            holder = new ViewHolder();
            holder.insuranceName = convertView.findViewById(R.id.textViewName);
            holder.insurancePurcheseDate = convertView.findViewById(R.id.textViewPurchase);
            holder.insuranceExpiredDate = convertView.findViewById(R.id.textViewExpired);
            holder.insurancesDetails = convertView.findViewById(R.id.textViewDetails);
            holder.btnClaim = convertView.findViewById(R.id.btnSubmitClaim);
            // associate the holder with the view for later lookup
            convertView.setTag(holder);
        }
        else {
            // view already exists, get the holder instance from the view
            holder = (ViewHolder) convertView.getTag();
        }
        // no local variables with findViewById here
        // use holder.nameText where you were
        // using the local variable nameText before

        holder.insuranceName.setText(my_insurances.get(position).getDescription());
        holder.insurancesDetails.setText(setDetailsText(my_insurances.get(position).getDescription(),position));
        holder.insurancePurcheseDate.setText("From:  " + my_insurances.get(position).getPurchaseDate());
        holder.insuranceExpiredDate.setText( "To:  " + my_insurances.get(position).getExpirationDate());
        holder.btnClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileHelper.saveToFile("Claim button pressed");
                Intent intent = new Intent(context, ProsecutionFormActivity.class);
                intent.putExtra("claim_id",insurance_id.get(position));
                intent.putExtra("insurance_name",my_insurances.get(position).getDescription());
                context.startActivity(intent);
            }
        });



        return convertView;

    }

    private String setDetailsText(String description, int position) {

        switch (description.substring(0, description.indexOf(" ")).trim()) {
            case "Car":
                return ("Car number: " + ((CarInsurance) my_insurances.get(position)).getCarNum());

            case "Life":
                return ("ID:  " + ((LifeInsurance) my_insurances.get(position)).getID());

            case "Apartment":
                return ("Address: " + ((ApartmentInsurance) my_insurances.get(position)).getAddress());

            case "Lost":
                return ("ID:  " + ((LostOfWorkAbilityInsurance) my_insurances.get(position)).getID());
        }
        return "";
    }





    public static class ViewHolder
    {
        TextView insuranceName;
        TextView insuranceExpiredDate;
        TextView insurancePurcheseDate;
        TextView insurancesDetails;
        Button btnClaim;


    }

    public void addId(String id){
        insurance_id.add(id);
    }

}
