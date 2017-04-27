package solitaire;

import java.util.Collections;
import solitaire.model.game.*;

import java.util.Scanner; // for printing, TODO - remove it
import java.util.regex.*; // for printing, TODO - remove it
import solitaire.model.board.*; // for printing, TODO - remove it
import solitaire.model.cards.*; // for printing, TODO - remove it

public class Solitaire {

	static Game GAME1;
	//static Game GAME2;
	//static Game GAME3;
	//static Game GAME4;

    public static void main(String[] args) {

    	GAME1 = new Game();
    	//GAME2 = new Game();
    	//GAME3 = new Game();
    	//GAME4 = new Game();

        Scanner scan = new Scanner(System.in);

        String str, val1, val2, val3, val4;
        int num1, num2, num3;
        boolean n1, n2, n3, flag;
        char c;

        Pattern regex = Pattern.compile("[\\s]*([abcdefg])(?:[\\s]+(\\d+))?(?:[\\s]+(\\d+))?(?:[\\s]+(\\d+))?[\\s]*");
        Matcher matcher;

        print_game_all(GAME1.workingArray, GAME1.targetArray, GAME1.GameDeck, GAME1.GameDeckUp);
        System.out.println("================================");

        while(scan.hasNextLine()) {
        	n1=n2=n3=false;
            str = scan.nextLine();
            matcher = regex.matcher(str);
            if (matcher.matches()) {
                val1 = matcher.group(1); // A-G
                val2 = matcher.group(2); // number 1
                val3 = matcher.group(3); // number 2
                val4 = matcher.group(4); // number 3
                if (val2!=null) {
                    num1 = Integer.parseInt(val2);
                    n1 = true;
                }
                else num1=0;
                if (val3!=null) {
                    num2 = Integer.parseInt(val3);
                    n2 = true;
                }
                else num2=0;
                if (val4!=null) {
                    num3 = Integer.parseInt(val4);
                    n3 = true;
                }
                else num3=0;
                c = val1.charAt(0);
                flag=true;
                switch (c) {
                    case 'a':
                        if (n1 && n2 && !n3) GAME1.workingToTarget(num1, num2);
                        else {
                        	System.out.println("ERROR: Please enter: num1 & num2. [[ GAME1.workingToTarget(num1, num2); ]]");
                        	flag=false;
                        }
                        break;
                    case 'b':
                        if (n1 && n2 && !n3) GAME1.targetToWorking(num1, num2);
                        else {
                        	System.out.println("ERROR: Please enter: num1 & num2. [[ GAME1.targetToWorking(num1, num2); ]]");
                        	flag=false;
                        }
                        break;
                    case 'c':
                        if (n1 && !n2 && !n3) GAME1.gameDeckUpToTarget(num1);
                        else {
                        	System.out.println("ERROR: Please enter: num1. [[ GAME1.gameDeckUpToTarget(num1); ]]");
                        	flag=false;
                        }
                        break;
                    case 'd':
                        if (n1 && !n2 && !n3) GAME1.gameDeckUpToWorking(num1);
                        else {
                        	System.out.println("ERROR: Please enter: num1. [[ GAME1.gameDeckUpToWorking(num1); ]]");
                        	flag=false;
                        }
                        break;
                    case 'e':
                        if (n1 && n2 && !n3) GAME1.TargetToTarget(num1, num2);
                        else {
                        	System.out.println("ERROR: Please enter: num1 & num2. [[ GAME1.TargetToTarget(num1, num2); ]]");
                        	flag=false;
                        }
                        break;
                    case 'f':
                        if (n1 && n2 && n3) GAME1.WorkingToWorking(num1, num2, num3);
                        else {
                        	System.out.println("ERROR: Please enter: num1 & num2 & num3. [[ GAME1.WorkingToWorking(num1, num2, num3) ]]");
                        	flag=false;
                        }
                        break;
                    default: // G
                        GAME1.deckToUp();
                        break;
                }
                if (flag) {
            		System.out.println("================================");
            		print_game_all(GAME1.workingArray, GAME1.targetArray, GAME1.GameDeck, GAME1.GameDeckUp);
            		System.out.println("\nCMD = "+GAME1.print_command());
            		System.out.println("================================");
            	}
            }
            else {
                System.out.println("Wrong input, please repeat again.");
            }
            
        }

    }

    public static void  print_game(CardStack[] Working, CardDeck[] Target, CardDeck GD, CardDeck GDUP) {

        Card top_card;
        int size;
        String[] data = new String[13];
        String str;
        CardDeck deck;
        CardStack stack;

        // TARGET decks
        for(int c=0; c<4; c++) {

            deck = Target[c];
            str   = "TARGET[" + String.valueOf(c) + "] {"+String.valueOf(deck.size())+"}\t= ";

            if (deck.isEmpty()==false) {
                size     = deck.size();
                top_card = deck.get();
                for(int i=0; i<size-1; i++) {
                    str = str + "(X) ";
                }
                str = str + "(" + top_card.toString() + ")\n";
            }
            else {
                str = str + "empty\n";
            }

            data[c] = str;
        }

         // WORKING stacks
        for(int c=0; c<7; c++) {

            stack = Working[c];
            str   = "WORKING[" + String.valueOf(c) + "] {"+String.valueOf(stack.size())+"}\t= ";

            if (stack.isEmpty()==false) {
                size     = stack.size();
                top_card = stack.get();
                for(int i=0; i<size-1; i++) {
                    str = str + "(X) ";
                }
                str = str + "(" + top_card.toString() + ")\n";
            }
            else {
                str = str + "empty\n";
            }

            data[4+c] = str;
        }

        // GD, GDUP decks
        for(int c=0; c<2; c++) {

            if (c==0) {
                deck = GD;
                str   = "GD    {"+String.valueOf(deck.size())+"}\t= ";
            }
            else {
                deck = GDUP;
                str   = "GD-UP {"+String.valueOf(deck.size())+"}\t= ";
            }

            if (deck.isEmpty()==false) {
                size     = deck.size();
                top_card = deck.get();
                for(int i=0; i<size-1; i++) {
                    str = str + "(X) ";
                }
                str = str + "(" + top_card.toString() + ")\n";
            }
            else {
                str = str + "empty\n";
            }

            data[11+c] = str;
        }

        // print out data
        for(int c=0; c<13; c++) {
            if (c==4 || c==11) System.out.println();
            System.out.print(data[c]);
        }

    }


    public static void  print_game_all(CardStack[] Working, CardDeck[] Target, CardDeck GD, CardDeck GDUP) {



        Card top_card;
        int size;
        String[] data = new String[13];
        String str, tmp;
        CardDeck deck;
        CardStack stack;



        // TARGET decks ==============================================================================
        for(int c=0; c<4; c++) {

            deck = Target[c];
            str   = "TARGET[" + String.valueOf(c) + "] {"+String.valueOf(deck.size())+"}\t= ";

            if (deck.isEmpty()==false) {
                size     = deck.size();
                top_card = deck.get();
                for(int i=0; i<size; i++) {
                    tmp = deck.get(i).print_log();
                    if (tmp.charAt(tmp.length()-2) == 'U') {
                        str = str + "(" + tmp + ") ";
                    }
                    else str = str + "(X) ";
                    tmp = "";
                }
            }
            else {
                str = str + "empty";
            }
            data[c] = str;
            str = "";
        }




         // WORKING stacks ==============================================================================
        for(int c=0; c<7; c++) {

            stack = Working[c];
            str   = "WORKING[" + String.valueOf(c) + "] {"+String.valueOf(stack.size())+"}\t= ";

            if (stack.isEmpty()==false) {
                size     = stack.size();
                top_card = stack.get();
                for(int i=0; i<size; i++) {
                    tmp = stack.get(i).print_log();
                    if (tmp.charAt(tmp.length()-2) == 'U') {
                        str = str + "(" + tmp + ") ";
                    }
                    else str = str + "(X) ";
                    tmp = "";
                }
            }
            else {
                str = str + "empty";
            }

            data[4+c] = str;
            str = "";
        }




        // GD deck ========================================================================================
        deck = GD;
        str   = "GD    {"+String.valueOf(deck.size())+"}\t= ";
        if (deck.isEmpty()==false) {
            size     = deck.size();
            top_card = deck.get();
            for(int i=0; i<size-1; i++) {
                str = str + "(X) ";
            }
            str = str + "(" + top_card.print_log() + ") ";
        }
        else {
            str = str + "empty";
        }
        data[11] = str;
        str = "";





        // GDUP decks ========================================================================================
        deck = GDUP;
        str   = "GD-UP {"+String.valueOf(deck.size())+"}\t= ";
        if (deck.isEmpty()==false) {
            size     = deck.size();
            top_card = deck.get();
            for(int i=0; i<size; i++) {
               str = str + "(" + deck.get(i).print_log() + ") ";
            }
        }
        else {
            str = str + "empty";
        }
        data[12] = str;
        str = "";





        // print out data =====================================================================================
        for(int c=0; c<13; c++) {
            if (c==4 || c==11) System.out.println();
            System.out.println(data[c]);
        }

    }

//===========================================================================
    public static void  print_game_all_hidden(CardStack[] Working, CardDeck[] Target, CardDeck GD, CardDeck GDUP) {

     Card top_card;
     int size;
     String[] data = new String[13];
     String str;
     CardDeck deck;
     CardStack stack;

     // TARGET decks
     for(int c=0; c<4; c++) {

         deck = Target[c];
         str   = "TARGET[" + String.valueOf(c+1) + "] {"+String.valueOf(deck.size())+"}\t= ";

         if (deck.isEmpty()==false) {
             size     = deck.size();
             top_card = deck.get();
             for(int i=0; i<size-1; i++) {
                 str = str + "(" + (deck.get(i)).print_log() + ") ";
             }
             str = str + "(" + top_card.print_log() + ")\n";
         }
         else {
             str = str + "empty\n";
         }

         data[c] = str;
     }

      // WORKING stacks
     for(int c=0; c<7; c++) {

         stack = Working[c];
         str   = "WORKING[" + String.valueOf(c+1) + "] {"+String.valueOf(stack.size())+"}\t= ";

         if (stack.isEmpty()==false) {
             size     = stack.size();
             top_card = stack.get();
             for(int i=0; i<size-1; i++) {
                 str = str + "(" + stack.get(i).print_log() + ") ";
             }
             str = str + "(" + top_card.print_log() + ")\n";
         }
         else {
             str = str + "empty\n";
         }

         data[4+c] = str;
     }

     // GD, GDUP decks
     for(int c=0; c<2; c++) {

         if (c==0) {
             deck = GD;
             str   = "GD    {"+String.valueOf(deck.size())+"}\t= ";
         }
         else {
             deck = GDUP;
             str   = "GD-UP {"+String.valueOf(deck.size())+"}\t= ";
         }

         if (deck.isEmpty()==false) {
             size     = deck.size();
             top_card = deck.get();
             for(int i=0; i<size-1; i++) {
                 str = str + "(" + deck.get(i).print_log() + ") ";
             }
             str = str + "(" + top_card.print_log() + ")\n";
         }
         else {
             str = str + "empty\n";
         }

         data[11+c] = str;
     }

     // print out data
     for(int c=0; c<13; c++) {
         if (c==4 || c==11) System.out.println();
         System.out.print(data[c]);
     }

 }
// END OF ===========================================================================

}
