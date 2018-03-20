package com.example.michael.designpattrenproject.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class OurInsuranceAdapter extends ArrayAdapter {

    Context context;
    String[] insurances;
    Insurance car_insurance , life_insurance, apartment_insurance, lost_of_work_insurance;

    public OurInsuranceAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
        this.context=context;
        insurances=objects;
        car_insurance = new CarInsurance();
        life_insurance = new LifeInsurance();
        apartment_insurance = new ApartmentInsurance();
        lost_of_work_insurance = new LostOfWorkAbilityInsurance();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        ViewHolder holder; // to reference the child views for later actions
        if (v == null) {
            LayoutInflater vi =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.our_insurance_listview_row, null);
            // cache view fields into the holder
            holder = new ViewHolder();
            holder.insuranceName = v.findViewById(R.id.insuranceName);
            holder.buyBtn = v.findViewById(R.id.buyInsuranceBtn);
            holder.insuranceName.setText(insurances[position]);
           holder.buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   showAlert(insurances[position]);
                }
            });
            // associate the holder with the view for later lookup
            v.setTag(holder);
        }
        else {
            // view already exists, get the holder instance from the view
            holder = (ViewHolder) v.getTag();
        }
        // no local variables with findViewById here
        // use holder.nameText where you were
        // using the local variable nameText before
        return v;

    }

    private void insuranceClicked(String alertCase, String extraDetails) {


        switch (alertCase){
            case "Car":
                FileHelper.saveToFile("Car insurance purchased");
                Toast.makeText(context,"Car insurance purchased",Toast.LENGTH_SHORT).show();
                car_insurance.BuyInsurance(extraDetails);
                break;
            case "Life":
                FileHelper.saveToFile("Life insurance purchased");
                Toast.makeText(context,"Life insurance purchased",Toast.LENGTH_SHORT).show();
                life_insurance.BuyInsurance(extraDetails);
                break;
            case "Apartment":
                FileHelper.saveToFile("Apartment insurance purchased");
                Toast.makeText(context,"Apartment insurance purchased",Toast.LENGTH_SHORT).show();
                apartment_insurance.BuyInsurance(extraDetails);
                break;
            case "Lost of work ability":
                FileHelper.saveToFile("Lost of work ability insurance purchased");
                Toast.makeText(context,"Lost of work ability insurance purchased",Toast.LENGTH_SHORT).show();
                lost_of_work_insurance.BuyInsurance(extraDetails);
                break;
        }
    }


    private void showAlert(final String alertCase){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        alertDialogBuilder.setView(input);

        switch (alertCase){
            case "Car":
                alertDialogBuilder.setTitle("Insert car number");
                break;
            case "Life":
                alertDialogBuilder.setTitle("Insert your ID");
                break;
            case "Apartment":
                alertDialogBuilder.setTitle("Insert apartment address");
                break;
            case "Lost of work ability":
                alertDialogBuilder.setTitle("Insert your ID");
                break;
        }
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Submit",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        FileHelper.saveToFile("Details inserted");
                        Toast.makeText(context,"Details inserted",Toast.LENGTH_SHORT).show();
                        insuranceClicked(alertCase,input.getText().toString());
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public class ViewHolder
    {
        TextView insuranceName;
        Button buyBtn;
    }
}
