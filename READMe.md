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
> **Réponse :**
```json
[
    {
        id_recipe: number;
        email: string;
        title: string;
        content: string;
        image: string;
        person: number;
        state: string;
        rate: number;
        nbRate: number;
        createTime: string;
        updateTime: string;
        diet: string
    }
]
```
### 🔹 Recherche d'une recette avec son id (`/recipe/{id}`)

> **Méthode :** `GET`  
> **Description :** Récuperer une recette grace à son id.  
> **Réponse :**
```json
{
    id_recipe: number;
    email: string;
    title: string;
    content: string;
    image: string;
    person: number;
    state: string;
    rate: number;
    nbRate: number;
    createTime: string;
    updateTime: string
}
```
### 🔹 Détails d'une recette (`/recipe/details/{id}`)

> **Méthode :** `GET`  
> **Description :** Récuperer tout les détails d'une recette (ingrédients, étapes et avis).  
> **Réponse :**
```json
{
    ingredientDetailDtos: [
        {
            ingredient_name: string;
            quantity: number;
            measurement: string;
            diet: string
        }
    ],
    stages: [
        {
            stage: number;
            id_recipe: number;
            content: string
        }
    ],
     opinions: [
        {
             id_recipe: number;
             email: string;
             rate: number;
             comment: string
        }
    ]
}
```
### 🔹 Créer une recette (`/recipe/new`)
> **Méthode :** `POST`  
> **Description :** Créer une recette.  
> **Body :**
```json
{
  "recipe":{
    "title" : string
  },
  "stages" : [
    {
      "stage" : int,
      "content" : string
    }
  ],
  "recipeIngredients": [
    {
      "id_ingredient": int,
      "quantity" : int,
      "id_measurement" : int
    },
    {
      "id_ingredient": int,
      "quantity" : int,
      "id_measurement" : int
    }
  ]
}
```

## 🛠️ Endpoints ingrédients

### 🔹 Tout les ingrédients (`/ingredient/all`)
> **Méthode :** `GET`  
> **Description :** Récuperer tout les ingrédients.  
> **Réponse :**
```json
[
    {
        id_ingredient: number;
        name: string;
        content: string;
        create_time: string;
        update_time: string
    }
]
```

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
> **Réponse :**
```json
[
    {
        id_measurement: number;
        name: string;
        type: string
    }
]
```
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

### 🔹 Faire une recherche avec des filtres et le nom (`/search?title={title}`) 
> **Méthode :** `POST`  
> **Description :** Récuperer une liste de recette/ingrédient en fonction d'une liste d'ingrédient données et du nom de l'ingrédient ou de la recette.

```json
[ int ]
```

## 🛠️ Endpoints favoris

### 🔹 Trouver les favoris d'un utilisateur (`favorite/search?email={email}`)
> **Méthode :** `GET`  
> **Description :** Récuperer un objet contenant une liste de recette favoris et d'ingrédient favoris en fonction d'un email.  
> **Réponse :**
```json
{
    [
        {
            id_recipe: number;
            email: string;
            title: string;
            content: string;
            image: string;
            person: number;
            state: string;
            rate: number;
            nbRate: number;
            createTime: string;
            updateTime: string
        }
    ],
    [
        {
            id_ingredient: number;
            name: string;
            content: string;
            create_time: string;
            update_time: string
        }
    ]
}
```
### 🔹 Créer un favoris (`/favorite/new`)
> **Méthode :** `POST`  
> **Description :** Créer un favoris.  
> **Body :**

```json
{
  email: string,
  favoriteable_type: string //ingredient ou recipe,
  favoriteable_id: int // id de la recette ou de l'ingrédient
}
```

## 🛠️ Endpoints avis

### 🔹 Créer un avis avec une note sur une recette (`opinion/new`)
> **Méthode :** `POST`  
> **Description :** Créer un avis sur une recette, ça modifiera la note de la recette en conséquence.  
> **Body :**
>

```json
{
  id_recipe: int,
  email: string,
  rate: int,
  comment: string
}
```