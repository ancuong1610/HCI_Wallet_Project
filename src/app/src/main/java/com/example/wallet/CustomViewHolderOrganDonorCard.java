package com.example.wallet;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomViewHolderOrganDonorCard extends BaseViewHolder<OrganDonorCard>{

    TextView firstName;
    TextView lastName;
    TextView birthday;
    TextView streetNumber;
    TextView postalCodeCity;

    public void setUser(User user) {
        this.user = user;
    }

    User user;
    public void setOrganDonorCard(OrganDonorCard organDonorCard) {
        this.organDonorCard = organDonorCard;
    }

    OrganDonorCard organDonorCard;
    ConstraintLayout constraintLayout;

    public CustomViewHolderOrganDonorCard(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.organDonorCardConstraint);
        constraintLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), DetailedViewActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("o_card", organDonorCard);
                intent.putExtra("saveButton", "true");
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void bind(OrganDonorCard od) {
        firstName = itemView.findViewById(R.id.od_firstname);
        lastName = itemView.findViewById(R.id.od_lastname);
        birthday = itemView.findViewById(R.id.od_birthday);
        streetNumber = itemView.findViewById(R.id.od_street_number);
        postalCodeCity = itemView.findViewById(R.id.od_postal_code_city);

        firstName.setText(od.getFirstName());
        lastName.setText(od.getLastName());
        birthday.setText(od.getBirthday());
        streetNumber.setText(od.getStreet_number());
        postalCodeCity.setText(od.getPostalCode_city());
    }
}
