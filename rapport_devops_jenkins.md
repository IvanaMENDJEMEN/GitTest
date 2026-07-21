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
1. Création d'un job Jenkins de type Freestyle nommé TP1_Projet_Freestyle.
2. Configuration de la source du code : dépôt Git distant https://github.com/IvanaMENDJEMEN/GitTest.git, branche master.
3. Ajout d'une étape de build Execute shell exécutant la compilation Java (javac Main.java) puis l'exécution (java Main).
4. Premier lancement du build avec une erreur volontaire dans le code source (point-virgule manquant) : le build se termine en FAILURE, avec le message d'erreur affiché dans la console (Main.java:3: error: ';' expected).
5. Correction de l'erreur dans le code, puis relance du build : compilation et exécution réussies, message Compilation et execution réussies ! affiché en console, build terminé en SUCCESS.
6. Vérification sur le tableau de bord Jenkins : le job TP1_Projet_Freestyle apparaît avec un statut vert (dernier build réussi en 5 sec).

### Captures d'écran
(TP1_Projet_Freestyle/Creation_job.png)
(TP1_Projet_Freestyle/Console_output.png)
(TP1_Projet_Freestyle/Console_failed.png)


### Conclusion du TP
Ce premier TP a permis de prendre en main la création d'un job Freestyle, sa connexion à un dépôt Git distant, et l'ajout d'une étape de build shell. L'analyse d'un build en échec (erreur de compilation) puis du build corrigé a permis de bien comprendre le lien entre la sortie console, le statut du build (FAILURE/SUCCESS) et l'historique des exécutions.

---

## TP2 — Projet Maven

### Objectifs
- Industrialiser le build avec Maven (`clean install`).
- Publier automatiquement les rapports de tests JUnit (Surefire).

### Étapes réalisées
1. Vérification du plugin Maven Integration et déclaration d'une installation Maven dans Global Tool Configuration.
2. Création d'un job Jenkins de type Projet Maven nommé TP2_Projet_Maven.
3. Configuration de la source Git (même dépôt que le TP1) et du Root POM (pom.xml).
4. Définition des goals de build : clean install.
5. Ajout d'un test unitaire JUnit (fr.isrc.AppTest) dans le projet, pour que Maven ait un test à exécuter lors du build.
6. Lancement du build : le cycle Maven s'exécute (resources → testResources → compile → test → package → install), avec exécution du test (Tests run: 1, Failures: 0, Errors: 0).
7. Le build se termine en BUILD SUCCESS, artefacts archivés par Jenkins (pom.xml et ProjetMaven-1.0.jar).
8. Vérification sur la page du job : le widget Test Result Trend confirme la publication automatique du rapport de test (Latest Test Result, aucun échec).

### Captures d'écran
(TP2_Projet_Maven/test.png)
(TP2_Projet_Maven/junit_report.png)

### Difficultés rencontrées
- La rédaction du pom.xml (dépendances, plugins, configuration du packaging) a demandé plusieurs ajustements avant d'obtenir une configuration correcte et exploitable par Jenkins.
- L'écriture du test unitaire JUnit a également nécessité plusieurs essais pour que le test soit correctement détecté et exécuté par Maven Surefire lors du build (structure du projet, dépendance JUnit, nommage de la classe de test en *Test).

### Conclusion du TP
Ce TP a permis d'industrialiser le build avec Maven à la place d'un simple script shell : le job utilise directement le pom.xml et le cycle de vie standard Maven (compile, test, package, install). L'ajout d'un test JUnit réel dans le projet a permis de valider de bout en bout la chaîne de publication des rapports Surefire par Jenkins, avec un résultat de test visible et exploitable dans l'interface.

---

## TP3 — Intégration Jenkins / GitHub

### Objectifs
- Déclencher automatiquement un build à chaque push GitHub.
- Configurer un webhook GitHub → Jenkins.

### Étapes réalisées
1. Génération d'un Personal Access Token (classic) sur GitHub (token_jenkins), avec les scopes repo et admin:repo_hook.
2. Déclaration du credential correspondant dans Jenkins (Manage Jenkins → Credentials), sous le nom github_token.
3. Configuration du job Jenkins (TP3_Github) : Source Code Management → Git → URL du dépôt + sélection du credential GitHub.
4. Exposition de l'instance Jenkins locale sur Internet via ngrok, pour la rendre joignable depuis GitHub.
5. Création du webhook côté GitHub (Settings → Webhooks) avec l'URL ngrok suivie de /github-webhook/, événement push.
6. Activation du déclencheur GitHub hook trigger for GITScm polling dans la configuration du job.
7. Test : push d'un commit ("Test webhook jenkins") sur le dépôt distant → le webhook livre la notification (Last delivery was successful) → le build Jenkins se déclenche automatiquement (Started by GitHub push by IvanaMENDJEMEN).

### Captures d'écran
![Configuration du webhook GitHub](TP3_Intégration_Jenkins_Github/webhook.png)
![Build déclenché automatiquement](TP3_Intégration_Jenkins_Github\build_auto.PNG)
![Configuration du Credentials sur Jenkins](TP3_Intégration_Jenkins_Github\Credential.PNG)
![Création du token PAT](TP3_Intégration_Jenkins_Github\creation_token.PNG)

### Difficultés rencontrées
- Jenkins tournant en local, il n'était pas joignable directement depuis GitHub pour recevoir les notifications du webhook. Il a fallu utiliser ngrok pour exposer temporairement l'instance Jenkins locale via une URL publique, à renseigner comme Payload URL du webhook.

### Conclusion du TP
Ce TP a permis de mettre en place un déclenchement automatique des builds à chaque push GitHub, remplaçant le lancement manuel utilisé jusqu'ici. La configuration du token d'accès, du credential Jenkins et du webhook a permis de comprendre la chaîne complète de communication entre GitHub et Jenkins, ainsi que la nécessité de rendre Jenkins accessible depuis l'extérieur (via ngrok en environnement de formation, ou une IP publique/reverse proxy en production).

---

## TP4 — Qualité de code avec SonarQube

### Objectifs
- Brancher SonarQube sur le job Maven.
- Analyser bugs, vulnérabilités, code smells, duplication, couverture.
- Observer le Quality Gate.

### Étapes réalisées
1. Démarrage du conteneur SonarQube (sonarqube:community) et connexion à l'interface web.
2. Génération d'un token d'accès (jenkins_token, type Global) depuis My Account → Security.
3. Modification des Goals and options du job Maven pour inclure l'analyse SonarQube : clean verify sonar:sonar -Dsonar.host.url=http://sonarqube:9000 -Dsonar.token=<token>
4. Lancement du build : exécution des tests, génération de la couverture, puis envoi de l'analyse à SonarQube.
5. Vérification sur le tableau de bord SonarQube du projet ProjetMaven : 75 lignes de code analysées, 0 issue de sécurité, 0 issue de fiabilité, 4 issues de maintenabilité, couverture de 77,8 % (sur 15 lignes à couvrir), 0 % de duplication.
6. Vérification du Quality Gate : statut Passed.

### Captures d'écran
![Tableau de bord SonarQube](TP4_SonarQube\Dashboard.PNG)
![Création du token SonarQube](TP4_SonarQube\token_sonarqube.PNG)
![Quality Gate](TP4_SonarQube/quality_gate.png)
![Configuration du job sur Goal Option](TP4_SonarQube/goal_config.png)

### Difficultés rencontrées
Premier essai de connexion à SonarQube échoué : l'URL http://localhost:9000 renseignée côté Jenkins n'était pas joignable, car Jenkins et SonarQube tournent dans des conteneurs Docker séparés. localhost pointait vers le conteneur Jenkins lui-même et non vers SonarQube. La correction a consisté à utiliser le nom du conteneur SonarQube sur le réseau Docker commun (http://sonarqube:9000) à la place de localhost.

### Conclusion du TP

Ce TP a permis de mettre en place l'analyse de qualité de code avec SonarQube, intégrée directement dans le build Maven. Le Quality Gate passé confirme que le code respecte les seuils définis (bugs, vulnérabilités, duplication). La principale difficulté — la résolution d'URL entre conteneurs Docker — a permis de bien comprendre la différence entre l'adressage réseau vu depuis l'hôte et celui vu depuis l'intérieur d'un conteneur, un point déjà rencontré lors de la configuration du webhook GitHub au TP3.

---

## TP5 — Builds paramétrés

### Objectifs
- Ajouter des paramètres (Choice, String) à un job.
- Utiliser les paramètres dans les étapes de build.

### Étapes réalisées
1. Activation de l'option « Ce build a des paramètres » (This project is parameterized) dans les réglages généraux du job TP3_Github.

2. Configuration du premier paramètre Choice Parameter nommé ENVIRONNEMENT contenant trois choix possibles : dev, recette et prod.

3. Configuration du second paramètre String Parameter nommé VERSION avec la valeur par défaut 1.0.0 pour définir la version de l'artefact.

4. Ajout d'une étape de build Execute shell exploitant les variables d'environnement ${ENVIRONNEMENT} et ${VERSION} pour afficher l'environnement ciblé, exécuter un contrôle conditionnel et simuler la création de l'artefact app-${ENVIRONNEMENT}-${VERSION}.jar.

5. Exécution du build via le menu Build with Parameters avec le choix de l'environnement dev et la version 2.1.0.

6. Validation dans la Console Output (build #9) : prise en compte des valeurs saisies (Environnement ciblé : dev, Version de l'application : 2.1.0), passage dans la branche de test et génération validée du fichier app-dev-2.1.0.jar.

### Captures d'écran
![Paramètres du job](TP5_Builds_paramétrés\param_choice.PNG)
![Sortie Console du build paramétré](TP5_Builds_paramétrés\console_param.PNG)
![Build avec Paramètre](TP5_Builds_paramétrés\build_with_param.PNG)
![Configuration du build](TP5_Builds_paramétrés\build_step_param.PNG)

### Conclusion du TP
Ce TP a permis d'ajouter de la flexibilité aux pipelines d'intégration continue en évitant d'avoir des scripts codés en dur. Grâce aux variables dynamiques transmises lors du déclenchement, un même job Jenkins peut désormais servir à builder, tester ou déployer différentes versions d'un projet sur plusieurs environnements (dev, recette, prod).

---

## TP6 — Déploiement automatique sur Tomcat

### Objectifs
- Déployer un WAR sur Tomcat à chaque build réussi.
- Configurer l'action post-build « Deploy to container ».

### Étapes réalisées
1. Préparation et démarrage de Tomcat :

- Lancement d'un conteneur Tomcat 9 sur le même réseau Docker que Jenkins (jenkins-net) sur le port local 8081.

- Restauration des applications web du dossier webapps.dist vers webapps.

- Édition du fichier conf/tomcat-users.xml pour déclarer un utilisateur admin associé au rôle manager-script.

- Suppression de la restriction d'accès IP (RemoteAddrValve) dans context.xml pour autoriser les requêtes distantes émanant du conteneur Jenkins.

2. Configuration des identifiants Jenkins :

- Création de l'identifiant global tomcat_credentials de type Username with password (admin / *****) dans l'interface de gestion des identifiants.

3. Adaptation du projet Maven (pom.xml) :

- Définition du packaging <packaging>war</packaging>.

- Définition du nom d'artefact cible <finalName>App-Sonia</finalName>.

- Configuration du plugin maven-war-plugin avec l'option <failOnMissingWebXml>false</failOnMissingWebXml> pour permettre la génération du WAR sans fichier web.xml obligatoire.

4. Configuration de l'action Post-build dans Jenkins :

- Ajout de l'étape Deploy war/ear to a container.

- Spécification du fichier à déployer : **/*.war.

- Déclaration du conteneur Tomcat 9.x Remote avec l'URL réseau Docker http://tomcat:8080 et sélection des identifiants tomcat_credentials.

5. Exécution et validation :

- Lancement du build #14 : exécution des tests unitaires, packaging réussi du fichier App-Sonia.war, puis transfert automatique vers Tomcat.

- Validation dans la console Jenkins (Deploying ... Doing a fresh deployment. Finished: SUCCESS).

- Confirmation de l'accès à l'instance Tomcat via l'URL http://localhost:8081.

### Captures d'écran
![Configuration du déploiement](TP6_Déploiement_Tomcat\credntials.PNG)
![Application déployée sur Tomcat](TP6_Déploiement_Tomcat\tomcat.PNG)

### Difficultés rencontrées
- Génération du WAR avec Maven : Le build échouait initialement avec l'erreur webxml attribute is required. Le problème a été résolu en ajustant la configuration du maven-war-plugin dans le pom.xml pour ignorer l'absence du fichier web.xml.

- Authentification et accès Tomcat (Erreurs 404 & 401) : L'endpoint du Manager Tomcat bloquait l'accès distant de Jenkins. Cela a été corrigé en copiant les ressources de webapps.dist, en attribuant le bon rôle manager-script dans tomcat-users.xml et en supprimant la restriction d'adresse IP dans le fichier context.xml.

### Conclusion du TP
Ce dernier TP a concrétisé la mise en place d'une chaîne CI/CD complète. Désormais, chaque modification poussée sur le dépôt GitHub passe par des phases automatisées d'analyse de code (SonarQube), d'exécution de tests (JUnit), de construction d'artefacts (Maven WAR) et enfin de livraison continue sans intervention manuelle sur le serveur d'applications Tomcat.

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
