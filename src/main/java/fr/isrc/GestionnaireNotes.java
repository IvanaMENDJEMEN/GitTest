package fr.isrc;

import java.util.List;

public class GestionnaireNotes {

    // Code smell : Variable inutile ou mal nommée
    private String unusedField = "test"; 

    // Méthode propre et testée
    public double calculerMoyenne(List<Double> notes) {
        if (notes == null || notes.isEmpty()) {
            return 0.0;
        }
        double somme = 0;
        for (double note : notes) {
            somme += note;
        }
        return somme / notes.size();
    }

    // Code smell & Bug potentiel : Méthode complexe / mauvaise gestion des erreurs
    public String evaluerEtudiant(double note) {
        // Sonar va signaler un Code Smell (utilisation de System.out.println au lieu d'un logger)
        System.out.println("Évaluation de la note : " + note); 

        if (note < 0 || note > 20) {
            // Sonar va repérer une branche d'erreur non couverte par les tests si non testée
            return "INVALID";
        }

        if (note >= 10) {
            return "ADMIS";
        } else {
            return "AJOURNÉ";
        }
    }
}