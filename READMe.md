**Endpoint user :
    Pour créer un user : "/auth/register":
        {
            email : string,
            password : string,
            pseudo : string
        } 
    Pour login un user : "/auth/login":
        {
            email : string,
            password : string,
            pseudo : string(Pseudo de ton choix)
        }
**Endpoint liste de recipe:
    Pour toute les recettes: "/recipe/all"
    Pour créer une recette: "/recipe/new" : 
        { 
            email : string,
            title : string
        }
**Endpoint étape des recipe:
    Pour toute les étapes d'une recettes "/stage/{id}"
    Pour créer les étapes d'une recettes "/stage/new":
        [
           {
               stage : int,
               recipe_id : int,
               content : String
           }
        ]