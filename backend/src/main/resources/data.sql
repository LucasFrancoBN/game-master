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
    ('17da6405-f24b-4836-9ef2-d923e8f10b86', 'game_master_manager_dev', '$2a$12$7SAqKygleu2avmnR5.Q8j.UfJbhf1uvvPv.Pi4iO8qB.zfUfF91wy', 'http://localhost:4200/auth', 'read write');

INSERT INTO gm_client (id, client_id, client_secret, redirect_uri, scope) VALUES
    ('20bbc7f3-27c8-4686-8fe1-6fe1d7494435', 'game_master_manager_homolog', '$2a$12$7SAqKygleu2avmnR5.Q8j.UfJbhf1uvvPv.Pi4iO8qB.zfUfF91wy', 'http://localhost:8082/auth', 'read write');


-- PRODUCT
INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
    '94f9acde-2d9a-4507-9995-c16bf1036808', 
    200, 
    500, 
    'Teclado Gamer Mecânico', 
    'AVAILABLE',
    300,
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
    - Dimensões: 14cm de altura, 38.7cm de largura e 4.5cm de profundidade.
    - Peso: 500g

    Aprimore sua jogabilidade e domine cada partida com o melhor teclado gamer!  

    Compre agora e leve sua experiência gamer para o próximo nível!'
);

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           '6f3b8cb3-4319-4e92-b110-df83f827e46a',
           350,
           800,
           'Mouse Gamer RGB 16000 DPI',
           'AVAILABLE',
           250,
           'Mouse Gamer RGB com sensor de 16000 DPI – Precisão e velocidade profissional

           Desenvolvido para oferecer precisão cirúrgica e resposta imediata, o Mouse Gamer RGB 16000 DPI é a arma ideal para quem leva os games a sério. Com design ergonômico, iluminação RGB e sensor óptico de alta performance, ele proporciona conforto e controle mesmo nas partidas mais intensas.

           Destaques do produto:
           - Sensor óptico de até 16000 DPI ajustável
           - Iluminação RGB com múltiplos efeitos e personalização via software
           - 8 botões programáveis com memória onboard
           - Design ergonômico com apoio para o polegar
           - Cabo trançado resistente de 1,8m

           Especificações:
           - Conectividade: USB 2.0 / 3.0
           - Dimensões: 12.5cm x 6.8cm x 4.2cm
           - Peso: 800g

           Eleve sua precisão e domine as arenas com um mouse projetado para vencer!'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           '1cbd4718-8c32-4a6a-bf7e-bc1db5f67a20',
           1200,
           3500,
           'Monitor Gamer 27" 165Hz 1ms',
           'AVAILABLE',
           100,
           'Monitor Gamer 27 Polegadas – Alta taxa de atualização para gameplay fluido

           Experimente a verdadeira imersão com o Monitor Gamer 27" Full HD com 165Hz e 1ms de tempo de resposta. Ideal para jogos competitivos e cenas rápidas, oferece fluidez, nitidez e qualidade de imagem excepcionais.

           Destaques do produto:
           - Taxa de atualização de 165Hz e tempo de resposta de 1ms
           - Tecnologia FreeSync para imagens sem cortes
           - Painel IPS com cores vibrantes e amplo ângulo de visão
           - Ajuste de altura e inclinação
           - Design sem bordas para uma experiência imersiva

           Especificações:
           - Resolução: Full HD (1920x1080)
           - Conectividade: HDMI, DisplayPort, Audio Out
           - Dimensões: 61.5cm x 36cm x 5.5cm (sem base)
           - Peso: 3.5kg

           Transforme sua gameplay com qualidade visual de alto nível!'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           'c3a81b2a-3b64-4f31-b55f-0e92d7a5fd3c',
           850,
           2500,
           'Cadeira Gamer Reclinável com Apoio para Pés',
           'UNAVAILABLE',
           75,
           'Cadeira Gamer Ergonômica – Conforto total para longas sessões de jogo

           Desenvolvida para unir conforto, postura e design, a Cadeira Gamer Reclinável com Apoio para Pés é ideal para quem joga ou trabalha por longas horas. Revestida com couro sintético, possui apoio lombar, encosto ajustável e base resistente.

           Destaques do produto:
           - Encosto reclinável até 180º com trava de posição
           - Apoio para pés retrátil e almofadas lombar e cervical
           - Rodízios em nylon de alta resistência
           - Base giratória em aço com pistão a gás classe 4
           - Suporta até 150kg

           Especificações:
           - Dimensões: 70cm x 130cm x 55cm
           - Peso: 2.5kg (estrutura) + 2500g total com estofamento

           Conforto e estilo para quem leva o jogo a sério!'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           'e9f1fc19-67f5-47fc-b70b-f5d7b788c4c9',
           400,
           1500,
           'Headset Gamer Surround 7.1',
           'AVAILABLE',
           180,
           'Headset Gamer Surround 7.1 – Imersão total em cada partida

           Com som surround 7.1 e microfone com cancelamento de ruído, o Headset Gamer proporciona áudio imersivo e comunicação clara, ideal para jogos cooperativos e competitivos.

           Destaques do produto:
           - Som Surround Virtual 7.1 para localização sonora precisa
           - Microfone com redução de ruído ajustável
           - Iluminação LED nos fones
           - Arco ajustável e almofadas com espuma memory foam
           - Compatível com PC, PS5, Xbox e dispositivos com P2/USB

           Especificações:
           - Conectividade: USB e P2
           - Frequência: 20Hz – 20kHz
           - Peso: 1.5kg

           Sinta cada detalhe do jogo com áudio profissional!'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           'fa58d271-9df2-4ec4-a3cc-c3f3f1de1084',
           180,
           900,
           'Mousepad Gamer XXL Antiderrapante',
           'UNAVAILABLE',
           500,
           'Mousepad Gamer XXL – Precisão e espaço total para movimentos livres

           Perfeito para setups gamers, o Mousepad XXL cobre teclado e mouse com superfície otimizada para precisão nos movimentos, base emborrachada antiderrapante e acabamento resistente.

           Destaques do produto:
           - Superfície speed para deslize suave e controle preciso
           - Costura reforçada nas bordas
           - Base de borracha antiderrapante
           - Resistente à água e fácil de limpar
           - Compatível com sensores ópticos e laser

           Especificações:
           - Dimensões: 90cm x 40cm x 0.4cm
           - Peso: 900g

           Liberdade de movimentos e estabilidade para suas jogadas!'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           'a1b2c3d4-e5f6-7890-1234-567890abcdef',
           150,
           120,
           'Mouse Gamer Profissional',
           'OUT_OF_STOCK',
           0,
           'Mouse Gamer de alta precisão com sensor óptico de 16000 DPI, iluminação RGB e 8 botões programáveis. Design ergonômico para longas sessões de jogo. Atualmente esgotado devido à alta demanda.'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           'b2c3d4e5-f6a7-8901-2345-67890abcdef1',
           1200,
           4500,
           'Monitor Curvo 32" 4K UHD',
           'DISCOUNTED',
           15,
           'Monitor gamer curvo de 32 polegadas com resolução 4K UHD, taxa de atualização de 144Hz e tempo de resposta 1ms. HDR10 e suporte a FreeSync. PROMOÇÃO POR TEMPO LIMITADO!'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           'c3d4e5f6-a7b8-9012-3456-7890abcdef12',
           350,
           320,
           'Headset Gamer Wireless 7.1',
           'OUT_OF_STOCK',
           0,
           'Headset gamer sem fio com surround virtual 7.1, microfone retrátil com cancelamento de ruído e autonomia de 30 horas. Conforto premium com almofadas em memory foam. Aguarde nova remessa!'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           'd4e5f6a7-b8c9-0123-4567-890abcdef123',
           399,
           80,
           'SSD NVMe 1TB Leitura 3500MB/s',
           'DISCOUNTED',
           42,
           'SSD NVMe de altíssima velocidade com 1TB de capacidade, leitura até 3500MB/s e escrita 3000MB/s. Tecnologia PCIe 3.0 x4 e controle térmico avançado. OFERSA ESPECIAL COM 25% DE DESCONTO!'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           'b1a2c3d4-e5f6-7890-ab12-3456cdef7890',
           299.90,
           2500,
           'Teclado Mecânico RGB Redragon Kumara K552',
           'AVAILABLE',
           150,
           'Teclado Mecânico Redragon Kumara K552 – Performance e iluminação para gamers exigentes

           Com switches mecânicos e iluminação RGB, o Kumara é ideal para quem busca precisão, durabilidade e estilo. Estrutura robusta em aço e keycaps de alta resistência.

           Destaques do produto:
           - Switches mecânicos Outemu Red
           - Iluminação RGB com efeitos personalizáveis
           - Estrutura metálica resistente
           - Layout ABNT2 com tecla "ç"
           - Conexão via USB com cabo trançado

           Especificações:
           - Peso: 1.2kg
           - Dimensões: 35cm x 12cm x 3.5cm

           Ideal para longas sessões de jogo ou digitação!'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           'dfe4bc23-0aa4-478b-9256-89d21cbffb3e',
           89.99,
           300,
           'Headset Gamer Multilaser PH073',
           'DISCOUNTED',
           320,
           'Headset Gamer Multilaser PH073 – Conforto e qualidade de som

           Equipado com som estéreo de alta definição, microfone ajustável e design confortável, é perfeito para jogadores casuais.

           Destaques do produto:
           - Drivers de 40mm com som limpo
           - Microfone com cancelamento de ruído
           - Arco ajustável e almofadas macias
           - Compatível com PC, consoles e mobile
           - Cabo com controle de volume

           Especificações:
           - Peso: 300g
           - Conexão: P2 3.5mm

           Experiência sonora imersiva com ótimo custo-benefício.'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           '45e7de90-2f65-4110-9343-03bd8a04d7a1',
           159.00,
           500,
           'Controle Xbox One Original Bluetooth',
           'OUT_OF_STOCK',
           0,
           'Controle Sem Fio Xbox One – Conectividade e precisão em todos os jogos

           O controle oficial da Microsoft conta com ergonomia aprimorada, gatilhos responsivos e suporte a Bluetooth para jogar no console ou no PC.

           Destaques do produto:
           - Conexão sem fio via Bluetooth
           - Design anatômico e confortável
           - Suporte a Windows 10/11
           - Entrada para fone de ouvido 3.5mm

           Especificações:
           - Peso: 500g
           - Compatibilidade: Xbox One, Xbox Series S/X, PC

           Jogue com mais liberdade e controle absoluto!'
       );

INSERT INTO PRODUCT (id, price, weight, name, status, amount, description)
VALUES (
           'e1a2f3c4-5678-4cba-9abc-df0123456789',
           119.90,
           150,
           'Cabo HDMI 4K Ultra HD 2.0 2 Metros',
           'AVAILABLE',
           1200,
           'Cabo HDMI 4K Ultra HD 2.0 – Qualidade de imagem e som para sua TV ou monitor

           Suporte a resoluções até 4K a 60Hz, com alta taxa de transferência e compatibilidade com HDR, áudio multicanal e ARC.

           Destaques do produto:
           - Suporte a 4K 60Hz e HDR
           - Compatível com HDMI 2.0/1.4
           - Conectores banhados a ouro
           - Revestimento em nylon trançado

           Especificações:
           - Comprimento: 2 metros
           - Peso: 150g

           Perfeito para games, filmes e produtividade em alta definição.'
       );
