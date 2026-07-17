# Rapport de TP — DevOps : Intégration Continue et Livraison Continue avec Jenkins

**Auteur :** Sonia
**Formation :** Master 1 SRC — Institut Européen F2I
**Période :** Juillet 2026

## Sommaire

- [TP1 — Projet Freestyle](#tp1--projet-freestyle)
- [TP2 — Projet Maven](#tp2--projet-maven)
- [TP3 — Intégration Jenkins / GitHub](#tp3--intégration-jenkins--github)
- [TP4 — Qualité de code avec SonarQube](#tp4--qualité-de-code-avec-sonarqube)
- [TP5 — Builds paramétrés](#tp5--builds-paramétrés)
- [TP6 — Déploiement automatique sur Tomcat](#tp6--déploiement-automatique-sur-tomcat)
- [TP7 — Jenkins Pipeline (Jenkinsfile / DSL)](#tp7--jenkins-pipeline-jenkinsfile--dsl)
- [TP8 — Architecture maître/esclave](#tp8--architecture-maîtreesclave)
- [TP9 — Sécurité, sauvegarde et restauration](#tp9--sécurité-sauvegarde-et-restauration)
- [Synthèse générale](#synthèse-générale)

---

## TP1 — Projet Freestyle

### Objectifs
- Créer un premier job Jenkins de type Freestyle.
- Compiler une application Java simple à partir du dépôt Git.
- Analyser le statut du build, l'historique et le workspace.

### Étapes réalisées
1. …
2. …

### Captures d'écran
![Création du job Freestyle](TP1-freestyle/captures/creation-job.png)
![Console de build réussie](TP1-freestyle/captures/console-success.png)
![Historique des builds](TP1-freestyle/captures/historique.png)

### Difficultés rencontrées
- …

### Conclusion du TP
…

---

## TP2 — Projet Maven

### Objectifs
- Industrialiser le build avec Maven (`clean install`).
- Publier automatiquement les rapports de tests JUnit (Surefire).

### Étapes réalisées
1. …

### Captures d'écran
![Configuration du job Maven](TP2-maven/captures/config-job.png)
![Rapport JUnit publié](TP2-maven/captures/junit-report.png)

### Difficultés rencontrées
- …

### Conclusion du TP
…

---

## TP3 — Intégration Jenkins / GitHub

### Objectifs
- Déclencher automatiquement un build à chaque push GitHub.
- Configurer un webhook GitHub → Jenkins.

### Étapes réalisées
1. …

### Captures d'écran
![Configuration du webhook GitHub](TP3-github/captures/webhook-config.png)
![Build déclenché automatiquement](TP3-github/captures/build-auto.png)

### Difficultés rencontrées
- …

### Conclusion du TP
…

---

## TP4 — Qualité de code avec SonarQube

### Objectifs
- Brancher SonarQube sur le job Maven.
- Analyser bugs, vulnérabilités, code smells, duplication, couverture.
- Observer le Quality Gate.

### Étapes réalisées
1. …

### Captures d'écran
![Tableau de bord SonarQube](TP4-sonarqube/captures/dashboard.png)
![Quality Gate](TP4-sonarqube/captures/quality-gate.png)

### Difficultés rencontrées
- …

### Conclusion du TP
…

---

## TP5 — Builds paramétrés

### Objectifs
- Ajouter des paramètres (Choice, String) à un job.
- Utiliser les paramètres dans les étapes de build.

### Étapes réalisées
1. …

### Captures d'écran
![Paramètres du job](TP5-parametres/captures/parametres-config.png)
![Build with Parameters](TP5-parametres/captures/build-with-params.png)

### Difficultés rencontrées
- …

### Conclusion du TP
…

---

## TP6 — Déploiement automatique sur Tomcat

### Objectifs
- Déployer un WAR sur Tomcat à chaque build réussi.
- Configurer l'action post-build « Deploy to container ».

### Étapes réalisées
1. …

### Captures d'écran
![Configuration du déploiement](TP6-tomcat/captures/deploy-config.png)
![Application déployée sur Tomcat](TP6-tomcat/captures/app-tomcat.png)

### Difficultés rencontrées
- …

### Conclusion du TP
…

---

## TP7 — Jenkins Pipeline (Jenkinsfile / DSL)

### Objectifs
- Écrire un pipeline déclaratif (Build, Test, Deploy).
- Versionner le Jenkinsfile dans GitHub (Pipeline script from SCM).

### Étapes réalisées
1. …

### Captures d'écran
![Stage View du pipeline](TP7-pipeline-dsl/captures/stage-view.png)
![Jenkinsfile versionné sur GitHub](TP7-pipeline-dsl/captures/jenkinsfile-github.png)

### Difficultés rencontrées
- …

### Conclusion du TP
…

---

## TP8 — Architecture maître/esclave

### Objectifs
- Ajouter un agent Jenkins et répartir l'exécution des builds.
- Restreindre un job à un label spécifique.

### Étapes réalisées
1. …

### Captures d'écran
![Ajout du nœud agent](TP8-maitre-esclave/captures/nouveau-node.png)
![Builds répartis sur les agents](TP8-maitre-esclave/captures/repartition-builds.png)

### Difficultés rencontrées
- …

### Conclusion du TP
…

---

## TP9 — Sécurité, sauvegarde et restauration

### Objectifs
- Activer la sécurité (matrice d'autorisation, comptes utilisateurs).
- Sauvegarder puis restaurer l'instance Jenkins (ThinBackup).

### Étapes réalisées
1. …

### Captures d'écran
![Matrice de sécurité](TP9-securite-backup/captures/matrice-securite.png)
![Sauvegarde effectuée](TP9-securite-backup/captures/backup.png)
![Restauration vérifiée](TP9-securite-backup/captures/restore.png)

### Difficultés rencontrées
- …

### Conclusion du TP
…

---

## Synthèse générale

Ce que ces TP m'ont permis de mettre en pratique :
- …

Difficultés transverses rencontrées sur l'ensemble du projet :
- …

Pistes d'amélioration / ce que je referais différemment :
- …
