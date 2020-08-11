
package blackjack;

import java.util.ArrayList;
import java.util.Scanner;
  import java.util.*;
/**
 *
 * @author harjeet kaur rathaur
 */
public class Blackjack {

    /**
     * @param args the command line arguments
     */
 

private static int cashmoney;//money inthe class with which the player bets
private static  int betmoney;//money the player used to bet
private static int AceCount;//number of ace in the user hands
private static ArrayList<Card> hand;//depicts the hand of the player.
private static int valueInHand;//player hand values
private static String playing;//player name
public static  void main(String[] args){
     System.out.println("Playing!!!!");
      System.out.println("*******BlackJack******");
    System.out.println("Hey Buddy What name you want in the game?");
    Scanner scan = new Scanner(System.in);
    playing = scan.nextLine();
    System.out.println("Come on!! "+playing+", lets play  and rock the game");
    System.out.println("How much cash money do you want to start with?");
    Scanner money = new Scanner(System.in);
    cashmoney = money.nextInt();
    
    while(cashmoney>0){
        GroupOfCards deckes = new GroupOfCards();
        deckes.shuffling();
        AceCount=0;
        Dealer dealer = new Dealer(deckes);
        List<Card> hand = new ArrayList<>();
        hand.add(deckes.drawingACard());
        hand.add(deckes.drawingACard());
        System.out.println("Money you would like to bet..");
        betmoney=Betting(cashmoney);
        System.out.println("table has money :"+betmoney);
        System.out.println("Cash Money:"+(cashmoney-betmoney)); 
        System.out.println("Here is your hand: ");
        System.out.println(hand);
        int valueInHand = calculatevalueInHand(hand);
        System.out.println("Dealer  shows: ");
        dealer.dealerFirstcard();
        if(haveBlack(valueInHand) && dealer.haveBlack())//determines wether player and dealer have blackjack
        {
            Push();
        }
        else if(haveBlack(valueInHand))//checks for player
        {
            System.out.println("Brawo you had a BlackJack!!!");
            System.out.println("You won double money as cash back");
            cashmoney=cashmoney + betmoney;
            Winner();
        }
        else if(dealer.haveBlack())//determine for dealer has blackjack.
        {
            System.out.println("Dealer's hand contains cards:");
            dealer.showDealerHand();
            Loser();
        }
        else
        {
           
            System.out.println("you will hit or stand?");//prompt the player its choice
            Scanner hitorstand = new Scanner(System.in);
            String hitter = hitorstand.nextLine();
            while(!isHitStand(hitter))
            {
                System.out.println("Type hit or stand:");
                hitter = hitorstand.nextLine();
            }
            while(hitter.equals("hit"))
            {
                Hitting(deckes, hand);
                System.out.println("Your hand is now:");
                System.out.println(hand);
                valueInHand = calculatevalueInHand(hand);
                if(checkBusting(valueInHand))//depicts whether the player get busted
                {
                    Loser();
                    break;
                }
                if(valueInHand<=21 && hand.size()==5)//determines  for a five card 
                {
                    FiveTrick();
                    break;
                }
                System.out.println("you will like to stand or hit?");
                hitter = hitorstand.nextLine();
            }
            if(hitter.equals("stand"))//if he choose stand
            {
                int dealerhand = dealer.takeDealerTurn(deckes);
                System.out.println(" ******");
                System.out.println("Dealer hand:");
                dealer.showDealerHand();
                if(dealerhand>21)//Player won and dealer loose
                {
                    Winner();
                }
                else
                {
                    int you = 21-valueInHand;
                    int deal = 21-dealerhand;
                    if(you==deal)
                    {
                        Push();
                    }
                    if(you<deal)
                    {
                        Winner();
                    }
                    if(deal<you)
                    {
                        Loser();
                    }
                }
            }
        }
    System.out.println("will you  play again the Black Jack Game?");
    Scanner yesorno = new Scanner(System.in);
    String answer = yesorno.nextLine();
    while(!isYesNo(answer))
            {
                System.out.println("Type your answer yes or no.");
                answer = yesorno.nextLine();
            }
    if(answer.equals("no"))
    {
        break;
    }
}
    System.out.println("you have: "+cashmoney+" Cash Money");
    if(cashmoney==0)
    {
        System.out.println("You have less cash!!");
    }
    else
    {
        System.out.println("you won "+playing+" Congo!");
    }
}
/*
 * determine whether user have black jack
 */
public static boolean haveBlack(int valueInHand)
{
    if(valueInHand==21)
    {
        return true;
    }
    return false;
}
/*
 *determine value of player hand
 */
public static int calculatevalueInHand(List<Card> hand)
{
    Card[] dealerHand = new Card[]{};
    dealerHand = hand.toArray(dealerHand);
    int valueInHand=0;
    for(int i=0; i<dealerHand.length; i++)
    {
        valueInHand += dealerHand[i].getValue();
        if(dealerHand[i].getValue()==11)
        {
            AceCount++;
        }
        while(AceCount>0 && valueInHand>21)
        {
            valueInHand-=10;
            AceCount--;
        }
    }
    return valueInHand;
}

/*
 * only if user wins
 */
public static void Winner()
{
    System.out.println("Congo ,Enjoy you won the game!");
    cashmoney=cashmoney+betmoney;
    System.out.println("Cash you have: "+cashmoney);
}
/*
 *if player loose
 */
public static void Loser()
{
    System.out.println("Sorry to say, you lose!");
    cashmoney=cashmoney-betmoney;
    System.out.println("cashmoney you have: "+cashmoney);
}
/*
 * Called if the player push
 */
public static void Push()
{     System.out.println("**************");
    System.out.println("It's a push!");
    System.out.println("you will have your money back.");
    System.out.println("cashmoney you have: "+cashmoney);
}
/*
 *creates card to player's hand and determines its  value of that hand. 
 */
public static void Hitting(GroupOfCards deckes, List<Card> hand)
{
    hand.add(deckes.drawingACard());
    Card[] dealerHand = new Card[]{};
    dealerHand = hand.toArray(dealerHand);
    valueInHand = 0;
    for(int s=0; s<dealerHand.length; s++)
    {
        valueInHand += dealerHand[s].getValue();
        if(dealerHand[s].getValue()==11)
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
 *checks player have hit or stand
 */
public static boolean isHitStand(String hitter)
{
    if(hitter.equals("hit") || hitter.equals("stand"))
    {
        return true;
    }
    return false;
}
/*
 * Determines if a player is busted.
 */
public static boolean checkBusting(int valueInHand)
{
    if(valueInHand>21)
    {
        System.out.println(playing+" !you have been busted!");
        return true;
    }
    return false;
}
/*
 * checks players input.
 */
public static boolean isYesNo(String answer)
{
    if(answer.equals("yes") || answer.equals("no"))
    {
        return true;
    }
    return false;
}
/*
 * only if the player have a five card trick.
 */
public static void FiveTrick()
{
    System.out.println(playing+" !!You have  five card trick.");
    Winner();
}

/*
 * Asks the user how much he or she would like to bet.
 */
public static int Betting(int cashmoney)
{
    Scanner sc=new Scanner(System.in);
    int betmoney=sc.nextInt();
    while(betmoney>cashmoney)
    {
        System.out.println("You cannot bet more cash than you have!");
        System.out.println("How much would you like to bet?");
        betmoney=sc.nextInt();
    }
    return betmoney;
}
}
