/*
Der Programmcode gehört mir. 

Ich habe meinen Code nach den Schritten in dieser Seite geschrieben
https://edabit.com/challenge/BWm34MorRuaJXiaz6

Vigenere ist andere Form von Verschlüsselungverfahren Caesar.

In Konstruktor werden Variablen nach Eingabe zugeordnet.

Method isCiphered()
	Es wird überprüft, ob der Text schon verschlüsselt wird.
	
Method getPlain()
	Die Buchstaben von Text wird vergrößert, und andere Characters, die
	nicht Buchstaben sind, werden weggelassen.
	
Method getKey()
	Ein Schlüssel wird nach Länge von Variabel keyword erzeugt.

mit Method encode wird Text verschlüsselt.
	

mit Method decode wird verschlüsselter Text entschlüsselt.
Aber erstmal soll verschlüsselter Text in einem String gespeichert werden.

Zusätzlich Sie können folgenden Link Beispiele(Tests) benutzen,
um Richtigkeit meines Codes  zu überprüfen.
Beispiele befinden sich oben rechts und links von Bereich "Console".
https://edabit.com/challenge/BWm34MorRuaJXiaz6
*/
package com.company;

public class Vigenere {

    private String text;
    private String keyword;
    private String plainText;
    private String key;

    public Vigenere(String text, String keyword){
        this.text    = text;
        this.keyword = keyword;

        if (isCiphered())
            plainText = text;
        else
            plainText = getPlain(text);

        key = getKey();
    }

    public String encode(){

        if (isCiphered())
            return "Dieser Text wird schon verschluesselt.";

        String ans = "";

        for(int i = 0; i < key.length(); i++){
            char col  = (char)(plainText.charAt(i) - 64);
            char row  = (char)(key.charAt(i) - 64);
            char temp = (char)(col + (row - 1));
            while (temp > 26)
                temp -= 26;
            ans += (char)(temp + 64);
        }

        return ans;
    }

    public String decode(){

        if (!isCiphered())
            return "Dieser Text wird schon entschlusselt.";

        String ans = "";

        for(int i = 0; i < key.length(); i++){
            int chip = (text.charAt(i) - 64);
            int row  = (key.charAt(i) - 64);
            int col  =(chip - (row - 1));
            if(col <= 0)
                col += 26;
            ans += (char)(col + 64);
        }
        return ans;
    }

    private boolean isCiphered(){
        for(int i = 0; i < text.length(); i++){
            char temp = text.charAt(i);
            if(!Character.isLetter(temp))
                return false;
        }
        return true;
    }

    private String getPlain(String text){
        String ans = "";

        for(int i = 0; i < text.length(); i++){
            char temp = text.charAt(i);
            if(Character.isLetter(temp))
                ans += temp;
        }

        return ans.toUpperCase();
    }

    private String getKey(){
        keyword = keyword.toUpperCase();
        String ans     = "";
        int    l       = plainText.length();

        while(l > 0){
            if(l < keyword.length())
                return ans += keyword.substring(0, l);
            ans += keyword;
            l -= keyword.length();

        }
        return ans;
    }
}
