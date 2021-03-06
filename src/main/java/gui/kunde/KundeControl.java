package gui.kunde;

import java.sql.SQLException;

import business.kunde.Kunde;
import business.kunde.KundeModel;
import gui.grundriss.GrundrissControl;
import gui.parkett.ParkettControl;
import gui.sanitaerinstallation.SanitaerinstallationControl;
import gui.aussenanlage.AussenanlageControl;
import gui.bild.BildControl;
import gui.Innentuer.InnentuerControl;
import javafx.stage.Stage;

/**
 * Klasse, welche das Grundfenster mit den Kundendaten kontrolliert.
 */
public class KundeControl {
       
    // das View-Objekt des Grundfensters mit den Kundendaten
	private KundeView kundeView;
    // das Model-Objekt des Grundfensters mit den Kundendaten
    private KundeModel kundeModel;
    /* das GrundrissControl-Objekt fuer die Sonderwuensche
    zum Grundriss zu dem Kunden */
	private GrundrissControl grundrissControl;
	/* das ParkettControl-Objekt fuer die Sonderwuensche
	 zum Parkett zu dem Kunden */
	private ParkettControl parkettControl;
	/* das SanitaerinstallationControl-Objekt fuer die Sonderwuensche
	 zur Sanitaerinstallation zu dem Kunden */
	private SanitaerinstallationControl sanitaerinstallationControl;
    /* das AussenanlageControl-Objekt fuer die Sonderwuensche
    	zur Aussenanlage zu dem Kunden */
    private AussenanlageControl aussenanlageControl;
    /* das AussenanlageControl-Objekt fuer die Sonderwuensche
	der Innent�ren zu dem Kunden */
    private InnentuerControl innentuerControl;
    /* das BildControl-Objekt fuer das Fenster
	des Bildes vom Haus */
    private BildControl bildControl;
    
    /**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum 
	 * Grundfenster mit den Kundendaten.
	 * @param primaryStage, Stage fuer das View-Objekt zu dem Grundfenster mit den Kundendaten
	 */
    public KundeControl(Stage primaryStage) {
		this.kundeModel = KundeModel.getInstance();
        this.kundeView = new KundeView(this, primaryStage, kundeModel);
    }
    
    /*
     * erstellt, falls nicht vorhanden, ein Grundriss-Control-Objekt.
     * Das GrundrissView wird sichtbar gemacht.
     */
    public void oeffneGrundrissControl(){
    	if (this.grundrissControl == null){
    		this.grundrissControl = new GrundrissControl(kundeModel);
      	}
    	this.grundrissControl.oeffneGrundrissView();
    }
    
    /*
     * erstellt, falls nicht vorhanden, ein Parkett-Control-Objekt.
     * Das ParkettView wird sichtbar gemacht.
     */
    public void oeffneParkettControl(){
    	if (this.parkettControl == null){
    		this.parkettControl = new ParkettControl(kundeModel);
      	}
    	this.parkettControl.oeffneParkettView();
    }
    /*
     * erstellt, falls nicht vorhanden, ein Sanitaerinstallation-Control-Objekt.
     * Das SanitaerinstallationView wird sichtbar gemacht.
     */
    public void oeffneSanitaerinstallationControl(){
    	if (this.sanitaerinstallationControl == null){
    		this.sanitaerinstallationControl = new SanitaerinstallationControl(kundeModel);
      	}
        this.sanitaerinstallationControl.oeffneSanitaerinstallationView();
    }

    public void oeffneAussenanlageControl(){
    	if (this.aussenanlageControl == null){
    		this.aussenanlageControl = new AussenanlageControl(kundeModel);
      	}
    	this.aussenanlageControl.oeffneAussenanlageView();
    }
    
    /*
     * erstellt, falls nicht vorhanden, ein Innentuer-Control-Objekt.
     * Das AussenanlageView wird sichtbar gemacht.
     */
    public void oeffneInnentuerControl(){
    	if (this.innentuerControl == null){
    		this.innentuerControl = new InnentuerControl(kundeModel);
      	}
    	this.innentuerControl.oeffneinnentuerView();
    }
    
    /*
     * erstellt, falls nicht vorhanden, ein Grundriss-Control-Objekt.
     * Das GrundrissView wird sichtbar gemacht.
     */
    public void oeffneBildControl(int plannummer){
    	if (this.bildControl == null){
    		this.bildControl = new BildControl(kundeModel);
      	}
    	this.bildControl.oeffneBildView(plannummer);
    }
    
	/**
	 * speichert ein Kunde-Objekt in die Datenbank
	 * @param kunde, Kunde-Objekt, welches zu speichern ist
	 */
    public void speichereKunden(Kunde kunde){
      	try{
    		kundeModel.speichereKunden(kunde);
    	}
    	catch(SQLException exc){
    		exc.printStackTrace();
    		this.kundeView.zeigeFehlermeldung("SQLException",
                "Fehler beim Speichern in die Datenbank");
    	}
    	catch(Exception exc){
    		exc.printStackTrace();
    		this.kundeView.zeigeFehlermeldung("Exception",
                "Unbekannter Fehler");
    	}
    }

    public int getPlannummer() {
    	return kundeView.getPlannummer();
	}
}
