package com.example.wallet;

public class Card {
    String card_name;
    int card_id;
    static int card_id_counter = 0;

    public Card(String cardName){
        this.card_name = cardName;
        this.card_id = card_id_counter++;
    }

    public int getCardId(){ return this.card_id; }

    public String getCardName(){ return this.card_name; }

}
