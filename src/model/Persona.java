package model;

public class Persona 
{
    public int id;
    public String nome,cognome;
    public int eta,altezza;
    public String colore_capelli;

    public Persona(){}

    //COSTRUTTORE che utilizziamo quando convertiamo una riga letta dal db in un oggetto Persona
    public Persona
           (
                int id,String nome,String cognome,
                int eta,int altezza,String colore_capelli
           )
    {
        //IN CASO DI OMONIMIA i PARAMETRI vincono SULLE PROPRIETÀ
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.altezza = altezza;
        this.colore_capelli = colore_capelli;
    }

    //COSTRUTTORE che utilizziamo quando creiamo una Persona su Java, che poi vogliamo inserire nel db
    public Persona
           (
                String nome,String cognome,
                int eta,int altezza,String colore_capelli
           )
    {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.altezza = altezza;
        this.colore_capelli = colore_capelli;
    }

    public Persona(String csv)
    {
        String parti[] = csv.split(",");
        //IN CASO DI OMONIMIA i PARAMETRI vincono SULLE PROPRIETÀ
        this.id             = Integer.parseInt(parti[0]);
        this.nome           = parti[1];
        this.cognome        = parti[2];
        this.eta            = Integer.parseInt(parti[3]);
        this.altezza        = Integer.parseInt(parti[4]);
        this.colore_capelli = parti[5];
    }


    //CUD
    public String generaInsert()
    {
        String template =   "INSERT INTO persone (nome,cognome,eta,altezza,colore_capelli) VALUES " +
                            "('[nome]','[cognome]',[eta],[altezza],'[colore_capelli]')";

        String sostituita =     template
                                .replace("[nome]"           , this.nome)
                                .replace("[cognome]"        , this.cognome)
                                .replace("[eta]"            , this.eta+"")
                                .replace("[altezza]"        , this.altezza+"")
                                .replace("[colore_capelli]" , this.colore_capelli);

        //INSERT INTO persone (nome,cognome,eta,altezza,colore_capelli) VALUES ('Stefano','Rubinetti',28,176,'neri')
        //'Stefano','Rubinetti',28,176,'neri'
        return sostituita;
    }

    public String generaUpdate()
    {
        String template =   "UPDATE persone set nome='[nome]',cognome='[cognome]',eta=[eta]," +
                            "altezza = [altezza] ,colore_capelli = '[colore_capelli]' WHERE id=[id]";

        String sostituita =     template
                                .replace("[nome]"           , this.nome)
                                .replace("[cognome]"        , this.cognome)
                                .replace("[eta]"            , this.eta+"")
                                .replace("[altezza]"        , this.altezza+"")
                                .replace("[colore_capelli]" , this.colore_capelli)
                                .replace("[id]"             , this.id+"");
        return sostituita;
    }

    public String generaDelete()
    {
        return "DELETE FROM persone WHERE id="+this.id;
    }

    public String toString()
    {
        return "Sono "+this.nome+" "+this.cognome+", ho "+this.eta+" anni,"+
                "sono alto/a "+this.altezza+" cm e ho i capelli "+this.colore_capelli;
    }
}
