/*
Der Programmcode gehört mir. 

Ich habe meinen Code nach den Schritten in dieser Seite geschrieben
https://edabit.com/challenge/Jfw8TqjNtNnq5jtkA

In Konstruktor werden Variablen nach Eingabe zugeordnet.

Method isCiphered()
	Es wird überprüft, ob der Text schon verschlüsselt wird.

Method trunc()
	Alle Buchstaben werden verkleinert.und Characters, die nicht Buchstabe sind,
	werden weggelassen.
	
Method fill()
	Ein 5x5 Feld wird erzeugt. Erstmal wird mit Buchstabe von Variabel keyword
	gefüllt.Die Reste werden von andere Buchstabe, die in keyword nicht vorhanden sind
	gefüllt.


Method shape()
	Der text wird nach einige Regeln verteilt.


mit Method encode wird Text verschlüsselt.
	
	
mit Method decode wird verschlüsselter Text entschlüsselt.
Aber erstmal soll verschlüsselter Text in einem String gespeichert werden.


Schuldigung es ist zu aufwändig andere Methode zu erklären.

Zusätzlich Sie können folgenden Link Beispiele(Tests) benutzen,
um Richtigkeit meines Codes  zu überprüfen.
Beispiele befinden sich oben rechts und links von Bereich "Console".
*/
package com.company;

public class PlayFair {

    private String text;
    private String keyword;
    private char[][] key;
    private String[] strs;
    private boolean  ciphered;

    public PlayFair(String text, String keyword){
        this.text     = text;
        ciphered      = isCiphered();
        this.keyword  = keyword;
        this.keyword  = trunc(this.keyword);
        key           = fill();

        if(ciphered)
            strs = splitEqual();
        else
            strs = shape();
    }

    public String encode(){

        if(ciphered)
            return "Dieser Text wird schon verschluesselt.";

        for(int i = 0; i < strs.length; i++){
            if(isInRow(key, strs[i]))
                strs = row(key, strs, i);
            else if(isInCol(key, strs[i]))
                strs = col(key, strs, i);
            else
                strs = dia(key, strs, i);
        }

        return reshape(strs);
    }

    public String decode(){

        if(!ciphered)
            return "Dieser Text wird schon entschluesselt.";

        for(int i = 0; i < strs.length; i++){
            if(isInRow(key, strs[i]))
                strs = row_reverse(key, strs, i);
            else if(isInCol(key, strs[i]))
                strs = col_reverse(key, strs, i);
            else
                strs = dia(key, strs, i);
        }

        return reshape(strs);
    }

    private String trunc(String str){
        str = str.toLowerCase();

        String temp = "";

        for(int i = 0; i < str.length(); i++){
            if(Character.isLetter(str.charAt(i)))
                temp += str.charAt(i);
        }

        return temp;
    }

    private char[][] fill(){
        char[][] ans = new char[5][5];

        String temp = "abcdefghiklmnopqrstuvwxyz";
        String used = "";
        int k = 0;
        int l = 0;

        for(int i = 0; i < ans.length; i++){
            if(k >= keyword.length())
                break;
            for(int j = 0; j < ans[i].length; j++){
                while(k < keyword.length()){
                    if(!used.contains(Character.toString(keyword.charAt(k))))
                    {
                        ans[i][j] = keyword.charAt(k);
                        used     += keyword.charAt(k);
                        break;
                    }
                    k++;
                }
            }
        }

        for(int i = 0; i < ans.length; i++){
            for(int j = 0; j < ans[i].length; j++){
                if(ans[i][j] == '\u0000')
                {
                    while(l < temp.length()){
                        if(!keyword.contains(Character.toString(temp.charAt(l)))){
                            ans[i][j] = temp.charAt(l);
                            l++;
                            break;
                        }
                        l++;
                    }
                }
            }
        }

        return ans;
    }

    private String[] shape(){
        text        = trunc(text);
        String ans  = "";

        for(int i = 0; i < text.length(); i++){
            if(text.length() - i < 2){ans += text.substring(i); break;}

            ans += text.substring(i, i + 2) + " ";
            i++;
        }

        String[] strs = ans.split(" ");

        if(hasPair(strs))
            strs = unPair(strs);

        if(strs[strs.length - 1].length() == 1)
            strs[strs.length - 1] = strs[strs.length - 1] + "x";

        return strs;
    }

    private boolean hasPair(String[] arr){

        for(int i = 0; i < arr.length; i++){
            if(arr[i].length() == 2 && arr[i].charAt(0) == arr[i].charAt(1))
                return true;
        }

        return false;
    }

    private String[] unPair(String[] arr){
        String  ans   = "";
        boolean flag  = false;
        char    plc   = ' ';
        for(int i = 0; i < arr.length; i++){
            if(flag)
            {
                ans = ans + plc + arr[i].charAt(0) + " ";
                if(arr[i].length() == 2)
                {
                    plc = arr[i].charAt(1);
                    if(i == arr.length - 1)
                        ans += plc;
                }
            }
            else if(arr[i].charAt(0) == arr[i].charAt(1))
            {
                ans += arr[i].charAt(0) + "x ";
                flag = true;
                plc  = arr[i].charAt(1);
            }
            else
                ans += arr[i] + " ";
        }

        String[] a = ans.split(" ");

        while(hasPair(a))
            a = unPair(a);

        return a;
    }

    private boolean isInRow(char[][] arr, String str){
        char    ch1  = str.charAt(0);
        char    ch2  = str.charAt(1);
        boolean flag = false;

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(arr[i][j] == ch1)
                {
                    if(flag)
                        return true;
                    else
                        flag = true;
                }
                else if(arr[i][j] == ch2)
                {
                    if(flag)
                        return true;
                    else
                        flag = true;
                }
            }
            flag = false;
        }

        return false;
    }

    private String[] row(char[][] keyArr, String[] strArr, int k){
        char ch1 = strArr[k].charAt(0);
        char ch2 = strArr[k].charAt(1);

        String ans = "";

        for(int i = 0; i < keyArr.length; i++){
            for(int j = 0; j < keyArr[i].length; j++){

                if(keyArr[i][j] == ch1){
                    if(j == keyArr[i].length - 1)
                        ans = keyArr[i][0] + ans;
                    else
                        ans = keyArr[i][j + 1] + ans;
                }
                else if(keyArr[i][j] == ch2)
                {
                    if(j == keyArr[i].length - 1)
                        ans += keyArr[i][0];
                    else
                        ans += keyArr[i][j + 1];
                }
            }
        }


        strArr[k] = ans;

        return strArr;
    }

    private String[] row_reverse(char[][] keyArr, String[] strArr, int k){

        char   ch1 = strs[k].charAt(0);
        char   ch2 = strs[k].charAt(1);
        String ans = "";

        for(int i = 0; i < keyArr.length; i++){
            for(int j = 0; j < keyArr[i].length; j++){
                if(keyArr[i][j] == ch1)
                {
                    if(j == 0)
                        ans = keyArr[i][4] + ans;
                    else
                        ans = keyArr[i][j - 1] + ans;
                }
                else if(keyArr[i][j] == ch2)
                {
                    if(j == 0)
                        ans += keyArr[i][4];
                    else
                        ans += keyArr[i][j - 1];
                }
            }
        }

        strArr[k] = ans;

        return strArr;
    }

    private boolean isInCol(char[][] arr, String str){
        char    ch1  = str.charAt(0);
        char    ch2  = str.charAt(1);
        boolean flag = false;

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(arr[j][i] == ch1)
                {
                    if(flag)
                        return true;
                    else
                        flag = true;
                }
                else if(arr[j][i] == ch2)
                {
                    if(flag)
                        return true;
                    else
                        flag = true;
                }
            }

            flag = false;
        }

        return false;
    }

    private String[] col(char[][] keyArr, String[] strArr, int k){
        char ch1 = strArr[k].charAt(0);
        char ch2 = strArr[k].charAt(1);

        String ans = "";

        for(int i = 0; i < keyArr.length; i++){
            for(int j = 0; j < keyArr[i].length; j++){
                if(keyArr[j][i] == ch1){
                    if(j == keyArr.length - 1)
                        ans = keyArr[0][i] + ans;
                    else
                        ans = keyArr[j + 1][i] + ans;
                }
                else if(keyArr[j][i] == ch2)
                {
                    if(j == keyArr.length - 1)
                        ans += keyArr[0][i];
                    else
                        ans += keyArr[j + 1][i];
                }
            }
        }


        strArr[k] = ans;

        return strArr;
    }

    private String[] col_reverse(char[][] keyArr, String[] strArr, int k){

        char   ch1 = strArr[k].charAt(0);
        char   ch2 = strArr[k].charAt(1);
        String ans = "";

        for(int i = 0; i < keyArr.length; i++){
            for(int j = 0; j < keyArr[i].length; j++){
                if(keyArr[j][i] == ch1)
                {
                    if(j == 0)
                        ans = keyArr[4][i] + ans;
                    else
                        ans = keyArr[j - 1][i] + ans;
                }
                else if(keyArr[j][i] == ch2)
                {
                    if(j == 0)
                        ans += keyArr[4][i];
                    else
                        ans += keyArr[j - 1][i];
                }
            }
        }

        strArr[k] = ans;

        return strArr;
    }

    private String[] dia(char[][] keyArr, String[] strArr, int k){
        char   ch1   = strArr[k].charAt(0);
        char   ch2   = strArr[k].charAt(1);
        int[]  ch1_i = new int[2];
        int[]  ch2_i = new int[2];
        String ans   = "";

        ch1_i = find(keyArr, ch1);
        ch2_i = find(keyArr, ch2);

        if(ch1_i.length != 0 && ch2_i.length != 0)
        {
            char a = keyArr[ch1_i[0]][ch2_i[1]];
            char b = keyArr[ch2_i[0]][ch1_i[1]];
            ans = ans + a + b;
        }

        strArr[k] = ans;

        return strArr;
    }

    private int[] find(char[][] keyArr, char ch){
        for(int i = 0; i < keyArr.length; i++){
            for(int j = 0; j < keyArr[i].length; j++){
                if(keyArr[i][j] == ch)
                    return new int[]{i, j};
            }
        }

        return new int[]{};
    }

    private String reshape(String[] arr){
        String ans = "";
        for(String temp: arr)
            ans += temp.toUpperCase();

        return ans;
    }

    private boolean isCiphered(){

        for (int i = 0; i < text.length(); i++) {
            if(!Character.isLetter(text.charAt(i)) ||
               Character.isLowerCase(text.charAt(i)))
                return false;
        }

        return true;
    }

    private String[] splitEqual(){

        String ans = "";

        for(int i = 0; i < text.length(); i+=2){
            ans += text.substring(i, i + 2).toLowerCase() + " ";
        }

        return ans.split(" ");
    }
}
