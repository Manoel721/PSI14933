CREATE TABLE [TESTE].[dbo].[Simulacao](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[clienteId] [bigint] NOT NULL,
	[produto] [varchar](800) NULL,
	[tipoProduto] [varchar](200) NULL,
	[rentabilidade] [float] NOT NULL,
	[valorInvestido] [float] NULL,
	[valorFinal] [float] NULL,
	[prazoMeses] [int] NULL,
	[dataSimulacao] [varchar](50) NULL
) ON [PRIMARY]

insert into [TESTE].[dbo].[Simulacao]
([clienteId],[produto],[tipoProduto],[rentabilidade],[valorInvestido],[valorFinal],[prazoMeses],[dataSimulacao])
values
(1,'CDB Banco X','CDB',0.015,10000,11500,12,'2025-10-01'),
(2,'LCI Banco Y','LCI',0.012,20000,22800,18,'2025-10-02'),
(3,'Tesouro Selic','Tesouro',0.010,5000,5060,6,'2025-10-03'),
(4,'Fundo Multimercado Alpha','Fundo',0.020,15000,18000,24,'2025-10-04'),
(5,'CDB Banco Z','CDB',0.018,8000,9200,8,'2025-10-05'),
(6,'LCI Banco W','LCI',0.013,12000,13500,10,'2025-10-06'),
(7,'Tesouro IPCA+','Tesouro',0.025,25000,31000,36,'2025-10-07'),
(8,'Fundo Ações Beta','Fundo',0.030,5000,6500,12,'2025-10-08'),
(9,'CDB Banco Y','CDB',0.017,7000,8200,9,'2025-10-09'),
(10,'LCI Banco X','LCI',0.011,9000,10000,12,'2025-10-10'),
(11,'Tesouro Prefixado','Tesouro',0.022,30000,36500,24,'2025-10-11'),
(12,'Fundo Multimercado Gamma','Fundo',0.019,20000,23000,18,'2025-10-12'),
(13,'CDB Banco W','CDB',0.016,6000,6900,7,'2025-10-13'),
(14,'LCI Banco Z','LCI',0.014,11000,12500,11,'2025-10-14'),
(15,'Tesouro Selic','Tesouro',0.010,4000,4050,6,'2025-10-15'),
(16,'Fundo Ações Delta','Fundo',0.028,15000,19000,18,'2025-10-16'),
(17,'CDB Banco V','CDB',0.019,13000,15500,12,'2025-10-17'),
(18,'LCI Banco U','LCI',0.012,14000,16000,14,'2025-10-18'),
(19,'Tesouro IPCA+','Tesouro',0.023,22000,28000,30,'2025-10-19'),
(20,'Fundo Multimercado Omega','Fundo',0.021,17000,20000,20,'2025-10-20'),
(21,'CDB Banco X','CDB',0.015,10500,12000,12,'2025-10-21'),
(22,'LCI Banco Y','LCI',0.012,21000,24000,18,'2025-10-22'),
(23,'Tesouro Selic','Tesouro',0.010,5500,5600,6,'2025-10-23'),
(24,'Fundo Multimercado Alpha','Fundo',0.020,16000,19000,24,'2025-10-24'),
(25,'CDB Banco Z','CDB',0.018,8500,9800,8,'2025-10-25'),
(26,'LCI Banco W','LCI',0.013,12500,14000,10,'2025-10-26'),
(27,'Tesouro IPCA+','Tesouro',0.025,26000,32000,36,'2025-10-27'),
(28,'Fundo Ações Beta','Fundo',0.030,5200,6700,12,'2025-10-28'),
(29,'CDB Banco Y','CDB',0.017,7300,8500,9,'2025-10-29'),
(30,'LCI Banco X','LCI',0.011,9500,10600,12,'2025-10-30'),
(31,'Fundo Multimercado Omega','Fundo',0.021,20000,23500,20,'2025-10-31');

select * from [TESTE].[dbo].[Simulacao]
