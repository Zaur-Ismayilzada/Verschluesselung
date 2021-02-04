/*
Der Programmcode gehört mir. 

Erstmal mit einem Text, der verschlüsselt werden soll, einen Objekt von Klasse
erzeugt werden. K ist ein spezial Faktor. Damit es wird bestimmt, ein Charakter im
Text mit welchem Buchstabe ersetzt werden kann.
Z.b k = 2 dann a -> c , b -> d und so

mit Method encode wird Text verschlüsselt.
	Erstmal k Faktor wird möglich erniedrigt.
	Dann Durch eine Schleife wird es bestimmt, ob ein Charakter größ oder klein ist.
	wenn endlich Text wird verschlüsselt.


mit Method decode wird verschlüsselter Text entschlüsselt.
Aber erstmal natürlich soll ein Text verschlüsselt werden.

Zusätzlich Sie können folgenden Link Beispiele(Tests) benutzen,
um Richtigkeit meines Codes  zu überprüfen.
Beispiele befinden sich oben rechts und links von Bereich "Console".
https://edabit.com/challenge/9qSNiDpyqipHFmdwp
*/
package com.company;

public class Caesar {

    private String text;
    private String encoded_text;
    private int    k;


    public Caesar(String text, int k){
        this.k = k;
        this.text = text;
    }

    public String encode(){

        String ans = "";
        int    l   = k;
        while (l > 26)
            l -= 26;

        for (int i = 0; i < text.length(); i++) {
            char temp = text.charAt(i);

            if(Character.isLetter(temp)){
                if(Character.isUpperCase(temp)){
                    if (temp + l > 90)
                        ans += (char)(temp + l - 26);
                    else
                        ans += (char)(temp +  l);
                }
                else{
                    if (temp + l > 122)
                        ans += (char)(temp + l - 26);
                    else
                        ans += (char)(temp + l);
                }
            }
            else
                ans += temp;

        }

        encoded_text = ans;

        return ans;
    }

    public String decode(){

        if (encoded_text == null)
            return "Btte erstmal verschluesseln Sie einen Text";

        String ans = "";
        int    l   = k;

        while (l > 26)
            l -= 26;

        for (int i = 0; i < encoded_text.length(); i++) {
            char temp = encoded_text.charAt(i);

            if (Character.isLetter(temp)){
                if (Character.isUpperCase(temp)){
                    if (temp - l < 65)
                        ans += (char)(temp - l + 26);
                    else
                        ans += (char)(temp - l);
                }
                else{
                    if (temp - l < 97)
                        ans += (char)(temp - l + 26);
                    else
                        ans += (char)(temp - l);
                }
            }
            else
                ans += temp;
        }

        return ans;
    }
}
