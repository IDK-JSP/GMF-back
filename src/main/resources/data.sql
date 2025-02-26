SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE `measurement`;
TRUNCATE TABLE `ingredient`;
TRUNCATE TABLE `diet`;
TRUNCATE TABLE `diet_ingredient`;

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
('Vanille', 'Épice aromatique extraite de gousses utilisées en pâtisserie.', NOW(), NOW());

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
(1, 16), -- Pâtes
(1, 23), -- Pain
(1, 25), -- Chocolat
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
(2, 22), -- Fromage
(2, 23), -- Pain
(2, 24), -- Miel
(2, 25), -- Chocolat
(2, 26), -- Noix
(2, 27), -- Lentilles
(2, 28), -- Pois chiches
(2, 29), -- Champignon
(2, 30), -- Épinard
(2, 31), -- Basilic
(2, 32), -- Thym
(2, 33), -- Coriandre
(2, 34), -- Persil
(2, 35), -- Curcuma
(2, 36), -- Cannelle
(2, 37), -- Piment
(2, 38); -- Vanille


SET FOREIGN_KEY_CHECKS = 1;