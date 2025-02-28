# 📌 API Documentation

## 🛠️ Endpoints User

### 🔹 Inscription (`/auth/register`)
> **Méthode :** `POST`  
> **Description :** Crée un nouvel utilisateur.  
> **Body :**
```json
{
  email: string,
  password: string,
  pseudo: string
}
```
### 🔹 Connexion (`/auth/login`)
> **Méthode :** `POST`  
> **Description :** Connecter un utilisateur.  
> **Body :**
```json
{
  email: string,
  password: string,
  pseudo: string(Pseudo de ton choix)
}
```
## 🛠️ Endpoints Recette

### 🔹 Liste de recette (`/recipe/all`)
> **Méthode :** `GET`  
> **Description :** Récuperer toute les recettes sans filtre.  

### 🔹 Créer une recette (`/recipe/new`)
> **Méthode :** `POST`  
> **Description :** Créer une recette.  
> **Body :**
```json
{ 
    email: string,
    title: string
}
```

### 🔹 Détails d'une recette (`/recipe/details/{id}`)

> **Méthode :** `GET`
> **Description :** Récuperer tout les détails d'une recette (ingrédients et étapes).


## 🛠️ Endpoints ingrédients

### 🔹 Tout les ingrédients (`/ingredient/all`)
> **Méthode :** `GET`  
> **Description :** Récuperer tout les ingrédients.

### 🔹 Créer un ingrédient (`/ingredient/new`)
> **Méthode :** `POST`  
> **Description :** Créer un ingrédient.  
> **Body :**
```json
{
   name: string
}
```

## 🛠️ Endpoints mesures

### 🔹 Toute les mesures (`/measurement/all`)
> **Méthode :** `GET`  
> **Description :** Récuperer toute les mesures.

### 🔹 Créer une mesure (`/measurement/new`)
> **Méthode :** `POST`  
> **Description :** Créer une mesure.  
> **Body :**

```json
{
  name: string,
  type: string
}
```

## 🛠️ Endpoints recherche

### 🔹 Faire une recherche (`/search?name={search}`)
> **Méthode :** `GET`  
> **Description :** Récuperer un objet contenant une liste de recette ou d'ingrédients en fonction du nom.

