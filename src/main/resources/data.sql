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
('Lardon', 'Un truc moins bien que le bacon.', NOW(), NOW());

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
(1, 38); -- Vanille

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
(2, 38); -- Vanille

INSERT INTO `recipe` (`email`, `title`, `search_title`, `content`, `image`, `person`, `state`, `rate`, `nb_rate`, `create_time`, `update_time`, `cooking_time`) VALUES
('admin@gmail.com', 'Pâtes à la Carbonara','pates a la carbonara', 'Une délicieuse recette italienne avec des œufs, du parmesan et des lardons.', NULL, 4, 'tovalidation', 4.8, 4, NOW(), NOW(), 30),
('admin@gmail.com', 'Salade César','salade cesar', 'Salade fraîche avec du poulet grillé, des croûtons et une sauce crémeuse. ', NULL, 2, 'tovalidation', 2.8, 5, NOW(), NOW(),40),
('admin@gmail.com', 'Ratatouille','ratatouille', 'Un plat provençal à base de légumes mijotés : tomates, courgettes, aubergines et poivrons.', NULL, 4, 'tovalidation', 3.3, 4, NOW(), NOW(),115),
('admin@gmail.com', 'Quiche Lorraine','quiche lorraine', 'Tarte salée garnie de crème, lardons et fromage.', NULL, 6, 'tovalidation', 4, 3, NOW(), NOW(), 40),
('admin@gmail.com', 'Tarte aux Pommes','tarte aux pommes', 'Pâte sablée croustillante garnie de pommes caramélisées.', NULL, 6, 'tovalidation', 1, 4, NOW(), NOW(),50),
('admin@gmail.com', 'Soupe de légumes','soupe de legumes', 'Un mélange de légumes mijotés pour une soupe savoureuse et réconfortante.', NULL, 4, 'tovalidation', 0, 0, NOW(), NOW(),45),
('admin@gmail.com', 'Couscous Royal','couscous royal', 'Plat traditionnel nord-africain avec semoule, légumes et viandes variées.', NULL, 6, 'tovalidation', 0, 0, NOW(), NOW(),120),
('admin@gmail.com', 'Poulet au curry','poulet au curry', 'Poulet mijoté dans une sauce crémeuse au curry et lait de coco.', NULL, 4, 'tovalidation', 0, 0, NOW(), NOW(),55),
('admin@gmail.com', 'Mousse au chocolat','mousse au chocolat', 'Dessert aérien et fondant à base de chocolat noir et œufs.', NULL, 4, 'tovalidation', 0, 0, NOW(), NOW(),60),
('admin@gmail.com', 'Pizza Margherita', 'pizza margherita', 'Une pizza classique avec une sauce tomate, mozzarella et basilic.', NULL, 2, 'tovalidation', 0, 0, NOW(), NOW(),1);

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
(2, 'bob75@gmail.com', 4, "Très bon, j'ai bien aimé.",NOW());
SET FOREIGN_KEY_CHECKS = 1;