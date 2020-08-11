
package blackjack;

/**
 *
 * @author harjeet
 */
import java.util.ArrayList;
import java.util.Random;
/*
 * it has the Fifty Two card mixed card deckes
 */
class GroupOfCards {
private ArrayList<Card> deckes;//contains the fifty two cards deck
GroupOfCards()
{
    deckes = new ArrayList<Card>();
    for(int j=0; j<4; j++)
    {
        for(int k=1; k<=13; k++)
        {
            deckes.add(new Card(j,k));
        }
    }
}

/*
 * taking a card from the fifty two cards
 */
public Card drawingACard()
{
    return deckes.remove(0);
}

/*
 * Mixes the cards all the Fifty two to play the game
 */
public void shuffling()
{
    Card t;
    Random ran = new Random();
    
    for(int i=0; i<200; i++)
    {
        int i1 = ran.nextInt(deckes.size()-1);
        int i2 = ran.nextInt(deckes.size()-1);
        t = deckes.get(i2);
        deckes.set(i2, deckes.get(i1));
        deckes.set(i1, t);
    }
}
}
