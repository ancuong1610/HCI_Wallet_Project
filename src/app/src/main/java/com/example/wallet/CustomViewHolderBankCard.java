package com.example.wallet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.net.NoRouteToHostException;

public class CustomViewHolderBankCard extends BaseViewHolder<BankCard> {
    TextView firstname;
    TextView lastname;
    TextView iban;

    public void setUser(User user) {
        this.user = user;
    }

    User user;
    BankCard bankCard;
    ConstraintLayout constraintLayout;

    public CustomViewHolderBankCard(@NonNull View itemView){
        super(itemView);

        constraintLayout = itemView.findViewById(R.id.constraintLayout_bankCard);
        constraintLayout.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view){
                Intent intent = new Intent(view.getContext(), DetailedViewActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("card", bankCard);
                intent.putExtra("saveButton", "false");
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public void bind(BankCard bc){
        firstname = itemView.findViewById(R.id.bankcard_firstname);
        lastname = itemView.findViewById(R.id.bankcard_lastname);
        iban = itemView.findViewById(R.id.bankcard_iban);

        firstname.setText(bc.getFirstName());
        lastname.setText(bc.getLastName());
        iban.setText(bc.getIban());
    }
    public void setBankCard(BankCard new_bankCard) {
        this.bankCard = new_bankCard;
    }
}
