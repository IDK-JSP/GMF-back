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

## 🛠️ Endpoints étape(s) d'une recette

### 🔹 Toute les étapes d'une recette (`/stage/{id}`)
> **Méthode :** `GET`  
> **Description :** Récuperer toute les étapes d'une recette.  

### 🔹 Créer les étapes d'une recette (`/stage/new`)
> **Méthode :** `POST`  
> **Description :** Créer toute les étapes d'une recette.  
> **Body :**
```json
[
   {
       stage: int,
       recipe_id: int,
       content: String
   }
]
```

## 🛠️ Endpoints ingrédients

### 🔹 Toute les ingrédients (`/ingredient/all`)
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