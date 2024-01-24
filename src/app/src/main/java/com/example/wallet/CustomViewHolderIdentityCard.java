package com.example.wallet;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolderIdentityCard extends BaseViewHolder<IdentityCard> {

    TextView passId;
    TextView passName;
    TextView birthname;
    TextView firstname;
    TextView birthdate;
    TextView nationality;
    TextView birthplace;
    TextView expiredate;
    ConstraintLayout constraintLayout;
    IdentityCard identityCard;

    public void setUser(User user) {
        this.user = user;
    }

    User user;

    public CustomViewHolderIdentityCard(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.id_card_constraint);
        constraintLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), DetailedViewActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("i_card", identityCard);
                intent.putExtra("saveButton", "false");
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void bind(IdentityCard ic){
        //IdentityCard ic = (IdentityCard) c;

        passId = itemView.findViewById(R.id.passId);
        passName = itemView.findViewById(R.id.passName);
        birthname = itemView.findViewById(R.id.passBirthname);
        firstname = itemView.findViewById(R.id.passFirstname);
        birthdate = itemView.findViewById(R.id.passBirthdate);
        nationality = itemView.findViewById(R.id.passNationality);
        birthplace = itemView.findViewById(R.id.passBirthplace);
        expiredate = itemView.findViewById(R.id.passExpirydate);

        passId.setText(ic.getId_Number());
        passName.setText(ic.getLastName());
        birthname.setText(ic.getBirthname());
        firstname.setText(ic.getFirstName());
        birthdate.setText(ic.getBirthday());
        nationality.setText(ic.getNationality());
        birthplace.setText(ic.getPlaceOfBirth());
        expiredate.setText(ic.getValidUntil());

        identityCard = ic;
    }
    public void setIdentityCard(IdentityCard identityCard) {
        this.identityCard = identityCard;
    }

}
