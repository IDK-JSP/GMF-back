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
    diet: string;
    is_favorite: string //true ou false
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
> **Description :** Créer une recette, un token dans le header est nécessaire.  
> **Body :**
```json
{
  "recipe":{
    "title" : string,
    "person" : int
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
      "quantity" : double,
      "id_measurement" : int
    },
    {
      "id_ingredient": int,
      "quantity" : double,
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
> **Description :** Créer un ingrédient, un token dans le header est nécessaire.  
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
> **Description :** Créer une mesure, un token dans le header est nécessaire.  
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
> **Description :** Récuperer un objet contenant une liste de recette favoris et d'ingrédient favoris en fonction d'un email, un token dans le header est nécessaire.  
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
> **Description :** Créer un favoris, un token dans le header est nécessaire.  
> **Body :**

```json
{
  favoriteable_type: string //ingredient ou recipe,
  favoriteable_id: int // id de la recette ou de l'ingrédient
}
```

## 🛠️ Endpoints avis

### 🔹 Créer un avis avec une note sur une recette (`opinion/new`)
> **Méthode :** `POST`  
> **Description :** Créer un avis sur une recette, ça modifiera la note de la recette en conséquence, un token dans le header est nécessaire.  
> **Body :**
>

```json
{
  id_recipe: int,
  rate: int
}
```

## 🛠️ Endpoints validation de recette

### 🔹 Récupérer toute les recettes à valider (`request/all`)
> **Méthode :** `GET`  
> **Description :** Récuperer la totalité des recettes qui sont en attente de validation, un token dans le header est nécessaire.  
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
        updateTime: string
    }
]
```

### 🔹 Valider une recette (`request/recipe`)

> **Méthode :** `POST`  
> **Description :** Valider une recette.
> **Body :**
```json
{
    id_recipe: int,
    email: string,
    title: string,
    content: string,
    image: string,
    person: int,
    state: string,
    rate: int,
    nbRate: int,
    createTime: string,
    updateTime: string
}
```

## 🛠️ Endpoints collection de recette
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
    diet: string;
    is_favorite: string //true ou false
  }
]
```
### 🔹 Récupérer les meilleurs recette (`collection/top`)
> **Méthode :** `GET`

### 🔹 Récupérer les recettes les plus notés (`collection/nbRate`)
> **Méthode :** `GET`  

### 🔹 Récupérer les recettes faites récemment (`collection/recent`)
> **Méthode :** `GET`  

### 🔹 Récupérer les recettes vege (`collection/vege`)
> **Méthode :** `GET`  

### 🔹 Récupérer les recettes vegan (`collection/vegan`)
> **Méthode :** `GET`  

### 🔹 Récupérer les recettes validés (`collection/validate`)
> **Méthode :** `GET`  