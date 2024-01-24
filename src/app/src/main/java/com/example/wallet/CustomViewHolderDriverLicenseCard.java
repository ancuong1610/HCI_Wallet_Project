package com.example.wallet;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomViewHolderDriverLicenseCard extends BaseViewHolder<DriversLicense>{
    TextView firstname;
    TextView lastname;
    TextView birthday;
    TextView placeOfBirth;
    TextView validFrom;
    TextView validUntil;
    TextView issuedBy;
    TextView driverLicenseID;
    ConstraintLayout constraintLayout;
    DriversLicense driversLicense;

    public void setUser(User user) {
        this.user = user;
    }

    User user;
    public CustomViewHolderDriverLicenseCard(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.dl_background_constraint);
        constraintLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), DetailedViewActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("d_card", driversLicense);
                intent.putExtra("saveButton", "false");
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void bind(DriversLicense dl) {
        firstname = itemView.findViewById(R.id.dl_firstname);
        lastname = itemView.findViewById(R.id.dl_lastname);
        birthday = itemView.findViewById(R.id.dl_birthday);
        placeOfBirth = itemView.findViewById(R.id.dl_placeOfBirth);
        validFrom = itemView.findViewById(R.id.dl_validFrom);
        validUntil = itemView.findViewById(R.id.dl_validUntil);
        issuedBy = itemView.findViewById(R.id.dl_issuedBy);
        driverLicenseID = itemView.findViewById(R.id.dl_driverLicenseId);

        firstname.setText(dl.getFirstName());
        lastname.setText(dl.getLastName());
        birthday.setText(dl.getBirthday());
        placeOfBirth.setText(dl.getPlaceOfBirth());
        validFrom.setText(dl.getValidFrom());
        validUntil.setText(dl.getValidUntil());
        issuedBy.setText(dl.getIssuedBy());
        driverLicenseID.setText(dl.getDriversLicenseID());
    }
    public void setDriversLicense(DriversLicense driversLicense) {
        this.driversLicense = driversLicense;
    }

}
