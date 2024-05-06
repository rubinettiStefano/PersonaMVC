package controller;

import java.util.Scanner;

import com.generation.db.Database;

import model.Persona;

public class Main 
{

    //PROPRIETÀ STATIC, accessibili in tutta la classe
    //Strumenti per interagire con il mondo esterno Scanner e Database
    static Scanner term = new Scanner(System.in);
   

    public static void main(String[] args) 
    {
        
        String cmd;
        String output;
        do 
        {
            System.out.println("-- Inserisci comando, help per la lista completa");
            cmd  = term.nextLine().toLowerCase();
            //i maiuscole e minuscole sono difficili da gestire
            //BONIFICA dell'INPUT

            //SWITCH per eseguire i sottoprogrammi
            switch (cmd) 
            {
                case "help":
                    output=commandList();
                break;
                case "c":
                    output=create();
                break;
                case "r":
                    output=read(true);
                break;
                case "u":
                    output=update();
                break;
                case "d":
                    output=delete();
                break;
                case "quit":
                    output="Programma Terminato";
                break;
                default:
                    output="Comando non riconosciuto";
                break;
            }

            System.out.println(output);
            
        } while (!cmd.equals("quit"));
        
    }

    /**
     * Metodo che chiede all'utente i dati per creare una persona
     * La inserisce nel database
     * restituisce la String "Persona creata con successo"
     * @return messaggio di successo
     */
    static String create() 
    {
        return "ciao";

    }

     /**
     * Metodo che mostra all'utente tutte le persone sul db
     * poi chiede l'id di quella che si vuole cancellare
     * L'utente deve poter inserire 0 per non effettuare la cancellazione
     * Cancella la persona con quell'id
     * restituisce la String "Persona cancellata con successo"
     * @return messaggio di successo
     */
    static String delete() 
    {
        System.out.println(read(false));
        return "ciao";
    }


    /**
     * Metodo che mostra all'utente tutte le persone sul db
     * poi chiede l'id di quella che si vuole modificare
     * L'utente deve poter inserire 0 per non effettuare la modifica
     * Chiede i nuovi dati (possono essere modificati solo eta,altezza e colore capelli)
     * Modifica la persona nel db
     * restituisce la String "Persona modificata con successo"
     * @return messaggio di successo
     */
    static String update() 
    {
        return "ciao";
    }


    //JAVADOC
    //Sistema di DOCUMENTAZIONE di Java, è un commento INTELLIGENTE allegato ad un metodo
    //quando un programmatore mette il mouse su di un metodo appare il suo javadoc
    /**
     * Sono un sottoprogramma della classe di avvio che si occupa della lettura da DATABASE
     * Per il momento leggo TUTTE le persone dal DB e restituisco una String contenente
     * i loro toString
     * @return una String contenente i toString di tutte le persone nel db
     */
    static String read(boolean chiedereCondizione) 
    {
        String condizione="";

        if(chiedereCondizione)
        {
            System.out.println("Inserisci ALL se vuoi leggere tutto o la condizione di lettura");
            String risposta = term.nextLine();
            
            if(!risposta.equalsIgnoreCase("ALL"))
            {
                condizione += " WHERE "+bonificaCondizione(risposta);
            }
        }
        
        String res = "-- Le persone lette dal db sono:\n"+PersonaRepository.read(condizione);

        return res;
    }

    /**
     * Restituisce la condizione inserita dall'utente formattata correttamente
     * @param condizione
     * @return
     */
    static String bonificaCondizione(String condizione)
    {
        String[] splittatoPerSpazio = condizione.split(" ");
        int dimensioneFinale =0;

        for(int i=0;i<splittatoPerSpazio.length;i++)
        {
            String elementoCorrente = splittatoPerSpazio[i];

            String[] splittatoPerUguale = elementoCorrente.split("=");
            dimensioneFinale += splittatoPerUguale.length;
        }

        String[] vettoreFinale = new String[dimensioneFinale];
        int posizioneVFin = 0;

        for(int i=0;i<splittatoPerSpazio.length;i++)
        {
            String elementoCorrente = splittatoPerSpazio[i];
            //splitta per OR, AND, = ,< ,>
            String[] splittatoPerSpeciale = elementoCorrente.split("[=<>!]|[\\\\s]*OR[\\\\s]*|[\\\\s]*AND[\\\\s]*");
            
            for(int y=0;y<splittatoPerSpeciale.length;y++)
            {
                vettoreFinale[posizioneVFin] = splittatoPerSpeciale[y];
                posizioneVFin++;
            }
        }

        String[] elementiNonInteressanti = new String[]{""," ","id","nome","cognome","eta","altezza","colore_capelli","=","<=",">=","!=","AND","OR"};

        for(int i=0;i<posizioneVFin;i++)
        {
            boolean trovato = false;
            for(int y=0;y<elementiNonInteressanti.length;y++)
            {
                if(vettoreFinale[i].trim().equalsIgnoreCase(elementiNonInteressanti[y]))
                    trovato=true;
            }

            if(!trovato)
            condizione = condizione.replace(vettoreFinale[i].trim(), "'"+vettoreFinale[i].trim()+"'");
        }

        return condizione;
    }

   

    static String commandList()
    {
        return  "-- C       -> Inserimento \n"+
                "-- R       -> Lettura \n"+
                "-- U       -> Modifica \n"+
                "-- D       -> Cancellazione \n"+
                "-- QUIT    -> Termina ";
    }

    
}
