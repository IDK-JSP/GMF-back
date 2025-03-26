SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE `measurement`;
TRUNCATE TABLE `ingredient`;
TRUNCATE TABLE `diet`;
TRUNCATE TABLE `diet_ingredient`;
TRUNCATE TABLE `recipe`;
TRUNCATE TABLE `recipe_ingredient`;
TRUNCATE TABLE `stage`;
TRUNCATE TABLE `opinion`;
TRUNCATE TABLE `favorite`;
TRUNCATE TABLE `user`;
INSERT INTO `user` (`email`,`password`,`role`) VALUES
('admin@gmail.com','$2a$10$11DN7XvJEu9ri3UmtS7wm.mzbXZsy.rjCHF5Potnfjy7mXRhNlvpa','ADMIN'),
('root@gmail.com','$2a$10$11DN7XvJEu9ri3UmtS7wm.mzbXZsy.rjCHF5Potnfjy7mXRhNlvpa','USER');

INSERT INTO `measurement` (`name`, `type`) VALUES
('cl', 'volume'),
('ml', 'volume'),
('l', 'volume'),
('c. à café', 'volume'),
('c. à soupe', 'volume'),
('tasse', 'volume'),
('verre', 'volume'),
('goutte', 'volume'),
('pinte', 'volume'),
('galon', 'volume'),

('g', 'poids'),
('kg', 'poids'),
('mg', 'poids'),
('oz', 'poids'),
('lb', 'poids'),

('unité', 'quantité'),
('pièce', 'quantité'),
('tranche', 'quantité'),
('pincée', 'quantité'),
('poignée', 'quantité'),
('botte', 'quantité'),

('cm', 'longueur'),
('mm', 'longueur'),
('pouce', 'longueur');


INSERT INTO `ingredient` (`name`, `content`, `create_time`, `update_time`) VALUES
('Lait', 'Liquide blanc produit par les mammifères, utilisé en cuisine et en pâtisserie.', NOW(), NOW()),
('Beurre', 'Produit laitier obtenu par barattage de la crème, utilisé en cuisine et pâtisserie.', NOW(), NOW()),
('Farine', 'Poudre obtenue par la mouture de céréales, base des pâtes et pains.', NOW(), NOW()),
('Sucre', 'Substance sucrante extraite de la canne à sucre ou de la betterave.', NOW(), NOW()),
('Sel', 'Cristaux minéraux utilisés pour assaisonner les plats.', NOW(), NOW()),
('Poivre', 'Épice provenant des baies de poivrier, utilisée pour relever les plats.', NOW(), NOW()),
('Oeuf', 'Produit issu des oiseaux, essentiel pour la pâtisserie et les omelettes.', NOW(), NOW()),
('Huile d\'olive', 'Huile végétale extraite des olives, utilisée en cuisine méditerranéenne.', NOW(), NOW()),
('Vinaigre', 'Liquide acide issu de la fermentation de l\'alcool, utilisé en assaisonnement.', NOW(), NOW()),
('Tomate', 'Fruit rouge utilisé dans de nombreuses recettes et sauces.', NOW(), NOW()),
('Oignon', 'Légume aromatique utilisé pour rehausser la saveur des plats.', NOW(), NOW()),
('Ail', 'Plante condimentaire au goût puissant, utilisée en cuisine.', NOW(), NOW()),
('Carotte', 'Légume-racine orange riche en vitamines et utilisé dans de nombreux plats.', NOW(), NOW()),
('Pomme de terre', 'Tubercule riche en amidon, utilisé pour les purées, frites et gratins.', NOW(), NOW()),
('Riz', 'Céréale de base dans de nombreuses cuisines du monde.', NOW(), NOW()),
('Pâtes', 'Aliment à base de blé, décliné sous diverses formes et préparations.', NOW(), NOW()),
('Viande de boeuf', 'Viande rouge issue du boeuf, utilisée pour steaks, ragoûts et grillades.', NOW(), NOW()),
('Viande de poulet', 'Viande blanche issue du poulet, polyvalente en cuisine.', NOW(), NOW()),
('Poisson', 'Produit de la mer ou des eaux douces, source de protéines et d\'oméga-3.', NOW(), NOW()),
('Crevette', 'Crustacé marin utilisé dans diverses préparations culinaires.', NOW(), NOW()),
('Fromage', 'Produit laitier fermenté, décliné en plusieurs variétés.', NOW(), NOW()),
('Yaourt', 'Produit laitier fermenté, consommé nature ou aromatisé.', NOW(), NOW()),
('Pain', 'Produit de boulangerie à base de farine, eau et levure.', NOW(), NOW()),
('Miel', 'Substance sucrée produite par les abeilles à partir du nectar des fleurs.', NOW(), NOW()),
('Chocolat', 'Produit dérivé du cacao, utilisé en pâtisserie et confiserie.', NOW(), NOW()),
('Noix', 'Fruits à coque riches en lipides, utilisés en pâtisserie et cuisine.', NOW(), NOW()),
('Lentilles', 'Légumineuses riches en protéines et en fibres.', NOW(), NOW()),
('Pois chiche', 'Légumineuse utilisée pour le houmous et divers plats méditerranéens.', NOW(), NOW()),
('Champignon', 'Fongus comestible utilisé dans les sauces, poêlées et plats mijotés.', NOW(), NOW()),
('Épinard', 'Légume vert riche en fer et vitamines.', NOW(), NOW()),
('Basilic', 'Herbe aromatique utilisée en cuisine méditerranéenne.', NOW(), NOW()),
('Thym', 'Plante aromatique utilisée dans les plats mijotés et rôtis.', NOW(), NOW()),
('Coriandre', 'Herbe parfumée utilisée dans la cuisine asiatique et mexicaine.', NOW(), NOW()),
('Persil', 'Plante aromatique utilisée pour assaisonner de nombreux plats.', NOW(), NOW()),
('Curcuma', 'Épice jaune aux propriétés antioxydantes.', NOW(), NOW()),
('Cannelle', 'Épice sucrée et parfumée utilisée en pâtisserie et cuisine.', NOW(), NOW()),
('Piment', 'Épice forte utilisée pour relever les plats.', NOW(), NOW()),
('Vanille', 'Épice aromatique extraite de gousses utilisées en pâtisserie.', NOW(), NOW()),
('Lardon', 'Un truc moins bien que le bacon.', NOW(), NOW()),
('Vin rouge', "Le vin c'est mal les enfants", NOW(), NOW()),
('Crême', 'Un pur délice si tu veux mon avis', NOW(),NOW()),
('Ciboulette','Une autre plante quoi',NOW(),NOW()),
('Estragon','Alors là tu me poses une colle',NOW(),NOW()),
('Pain de mie','Moins bon que le vrai pain',NOW(),NOW()),
('Jambon', 'Pas bon ça',NOW(),NOW()),
('Tofu', 'Pas mal pour les veges',NOW(),NOW()),
('Algue','Un pur délice', NOW(),NOW()),
('Miso',"Pareille c'est un banger ça", NOW(),NOW()),
('Petits pois', 'Bien vert bien bon', NOW(), NOW()),
('Poivron', 'Pas bon ça non plus', NOW(),NOW()),
('Concombre','Pas mal avec de la vinaigrette',NOW(),NOW());

INSERT INTO `diet` (`name`, `content`) VALUES
('Végan', 'Régime excluant tous les produits d’origine animale, y compris viande, poisson, œufs, produits laitiers et miel.'),
('Végétarien', 'Régime excluant la consommation de viande et de poisson, mais autorisant les produits laitiers et les œufs.');

-- Association pour le régime Végan (id_diet = 1)
INSERT INTO `diet_ingredient` (`id_diet`, `id_ingredient`) VALUES
(1, 3),  -- Farine
(1, 4),  -- Sucre
(1, 5),  -- Sel
(1, 6),  -- Poivre
(1, 8),  -- Huile d'olive
(1, 9),  -- Vinaigre
(1, 10), -- Tomate
(1, 11), -- Oignon
(1, 12), -- Ail
(1, 13), -- Carotte
(1, 14), -- Pomme de terre
(1, 15), -- Riz
(1, 23), --
(1, 26), -- Noix
(1, 27), -- Lentilles
(1, 28), -- Pois chiches
(1, 29), -- Champignon
(1, 30), -- Épinard
(1, 31), -- Basilic
(1, 32), -- Thym
(1, 33), -- Coriandre
(1, 34), -- Persil
(1, 35), -- Curcuma
(1, 36), -- Cannelle
(1, 37), -- Piment
(1, 38), -- Vanille
(1,40),
(1,42),
(1,43),
(1,46),
(1,47),
(1,48),
(1,49),
(1,50),
(1,51);
-- Association pour le régime Végétarien (id_diet = 2)
INSERT INTO `diet_ingredient` (`id_diet`, `id_ingredient`) VALUES
(2, 1),  -- Lait
(2, 2),  -- Beurre
(2, 3),  -- Farine
(2, 4),  -- Sucre
(2, 5),  -- Sel
(2, 6),  -- Poivre
(2, 7),  -- Œuf
(2, 8),  -- Huile d'olive
(2, 9),  -- Vinaigre
(2, 10), -- Tomate
(2, 11), -- Oignon
(2, 12), -- Ail
(2, 13), -- Carotte
(2, 14), -- Pomme de terre
(2, 15), -- Riz
(2, 16), -- Pâtes
(2, 20), -- Fromage
(2, 21), -- Pain
(2, 22), -- Miel
(2, 23), -- Chocolat
(2, 24), -- Noix
(2, 25), -- Lentilles
(2, 26), -- Pois chiches
(2, 27), -- Champignon
(2, 28), -- Épinard
(2, 29), -- Basilic
(2, 30), -- Thym
(2, 31), -- Coriandre
(2, 32), -- Persil
(2, 33), -- Curcuma
(2, 34), -- Cannelle
(2, 35), -- Piment
(2, 36), -- Vanille
(2, 37), -- Vanille
(2, 38),
(2,40),
(2,41),
(2,42),
(2,43),
(2,44),
(2,46),
(2,47),
(2,48),
(2,49),
(2,50),
(2,51); -- Vanille

INSERT INTO `recipe` (`email`, `title`, `search_title`, `content`, `image`, `person`, `state`, `rate`, `nb_rate`, `create_time`, `update_time`, `cooking_time`) VALUES
('admin@gmail.com', 'Pâtes à la Carbonara','pates a la carbonara', 'Une délicieuse recette italienne avec des œufs, du parmesan et des lardons.', 'recipe_1', 4, 'validate', 4.8, 4, NOW(), NOW(), 30),
('admin@gmail.com', 'Salade César','salade cesar', 'Salade fraîche avec du poulet grillé, des croûtons et une sauce crémeuse. ', 'recipe_2', 2, 'validate', 2.8, 5, NOW(), NOW(),40),
('admin@gmail.com', 'Ratatouille','ratatouille', 'Un plat provençal à base de légumes mijotés : tomates, courgettes, aubergines et poivrons.', 'recipe_3', 4, 'validate', 3.3, 4, NOW(), NOW(),115),
('admin@gmail.com', 'Quiche Lorraine','quiche lorraine', 'Tarte salée garnie de crème, lardons et fromage.', 'recipe_4', 6, 'validate', 4, 3, NOW(), NOW(), 40),
('admin@gmail.com', 'Tarte aux Pommes','tarte aux pommes', 'Pâte sablée croustillante garnie de pommes caramélisées.', 'recipe_5', 6, 'validate', 1, 4, NOW(), NOW(),50),
('admin@gmail.com', 'Soupe de légumes','soupe de legumes', 'Un mélange de légumes mijotés pour une soupe savoureuse et réconfortante.', 'recipe_6', 4, 'validate', 0, 0, NOW(), NOW(),45),
('admin@gmail.com', 'Couscous Royal','couscous royal', 'Plat traditionnel nord-africain avec semoule, légumes et viandes variées.', 'recipe_7', 6, 'validate', 0, 0, NOW(), NOW(),120),
('admin@gmail.com', 'Poulet au curry','poulet au curry', 'Poulet mijoté dans une sauce crémeuse au curry et lait de coco.', 'recipe_8', 4, 'validate', 0, 0, NOW(), NOW(),55),
('admin@gmail.com', 'Mousse au chocolat','mousse au chocolat', 'Dessert aérien et fondant à base de chocolat noir et œufs.', 'recipe_9', 4, 'validate', 0, 0, NOW(), NOW(),60),
('admin@gmail.com', 'Pizza Margherita', 'pizza margherita', 'Une pizza classique avec une sauce tomate, mozzarella et basilic.', 'recipe_10', 2, 'validate', 0, 0, NOW(), NOW(),1),
('admin@gmail.com', 'Bœuf Bourguignon', 'boeuf bourguignon', 'Un délicieux plat mijoté de viande de bœuf, vin rouge et légumes.', 'recipe_11', 6, 'tovalidation', 4.5, 6, NOW(), NOW(), 180),
('admin@gmail.com', 'Tajine de poulet', 'tajine de poulet', 'Un plat marocain savoureux avec du poulet, des olives et des citrons confits.', 'recipe_12', 4, 'tovalidation', 4.7, 5, NOW(), NOW(), 90),
('admin@gmail.com', 'Lasagnes', 'lasagnes', 'Des couches de pâtes, sauce tomate, viande hachée et béchamel gratinée au four.', 'recipe_13', 6, 'tovalidation', 5.0, 8, NOW(), NOW(), 60),
('admin@gmail.com', 'Gâteau au chocolat', 'gateau au chocolat', 'Un gâteau moelleux et fondant au chocolat noir.', 'recipe_14', 8, 'tovalidation', 4.9, 10, NOW(), NOW(), 45),
('admin@gmail.com', 'Blanquette de veau', 'blanquette de veau', 'Un plat traditionnel français avec du veau mijoté dans une sauce crémeuse.', 'recipe_15', 6, 'tovalidation', 4.6, 7, NOW(), NOW(), 120),
('admin@gmail.com', 'Chili con carne', 'chili con carne', 'Un plat épicé mexicain à base de bœuf, haricots rouges et épices.', 'recipe_0', 4, 'tovalidation', 4.3, 6, NOW(), NOW(), 75),
('admin@gmail.com', 'Risotto aux champignons', 'risotto aux champignons', 'Un riz crémeux et savoureux avec des champignons et du parmesan.', 'recipe_0', 4, 'tovalidation', 4.8, 5, NOW(), NOW(), 40),
('admin@gmail.com', 'Paella', 'paella', 'Un plat espagnol à base de riz, fruits de mer et épices.', 'recipe_0', 6, 'tovalidation', 4.9, 8, NOW(), NOW(), 90),
('admin@gmail.com', 'Tiramisu', 'tiramisu', 'Dessert italien à base de café, mascarpone et biscuits imbibés.', 'recipe_0', 6, 'tovalidation', 4.9, 12, NOW(), NOW(), 20),
('admin@gmail.com', 'Burger maison', 'burger maison', 'Un délicieux burger avec steak haché, cheddar et sauce maison.', 'recipe_0', 2, 'tovalidation', 4.5, 4, NOW(), NOW(), 30),
('admin@gmail.com', 'Sushis variés', 'sushis varies', 'Assortiment de sushis faits maison avec poisson frais et riz vinaigré.', 'recipe_0', 4, 'tovalidation', 4.7, 6, NOW(), NOW(), 60),
('admin@gmail.com', 'Gratin dauphinois', 'gratin dauphinois', 'Des pommes de terre fondantes gratinées avec de la crème et du fromage.', 'recipe_0', 4, 'tovalidation', 4.6, 5, NOW(), NOW(), 70),
('admin@gmail.com', 'Omelette aux fines herbes', 'omelette fines herbes', 'Une omelette légère et savoureuse avec persil, ciboulette et estragon.', 'recipe_0', 2, 'tovalidation', 3.8, 3, NOW(), NOW(), 10),
('admin@gmail.com', 'Croque-monsieur', 'croque monsieur', 'Un sandwich chaud avec jambon, fromage et béchamel, grillé à la perfection.', 'recipe_0', 2, 'tovalidation', 4.2, 4, NOW(), NOW(), 15),
('admin@gmail.com', 'Pancakes américains', 'pancakes americains', 'Des pancakes moelleux à déguster avec du sirop d’érable.', 'recipe_0', 4, 'tovalidation', 4.8, 7, NOW(), NOW(), 20),
('admin@gmail.com', 'Soupe miso', 'soupe miso', 'Une soupe japonaise légère avec tofu, algues et miso.', 'recipe_0', 2, 'tovalidation', 4.1, 4, NOW(), NOW(), 10),
('admin@gmail.com', 'Riz cantonais', 'riz cantonais', 'Un riz sauté avec œufs, crevettes, petits pois et sauce soja.', 'recipe_0', 4, 'tovalidation', 4.3, 5, NOW(), NOW(), 25),
('admin@gmail.com', 'Crêpes sucrées', 'crepes sucrees', 'De délicieuses crêpes fines à garnir avec du sucre, chocolat ou confiture.', 'recipe_0', 6, 'tovalidation', 4.9, 9, NOW(), NOW(), 20),
('admin@gmail.com', 'Fondue savoyarde', 'fondue savoyarde', 'Un mélange de fromages fondus, parfait pour une soirée conviviale.', 'recipe_0', 4, 'tovalidation', 4.7, 6, NOW(), NOW(), 30),
('admin@gmail.com', 'Gaspacho', 'gaspacho', 'Une soupe froide rafraîchissante à base de tomates et légumes mixés.', 'recipe_0', 4, 'tovalidation', 4.5, 5, NOW(), NOW(), 15);
-- Pâtes à la Carbonara (id_recipe = 1)
INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(1, 16, 200, 11), -- Pâtes (g)
(1, 7, 2, 16),  -- Œufs (pièce)
(1, 2, 50, 11),  -- Beurre (g)
(1, 5, 1, 19),-- Sel (pincée)
(1,39,200,11),
(1, 6, 1, 19);  -- Poivre (pincée)

-- Salade César (id_recipe = 2)
INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(2, 18, 200, 11), -- Poulet (g)
(2, 10, 2, 17),  -- Tomates (pièce)
(2, 11, 1, 17),  -- Oignon (pièce)
(2, 5, 1, 19),   -- Sel (pincée)
(2, 6, 1, 19),   -- Poivre (pincée)
(2, 8, 20, 2);   -- Huile d'olive (ml)

-- Ratatouille (id_recipe = 3)
INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(3, 10, 3, 17), -- Tomates (pièce)
(3, 13, 1, 17), -- Carotte (pièce)
(3, 29, 100, 11), -- Champignons (g)
(3, 11, 1, 17), -- Oignon (pièce)
(3, 12, 1, 17), -- Ail (pièce)
(3, 8, 30, 2),  -- Huile d’olive (ml)
(3, 5, 1, 19),  -- Sel (pincée)
(3, 6, 1, 19);  -- Poivre (pincée)

-- Quiche Lorraine (id_recipe = 4)
INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(4, 3, 250, 1), -- Farine (g)
(4, 7, 3, 19),  -- Œufs (pièce)
(4, 2, 100, 1), -- Beurre (g)
(4, 22, 150, 1), -- Fromage (g)
(4, 5, 1, 18),  -- Sel (pincée)
(4, 6, 1, 18);  -- Poivre (pincée)

-- Tarte aux Pommes (id_recipe = 5)
INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(5, 3, 250, 11), -- Farine (g)
(5, 2, 100, 11), -- Beurre (g)
(5, 4, 100, 11), -- Sucre (g)
(5, 10, 3, 17); -- Pommes (pièce)

-- Soupe de légumes (id_recipe = 6)
INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(6, 13, 2, 17), -- Carotte (pièce)
(6, 14, 2, 17), -- Pomme de terre (pièce)
(6, 29, 100, 11), -- Champignons (g)
(6, 11, 1, 17), -- Oignon (pièce)
(6, 8, 30, 2),  -- Huile d'olive (ml)
(6, 5, 1, 19);  -- Sel (pincée)

-- Couscous Royal (id_recipe = 7)
INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(7, 15, 250, 11), -- Semoule (g)
(7, 18, 300, 11), -- Viande de boeuf (g)
(7, 21, 300, 11), -- Poulet (g)
(7, 13, 2, 17),  -- Carotte (pièce)
(7, 14, 2, 17),  -- Pomme de terre (pièce)
(7, 5, 1, 19);   -- Sel (pincée)

-- Poulet au curry (id_recipe = 8)
INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(8, 21, 300, 11), -- Poulet (g)
(8, 35, 10, 2),  -- Curcuma (ml)
(8, 5, 1, 19),   -- Sel (pincée)
(8, 6, 1, 19);   -- Poivre (pincée)

-- Mousse au Chocolat (id_recipe = 9)
INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(9, 25, 200, 11), -- Chocolat (g)
(9, 7, 3, 17),   -- Œufs (pièce)
(9, 4, 50, 11);   -- Sucre (g)

-- Pizza Margherita (id_recipe = 10)
INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(10, 3, 250, 11), -- Farine (g)
(10, 8, 20, 2),  -- Huile d'olive (ml)
(10, 10, 3, 17), -- Tomates (pièce)
(10, 22, 200, 11), -- Fromage (g)
(10, 5, 1, 19),  -- Sel (pincée)
(10, 6, 1, 19);  -- Poivre (pincée)

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(11, 17, 6, 17), -- Viande de boeuf, 6 pièces
(11, 40, 500, 11), -- Vin rouge, 500g (pouvant être exprimé en ml pour un liquide, mais ici pris en poids)
(11, 13, 2, 17), -- Carottes, 2 pièces
(11, 11, 1, 17), -- Oignons, 1 pièce
(11, 29, 100, 11), -- Champignons, 100g
(11, 5, 1, 19), -- Sel, 1 pincée
(11, 6, 1, 19); -- Poivre, 1 pincée

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(12, 18, 4, 17), -- Viande de poulet, 4 pièces
(12, 11, 1, 17), -- Oignons, 1 pièce
(12, 13, 2, 17), -- Carottes, 2 pièces
(12, 10, 3, 17), -- Tomates, 3 pièces
(12, 8, 2, 5), -- Huile d'olive, 2 cuillères à soupe
(12, 31, 1, 21), -- Basilic, 1 botte
(12, 9, 2, 5), -- Vinaigre, 2 cuillères à soupe
(12, 20, 150, 11); -- Crevettes, 150g

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(13, 16, 12, 17), -- Pâtes, 12 pièces
(13, 17, 500, 11), -- Viande de boeuf, 500g
(13, 10, 3, 17), -- Tomates, 3 pièces
(13, 11, 1, 17), -- Oignons, 1 pièce
(13, 21, 200, 11), -- Fromage, 200g
(13, 5, 1, 19); -- Sel, 1 pincée

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(14, 25, 200, 11), -- Chocolat, 200g
(14, 4, 100, 11), -- Sucre, 100g
(14, 7, 4, 17), -- Oeufs, 4 pièces
(14, 3, 100, 11), -- Farine, 100g
(14, 2, 100, 11), -- Beurre, 100g
(14, 5, 1, 19); -- Sel, 1 pincée

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(15, 17, 500, 11), -- Viande de boeuf, 500g
(15, 13, 2, 17), -- Carottes, 2 pièces
(15, 12, 2, 17), -- Oignons, 2 pièces
(15, 29, 100, 11), -- Champignons, 100g
(15, 5, 1, 19), -- Sel, 1 pincée
(15, 6, 1, 19); -- Poivre, 1 pincée

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(16, 17, 300, 11), -- Viande de boeuf, 300g
(16, 15, 200, 11), -- Riz, 200g
(16, 13, 2, 17), -- Carottes, 2 pièces
(16, 11, 1, 17), -- Oignon, 1 pièce
(16, 12, 3, 17), -- Ail, 3 pièces
(16, 9, 2, 5), -- Vinaigre, 2 cuillères à soupe
(16, 5, 1, 19), -- Sel, 1 pincée
(16, 6, 1, 19), -- Poivre, 1 pincée
(16, 37, 1, 19); -- Piment, 1 pincée

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(17, 16, 200, 11), -- Pâtes, 200g
(17, 29, 150, 11), -- Champignon, 150g
(17, 7, 1, 17), -- Oeuf, 1 pièce
(17, 21, 50, 11), -- Fromage, 50g
(17, 4, 1, 5), -- Sucre, 1 cuillère à soupe
(17, 2, 50, 11), -- Beurre, 50g
(17, 8, 1, 17), -- Huile d'olive, 1 pièce
(17, 10, 10, 5); -- Tomate, 10 cuillères à soupe

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(18, 15, 400, 11), -- Riz, 400g
(18, 19, 200, 11), -- Poisson, 200g
(18, 20, 200, 11), -- Crevette, 200g
(18, 16, 300, 11), -- Pâtes, 300g
(18, 31, 1, 21), -- Basilic, 1 botte
(18, 11, 1, 17), -- Oignon, 1 pièce
(18, 14, 2, 17); -- Pomme de terre, 2 pièces

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(19, 25, 200, 11), -- Chocolat, 200g
(19, 21, 150, 11), -- Fromage, 150g
(19, 7, 3, 17), -- Oeuf, 3 pièces
(19, 24, 50, 11), -- Miel, 50g
(19, 23, 4, 18); -- Pain, 4 tranches

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(20, 17, 150, 11), -- Viande de boeuf, 150g
(20, 23, 2, 17), -- Pain, 2 pièces
(20, 21, 50, 11), -- Fromage, 50g
(20, 12, 2, 17), -- Ail, 2 pièces
(20, 6, 1, 19), -- Poivre, 1 pincée
(20, 5, 1, 19); -- Sel, 1 pincée

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(21, 15, 200, 11), -- Riz, 200g
(21, 19, 100, 11), -- Poisson, 100g
(21, 20, 50, 11), -- Crevette, 50g
(21, 12, 1, 17), -- Ail, 1 pièce
(21, 16, 2, 17), -- Pâtes, 2 pièces
(21, 9, 3, 5); -- Vinaigre, 3 cuillères à soupe

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(22, 14, 800, 11), -- Pommes de terre, 800g
(22, 21, 200, 11), -- Fromage, 200g
(22, 41, 100, 11), -- Crème, 100g
(22, 12, 2, 17), -- Ail, 2 pièces
(22, 5, 1, 19), -- Sel, 1 pincée
(22, 6, 1, 19); -- Poivre, 1 pincée

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(23, 7, 3, 17), -- Oeufs, 3 pièces
(23, 34, 20, 11), -- Persil, 20g
(23, 42, 10, 11), -- Ciboulette, 10g
(23, 43, 5, 11), -- Estragon, 5g
(23, 12, 1, 17), -- Ail, 1 pièce
(23, 2, 1, 5); -- Beurre, 1 cuillère à soupe

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(24, 44, 2, 18), -- Pain de mie, 2 tranches
(24, 45, 100, 11), -- Jambon, 100g
(24, 21, 50, 11), -- Fromage râpé, 50g
(24, 2, 1, 17), -- Beurre, 1 pièce
(24, 41, 1, 19); -- Crème, 1 pincée

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(25, 3, 250, 11), -- Farine, 250g
(25, 7, 1, 17), -- Oeufs, 1 pièce
(25, 4, 50, 11), -- Sucre, 50g
(25, 1, 1, 5), -- Lait, 1 cuillère à soupe
(25, 2, 1, 19), -- Beurre, 1 pincée
(25, 5, 2, 19); -- Sel, 2 pincées

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(26, 46, 100, 11), -- Tofu, 100g
(26, 47, 2, 5), -- Algues, 2 cuillères à soupe
(26, 37, 1, 19), -- Piment, 1 pincée
(26, 48, 3, 5); -- Miso, 3 cuillères à soupe

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(27, 15, 200, 11), -- Riz, 200g
(27, 7, 2, 17), -- Oeuf, 2 pièces
(27, 20, 50, 11), -- Crevettes, 50g
(27, 49, 50, 11), -- Petits pois, 50g
(27, 12, 1, 17); -- Oignon, 1 pièce

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(28, 3, 250, 11), -- Farine, 250g
(28, 7, 3, 17), -- Oeuf, 3 pièces
(28, 4, 50, 11), -- Sucre, 50g
(28, 2, 1, 19), -- Beurre, 1 pincée
(28, 1, 1, 7), -- Lait, 1 verre
(28, 5, 2, 19); -- Sel, 2 pincées

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(29, 21, 500, 11), -- Fromage, 500g
(29, 23, 100, 11), -- Pain, 100g
(29, 2, 3, 17), -- Beurre, 3 pièces
(29, 41, 2, 17), -- Crème, 2 pièces
(29, 12, 2, 16); -- Ail, 2 gousses

INSERT INTO `recipe_ingredient` (`id_recipe`, `id_ingredient`, `quantity`, `id_measurement`) VALUES
(30, 10, 400, 11), -- Tomates, 400g
(30, 51, 1, 17), -- Poivron, 1 pièce
(30, 52, 1, 17), -- Concombre, 1 pièce
(30, 11, 2, 17), -- Oignon, 2 pièces
(30, 12, 1, 17), -- Ail, 1 gousse
(30, 9, 1, 5); -- Vinaigre, 1 cuillère à soupe

-- Pâtes à la Carbonara (id_recipe = 1)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 1, 'Faites cuire les pâtes dans une grande casserole d’eau bouillante salée.'),
(2, 1, 'Pendant ce temps, battez les œufs avec le parmesan râpé.'),
(3, 1, 'Faites revenir les lardons à feu moyen sans matière grasse.'),
(4, 1, 'Égouttez les pâtes et mélangez-les immédiatement avec les œufs battus et les lardons.'),
(5, 1, 'Servez chaud avec un peu de poivre et du parmesan supplémentaire.');

-- Salade César (id_recipe = 2)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 2, 'Lavez et coupez la salade en morceaux.'),
(2, 2, 'Faites griller le poulet, puis coupez-le en lamelles.'),
(3, 2, 'Mélangez la salade avec les tomates coupées, l’oignon émincé et les croûtons.'),
(4, 2, 'Ajoutez le poulet grillé et assaisonnez avec une vinaigrette maison.'),
(5, 2, 'Parsemez de parmesan râpé avant de servir.');

-- Ratatouille (id_recipe = 3)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 3, 'Lavez et découpez tous les légumes en petits morceaux.'),
(2, 3, 'Faites revenir l’oignon et l’ail dans un peu d’huile d’olive.'),
(3, 3, 'Ajoutez les légumes et laissez mijoter à feu doux pendant 30 minutes.'),
(4, 3, 'Remuez de temps en temps et assaisonnez avec sel et poivre.'),
(5, 3, 'Servez chaud, seul ou en accompagnement.');

-- Quiche Lorraine (id_recipe = 4)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 4, 'Préchauffez le four à 180°C.'),
(2, 4, 'Étalez la pâte dans un moule à tarte et piquez le fond avec une fourchette.'),
(3, 4, 'Dans un bol, battez les œufs avec la crème, le sel et le poivre.'),
(4, 4, 'Ajoutez les lardons et le fromage râpé, puis versez sur la pâte.'),
(5, 4, 'Enfournez pendant 30 minutes, jusqu’à ce que la quiche soit dorée.');

-- Tarte aux Pommes (id_recipe = 5)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 5, 'Préchauffez le four à 180°C.'),
(2, 5, 'Étalez la pâte dans un moule et piquez le fond.'),
(3, 5, 'Épluchez et coupez les pommes en fines tranches.'),
(4, 5, 'Disposez les pommes sur la pâte et saupoudrez de sucre.'),
(5, 5, 'Enfournez pendant 30 minutes jusqu’à ce que les pommes soient dorées.');

-- Soupe de légumes (id_recipe = 6)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 6, 'Épluchez et coupez les légumes en morceaux.'),
(2, 6, 'Faites revenir l’oignon dans un peu d’huile d’olive.'),
(3, 6, 'Ajoutez tous les légumes et couvrez d’eau.'),
(4, 6, 'Laissez mijoter pendant 20 minutes.'),
(5, 6, 'Mixez la soupe et servez chaude.');

-- Couscous Royal (id_recipe = 7)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 7, 'Préparez la semoule en l’arrosant d’eau chaude et en égrainant avec une fourchette.'),
(2, 7, 'Dans une grande casserole, faites cuire la viande avec les légumes.'),
(3, 7, 'Ajoutez les épices et laissez mijoter pendant 40 minutes.'),
(4, 7, 'Ajoutez les pois chiches 10 minutes avant la fin de la cuisson.'),
(5, 7, 'Servez avec la semoule et arrosez de sauce.');

-- Poulet au curry (id_recipe = 8)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 8, 'Faites revenir le poulet dans une poêle avec un peu d’huile.'),
(2, 8, 'Ajoutez le curry et mélangez bien.'),
(3, 8, 'Versez un peu de lait de coco et laissez mijoter 15 minutes.'),
(4, 8, 'Assaisonnez avec sel et poivre.'),
(5, 8, 'Servez avec du riz.');

-- Mousse au Chocolat (id_recipe = 9)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 9, 'Faites fondre le chocolat au bain-marie.'),
(2, 9, 'Séparez les blancs des jaunes d’œufs.'),
(3, 9, 'Incorporez les jaunes au chocolat fondu.'),
(4, 9, 'Montez les blancs en neige et ajoutez-les délicatement au chocolat.'),
(5, 9, 'Réfrigérez pendant au moins 3 heures avant de servir.');

-- Pizza Margherita (id_recipe = 10)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 10, 'Préchauffez le four à 220°C.'),
(2, 10, 'Étalez la pâte à pizza sur une plaque de cuisson.'),
(3, 10, 'Étalez la sauce tomate et ajoutez la mozzarella.'),
(4, 10, 'Parsemez de basilic et d’un filet d’huile d’olive.'),
(5, 10, 'Enfournez pendant 15 minutes jusqu’à ce que la pizza soit dorée.');

-- Bœuf Bourguignon (ID 11)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 11, 'Faire revenir les morceaux de bœuf dans une cocotte avec un peu de beurre jusqu’à ce qu’ils soient bien dorés.'),
(2, 11, 'Ajouter les oignons émincés, les carottes coupées en rondelles et l’ail haché. Faire revenir 5 minutes.'),
(3, 11, 'Verser le vin rouge et laisser réduire de moitié. Ajouter le bouillon de bœuf, les herbes, le sel et le poivre.'),
(4, 11, 'Laisser mijoter à feu doux pendant environ 2 heures, jusqu’à ce que la viande soit tendre.'),
(5, 11, 'Ajouter les champignons et les lardons en fin de cuisson. Servir chaud avec des pommes de terre ou des pâtes.');

-- Tajine de poulet (ID 12)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 12, 'Faire dorer les morceaux de poulet dans une grande cocotte avec un peu d’huile.'),
(2, 12, 'Ajouter les oignons émincés, l’ail et les épices (cumin, paprika, curcuma). Mélanger pour bien enrober le poulet.'),
(3, 12, 'Ajouter les tomates pelées, coupées en morceaux, et les citrons confits. Mélanger et laisser cuire 10 minutes.'),
(4, 12, 'Verser un peu d’eau, couvrir et laisser mijoter à feu doux pendant environ 1h30.'),
(5, 12, 'Ajouter les olives et les amandes en fin de cuisson. Servir chaud avec du couscous.');

-- Lasagnes (ID 13)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 13, 'Cuire les pâtes à lasagnes dans de l’eau salée pendant 10-12 minutes. Égoutter et réserver.'),
(2, 13, 'Préparer une sauce bolognaise avec de la viande hachée, des tomates, des oignons, de l’ail et des herbes.'),
(3, 13, 'Préparer une béchamel en faisant fondre du beurre, en ajoutant de la farine et du lait pour obtenir une sauce épaisse.'),
(4, 13, 'Dans un plat à gratin, étaler une couche de béchamel, puis une couche de pâtes, une couche de sauce bolognaise et une couche de béchamel. Répéter l’opération.'),
(5, 13, 'Terminer avec une couche de fromage râpé et faire cuire au four pendant 30-40 minutes à 180°C.');

-- Gâteau au chocolat (ID 14)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 14, 'Faire fondre le chocolat et le beurre au bain-marie.'),
(2, 14, 'Mélanger les œufs et le sucre dans un bol, puis ajouter la farine et la levure.'),
(3, 14, 'Incorporer le mélange de chocolat fondu et mélanger jusqu’à obtenir une pâte lisse.'),
(4, 14, 'Verser la pâte dans un moule beurré et fariné, puis cuire au four à 180°C pendant 20-25 minutes.'),
(5, 14, 'Laisser refroidir avant de démouler et de servir.');

-- Blanquette de veau (ID 15)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 15, 'Faire dorer les morceaux de veau dans une cocotte avec un peu de beurre.'),
(2, 15, 'Ajouter les oignons émincés, les carottes coupées en rondelles et l’ail haché. Mélanger.'),
(3, 15, 'Verser du bouillon de volaille et ajouter du laurier et du thym. Laisser mijoter à feu doux pendant environ 1h30.'),
(4, 15, 'Dans une autre casserole, préparer la sauce en faisant fondre du beurre, en ajoutant de la farine et du lait.'),
(5, 15, 'Incorporer la sauce dans la cocotte et laisser cuire encore 15 minutes avant de servir.');

-- Chili con carne (ID 16)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 16, 'Faire revenir la viande de bœuf hachée dans une grande poêle avec un peu d’huile.'),
(2, 16, 'Ajouter les oignons, l’ail et les épices (paprika, cumin, chili). Faire revenir 5 minutes.'),
(3, 16, 'Ajouter les tomates pelées, les haricots rouges, le maïs et un peu de bouillon. Mélanger bien.'),
(4, 16, 'Laisser mijoter pendant environ 45 minutes à feu doux, jusqu’à ce que le mélange épaississe.'),
(5, 16, 'Servir avec du riz ou des tortillas.');

-- Risotto aux champignons (ID 17)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 17, 'Faire revenir l’oignon émincé dans une poêle avec un peu de beurre.'),
(2, 17, 'Ajouter les champignons coupés en lamelles et faire cuire jusqu’à ce qu’ils soient dorés.'),
(3, 17, 'Ajouter le riz arborio et mélanger pour bien enrober le riz avec le beurre.'),
(4, 17, 'Verser le bouillon petit à petit, en remuant constamment jusqu’à ce que le riz soit cuit (environ 20 minutes).'),
(5, 17, 'Ajouter le parmesan râpé et mélanger avant de servir.');

-- Paella (ID 18)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 18, 'Faire chauffer l’huile d’olive dans une grande poêle ou une paellera. Ajouter les oignons, l’ail et les poivrons.'),
(2, 18, 'Ajouter les fruits de mer (moules, crevettes) et faire revenir 5 minutes.'),
(3, 18, 'Ajouter le riz, les tomates et le bouillon, puis assaisonner avec du safran, du sel et du poivre.'),
(4, 18, 'Laisser cuire à feu doux pendant 20-25 minutes, jusqu’à ce que le riz soit cuit et que le liquide soit absorbé.'),
(5, 18, 'Servir chaud, décoré de citron et de persil.');

-- Tiramisu (ID 19)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 19, 'Séparer les blancs des jaunes d’œufs. Fouetter les jaunes avec le sucre jusqu’à ce qu’ils blanchissent.'),
(2, 19, 'Ajouter le mascarpone au mélange de jaunes d’œufs et mélanger jusqu’à obtenir une crème lisse.'),
(3, 19, 'Monter les blancs d’œufs en neige et les incorporer délicatement à la préparation au mascarpone.'),
(4, 19, 'Tremper rapidement les biscuits à la cuillère dans le café et les disposer dans le fond d’un plat.'),
(5, 19, 'Recouvrir les biscuits d’une couche de crème au mascarpone, puis répéter l’opération. Terminer par une couche de crème.'),
(6, 19, 'Laisser reposer au réfrigérateur pendant au moins 4 heures, puis saupoudrer de cacao avant de servir.');

-- Burger maison (ID 20)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 20, 'Cuire les steaks hachés à la poêle ou au barbecue selon la cuisson souhaitée.'),
(2, 20, 'Toaster légèrement les pains à burger.'),
(3, 20, 'Sur chaque pain, ajouter la sauce de votre choix, puis poser le steak cuit.'),
(4, 20, 'Ajouter du fromage, des légumes frais (salade, tomates, oignons) et refermer le burger.'),
(5, 20, 'Servir immédiatement avec des frites ou une salade.');

-- Sushis variés (ID 21)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 21, 'Cuire le riz à sushi en suivant les instructions, puis le laisser refroidir.'),
(2, 21, 'Préparer le poisson en le coupant en tranches fines (saumon, thon, etc.).'),
(3, 21, 'Tapisser une feuille de nori de riz, en laissant un bord libre en haut.'),
(4, 21, 'Placer une tranche de poisson et d’autres garnitures (concombre, avocat) sur le riz.'),
(5, 21, 'Rouler délicatement le sushi à l’aide d’un tapis en bambou et couper en morceaux.');

-- Gratin dauphinois (ID 22)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 22, 'Préchauffer le four à 180°C. Beurrer un plat à gratin.'),
(2, 22, 'Éplucher et couper les pommes de terre en fines tranches.'),
(3, 22, 'Disposer les pommes de terre dans le plat à gratin, en alternant les couches et en assaisonnant avec sel, poivre et noix de muscade.'),
(4, 22, 'Verser la crème liquide sur les pommes de terre et parsemer de fromage râpé.'),
(5, 22, 'Cuire au four pendant environ 1 heure, jusqu’à ce que le gratin soit doré et fondant.');

-- Omelette aux fines herbes (ID 23)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 23, 'Battre les œufs avec du sel, du poivre et les fines herbes hachées (persil, ciboulette, estragon).'),
(2, 23, 'Faire chauffer une poêle avec un peu de beurre ou d’huile.'),
(3, 23, 'Verser le mélange d’œufs dans la poêle chaude et cuire à feu doux.'),
(4, 23, 'Lorsque l’omelette commence à prendre, la plier en deux et continuer la cuisson pendant quelques minutes.'),
(5, 23, 'Servir chaud avec une salade verte ou des légumes.');

-- Croque-monsieur (ID 24)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 24, 'Beurrer une tranche de pain de mie sur une face.'),
(2, 24, 'Sur la face non beurrée, étaler une fine couche de béchamel, puis ajouter une tranche de jambon et une tranche de fromage.'),
(3, 24, 'Recouvrir avec une autre tranche de pain de mie, face beurrée vers l’extérieur.'),
(4, 24, 'Faire cuire à la poêle ou sous un grill jusqu’à ce que le pain soit doré et le fromage fondu.'),
(5, 24, 'Servir chaud, accompagné d’une salade verte ou de frites.');

-- Pancakes américains (ID 25)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 25, 'Mélanger la farine, le sucre, la levure et le sel dans un grand bol.'),
(2, 25, 'Dans un autre bol, battre les œufs, ajouter le lait et le beurre fondu.'),
(3, 25, 'Incorporer progressivement les ingrédients liquides aux ingrédients secs.'),
(4, 25, 'Faire chauffer une poêle et y verser une petite louche de pâte. Cuire chaque pancake jusqu’à ce qu’il soit doré des deux côtés.'),
(5, 25, 'Servir chaud avec du sirop d’érable ou des fruits frais.');

-- Soupe miso (ID 26)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 26, 'Chauffer de l’eau dans une casserole et y ajouter la pâte de miso.'),
(2, 26, 'Ajouter les algues séchées et le tofu coupé en cubes.'),
(3, 26, 'Laisser mijoter pendant 10-15 minutes pour bien mélanger les saveurs.'),
(4, 26, 'Rectifier l’assaisonnement avec un peu de sel ou de sauce soja si nécessaire.'),
(5, 26, 'Servir chaud, garni de ciboulette ou d’autres herbes fraîches.');

-- Riz cantonais (ID 27)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 27, 'Faire cuire le riz et le laisser refroidir.'),
(2, 27, 'Dans une poêle, faire revenir les œufs battus jusqu’à ce qu’ils soient brouillés.'),
(3, 27, 'Ajouter les crevettes, les petits pois et les carottes coupées en dés. Faire revenir quelques minutes.'),
(4, 27, 'Ajouter le riz froid et mélanger avec de la sauce soja. Faire sauter pendant 5-7 minutes.'),
(5, 27, 'Servir chaud, garni de ciboulette ou de coriandre.');

-- Crêpes sucrées (ID 28)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 28, 'Mélanger la farine, les œufs, le sucre, le lait et une pincée de sel pour obtenir une pâte lisse.'),
(2, 28, 'Laisser reposer la pâte pendant environ 30 minutes.'),
(3, 28, 'Chauffer une poêle antiadhésive et verser une petite louche de pâte. Faire cuire chaque crêpe jusqu’à ce qu’elle soit dorée des deux côtés.'),
(4, 28, 'Garnir les crêpes avec du sucre, du chocolat fondu, des fruits ou de la confiture.'),
(5, 28, 'Servir immédiatement, roulées ou pliées.');

-- Fondue savoyarde (ID 29)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 29, 'Faire chauffer le vin blanc dans une grande casserole, puis ajouter les fromages râpés en plusieurs fois en remuant.'),
(2, 29, 'Ajouter l’ail écrasé et un peu de jus de citron. Assaisonner avec du sel et du poivre.'),
(3, 29, 'Laisser fondre complètement en remuant jusqu’à obtenir une texture lisse et crémeuse.'),
(4, 29, 'Servir dans un caquelon à fondue avec des morceaux de pain à tremper.'),
(5, 29, 'Accompagner de légumes ou de charcuterie si désiré.');

-- Gaspacho (ID 30)
INSERT INTO `stage` (`stage`, `id_recipe`, `content`) VALUES
(1, 30, 'Couper les tomates, le concombre, les poivrons et les oignons en morceaux.'),
(2, 30, 'Mixer tous les légumes avec de l’ail, du vinaigre, de l’huile d’olive et un peu d’eau.'),
(3, 30, 'Assaisonner avec du sel, du poivre et un peu de sucre. Mélanger bien.'),
(4, 30, 'Laisser reposer au réfrigérateur pendant 1 à 2 heures pour que les saveurs se mélangent.'),
(5, 30, 'Servir froid, garni de croutons ou de basilic frais.');

TRUNCATE TABLE opinion;
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(2, 'eva10@example.com', 2, 'Pas terrible, manque de saveur.',NOW()),
(3, 'charlie93@hotmail.com', 2, 'Moyen, mais peut être amélioré.',NOW()),
(5, 'frank80@hotmail.com', 1, 'Vraiment pas bon, je ne referai pas.',NOW()),
(2, 'alice52@yahoo.com', 2, 'Assez fade, déçu du résultat.',NOW()),
(5, 'david10@hotmail.com', 1, 'Horrible expérience, goût désagréable.',NOW()),
(1, 'bob25@gmail.com', 5, 'Fantastique, je recommande vivement !',NOW()),
(4, 'grace12@example.com', 4, "Très bon, j'ai bien aimé.",NOW()),
(3, 'henry99@yahoo.com', 3, 'Bon, mais pourrait être meilleur.',NOW()),
(1, 'irene45@gmail.com', 5, 'Incroyable, un vrai délice !',NOW()),
(2, 'jack33@hotmail.com', 4, 'Un plat savoureux, je le referai.',NOW()),
(3, 'alice88@example.com', 3, "Correct, mais rien d'exceptionnel.",NOW()),
(4, 'charlie77@yahoo.com', 5, 'Un pur régal, bravo pour la recette !',NOW()),
(1, 'david60@gmail.com', 4, 'Excellente recette, presque parfaite.',NOW()),
(5, 'eva99@example.com', 1, 'Très décevant, je ne recommande pas.',NOW()),
(2, 'frank45@yahoo.com', 2, 'Moyen, mais peut être amélioré.',NOW()),
(4, 'grace78@hotmail.com', 3, 'Une recette sympa mais sans plus.',NOW()),
(3, 'henry21@example.com', 5, 'Fantastique, je recommande vivement !',NOW()),
(5, 'irene67@yahoo.com', 1, 'Horrible expérience, goût désagréable.',NOW()),
(1, 'jack50@hotmail.com', 5, 'Un pur régal, bravo pour la recette !',NOW()),
(2, 'bob75@gmail.com', 4, "Très bon, j'ai bien aimé.",NOW()),
(11, 'jack33@hotmail.com', 5, 'Un plat savoureux, je le referai.', NOW()),
(11, 'alice88@example.com', 4, "Correct, mais rien d'exceptionnel.", NOW()),
(11, 'charlie77@yahoo.com', 5, 'Un pur régal, bravo pour la recette !', NOW()),
(11, 'david60@gmail.com', 5, 'Excellente recette, presque parfaite.', NOW()),
(11, 'eva99@example.com', 3, 'Assez bon, mais j’ai connu mieux.', NOW()),
(11, 'frank45@yahoo.com', 4, 'Très bon, je le recommande.', NOW()),
(12, 'sophie22@gmail.com', 5, 'Délicieux, les saveurs sont incroyables !', NOW()),
(12, 'lucas77@hotmail.com', 5, 'Un vrai régal, je recommande à 100% !', NOW()),
(12, 'emma33@yahoo.com', 5, 'Très bon, mais un peu trop salé à mon goût.', NOW()),
(12, 'paul88@example.com', 5, 'Une merveilleuse recette, parfaitement équilibrée.', NOW()),
(12, 'nicolas55@yahoo.com', 4, 'Très bon, mais je préfère avec plus d’épices.', NOW());
-- Lasagnes (ID 13)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(13, 'emma27@gmail.com', 5, 'Des lasagnes parfaites, un goût incroyable !', NOW()),
(13, 'lucas84@hotmail.com', 5, 'Vraiment excellentes, je recommande à 100%.', NOW()),
(13, 'sophie33@example.com', 5, 'Les meilleures lasagnes que j’ai mangées.', NOW()),
(13, 'marc12@gmail.com', 4, 'Très bonnes, mais un peu plus de sauce aurait été mieux.', NOW()),
(13, 'ines74@gmail.com', 5, 'Recette délicieuse, je la referai sans hésiter !', NOW()),
(13, 'paul76@example.com', 5, 'Lasagnes très savoureuses, bien équilibrées.', NOW());

-- Gâteau au chocolat (ID 14)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(14, 'lisa56@gmail.com', 5, 'Un gâteau vraiment délicieux et fondant.', NOW()),
(14, 'kevin87@example.com', 5, 'Je suis accro à ce gâteau, il est incroyable !', NOW()),
(14, 'alice92@hotmail.com', 4, 'Gâteau fondant, mais il pourrait être encore plus chocolaté.', NOW()),
(14, 'james74@gmail.com', 5, 'Excellent gâteau, une pure merveille !', NOW()),
(14, 'lucy66@example.com', 5, 'Un vrai délice, parfait pour le goûter.', NOW()),
(14, 'pauline34@gmail.com', 4, 'Très bon, mais j’aurais aimé un peu plus de sucre.', NOW());

-- Blanquette de veau (ID 15)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(15, 'thomas88@gmail.com', 4, 'Une blanquette crémeuse et savoureuse.', NOW()),
(15, 'marion66@example.com', 5, 'Parfaite, la viande était fondante et la sauce délicieuse !', NOW()),
(15, 'victor92@hotmail.com', 4, 'Très bon, mais j’ai trouvé la sauce un peu trop liquide.', NOW()),
(15, 'lucie74@gmail.com', 5, 'Une recette digne des meilleurs restaurants !', NOW()),
(15, 'arnaud56@example.com', 5, 'Vraiment savoureuse et bien équilibrée.', NOW()),
(15, 'caroline33@gmail.com', 4, 'Bonne blanquette, mais j’ai ajouté des légumes en plus pour plus de goût.', NOW());

-- Chili con carne (ID 16)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(16, 'olivier33@example.com', 3, 'Bon, mais un peu trop épicé à mon goût.', NOW()),
(16, 'nathalie54@hotmail.com', 4, 'Plat savoureux, mais attention à la dose de piment.', NOW()),
(16, 'julien88@gmail.com', 4, 'Un bon chili, je l’ai servi avec du riz, c’était top.', NOW()),
(16, 'amandine22@example.com', 5, 'J’adore ce plat, riche en saveurs et épices !', NOW()),
(16, 'marc12@gmail.com', 4, 'Très bon, mais pas assez relevé pour un vrai chili.', NOW()),
(16, 'jean34@gmail.com', 3, 'Pas mal, mais il manque un peu de piquant pour moi.', NOW());

-- Risotto aux champignons (ID 17)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(17, 'alice12@hotmail.com', 5, 'Un risotto crémeux et plein de saveurs.', NOW()),
(17, 'lucas77@example.com', 5, 'Très bon risotto, les champignons étaient parfaits.', NOW()),
(17, 'victoria92@gmail.com', 4, 'Plat délicieux, mais j’aurais aimé un peu plus de parmesan.', NOW()),
(17, 'joseph56@example.com', 4, 'Très bon, mais il manquait un peu de consistance à mon goût.', NOW()),
(17, 'camille33@hotmail.com', 5, 'Recette délicieuse, je vais la refaire pour mes invités !', NOW()),
(17, 'paul44@gmail.com', 5, 'Un risotto digne d’un restaurant italien, vraiment savoureux.', NOW());
-- Paella (ID 18)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(18, 'jean54@example.com', 5, 'Excellente paella, riche en saveurs et bien équilibrée.', NOW()),
(18, 'manon87@gmail.com', 5, 'Un vrai délice, je l’ai servie avec une sauce allioli, parfait.', NOW()),
(18, 'olivier72@example.com', 4, 'Très bonne paella, mais j’aurais préféré plus de fruits de mer.', NOW()),
(18, 'carla11@gmail.com', 5, 'Un goût incroyable, c’est la meilleure paella que j’ai jamais mangée.', NOW()),
(18, 'julien56@example.com', 4, 'Très bonne, mais un peu plus de safran aurait été bien.', NOW()),
(18, 'sofia74@hotmail.com', 5, 'Je recommande vivement, c’était délicieux et plein de saveurs.', NOW());

-- Tiramisu (ID 19)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(19, 'maureen22@example.com', 5, 'Un tiramisu onctueux et délicieux, un régal !', NOW()),
(19, 'sandra45@gmail.com', 5, 'Recette parfaite, le tiramisu était juste comme je l’aime.', NOW()),
(19, 'thomas33@example.com', 5, 'Un tiramisu qui fond en bouche, vraiment top.', NOW()),
(19, 'ludivine77@example.com', 4, 'Très bon, mais un peu trop sucré à mon goût.', NOW()),
(19, 'georges88@example.com', 5, 'Un tiramisu au top, je vais le refaire pour un dîner entre amis.', NOW()),
(19, 'lucie54@gmail.com', 5, 'Recette parfaite, la texture était idéale.', NOW());

-- Burger maison (ID 20)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(20, 'michel88@gmail.com', 4, 'Très bon burger, mais la viande pourrait être un peu plus épicée.', NOW()),
(20, 'audrey77@example.com', 4, 'Bon burger, mais j’aurais aimé un peu plus de garniture.', NOW()),
(20, 'nicolas22@example.com', 5, 'Super burger maison, j’ai adoré la sauce.', NOW()),
(20, 'martine44@hotmail.com', 5, 'Un burger très savoureux, avec une viande bien juteuse.', NOW()),
(20, 'karim87@gmail.com', 4, 'Vraiment bon, mais j’ai ajouté un peu de fromage en plus.', NOW()),
(20, 'amelie33@example.com', 5, 'Le burger était parfait, je le recommande sans hésiter !', NOW());

-- Sushis variés (ID 21)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(21, 'morgane56@gmail.com', 5, 'Des sushis frais et délicieux, un vrai régal.', NOW()),
(21, 'lucas54@example.com', 5, 'Les meilleurs sushis que j’ai mangés !', NOW()),
(21, 'sophie33@example.com', 4, 'Très bons, mais j’aurais aimé un peu plus de variété.', NOW()),
(21, 'patrick12@gmail.com', 5, 'Sushis délicieux, parfaits pour un dîner entre amis.', NOW()),
(21, 'isabelle22@hotmail.com', 5, 'Super frais, une excellente recette de sushis maison.', NOW()),
(21, 'olivier77@example.com', 5, 'Sushis parfaits, bien équilibrés et délicieux.', NOW());

-- Gratin dauphinois (ID 22)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(22, 'marie88@example.com', 5, 'Un gratin dauphinois crémeux et fondant, délicieux !', NOW()),
(22, 'louis44@gmail.com', 5, 'Excellent gratin, bien crémeux et savoureux.', NOW()),
(22, 'geraldine22@example.com', 4, 'Très bon, mais j’aurais aimé qu’il soit un peu plus gratiné.', NOW()),
(22, 'olga55@gmail.com', 5, 'Le gratin était parfait, tout le monde a adoré !', NOW()),
(22, 'alain66@example.com', 5, 'Un gratin délicieux, la recette est vraiment bien équilibrée.', NOW()),
(22, 'joseph44@example.com', 5, 'Une excellente recette, je la recommande sans hésiter.', NOW());
-- Omelette aux fines herbes (ID 23)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(23, 'amandine33@example.com', 3, 'Correcte, mais manquait un peu de goût pour moi.', NOW()),
(23, 'michel23@gmail.com', 4, 'Bonne omelette, mais j’aurais aimé plus d’herbes.', NOW()),
(23, 'claire44@example.com', 3, 'Assez fade, pas assez assaisonnée à mon goût.', NOW()),
(23, 'romain22@gmail.com', 2, 'Pas assez savoureuse, je préfère une omelette plus relevée.', NOW()),
(23, 'martine11@example.com', 4, 'Une omelette simple mais bonne, les fines herbes étaient agréables.', NOW()),
(23, 'eric77@example.com', 4, 'Bonne recette, mais j’aurais mis un peu plus de persil.', NOW());

-- Croque-monsieur (ID 24)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(24, 'julie56@example.com', 4, 'Bon croque-monsieur, mais le fromage pourrait être plus fondant.', NOW()),
(24, 'paul32@hotmail.com', 3, 'Bonne recette, mais un peu trop simple à mon goût.', NOW()),
(24, 'nadine55@example.com', 5, 'Un croque-monsieur délicieux, juste comme je l’aime !', NOW()),
(24, 'laurent88@example.com', 4, 'Très bon, mais j’aurais ajouté un peu plus de jambon.', NOW()),
(24, 'marc22@gmail.com', 5, 'Parfaitement gratiné et bien équilibré, un vrai délice.', NOW()),
(24, 'sarah11@example.com', 4, 'Bonne recette, mais j’ai ajouté un peu de moutarde pour plus de goût.', NOW());

-- Pancakes américains (ID 25)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(25, 'emilie44@example.com', 5, 'Des pancakes super moelleux, un vrai régal.', NOW()),
(25, 'antoinette22@gmail.com', 4, 'Bonne recette, mais j’aurais aimé qu’ils soient encore plus épais.', NOW()),
(25, 'franck66@example.com', 5, 'Les meilleurs pancakes que j’ai mangés, parfaits avec du sirop d’érable.', NOW()),
(25, 'lucas77@example.com', 5, 'Délicieux et bien aérés, je vais refaire cette recette !', NOW()),
(25, 'elisabeth33@example.com', 4, 'Très bons pancakes, mais la texture était un peu trop légère pour moi.', NOW()),
(25, 'nathalie11@example.com', 5, 'Top recette, mes enfants ont adoré !', NOW());

-- Soupe miso (ID 26)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(26, 'jeanne88@example.com', 4, 'Très bonne soupe miso, mais j’aurais ajouté plus de tofu.', NOW()),
(26, 'emmanuel23@hotmail.com', 3, 'Correcte, mais la soupe manquait de profondeur de saveur.', NOW()),
(26, 'aline22@example.com', 5, 'Une soupe miso parfaite, légère et savoureuse.', NOW()),
(26, 'yves44@gmail.com', 4, 'Bonne recette, mais j’ai mis un peu trop de miso, c’était salé.', NOW()),
(26, 'marie33@example.com', 4, 'Très bon goût, mais j’aurais aimé plus d’algues.', NOW()),
(26, 'olga11@example.com', 5, 'Recette excellente, elle m’a rappelé mes voyages au Japon.', NOW());

-- Riz cantonais (ID 27)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(27, 'olga22@gmail.com', 4, 'Bonne recette de riz cantonais, mais j’aurais ajouté un peu plus de crevettes.', NOW()),
(27, 'celine33@example.com', 5, 'Un riz cantonais délicieux, j’ai adoré les légumes et la sauce soja.', NOW()),
(27, 'laura55@example.com', 3, 'Moyenne, il manquait un peu de saveur pour moi.', NOW()),
(27, 'françois22@example.com', 4, 'Très bon, mais j’ai préféré un peu plus d’œufs et de légumes.', NOW()),
(27, 'bernard44@example.com', 5, 'Un vrai régal, j’adore le riz cantonais avec cette recette !', NOW()),
(27, 'claude33@example.com', 5, 'Recette super bonne et bien équilibrée, à refaire sans hésiter.', NOW());

-- Crêpes sucrées (ID 28)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(28, 'geraldine44@example.com', 5, 'Des crêpes fines et délicieuses, un vrai délice sucré !', NOW()),
(28, 'dominique22@gmail.com', 5, 'Excellentes crêpes, je les ai garnies de sucre et de Nutella, parfaites.', NOW()),
(28, 'christine77@example.com', 5, 'Les crêpes étaient super légères, j’ai adoré.', NOW()),
(28, 'sandrine33@example.com', 4, 'Très bonnes, mais un peu trop fines pour moi.', NOW()),
(28, 'antoine11@example.com', 5, 'Super recette, les crêpes étaient parfaitement dorées.', NOW()),
(28, 'elise77@example.com', 4, 'Bonne recette, mais j’aurais aimé qu’elles soient encore plus moelleuses.', NOW());

-- Fondue savoyarde (ID 29)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(29, 'marie44@gmail.com', 5, 'Une fondue savoyarde excellente, parfaite pour une soirée entre amis.', NOW()),
(29, 'jacques77@example.com', 5, 'Recette au top, la fondue était crémeuse et savoureuse.', NOW()),
(29, 'sophie22@example.com', 4, 'Bonne fondue, mais un peu trop liquide à mon goût.', NOW()),
(29, 'georges66@example.com', 5, 'Une fondue savoyarde parfaite, avec un bon mélange de fromages.', NOW()),
(29, 'jeanette55@gmail.com', 5, 'Je recommande cette fondue, elle était parfaite.', NOW()),
(29, 'isabelle33@example.com', 5, 'Un vrai régal, j’adore la fondue savoyarde !', NOW());

-- Gaspacho (ID 30)
INSERT INTO bddgmf.opinion (id_recipe, email, rate, comment, create_time) VALUES
(30, 'louis44@example.com', 4, 'Un bon gaspacho, bien frais, mais j’aurais aimé plus de tomates.', NOW()),
(30, 'amelie77@gmail.com', 5, 'Un gaspacho rafraîchissant et délicieux, parfait pour l’été.', NOW()),
(30, 'jeanette33@example.com', 4, 'Très bon, mais j’aurais préféré un peu plus de concombres.', NOW()),
(30, 'patrick22@example.com', 5, 'Excellent gaspacho, je l’ai servi avec des croûtons maison.', NOW()),
(30, 'marc44@example.com', 5, 'Un gaspacho bien frais, j’ai adoré son goût léger et savoureux.', NOW()),
(30, 'christine77@example.com', 4, 'Très bon, mais un peu trop de poivron pour moi.', NOW());

SET FOREIGN_KEY_CHECKS = 1;