# Questions DevOps

---

### 🛠️ Comment définiriez-vous le DevOps ?
Le DevOps est une philosophie ou méthode de travail qui vise à rapprocher les équipes de développement (Dev) et d’exploitation (Ops). L’objectif est de travailler plus efficacement ensemble en :

- Automatisant les tâches répétitives (tests, déploiement, etc.),
- sécurisant les livraisons via des tests réguliers,
- en travaillant sur des environnements distincts (développement, test, production) pour éviter tout impact direct sur l’environnement client.

---

### 📌 Qu’impose le DevOps ?
Le DevOps implique plusieurs bonnes pratiques, dont :

- L’utilisation systématique de tests unitaires en front comme en back,
- La mise en place de pipelines d’intégration continue (CI), comme GitHub Actions, pour garantir que le code est fonctionnel avant d’être intégré,
- Un processus de validation rigoureux : sans tests valides, pas de merge vers la branche de production.

---

### ⚖️ Quels sont les avantages et les inconvénients du DevOps ?
#### ✅ Points forts :
- Meilleure communication et collaboration entre les équipes,
- Automatisation des tâches répétitives = gain de temps et réduction des erreurs humaines,
- Livraisons plus fréquentes et plus sûres, donc une amélioration globale de la qualité du produit.

#### ⚠️ Points faibles :
- Une mise en place parfois longue en entreprise, surtout si les équipes ne sont pas habituées,
- Cela demande un changement de culture,  les devs et les ops doivent apprendre à travailler ensemble, ce qui peut prendre du temps.

---

### 💬 Quel est votre avis sur le DevOps ?
Je trouve que c’est une approche moderne, utile et efficace. Même si l’automatisation peut demander du temps au départ, c’est un vrai gain sur le long terme. Elle encourage la communication entre les équipes, ce qui rend le suivi des tâches et l’avancement des projets beaucoup plus clair.

---

### 🧪 Quels sont les tests primordiaux pour toute application ?
Trois types de tests sont essentiels pour garantir la qualité d’une application :
- Les tests unitaires,
- Les tests d’intégration,
- Les tests fonctionnels

---

### Shema de l'architecture 
![image](https://github.com/user-attachments/assets/d89ecf1f-8e4f-475f-a967-d3c447bca8ac)

---

### Les réalisations : 
- 1. Création back des quiz avec persistance
- 2. Création back pour répondre aux quiz
- 3. Calcul automatique de la note du quiz
- 4. Mise en production sur DockerHub (CD ici)

J’ai fait le choix de me concentrer sur le déploiement via Docker et une utilisation rigoureuse de Git, plutôt que sur le design de l’interface.

Je prévois de poursuivre ce projet sur mon temps libre afin de développer davantage la partie DevOps, en vue de la présenter lors de l’oral.

---

