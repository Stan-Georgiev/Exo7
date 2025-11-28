/** Interface définissant les fonctions de contrôle du thermostat. */
public interface ServiceThermostat {

    /** Fonction permettant de démarrer le chauffage afin d'augmenter la température. */
    void demarrerChauffage();

    /** Fonction permettant d'arrêter le chauffage afin de laisser la température baisser. */
    void arreterChauffage();
}
