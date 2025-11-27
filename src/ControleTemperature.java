public class ControleTemperature implements IServiceThermostat, IServiceTemperature{

    private IServiceTemperature serviceTemperature;
    private IServiceThermostat serviceChauffage;
    private Double derniereTemperature = null;


    public ControleTemperature(IServiceTemperature Temp, IServiceThermostat Chauf){
        this.serviceTemperature = Temp;
        this.serviceChauffage = Chauf;
    }

    private void appliquerRegle(double temp) {
        if (temp < 21.0) {
            serviceChauffage.demarrerChauffage();
        } else {
            serviceChauffage.arreterChauffage();
        }
    }

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
        return derniereTemperature;
    }

    @Override
    public void demarrerChauffage() {

    }

    @Override
    public void arreterChauffage() {

    }
}
