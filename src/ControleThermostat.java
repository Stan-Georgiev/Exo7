/** Classe permettant de contrôler le thermostat en fonction de la température. */
public class ControleThermostat implements ServiceThermostat, ServiceTemperature {
    /** Instance de l'interface ServiceTemperature. */
    private ServiceTemperature serviceTemperature;
    /** Instance de l'interface ServiceThermostat. */
    private ServiceThermostat serviceThermostat;
    /** Valeur de la dernière température enregistrée. */
    private Double derniereTemperature = null;

    /** Constructeur de la classe ControleThermostat.
     *
     * @param temp Interface ServiceTemperature
     * @param therm Interface ServiceThermostat
     */
    public ControleThermostat(ServiceTemperature temp, ServiceThermostat therm) {
        this.serviceTemperature = temp;
        this.serviceThermostat = therm;
    }

    /** Fonction qui permet de démarrer ou arrêter le chauffage en fonction de la température.
     *
     * @param temp la température de la pièce
     */
    private void appliquerRegle(double temp) {
        if (temp < 21.0) {
            serviceThermostat.demarrerChauffage();
        } else {
            serviceThermostat.arreterChauffage();
        }
    }

    /** Fonction qui applique les règles de fonction du thermostat tout en s'assurant que la température reçue
     * ne soit pas une donnée erronée.
     */
    public void executer() {
        double tempActuelle = serviceTemperature.getTemperature();

        if (derniereTemperature == null) {
            appliquerRegle(tempActuelle);
            derniereTemperature = tempActuelle;
            return;
        }

        if (Math.abs(tempActuelle - derniereTemperature) > 0.5) {
            return;
        }

        appliquerRegle(tempActuelle);

        derniereTemperature = tempActuelle;
    }

    @Override
    public double getTemperature() {
        if (derniereTemperature == null) {
            derniereTemperature = serviceTemperature.getTemperature();
        }
        return derniereTemperature;
    }

    @Override
    public void demarrerChauffage() {
        serviceThermostat.demarrerChauffage();
    }

    @Override
    public void arreterChauffage() {
        serviceThermostat.arreterChauffage();
    }
}
