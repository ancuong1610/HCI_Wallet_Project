package com.example.wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomViewAdapterCards extends RecyclerView.Adapter<BaseViewHolder>{
    private static final int TYPE_IDENTITY = 0;
    private static final int TYPE_BANK = 1;
    private static final int TYPE_DRIVERLICENSE = 2;
    private static final int TYPE_ORGANDONOR = 3;

    public void setUser(User user) {
        this.user = user;
    }

    User user;
    private List<Card> cards;
    Context context;

    public CustomViewAdapterCards(){
    }

    public void add(List newCards){
        if (cards == null){
            cards = new ArrayList<Card>();
        }
        cards.clear();
        cards.addAll(newCards);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        switch (viewType) {
            case TYPE_IDENTITY: {
                View view = LayoutInflater.
                        from(context).
                        inflate(R.layout.layout_card_pass, parent, false);
                CustomViewHolderIdentityCard temp = new CustomViewHolderIdentityCard(view);
                temp.setUser(user);
                return temp;
            }
            case TYPE_BANK: {
                View view = LayoutInflater.
                        from(context).
                        inflate(R.layout.layout_card_bank, parent, false);
                CustomViewHolderBankCard temp = new CustomViewHolderBankCard(view);
                temp.setUser(user);
                return temp;
            }
            case TYPE_DRIVERLICENSE: {
                View view = LayoutInflater.
                        from(context).
                        inflate(R.layout.layout_driver_license, parent, false);
                CustomViewHolderDriverLicenseCard temp = new CustomViewHolderDriverLicenseCard(view);
                temp.setUser(user);
                return temp;
            }
            case TYPE_ORGANDONOR: {
                View view = LayoutInflater.
                        from(context).
                        inflate(R.layout.layout_card_organ_donor, parent, false);
                CustomViewHolderOrganDonorCard temp = new CustomViewHolderOrganDonorCard(view);
                temp.setUser(user);
                return temp;
            }
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position){
        Card element = cards.get(position);
        holder.bind(element);
        if (holder instanceof CustomViewHolderBankCard) {
            CustomViewHolderBankCard bankCardViewHolder = (CustomViewHolderBankCard) holder;
            BankCard bankCard = (BankCard) element;
            bankCardViewHolder.setBankCard(bankCard);
        }
        else if (holder instanceof CustomViewHolderDriverLicenseCard) {
            CustomViewHolderDriverLicenseCard driverLicenseViewHolder = (CustomViewHolderDriverLicenseCard) holder;
            DriversLicense driversLicense = (DriversLicense) element;
            driverLicenseViewHolder.setDriversLicense(driversLicense);
        }
        else if (holder instanceof CustomViewHolderOrganDonorCard){
            CustomViewHolderOrganDonorCard odHolder = (CustomViewHolderOrganDonorCard) holder;
            OrganDonorCard odcard = (OrganDonorCard) element;
            odHolder.setOrganDonorCard(odcard);
        } else if (holder instanceof CustomViewHolderIdentityCard) {
            CustomViewHolderIdentityCard idHolder = (CustomViewHolderIdentityCard) holder;
            IdentityCard identityCard = (IdentityCard) element;
            idHolder.setIdentityCard(identityCard);
        }
    }
   @Override
   public int getItemViewType(int position){
       Card element = cards.get(position);
        if(element instanceof IdentityCard){
            return TYPE_IDENTITY;
        } else if (element instanceof BankCard){
            return TYPE_BANK;
        } else if (element instanceof DriversLicense){
            return TYPE_DRIVERLICENSE;
        } else if (element instanceof OrganDonorCard){
            return TYPE_ORGANDONOR;
        }

        throw new IllegalArgumentException("Invalid position " + position);
   }

    @Override
    public int getItemCount(){return cards.size();}

    public void setItemList(ArrayList<Card> newList){
        cards = newList;
        notifyDataSetChanged();
    }


}
