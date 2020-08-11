
package blackjack;

/**
 *
 * @author harjeet kaur Rathaur
 */
class Card {
/*
 * generates a playing card
    Model class of Black jack project
 */
private int CardRank;//depicts card rank
private int CardSuit;//depicts card suit
private int value;//depicts card value
private static String[] ranking = {"Joker","Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
private static String[] suits = {"Clubs","Diamonds","Hearts","Spades"};

Card(int CardSuit, int values)
{
    this.CardRank=values;
    this.CardSuit=CardSuit;
}
/*
 * return the cards in the form of the string
 */
public String toString()
{
    return ranking[CardRank]+" in the  "+suits[CardSuit];
}
/*
 * get card ranking
 */
public int getCardRank()
{
    return CardRank;
}
/*
 * get the card suit
 */
public int getCardSuit()
{
    return CardSuit;
}
/*
 * get the value of the card 
 */
public int getValue()
{
    if(CardRank>10)
    {
        value=10;
    }
    else if(CardRank==1)
    {
        value=11;
    }
    else
    {
        value=CardRank;
    }
    return value;
}
/*
 * Setting value of the cards
 */
public void setValue(int set)
{
    value = set;
}
}
