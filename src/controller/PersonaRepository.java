package controller;

import com.generation.db.Database;

import model.Persona;
import view.PersonaView;

public class PersonaRepository 
{
    static Database db = new Database("config.txt");
    static PersonaView view = new PersonaView();
 
    static String read(String condizione) 
    {
        String query = "SELECT * FROM persone "+condizione;
        String[] datiPersone = db.select(query);
        Persona[] persone = convertiInVettorePersone(datiPersone);
        String res = view.renderMany(persone);

        return res;
    }

     /**
     * Metodo che converte un vettore contenete csv in un vettore di Persona
     * 
     * @param datiPersone un vettore contenente i csv delle persone lette dal db
     * @return un vettore di Persona con le righe lette dal db convertite in oggetti Persona
     */
    static Persona[] convertiInVettorePersone(String[] datiPersone) 
    {
        Persona[] res = new Persona[datiPersone.length];

        for(int i=0;i<datiPersone.length;i++)
        {
            res[i] = new Persona(datiPersone[i]);
        }

        return res;
    }

}
