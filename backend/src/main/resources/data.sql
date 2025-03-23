-- User
INSERT INTO gm_user (id, email, password) VALUES
    ('5aa0c6f2-e430-4493-8596-09ec106e75dd', 'admin@example.com', '$2a$12$CAJ1pP2ykAIMG6fMtg3DiuoDPPWLkAPTD7H4dQUE2O6Wgyw/uOTGK');

INSERT INTO gm_user (id, email, password) VALUES
    ('00d9276b-fefb-4c78-9bdc-a522df3927d2', 'user@example.com', '$2a$12$TFWImeWAojD7ocX16DJVTu/Twd.1t9BEpnjR05ZpkumtutMzKvd5y');

-- Role
INSERT INTO gm_role (id, authority_name, authority, description) VALUES
    ('166797b4-93a1-4694-8dfc-ea8bd0b9b0ba', 'Admin role', 'ADMIN', 'Administrator role');

INSERT INTO gm_role (id, authority_name, authority, description) VALUES
    ('273068c3-3ef3-4469-8970-ab971889cab6', 'Operator role', 'OPERATOR', 'Operator role');

-- User role
INSERT INTO gm_user_role (user_id, role_id) VALUES
    ('5aa0c6f2-e430-4493-8596-09ec106e75dd', '166797b4-93a1-4694-8dfc-ea8bd0b9b0ba');

INSERT INTO gm_user_role (user_id, role_id) VALUES
    ('5aa0c6f2-e430-4493-8596-09ec106e75dd', '273068c3-3ef3-4469-8970-ab971889cab6');

INSERT INTO gm_user_role (user_id, role_id) VALUES
    ('00d9276b-fefb-4c78-9bdc-a522df3927d2', '273068c3-3ef3-4469-8970-ab971889cab6');

-- Client
-- secret game_master_ecommerce_secret
INSERT INTO gm_client (id, client_id, client_secret, redirect_uri, scope) VALUES
    ('70d9eb9a-de2a-4ef3-aa43-e9ade42edac1', 'game_master_ecommerce', '$2a$12$aKsrmrqF38KpNewUs4g4lOM6AQrPy0VLINDibvjt1SR1bmTRydlim', 'http://localhost:3000', 'read write');

-- secret game_master_manager_secret
INSERT INTO gm_client (id, client_id, client_secret, redirect_uri, scope) VALUES
    ('17da6405-f24b-4836-9ef2-d923e8f10b86', 'game_master_manager', '$2a$12$7SAqKygleu2avmnR5.Q8j.UfJbhf1uvvPv.Pi4iO8qB.zfUfF91wy', 'http://localhost:4200/auth', 'read write');


-- PRODUCT
INSERT INTO PRODUCT (id, price, weight, name, status, description) 
VALUES (
    '94f9acde-2d9a-4507-9995-c16bf1036808', 
    200, 
    500, 
    'Teclado Gamer Mecânico', 
    'AVAILABLE', 
    'Teclado Gamer Mecânico RGB – Alta Performance para Jogos  

    Desempenho máximo para gamers exigentes! O Teclado Gamer Mecânico RGB foi projetado para proporcionar velocidade, precisão e conforto em cada jogada. Com switches mecânicos de alta durabilidade, iluminação RGB personalizável e tecnologia anti-ghosting, ele garante respostas rápidas e um visual incrível para o seu setup.  

    Destaques do produto:  
    - Switches mecânicos para sensação tátil aprimorada e resposta ultra rápida  
    - Iluminação RGB personalizável com diversos modos e cores  
    - Tecnologia anti-ghosting e N-Key Rollover para pressionar várias teclas ao mesmo tempo sem falhas  
    - Estrutura resistente e construção robusta para maior durabilidade  
    - Teclas programáveis para configuração de atalhos e macros  
    - Compatibilidade universal com Windows, macOS e consoles via conexão USB  

    Especificações:  
    - Conectividade: USB 2.0 / 3.0  
    - Dimensões: [Insira dimensões aqui]  
    - Peso: [Insira peso aqui]  

    Aprimore sua jogabilidade e domine cada partida com o melhor teclado gamer!  

    Compre agora e leve sua experiência gamer para o próximo nível!'
);


-- IMAGES
INSERT INTO IMAGE (SIZE, PRODUCT_ID, PATH, NAME, TYPE) 
VALUES (11813257216, '94f9acde-2d9a-4507-9995-c16bf1036808', '/home/lucas_fbn/projetos/game-master/backend/.backend/opt/uploads/20250323-125454-996-$2054-bc1e.webp', '20250323-125454-996-$2054-bc1e.webp', 'WEBP');

INSERT INTO IMAGE (SIZE, PRODUCT_ID, PATH, NAME, TYPE) 
VALUES (12046041088, '94f9acde-2d9a-4507-9995-c16bf1036808', '/home/lucas_fbn/projetos/game-master/backend/.backend/opt/uploads/20250323-125454-994-$1721-7a3a.webp', '20250323-125454-994-$1721-7a3a.webp', 'WEBP');

INSERT INTO IMAGE (SIZE, PRODUCT_ID, PATH, NAME, TYPE) 
VALUES (14986248192, '94f9acde-2d9a-4507-9995-c16bf1036808', '/home/lucas_fbn/projetos/game-master/backend/.backend/opt/uploads/20250323-125454-992-$2964-74da.webp', '20250323-125454-992-$2964-74da.webp', 'WEBP');

INSERT INTO IMAGE (SIZE, PRODUCT_ID, PATH, NAME, TYPE) 
VALUES (2136997888, '94f9acde-2d9a-4507-9995-c16bf1036808', '/home/lucas_fbn/projetos/game-master/backend/.backend/opt/uploads/20250323-125454-991-$6415-14ea.webp', '20250323-125454-991-$6415-14ea.webp', 'WEBP');

INSERT INTO IMAGE (SIZE, PRODUCT_ID, PATH, NAME, TYPE) 
VALUES (10701766656, '94f9acde-2d9a-4507-9995-c16bf1036808', '/home/lucas_fbn/projetos/game-master/backend/.backend/opt/uploads/20250323-125454-748-$1901-ba29.webp', '20250323-125454-748-$1901-ba29.webp', 'WEBP');


