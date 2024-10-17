INSERT IGNORE INTO produto (id_produto, nome, codigo_listagem, descricao, detalhes, categoria, data_cadastro, status, imagem) VALUES
(1, 'Produto 1', 1001, 'Descrição 1', 'Detalhes 1', 1, NOW(), TRUE, NULL),
(2, 'Produto 2', 1002, 'Descrição 2', 'Detalhes 2', 1, NOW(), TRUE, NULL),
(3, 'Produto 3', 1003, 'Descrição 3', 'Detalhes 3', 2, NOW(), TRUE, NULL),
(4, 'Produto 4', 1004, 'Descrição 4', 'Detalhes 4', 2, NOW(), TRUE, NULL),
(5, 'Produto 5', 1005, 'Descrição 5', 'Detalhes 5', 3, NOW(), TRUE, NULL),
(6, 'Produto 6', 1006, 'Descrição 6', 'Detalhes 6', 3, NOW(), TRUE, NULL),
(7, 'Produto 7', 1007, 'Descrição 7', 'Detalhes 7', 4, NOW(), TRUE, NULL),
(8, 'Produto 8', 1008, 'Descrição 8', 'Detalhes 8', 4, NOW(), TRUE, NULL),
(9, 'Produto 9', 1009, 'Descrição 9', 'Detalhes 9', 5, NOW(), TRUE, NULL),
(10, 'Produto 10', 1010, 'Descrição 10', 'Detalhes 10', 5, NOW(), TRUE, NULL);

INSERT IGNORE INTO categoria (id_categoria, descricao, ordem, produto, data_atualizacao) VALUES
(1, 'Bebidas', 1, 1, NOW()),
(2, 'Salgados', 2, 2, NOW()),
(3, 'Doces', 3, 3, NOW()),
(4, 'Lanches', 4, 4, NOW()),
(5, 'Sobremesas', 5, 5, NOW()),
(6, 'Refrigerantes', 6, 6, NOW()),
(7, 'Sucos Naturais', 7, 7, NOW()),
(8, 'Cafés', 8, 8, NOW()),
(9, 'Sanduíches', 9, 9, NOW()),
(10, 'Combos', 10, 10, NOW());

INSERT IGNORE INTO comanda (id_comanda, descricao, nome, status) VALUES
(1, 'Comanda aberta para João', 'Comanda João', 'DISPONIVEL'),
(2, 'Comanda em preparo para Maria', 'Comanda Maria', 'DISPONIVEL'),
(3, 'Comanda fechada para Carlos', 'Comanda Carlos', 'OCUPADA'),
(4, 'Comanda finalizada para Ana', 'Comanda Ana', 'OCUPADA'),
(5, 'Comanda aberta para Pedro', 'Comanda Pedro', 'DISPONIVEL'),
(6, 'Comanda em preparo para Lucas', 'Comanda Lucas', 'OCUPADA'),
(7, 'Comanda fechada para Júlia', 'Comanda Júlia', 'OCUPADA'),
(8, 'Comanda finalizada para Mariana', 'Comanda Mariana', 'OCUPADA'),
(9, 'Comanda aberta para Gabriel', 'Comanda Gabriel', 'DISPONIVEL'),
(10, 'Comanda em preparo para Sofia', 'Comanda Sofia', 'OCUPADA');
