
package blackjack;

/**
 *
 * @author Harjeet Kaur Rathaur
 */
import java.util.ArrayList;
import java.util.Arrays;
/*
 * it is a dealer class along with which the dealer plays the cards
 */
class Dealer {
ArrayList<Card> hand;//dealers hand
private int valueInHand=0;// the value contain by the hand of the dealer
private Card[] dealerHand;// changes the hand in the arraylist
private int AceCount;//numbers of Aces in the hand of the dealer
Dealer(GroupOfCards deckes)
{
     int AceCount=0;
    hand = new ArrayList<>();
    dealerHand = new Card[]{};
   
    for(int a=0; a<2; a++)
    {
        hand.add(deckes.drawingACard());
    }
    
    dealerHand = hand.toArray(dealerHand);
    for(int b=0; b<dealerHand.length; b++)
    {
        valueInHand += dealerHand[b].getValue();
        if(dealerHand[b].getValue()==11)
        {
            AceCount++;
        }
        while(AceCount>0 && valueInHand>21)
        {
            valueInHand-=10;
            AceCount--;
        }
    }
}

    
/*
 * display whats in the dealers first hand
 */
public void dealerFirstcard()
{
    Card[] firstCard = new Card[]{};
    firstCard = hand.toArray(firstCard);
    System.out.println("("+firstCard[0]+")");
}
/*
 * hits the dealer with other card and increase its value.
 */
public void Hitting(GroupOfCards deckes)
{
        valueInHand = 0;
    hand.add(deckes.drawingACard());
    dealerHand = hand.toArray(dealerHand);

    for(int z=0; z<dealerHand.length; z++)
    {
        valueInHand = dealerHand[z].getValue() + valueInHand;
        if(dealerHand[z].getValue()==11)
        {
            AceCount++; //number of ace in the hand of the dealer
        }
        while(AceCount>0 && valueInHand>21)
        {
            valueInHand-=10;
            AceCount--;
        }
    }
}

/*
 * Only true if the dealer is having the blackjack card
 */
public boolean haveBlack()
{
    if(hand.size()==2 && valueInHand==21)
    {
        System.out.println("---------- Dealer is having the Blackjack card----------------");
        return true;
    }
    return false;
}
/*
 * Displays the dealer hands
 */
public void showDealerHand()
{
    System.out.println(" "+hand+" ");//shows cards in the hand
}

/*
 * Determines if a dealer has busted.
 */
public boolean dealerBusted(int valueInHand)
{
    if(valueInHand>21)
    {
        System.out.println("-+-+-+The Dealer got busted!!!!!!-+-+-+");
        return true;
    }
    return false;
}
/*
 * Takes the turn for the dealer and returns the value of his hand.
 */
public int takeDealerTurn(GroupOfCards deckes)
{
    while(dealerWantedToHint())
    {
        System.out.println("******Dealer is hitting******");
        Hitting(deckes);
        if(dealerBusted(valueInHand))
        {
            break;
        }
    }
    if(valueInHand<=21)
    {
        System.out.print("o0o0o0odealer still stands00o0o0o0o");
    }
    return valueInHand;
}
/*
 * if dealer wanted a hint
 */
public boolean dealerWantedToHint()
{
    if(valueInHand<17)
    {
        return true; //return true if he wanted hint
    }
    return false;
}
/*
 * getting value of the dealers hand
 */
public int getvalueInHand()
{
    return valueInHand; //returns the cards in the hand
}
}
