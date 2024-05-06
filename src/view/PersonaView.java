package view;

import model.Persona;

/**
 * Per ogni Classe Tipo una view, che contiene 2 metodi
 */
public class PersonaView 
{


    public String render(Persona daGraficare)
    {
       
        String template = "Persona con id: [id], chiamata [nome] [cognome], di anni [eta] e con capelli di colore [colore] ";

        template =  template
                    .replace("[id]", daGraficare.id+"")
                    .replace("[nome]", daGraficare.nome+"")
                    .replace("[cognome]", daGraficare.cognome+"")
                    .replace("[eta]", daGraficare.eta+"")
                    .replace("[colore]", daGraficare.colore_capelli+"");

        return template;
        
    }

    public String renderMany(Persona[] personedaGraficare)
    {
        String res="";
        for(int i=0;i<personedaGraficare.length;i++)
        {
            res+= render(personedaGraficare[i]) + "\n";
        }

        return res;
    }
}
