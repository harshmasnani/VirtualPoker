import java.util.*;
import java.math.BigInteger; 
import java.security.SecureRandom; 
import javax.crypto.spec.IvParameterSpec; 
import javax.crypto.spec.SecretKeySpec; 



//public class AesCtr extends JavaxCipher
interface shuffleAndEncrypt extends Cipher
{
	
	static void shuffle(String[] deck)
	{

	}

	static void encryptDeck(String[] deck, int shuffleKey)
	{
		//pass deck here
	}
} 

interface shuffleLockRemove
{
	static void decryptDeck(String[] deck, int shuffleKey)
	{
		//pass deck here
	}
}

interface encryptCards
{
	static void encryptEachCard(String[] deck, int[] cardKeys)
	{

	}	
}

interface decryptCards
{
// take deck and return decrypted cards if MyCardsIndex match 
	static Card[] decryptMyOwnCards() 
	{
		//if(listOfClients.get);
		return new Card[0];
	}
}

class Card
{
	enum suit 
	{ 
    	DIAMOND, HEART, SPADE, CLUB ;
    }
	Card(int i,suit s)
	{
		theSuit=s;
		this.cardNumber=i;
	}

	Integer cardNumber;
	suit theSuit;
}


class Deck
{
	static List<Card> TheDeck=new ArrayList<>();
	static List<Card> createDeck()
	{
		
		for(Card.suit s : Card.suit.values())
		{
			for(int j=0;j<13;j++)
			{
				TheDeck.add(new Card(j,s));
			}
		}
		return TheDeck;
	}
} 

public class pokerDC implements shuffleAndEncrypt
{
	private static List<Client> listOfClients=new ArrayList<>();

	protected static void addToList(Client c){listOfClients.add(c);}
	protected static int numberOfClients(){return listOfClients.size();}
	
	static void initIndexs()
    {
    	int k=0;
		for(int i=0; i<numberOfClients(); i++)
		{
			Client x=listOfClients.get(i);
			x.MyCardsIndex[0]=k++;
			x.MyCardsIndex[1]=k++;
		}
    }

	protected static void distributeCards()
	{
		int k=0;
        for(int i=0; i<numberOfClients(); i++)
        {
            Client x=listOfClients.get(i);
            x.MyCards[0]=Deck.TheDeck.get(k++);
            x.MyCards[1]=Deck.TheDeck.get(k++);
        }
	}

	protected static void distributeKeys()
	{
		for(int i=0; i<numberOfClients(); i++)
		{
			Client x=listOfClients.get(i);	
			int k=0;
			for(int j=0; j<listOfClients.size() && k<listOfClients.size(); j++)
			{
				Client y=listOfClients.get(j);
				x.OCK[0][0][k++]=y.CK[x.MyCardsIndex[0]];
				x.OCK[0][1][k++]=y.CK[x.MyCardsIndex[1]];
			}	
		}
	}

	public static void main(String[] args){}
}

class Client extends pokerDC implements shuffleAndEncrypt 
{
	 //dont forget to resolve and add visibility modifiers 
	 byte[] SK; //shuffleKey
	 byte[][] CK=new byte[52][16]; //uniqueCardKey for encryption
	 byte[][][][] OCK=new byte[1][2][numberOfClients()][16]; //Set of Keys for own cards
	 int[] MyCardsIndex=new int[2];
	 Card[] MyCards=new Card[2];

	public static void main(String[] args) 
	{
		//for every client joining create object
		
	}
}