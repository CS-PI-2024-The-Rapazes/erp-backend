
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

INSERT IGNORE INTO produto (id_produto, nome, codigo_listagem, descricao, detalhes, data_cadastro, status, imagem, id_categoria) VALUES
(1, 'Produto 1', 1001, 'Descrição 1', 'Detalhes 1', NOW(), TRUE, NULL, 1),
(2, 'Produto 2', 1002, 'Descrição 2', 'Detalhes 2', NOW(), TRUE, NULL, 1),
(3, 'Produto 3', 1003, 'Descrição 3', 'Detalhes 3', NOW(), TRUE, NULL, 2),
(4, 'Produto 4', 1004, 'Descrição 4', 'Detalhes 4', NOW(), TRUE, NULL, 3),
(5, 'Produto 5', 1005, 'Descrição 5', 'Detalhes 5', NOW(), TRUE, NULL, 3),
(6, 'Produto 6', 1006, 'Descrição 6', 'Detalhes 6', NOW(), TRUE, NULL, 4),
(7, 'Produto 7', 1007, 'Descrição 7', 'Detalhes 7', NOW(), TRUE, NULL, 5),
(8, 'Produto 8', 1008, 'Descrição 8', 'Detalhes 8', NOW(), TRUE, NULL, 2),
(9, 'Produto 9', 1009, 'Descrição 9', 'Detalhes 9', NOW(), TRUE, NULL, 1),
(10, 'Produto 10', 1010, 'Descrição 10', 'Detalhes 10', NOW(), TRUE, NULL, 2);

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

INSERT IGNORE INTO carteira (id_carteira, descricao, numero_conta, status, tipo) VALUES
(1, 'Carteira Pessoal', '1234567890', TRUE, 'BANCARIA'),
(2, 'Carteira Empresarial', '0987654321', TRUE, 'BANCARIA'),
(3, 'Carteira de Investimentos', '1122334455', TRUE, 'BANCARIA'),
(4, 'Carteira Digital', '5566778899', TRUE, 'BANCARIA'),
(5, 'Carteira Corporativa', '9988776655', TRUE, 'BANCARIA'),
(6, 'Carteira de Pagamentos', '1234432111', TRUE, 'BANCARIA'),
(7, 'Carteira Universitária', '7896541230', TRUE, 'LOCAL'),
(8, 'Carteira de Recompensas', '5432109876', TRUE, 'LOCAL'),
(9, 'Carteira Internacional', '5678901234', TRUE, 'LOCAL'),
(10, 'Carteira Black', '8765432109', TRUE, 'LOCAL');

INSERT IGNORE INTO forma_pagamento (id_forma_pagamento, chave, descricao, juros, max_parcelas, min_parcelas, parcelado, parcelas_sem_juros, status, tipo, id_carteira) VALUES
(1, 'chave1', 'Cartão de Crédito', 5.0, 12, 1, TRUE, 6, TRUE, 'CREDITO', 1),
(2, 'chave2', 'Cartão de Débito', NULL, NULL, NULL, FALSE, NULL, TRUE, 'CREDITO', 1),
(3, 'chave3', 'Boleto Bancário', NULL, NULL, NULL, FALSE, NULL, TRUE, 'PIX', 2),
(4, 'chave4', 'Pix', NULL, NULL, NULL, FALSE, NULL, TRUE, 'CREDIARIO', 3),
(5, 'chave5', 'Dinheiro', NULL, NULL, NULL, FALSE, NULL, TRUE, 'CREDITO', 4),
(6, 'chave6', 'Transferência Bancária', NULL, NULL, NULL, FALSE, NULL, TRUE, 'CREDITO', 5),
(7, 'chave7', 'Vale Alimentação', 0.0, 1, 1, TRUE, 1, TRUE, 'CREDIARIO', 6),
(8, 'chave8', 'Cartão Pré-Pago', 2.0, 10, 1, TRUE, 5, TRUE, 'PIX', 7),
(9, 'chave9', 'Cartão Fidelidade', 0.0, 6, 1, TRUE, 3, TRUE, 'PIX', 8),
(10, 'chave10', 'Outro Tipo de Pagamento', 3.0, 24, 1, TRUE, 12, TRUE, 'PIX', 9);