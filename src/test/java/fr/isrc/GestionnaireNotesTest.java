package fr.isrc;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class GestionnaireNotesTest {

    private final GestionnaireNotes gestionnaire = new GestionnaireNotes();

    @Test
    public void testCalculerMoyenneValide() {
        double moyenne = gestionnaire.calculerMoyenne(Arrays.asList(12.0, 14.0, 16.0));
        assertEquals(14.0, moyenne, 0.001, "La moyenne de 12, 14 et 16 doit être 14");
    }

    @Test
    public void testCalculerMoyenneListeVide() {
        double moyenne = gestionnaire.calculerMoyenne(Collections.emptyList());
        assertEquals(0.0, moyenne, "La moyenne d'une liste vide doit être 0");
    }

    @Test
    public void testEvaluerEtudiantAdmis() {
        String resultat = gestionnaire.evaluerEtudiant(15.0);
        assertEquals("ADMIS", resultat);
    }
}