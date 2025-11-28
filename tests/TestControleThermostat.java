import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

/** Classe permettant de tester le bon fonctionnement de la classe ControleThermostat. */
public class TestControleThermostat {
    /** Instance de la classe ControleThermostat. */
    private ControleThermostat controleThermostat;
    /** Stub de l'interface ServiceThermostat. */
    private ServiceThermostat serviceThermostat;
    /** Stub de l'interface ServiceTemperature. */
    private ServiceTemperature serviceTemperature;

    /** Fonction de setup permettant d'instancier les Stubs et de les assigner à controleThermostat. */
    @Before
    public void setUp() {
        serviceTemperature = mock(ServiceTemperature.class);
        serviceThermostat = mock(ServiceThermostat.class);
        controleThermostat = new ControleThermostat(serviceTemperature, serviceThermostat);
    }

    @Test
    public void testExecuter() {
        when(serviceTemperature.getTemperature()).thenReturn(20.0);
        controleThermostat.executer();
        verify(serviceTemperature).getTemperature();
        verify(serviceThermostat).demarrerChauffage();

        when(serviceTemperature.getTemperature()).thenReturn(22.0);
        controleThermostat.executer();
        verify(serviceTemperature, times(2)).getTemperature();

        when(serviceTemperature.getTemperature()).thenReturn(20.5);
        controleThermostat.executer();
        verify(serviceTemperature, times(3)).getTemperature();
        verify(serviceThermostat, times(2)).demarrerChauffage();

        when(serviceTemperature.getTemperature()).thenReturn(21.0);
        controleThermostat.executer();
        verify(serviceTemperature, times(4)).getTemperature();
        verify(serviceThermostat).arreterChauffage();

        when(serviceTemperature.getTemperature()).thenReturn(19.0);
        controleThermostat.executer();
        verify(serviceTemperature, times(5)).getTemperature();

        when(serviceTemperature.getTemperature()).thenReturn(21.5);
        controleThermostat.executer();
        verify(serviceTemperature, times(6)).getTemperature();
        verify(serviceThermostat, times(2)).arreterChauffage();
    }

    @Test
    public void testGetTemperature() {
        when(serviceTemperature.getTemperature()).thenReturn(20.0);
        controleThermostat.getTemperature();
        verify(serviceTemperature).getTemperature();
        controleThermostat.getTemperature();
        verify(serviceTemperature).getTemperature();
    }

    @Test
    public void testDemarrerChauffage() {
        when(serviceTemperature.getTemperature()).thenReturn(20.0);
        controleThermostat.getTemperature();
        controleThermostat.demarrerChauffage();
        verify(serviceThermostat).demarrerChauffage();
    }

    @Test
    public void testArreterChauffage() {
        when(serviceTemperature.getTemperature()).thenReturn(21.0);
        controleThermostat.getTemperature();
        controleThermostat.arreterChauffage();
        verify(serviceThermostat).arreterChauffage();
    }
}
